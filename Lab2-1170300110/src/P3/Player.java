package P3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {
	private final String name;
	private StringBuffer history = new StringBuffer();
	private final List<Piece> pieces = new ArrayList<Piece>();
	
	// Abstraction function:
	// TODO 棋手 存有名字、拥有的棋子、历史纪录
	// Representation invariant:
	// TODO 棋子列表中的棋子需要合法
	// Safety from rep exposure:
	// TODO private 使用副本
	
	/**
	 * 构造器
	 * @param name
	 */
	public Player(String name) {
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
	 * @return 返回历史
	 */
	public String getHistory() {
		return this.history.toString();
	}
	
	/**
	 * get方法
	 * @return 返回棋手拥有的棋子列表
	 */
	public List<Piece> getPieces(){
		///保护一下
		List<Piece> res = new ArrayList<Piece>();
		for(Piece i : this.pieces) {
			res.add(i);
		}
		return res;
	}
	
	/**
	 * 把这个棋手的位于x y处的棋子移动到x1 y1
	 * @param x
	 * @param y
	 * @param x1
	 * @param y1
	 */
	public void moveTo(int x, int y, int x1, int y1) {
		Iterator<Piece> it = pieces.iterator();
		while(it.hasNext()) {
		  Piece p = it.next();
		  if(p.getPosition().equals(new Position(x, y))){
			  p.setPosition(x1, y1);
		  }
		}
	}
	
	/**
	 * 把棋手位于x y处的棋子删除
	 * @param x
	 * @param y
	 */
	public void removePiece(int x, int y) {
		Iterator<Piece> it = pieces.iterator();
		while(it.hasNext()) {
		  Piece p = it.next();
		  if(p.getPosition().equals(new Position(x, y))){
			  it.remove();
		  }
		}
	}
	
	/**
	 * 判断棋手是否有piece棋子
	 * @param piece
	 * @return 如果有返回ture
	 */
	public boolean ifContain(Piece piece) {
		boolean flag = false;
		for(Piece p : pieces) {
			if(p.getName().equals(piece.getName())&& p.getPosition().equals(piece.getPosition()) ) {
				flag = true;
			}
		}
		
		if(flag) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * get方法
	 * @return 返回棋手拥有的棋子数量
	 */
	public int getNums() {
		return pieces.size();
	}
	/**
	 * 	///向这名棋手添加棋子
	 * @param p
	 * @return 如果着名棋手没有p棋子 添加成功返回true 如果有返回false
	 */

	public boolean addPieces(Piece p) {
		if(pieces.contains(p)) {
			return false;
		}else {
			pieces.add(p);
			return true;
		}
	}
	/**
	 * 向这名棋手 添加历史
	 * @param step
	 */
	public void putStep(String step) {
		history.append(step);
		history.append("\n");
	}
}
