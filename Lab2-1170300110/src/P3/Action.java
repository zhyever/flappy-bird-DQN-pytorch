package P3;

public class Action {
	private Board gameBoard;
	private Player player1;
	private Player player2;
	
	public Action(Player player1, Player player2,Board gameBoard) {
		this.gameBoard = gameBoard;
		this.player1 = player1;
		this.player2 = player2;
		
	}
	
	// Abstraction function:
	// TODO 操作 存有游戏的棋盘和两个棋手 需要将对棋盘的操作和对棋手的操作结合起来
	// Representation invariant:
	// TODO 棋手、棋盘信息需要合法
	// Safety from rep exposure:
	// TODO private 使用副本

	
	/**
	 * 放置棋子for go
	 * @param player
	 * @param piece
	 * @param x
	 * @param y
	 */
	public void putPiece(Player player,Piece piece, int x, int y) {
		player.addPieces(piece);
		piece.setPosition(x, y);
		gameBoard.putPiece(x, y, piece);
	}
	
	/**
	 * 移动棋子for chess
	 * @param player
	 * @param x
	 * @param y
	 * @param x1
	 * @param y1
	 */
	public void movePiece(Player player, int x, int y, int x1, int y1) {
		player.moveTo(x, y, x1, y1);
		Piece temp = gameBoard.getPiece(x, y);
		gameBoard.putPiece(x, y, null);
		gameBoard.putPiece(x1, y1, temp);
	}
	
	/**
	 * 吃子for go
	 * @param player
	 * @param x
	 * @param y
	 */
	public void removePiece(Player player,int x, int y) {
		player.removePiece(x, y);
		gameBoard.putPiece(x, y, null);
	}
	
	/**
	 * 吃子for chess
	 * @param player
	 * @param x
	 * @param y
	 * @param x1
	 * @param y1
	 * @param opponent
	 */
	public void eatPiece(Player player, int x, int y, int x1, int y1, Player opponent) {
		Piece temp = gameBoard.getPiece(x, y);
		gameBoard.putPiece(x, y, null);
		gameBoard.putPiece(x1, y1, temp);
		player.moveTo(x, y, x1, y1);
		temp.setPosition(x1, y1);
		opponent.removePiece(x1, y1);
		player.moveTo(x, y, x1, y1);
	}
}
