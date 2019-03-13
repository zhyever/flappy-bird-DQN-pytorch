package P3;
import java.util.ArrayList;

import P3.Person;

public class FriendshipGraph {
	
	static int max = 10000;
	// 定义最大值
	static int inf = 9999; 
	// 定义一个无穷大
	static int nums = 0;
	// 总人数
	static ArrayList<Person> vertex = new ArrayList<Person>();
	static ArrayList<String> people = new ArrayList<String>();
	// Person的一个列表
	static int[][] distance = new int[max][max];
	// 距离二维数组
	
	/**
	 * 增加向量//增加一个人
	 * @param name 人名
	 * @return 是否成功加入
	 */
	public boolean addVertex(Person name) {

		
		if(people.contains(name.name)) {
			System.out.println("Name must be unique!!!");
			System.exit(0);
		}
		
		///增加这么个person
		people.add(name.name);
		///将这个人加入到vertex列表中
		vertex.add(name);
		///数量加一
		nums++;
		
		/// 初始化这个距离二维数组 
		for(int k = 0; k < nums; k++) {
			for(int j = 0; j < nums; j++) {
				distance[k][j] = inf;
			}
		}
		
		return true;
	}
	/**
	 * 两个人之间建立联系 注意单向性
	 * @param name1
	 * @param name2
	 * @return 是否增加成功
	 */
	public boolean addEdge(Person name1, Person name2) {
		
		
		int index1 = 0;
		int index2 = 0;
		
		///寻找到这两个人的在vertex里的下标
		for(int i = 0; i < nums; i++) {
			if(vertex.get(i).name.equals(name1.name)) {
				index1 = i;
			}
		}
		
		for(int i = 0; i < nums; i++) {
			if(vertex.get(i).name.equals(name2.name)) {
				index2 = i;
			}
		}
		
		///在图中 将他们之间的距离设定为1
		///注意只有一半
		distance[index1][index2] = 1;
		
		return true;
	}
	
	/**
	 * 得到两人之间距离
	 * @param name1
	 * @param name2
	 * @return 有距离返回距离 如果没有联系 返回-1 如果是同一个人返回0
	 */
	public int getDistance(Person name1, Person name2){
		///dijstla 算法 三重循环~
		for(int k = 0; k < nums; k++) {
			for(int i = 0; i < nums; i++) {
				for(int j = 0; j < nums; j++) {
					if(distance[i][k] + distance[k][j] < distance[i][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}
		
		///寻找两个名字的下标位置
		int index1 = 0;
		int index2 = 0;
		
		for(int i = 0; i < nums; i++) {
			if(vertex.get(i).name.equals(name1.name)) {
				index1 = i;
			}
		}
		
		for(int i = 0; i < nums; i++) {
			if(vertex.get(i).name.equals(name2.name)) {
				index2 = i;
			}
		}
		
		///如果是特殊情况 是同一个人 那么返回距离0
		if(index1 == index2) {
			return 0;
		}
		
		///如果是无穷大 即两人之间没有距离 返回-1
		if(distance[index1][index2] == inf) {
			return -1;
		}
		
		return distance[index1][index2];
	}
	
	public static void main(String[] args) {
		
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		//Person ross = new Person("Rachel");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		
		graph.addEdge(ross, rachel);
		
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross)); 
		//should print 1
		System.out.println(graph.getDistance(rachel, ben)); 
		//should print 2
		System.out.println(graph.getDistance(rachel, rachel)); 
		//should print 0
		System.out.println(graph.getDistance(rachel, kramer)); 
		//should print -1
	}
}
