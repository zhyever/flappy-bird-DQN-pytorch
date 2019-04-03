package P3;

public class Position {
	public int x;
	public int y;
	// 横纵坐标 方便改变 设置为public
	
	// Abstraction function:
	// TODO xy 表示在空间上的坐标位置
	// Representation invariant:
	// TODO xy 要大于等于0
	// Safety from rep exposure:
	// TODO 是muttable变量 可变
	
	/**
	 * 构造器
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
//	public Position() {
//		this.x = -1;
//		this.y = -1;
//	}
    
	//重写equals xy 相等
	@Override 
	public boolean equals(Object p) {
		if(((Position) p).x == x && ((Position) p).y == y){
			return true;
		}else {
			return false;
		}
	}
}
