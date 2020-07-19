import pdb
import cv2
import sys
import os
# sys.path.append("game/")
import wrapped_flappy_bird as game
import random
import numpy as np
from collections import deque
import torch
from torch.autograd import Variable
import torch.nn as nn

import datetime

GAME = 'bird' # the name of the game being played for log files
ACTIONS = 2 # number of valid actions
GAMMA = 0.99 # decay rate of past observations 未来的衰减因子 BELLMAN方程中的系数
OBSERVE = 1000. # timesteps to observe before training 观察 前OBSERVE次都是在观察 只向经验池中添加经验积累
EXPLORE = 20000. # frames over which to anneal epsilon 观察完探索 (之后EXPLORE次都在探索 这个过程中有一个随机试探 并衰减
FINAL_EPSILON = 0.0001 # final value of epsilon 最后随即做一步的概率
INITIAL_EPSILON = 0.01 # starting value of epsilon 最开始随机做一步的概率
REPLAY_MEMORY = 50000 # number of previous transitions to remember 经验池大小
BATCH_SIZE = 32 # size of minibatch
FRAME_PER_ACTION = 1
UPDATE_TIME = 10000
width = 80
height = 80

def preprocess(observation):
    # 图片预处理 转灰度图、二值化
    observation = cv2.cvtColor(cv2.resize(observation, (80, 80)), cv2.COLOR_BGR2GRAY)
    ret, observation = cv2.threshold(observation,1,255,cv2.THRESH_BINARY)
    return np.reshape(observation, (1,80,80))

class DeepNetWork(nn.Module):
    def __init__(self,):
        super(DeepNetWork,self).__init__()
        self.conv1 = nn.Sequential(
            nn.Conv2d(in_channels=4, out_channels=32, kernel_size=8, stride=4, padding=2),
            nn.ReLU(inplace=True),
            nn.MaxPool2d(kernel_size=2)
        )
        self.conv2 = nn.Sequential(
            nn.Conv2d(in_channels=32, out_channels=64, kernel_size=4, stride=2, padding=1),
            nn.ReLU(inplace=True)
        )
        self.conv3 = nn.Sequential(
            nn.Conv2d(in_channels=64, out_channels=64, kernel_size=3, stride=1, padding=1),
            nn.ReLU(inplace=True)
        )
        self.fc1 = nn.Sequential(
            nn.Linear(1600,256),
            nn.ReLU()
        )
        self.out = nn.Linear(256,2)

    def forward(self, x):
        x = self.conv1(x)
        x = self.conv2(x)
        x = self.conv3(x)
        # 全连接之前展平
        x = x.view(x.size(0),-1)
        x = self.fc1(x)
        return self.out(x)

class BrainDQNMain(object):
    #保存模型
    def save(self):
        print("save model param")
        torch.save(self.Q_net.state_dict(), 'params3.pth')

    #加载模型
    def load(self):
        if os.path.exists("params3.pth"):
            print("load model param")
            self.Q_net.load_state_dict(torch.load('params3.pth'))
            self.Q_netT.load_state_dict(torch.load('params3.pth'))

    def __init__(self,actions):
        # init some parameters
        # 经验池 用一个队列得形式
        self.replayMemory = deque()
        # 从0time开始
        self.timeStep = 0
        self.epsilon = INITIAL_EPSILON
        self.actions = actions
        self.Q_net=DeepNetWork().cuda()
        self.Q_netT=DeepNetWork().cuda()
        # 尝试加载模型
        self.load()
        # 使用均方误差
        self.loss_func=nn.MSELoss()
        # 学习率 1e-6
        LR=1e-5
        # Adam
        self.optimizer = torch.optim.Adam(self.Q_net.parameters(), lr=LR)

    def train(self):
        # Step 1: obtain random minibatch from replay memory 从经验池中采样 一次BATCH_SIZE个
        minibatch = random.sample(self.replayMemory, BATCH_SIZE)
        # 拆开 状态、行动、奖励、下一个状态
        state_batch = [data[0] for data in minibatch]
        action_batch = [data[1] for data in minibatch]
        reward_batch = [data[2] for data in minibatch]
        nextState_batch = [data[3] for data in minibatch]


        # Step 2: calculate y
        # 初始化y列表 每一个batch中的每个data都对应一个y值
        y_batch = np.zeros([BATCH_SIZE,1])
        # 找到下一个状态
        nextState_batch=np.array(nextState_batch)
        # 封装成tensor
        nextState_batch=torch.Tensor(nextState_batch)

        # 动作、奖励表 shape = (m, n) m是当前处在的状态 n是不同动作的奖励
        action_batch=np.array(action_batch)
        # 选取奖励最大的那个作为动作
        index=action_batch.argmax(axis=1)
        # print("action "+str(index))
        # 转成列
        index=np.reshape(index,[BATCH_SIZE,1])
        # 分装成tensor
        action_batch_tensor=torch.LongTensor(index)

        # 将nextState_batch 当前状态的下一状态 喂给我们的神经网络 去计算我们想要估计的Q(s', a') 因为是下一状态所以是'
        QValue_batch = self.Q_netT(nextState_batch.cuda())
        # 将结果转换成numpy形式
        QValue_batch= QValue_batch.cpu().detach().numpy()

        # 对于每个BATCH：
        for i in range(0, BATCH_SIZE):
            # 终止状态标记在经验池的index = 4那个位置
            terminal = minibatch[i][4]
            # 如果这个当前状态是终止状态了，那么我这个y就等于那个对应的reward
            if terminal:
                y_batch[i][0]=reward_batch[i]
            # 否则：
            else:
                # 这个y等于当前的这一个r + 衰减因子*未来的Q最大值(bellman 方程) 其实就是Q(s, a)
                # 而这个未来的Q最大值，是我们的Q_NETT预测出的结果 (上边写了)，注意要横向取最大值，即选取maxQ(s', a', theta_i-1)
                y_batch[i][0]=reward_batch[i] + GAMMA * np.max(QValue_batch[i])


        # 似乎没意义 保证y的形状
        y_batch=np.array(y_batch)
        y_batch=np.reshape(y_batch,[BATCH_SIZE,1])


        # 这步很慢
        state_batch_tensor=torch.from_numpy(np.array(state_batch))
        state_batch_tensor= torch.Tensor.float(state_batch_tensor)
        y_batch_tensor=torch.Tensor(y_batch)

        # 我们pred y就是Q_NET的结果 gather的目的是选取采用的那个a对应的Q 细品Q(s, a!!!!) 要有那个a才行
        # 同时这里也体现了迭代的感觉 细品 下边训练了这个网络 得到参数t， 然后用这个网络取算上边的那个y目标
        # 然后又用那个目标去训练网络 得到新的参数t+1


        y_predict=self.Q_net(state_batch_tensor.cuda()).cpu().gather(1,action_batch_tensor)
        loss=self.loss_func(y_predict,y_batch_tensor)
        print("loss is "+str(loss))

        # 常规操作
        self.optimizer.zero_grad()
        loss.backward()
        self.optimizer.step()


        # 到时间了保存一下XD
        if self.timeStep % UPDATE_TIME == 0:
            self.Q_netT.load_state_dict(self.Q_net.state_dict())
            self.save()

    def setPerception(self,nextObservation,action,reward,terminal):
        # print(nextObservation.shape)
        # newState = np.append(nextObservation,self.currentState[:,:,1:],axis = 2)
        # 最新的图像在最前 舍弃最老的图像
        # newState = np.append(nextObservation, self.currentState[:, :, :3], axis=2)
        newState = np.append(self.currentState[1:,:,:],nextObservation,axis = 0)

        self.replayMemory.append((self.currentState,action,reward,newState,terminal))
        if len(self.replayMemory) > REPLAY_MEMORY:
            self.replayMemory.popleft()
        if self.timeStep > OBSERVE: # Train the network
            self.train()

        # print info
        state = ""
        if self.timeStep <= OBSERVE:
            state = "observe"
        elif self.timeStep > OBSERVE and self.timeStep <= OBSERVE + EXPLORE:
            state = "explore"
        else:
            state = "train"
        print ("TIMESTEP", self.timeStep, "/ STATE", state, "/ EPSILON", self.epsilon)
        self.currentState = newState
        self.timeStep += 1

    def getAction(self):
        ## fix currentState --> currentState_copy
        currentState_copy = torch.Tensor([self.currentState])
        QValue = self.Q_net(currentState_copy.cuda())[0]
        action = np.zeros(self.actions)
        if self.timeStep % FRAME_PER_ACTION == 0:
            # 非游戏第一帧：
            if random.random() <= self.epsilon:
                # 随机探索 对应行动位置置1
                action_index = random.randrange(self.actions)
                print("choose random action " + str(action_index))
                action[action_index] = 1
            else:
                # 采取最大奖励决策 将对应行动位置置1
                action_index = np.argmax(QValue.cpu().detach().numpy())
                print("choose qnet value action " + str(action_index))
                action[action_index] = 1
        else:
            # 游戏第一帧：
            action[0] = 1  # do nothing

        # change episilon
        if self.epsilon > FINAL_EPSILON and self.timeStep > OBSERVE:
            self.epsilon -= (INITIAL_EPSILON - FINAL_EPSILON) / EXPLORE
        return action

    def setInitState(self, observation):
        self.currentState = np.stack((observation, observation, observation, observation),axis=0)
        print(self.currentState.shape)

if __name__ == '__main__': 
    # Step 1: init BrainDQN
    actions = 2
    brain = BrainDQNMain(actions) # Step 2: init Flappy Bird Game
    flappyBird = game.GameState() # Step 3: play game
    # Step 3.1: obtain init state
    action0 = np.array([1,0]) # do nothing
    observation0, reward0, terminal = flappyBird.frame_step(action0)
    observation0 = cv2.cvtColor(cv2.resize(observation0, (80, 80)), cv2.COLOR_BGR2GRAY)
    ret, observation0 = cv2.threshold(observation0,1,255,cv2.THRESH_BINARY)
    brain.setInitState(observation0)
    print(brain.currentState.shape) # Step 3.2: run the game

    while 1!= 0:
        action = brain.getAction()
        nextObservation,reward,terminal = flappyBird.frame_step(action)
        nextObservation = preprocess(nextObservation)
        #print(nextObservation.shape)
        brain.setPerception(nextObservation,action,reward,terminal)
