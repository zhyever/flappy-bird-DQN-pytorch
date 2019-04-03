package P3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyChessAndGoGame {
	private Game game;

	public void runGame() {
		///easy for test
		String gameType1 = "Go";
		String gameType2 = "Chess";
		
		String playerName1 = "A";
		String playerName2 = "B";
		///
		int x;
		int y;
		int x1;
		int y1;
		String[] str;
		List<Player> players = new ArrayList<Player>();

		System.out.println("A small game!");
		System.out.println("Input the type you want to play:(Chess or Go)");
		Scanner in = new Scanner(System.in);
		while (true) {

			String type = in.nextLine();
			//String type = gameType2;
			if (type.equals("Chess")) {
				game = new Game("Chess");
				break;
			} else if (type.equals("Go")) {
				game = new Game("Go");
				break;
			} else {
				System.out.println("Please input according to the prompt. Try again!");
			}
		}

		System.out.println("Please input the first player's name:");
		String name1 = in.nextLine();
		//String name1 = playerName1;
		System.out.println("Please input the second player's name:");
		String name2 = in.nextLine();
		//String name2 = playerName2;

		game.init(name1, name2);
		players.add(game.getPlayer(name1));
		players.add(game.getPlayer(name2));

		boolean exitFlag = false;
		int count = 0;
		while (true) {
			game.getBoard().showBoard();
			Player p = players.get(count % 2);
			System.out.printf("%s's round. Please input the num to play!\n", p.getName());
			printMenu();
			String choose = in.nextLine();

			switch (choose) {
			case "1":
				//放置棋子 我觉着只有围棋才有这个操作
				System.out.println("Input the position you want to put your piece to:(x y) input (-1 -1) to reinput!");
				while(true) {
					str = in.nextLine().split(" ");
					x = Integer.valueOf(str[0]);
					y = Integer.valueOf(str[1]);
					if(x == -1) {
						break;
					}
					//如果输入坐标超越了棋盘
					if(x > 18 || y > 18) {
						System.out.println("Get out of the board! Please input again!");
						continue;
					}
					//如果这个位置已经有了棋子
					if(game.ifHavePiece(x, y)) {
						System.out.println("The position has piece! Please input again!");
						continue;
					}
					break;
				}
				if(x == -1) {
					break;
				}else {
					count++;
					game.putPiece(p, x, y);
					break;
					
				}
				
			case "2":
				//移动棋子
				System.out.println("Input the position of the piece you want to move:(x y) input -1 -1 to reinput!");
				while(true) {
					str = in.nextLine().split(" ");
					x = Integer.valueOf(str[0]);
					y = Integer.valueOf(str[1]);
					if(x == -1) {
						break;
					}
					//输入坐标超过棋盘
					if(x > 8 || y > 8) {
						System.out.println("Get out of the board! Please input again!");
						continue;
					}
					//如果不存在要移动的棋子
					if(!game.getBoard().isContain(x, y)) {
						System.out.println("There is no piece at that position! Please input agian!");
						continue;
					}
					//如果要移动的棋子不是你的
					if(!p.ifContain(game.getBoard().getPiece(x, y))) {
						System.out.println("The piece doesn't belong to you! Please input agian!");
						continue;
					}
					break;
				}
				if(x == -1) {
					break;
				}
				System.out.println("Input the position you want to put at:(x y) input -1 -1 to reinput!");
				while(true) {
					str = in.nextLine().split(" ");
					x1 = Integer.valueOf(str[0]);
					y1 = Integer.valueOf(str[1]);
					if(x1 == -1) {
						break;
					}
					//越界
					if(x > 8 || y > 8) {
						System.out.println("Get out of the board! Please input again!");
						continue;
					}
					//那个位置已经有棋子了
					if(game.getBoard().getPiece(x1, y1) != null) {
						System.out.println("There is already a piece at that position! Please input again!");
						continue;
					}
					//MOVE不符合国际象棋规则
					if(!game.checkMove(game.getBoard().getPiece(x, y), x, y, x1, y1)) {
						System.out.println("Break the rules! Please input again!");
						continue;
					}
					break;
				}
				if(x1 == -1) {
					break;
				}else {
					count++;
					game.movePiece(p, x, y, x1, y1);
					break;
				}
				
				
				
			case "3":
				//吃子
				System.out.println("Input the position of the opponent's piece you want to remove:(x y) input -1 -1 to reinput!");
				while(true) {
					str = in.nextLine().split(" ");
					x = Integer.valueOf(str[0]);
					y = Integer.valueOf(str[1]);
					if(x == -1) {
						break;
					}
					//越界
					if(x > 18 || y > 18) {
						System.out.println("Get out of the board! Please input again!");
						continue;
					}
					//那个位置根本没有棋子
					if(game.getBoard().getPiece(x, y) == null) {
						System.out.println("There is no piece at that position! Please input again!");
						continue;
					}
					//如果那个棋子不是对方的棋子
					if(!players.get((count+1)%2).ifContain(game.getBoard().getPiece(x, y))) {
						System.out.println("The piece is yours! Please input agian!");
						continue;
					}
					break;
				}
				if(x == -1) {
					break;
				}else {
					game.removePiece(p, x, y, players.get((count+1)%2));
					count++;
					break;
				}
				
			case "4":	
				//吃子
				System.out.println("Input the position of your piece:(x y) input -1 -1 to reinput!");
				while(true) {
					str = in.nextLine().split(" ");
					x = Integer.valueOf(str[0]);
					y = Integer.valueOf(str[1]);
					if(x == -1) {
						break;
					}
					//越界
					if(x > 8 || y > 8) {
						System.out.println("Get out of the board! Please input again!");
						continue;
					}
					//不存在要操作的棋子
					if(!game.getBoard().isContain(x, y)) {
						System.out.println("There is no piece at that position! Please input agian!");
						continue;
					}
					//棋子不是你的
					if(!p.ifContain(game.getBoard().getPiece(x, y))) {
						System.out.println("The piece does't belong to you! Please input agian!");
						continue;
					}
					break;
				}
				if(x == -1) {
					break;
				}
				System.out.println("Input the position of the piece you want to eat:(x y) input -1 -1 to reinput!");
				while(true) {
					str = in.nextLine().split(" ");
					x1 = Integer.valueOf(str[0]);
					y1 = Integer.valueOf(str[1]);
					//越界
					if(x1 == -1) {
						break;
					}
					if(x > 8 || y > 8) {
						System.out.println("Get out of the board! Please input again!");
						continue;
					}
					//那个点没有棋子
					if(game.getBoard().getPiece(x1, y1) == null) {
						System.out.println("There is no piece at that position! Please input again!");
						continue;
					}
					//那个棋子不是对方的
					if(p.ifContain(game.getBoard().getPiece(x1, y1))) {
						System.out.println("The piece is yours! Please input agian!");
						continue;
					}
					//不符合国际象棋的规则
					if(!game.checkEat(game.getBoard().getPiece(x, y), x, y, x1, y1)) {
						System.out.println("Break rules! Please input agian!");
						continue;
					}
					break;
				}
				
				if(x1 == -1) {
					break;
				}else {
					game.eatPiece(p, x, y, x1, y1, players.get((count+1)%2));
					count++;
					break;
				}
				
				
			case "5":	
				//查询某点的情况
				System.out.println("Input the position of the piece you want to query:(x y)");
				while(true) {
					str = in.nextLine().split(" ");
					x = Integer.valueOf(str[0]);
					y = Integer.valueOf(str[1]);
					//越界
					if(game.getGameType().equals("Chess")) {
						if(x > 8|| y > 8) {
							System.out.println("Get out of the board! Please input again!");
							continue;
						}
					}
					
					if(game.getGameType().equals("Go")) {
						if(x > 18 || y > 18) {
							System.out.println("Get out of the board! Please input again!");
							continue;
						}
					}
					break;
				}
				Piece piece = game.query(p, x, y);
				if(piece != null) {
					System.out.println("The name of the piece is: " + piece.getName());
					if(p.ifContain(piece)) {
						System.out.println("The piece belongs to " + p.getName());
					}else {
						System.out.println("The piece belongs to " + players.get((count+1)%2).getName());
					}
				}else {
					System.out.println("There is no piece!");
				}
			//	count++;
				break;
				
			case "6":
				//查询棋子数量
				int numPiece = game.getNum(players.get(0));
				if(numPiece == 0) {
					System.out.println(players.get(0).getName() + " has no piece!");
				}else if(numPiece == 1) {
					System.out.println(players.get(0).getName() + " has 1 piece!");
				}else {
					System.out.println(players.get(0).getName() + " has " + Integer.toString(numPiece) + " pieces!");
				}
				
				numPiece = game.getOtherNum(players.get(1));
				if(numPiece == 0) {
					System.out.println(players.get(1).getName() + " has no piece!");
				}else if(numPiece == 1) {
					System.out.println(players.get(1).getName() + " has 1 piece!");
				}else {
					System.out.println(players.get(1).getName() + " has " + Integer.toString(numPiece) + " pieces!");
				}
			//	count++;
				break;
				
			case "7":
				//跳过本回合
				game.skip(p);
				count++;
				break;
				
			case "end":	
				//结束游戏 打印history
				System.out.println("Here is the play histroy:");
				System.out.println(players.get(0).getName()+":");
				System.out.printf(players.get(0).getHistory());
				System.out.println(players.get(1).getName()+":");
				System.out.printf(players.get(1).getHistory());
				exitFlag = true;
				break;
			}

			if(exitFlag) break;

		}

	}

	//打印选项菜单
	private void printMenu() {
		System.out.println("1.\tput the piece at the position(Just for Go)");
		// 将尚未在棋盘上的一颗棋子放在棋盘上的指定位置 go
		System.out.println("2.\tmove the piece to the position(Just for Chess)");
		// 移动棋盘上的某个位置的棋子至新的位置 chess
		System.out.println("3.\tremove the piece(Just for Go)");
		// 提子（移除对手棋子）go
		System.out.println("4.\teat the piece(Just for Chess)");
		// 吃子（使用己方棋子吃掉对手棋子）chess
		System.out.println("5.\tquery state of the position");
		// 查询某个位置的占用情况
		System.out.println("6.\tcount the nums of pieces");
		// 计算两个玩家分别在棋盘上的棋子总数
		System.out.println("7.\tskip over");
		// 跳过
		System.out.println("end.\tgame over");
		// 结束操作
	}
	

	public static void main(String[] args) {
		new MyChessAndGoGame().runGame();
	}
}
