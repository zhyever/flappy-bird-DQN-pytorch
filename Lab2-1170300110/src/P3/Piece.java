package P3;

public class Piece {
	//private int state; // 0 未落下 1 落下 2 被吃
	private Position p; // 坐标信息
	private final String name; // 棋子名字
	
	// Abstraction function:
	// TODO 棋子 存有坐标信息、棋子名字
	// Representation invariant:
	// TODO 坐标信息需要合法
	// Safety from rep exposure:
	// TODO private 使用副本
	/**
	 * 两个构造器
	 * @param name
	 */
//	public Piece(String name) {
//		this.p = new Position();
//		this.name = name;
//		//this.state = 0;
//	}
//	
	
	public Piece(String name, int x, int y) {
		this.p = new Position(x, y);
		//this.state = state;
		this.name = name;
	}
	

	/**
	 * get方法
	 * @return 返回名字
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * get方法
	 * @return 返回位置
	 */
	public Position getPosition() {
		return new Position(p.x, p.y);
	}
	
//
//	/**
//	 * 对棋子命名
//	 * @param name
//	 */
//	public void setName(String name) {
//		this.name = name;
//	}
//	
	/**
	 * 改变棋子位置
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		this.p.x = x;
		this.p.y = y;
	}
	

}
