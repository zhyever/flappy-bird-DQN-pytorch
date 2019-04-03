package P3;

public class Board {
	private String boardType; // 游戏类型
	private int size; // 棋盘的尺寸
	private Piece boardPieces[][]; //放在棋子放在这个二维数组中
	
	// Abstraction function:
	// TODO 棋盘 存有棋盘的尺寸和一个二维数组来表示棋盘中的内容
	// Representation invariant:
	// TODO 二维数组中的棋子需要合法 size要符合实际情况
	// Safety from rep exposure:
	// TODO private 使用副本
	
	/**
	 * 构造器 注意初始化象棋！
	 * @param type
	 */
	public Board(String type) {
		if(type.equals("Go")) {
			this.boardType = "Go";
			this.size = 19;
			this.boardPieces = new Piece[this.size][this.size];
		}else if(type.equals("Chess")) {
			this.boardType = "Chess";
			this.size = 8;
			this.boardPieces = new Piece[this.size][this.size];
			
		    
			boardPieces[0][1] = new Piece("Pawn1",0,1);
			boardPieces[1][1] = new Piece("Pawn2",1,1);
			boardPieces[2][1] = new Piece("Pawn3",2,1);
			boardPieces[3][1] = new Piece("Pawn4",3,1);
			boardPieces[4][1] = new Piece("Pawn5",4,1);
			boardPieces[5][1] = new Piece("Pawn6",5,1);
			boardPieces[6][1] = new Piece("Pawn7",6,1);
			boardPieces[7][1] = new Piece("Pawn8",7,1);
			
			///马
			boardPieces[1][0] = new Piece("Knight1",1,0);
			boardPieces[6][0] = new Piece("Knight2",6,0);
			
			///象
			boardPieces[2][0] = new Piece("Bishop1",2,0);
			boardPieces[5][0] = new Piece("Bishop2",5,0);
			
			///车
			boardPieces[0][0] = new Piece("Rook1",0,0);
			boardPieces[7][0] = new Piece("Rook2",7,0);
			
			///后
			///王
			boardPieces[3][0] =new Piece("Queen",3,0);
			boardPieces[4][0] =new Piece("King",4,0);
			
			
			boardPieces[0][6] = new Piece("Pawn1_",0,6);
			boardPieces[1][6] = new Piece("Pawn2_",1,6);
			boardPieces[2][6] = new Piece("Pawn3_",2,6);
			boardPieces[3][6] = new Piece("Pawn4_",3,6);
			boardPieces[4][6] = new Piece("Pawn5_",4,6);
			boardPieces[5][6] = new Piece("Pawn6_",5,6);
			boardPieces[6][6] = new Piece("Pawn7_",6,6);
			boardPieces[7][6] = new Piece("Pawn8_",7,6);
			
			///马
			boardPieces[1][7] = new Piece("Knigh1_",1,7);
			boardPieces[6][7] = new Piece("Knigh2_",6,7);
			
			///象
			boardPieces[2][7] = new Piece("Bisho1_",2,7);
			boardPieces[5][7] = new Piece("Bisho2_",5,7);
			
			///车
			boardPieces[0][7] = new Piece("Rook1_",0,7);
			boardPieces[7][7] = new Piece("Rook2_",7,7);
			
			///后
			///王
			boardPieces[3][7] =new Piece("Queen_",3,7);
			boardPieces[4][7] =new Piece("King_",4,7);
			
		}else {
			//do nothing
		}
	}
	
	/**
	 * 打印棋盘
	 */
	public void showBoard() {
		for(int i = size-1; i >= 0; i--) {
			System.out.printf(Integer.toString(i) + "\t");
			for(int j = 0; j < size; j++) {
				if(boardPieces[j][i] == null) {
					System.out.printf("O\t");
				}else {
					System.out.printf(boardPieces[j][i].getName() + "\t");
				}
			}
			System.out.printf("\n");
		}
		System.out.printf("\t");
		for(int k = 0; k <= size - 1; k++) {
			System.out.printf(Integer.toString(k) + "\t");
		}
		System.out.printf("\n");
	}
	
	/**
	 * 取某点棋子信息
	 * @param x
	 * @param y
	 * @return 该点棋子，如果没有返回null
	 */
	public Piece getPiece(int x, int y) {
		///没有拿掉
		if(boardPieces[x][y]!=null) {
			return boardPieces[x][y];
		}else {
			return null;
		}	
	}
	
	/**
	 * 判断某点是否有棋子
	 * @param x
	 * @param y
	 * @return 如果有返回true 反之false
	 */
	public boolean isContain(int x, int y) {
		if(boardPieces[x][y] == null) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * 落子 将PIECE落到xy处
	 * @param x
	 * @param y
	 * @param piece
	 */
	public void putPiece(int x, int y, Piece piece) {
		boardPieces[x][y] = piece;
		if(piece != null) {
			piece.setPosition(x, y);
		}
		
	}
	
}
