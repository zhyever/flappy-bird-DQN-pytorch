package track;
import java.io.Serializable;

import circularOrbit.number;
public class Track implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//RI:
	//num >= 1
	//radius >= 0
	//AF:
	//轨道系统中的轨道类
	//含有轨道编号、半径的属性
	//防止REP暴露:
	//使用private属性且不存在set方法
	
	//轨道序号 //name
	private int num;
	//半径
	private number radius;
	
	
	/**
	 * 构造方法
	 * @param 传入num轨道序号
	 */
	public Track(int num) {
		this.num = num;
	}
	
	/**
	 * get方法
	 * @return 返回轨道编号
	 */
	public int getNum() {
		return num;
	}
	
	/**
	 * get方法
	 * @return 返回轨道半径
	 */
	public number getRadius() {
		return new number(this.radius.getNum());
	}
	

	/**
	 * 编号相等认为相等
	 */
	@Override
	public boolean equals(Object o) {
		if( ((Track) o).num == this.num) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 定义为num + 1
	 */
	@Override
	public int hashCode() {
		return this.num;
	}
}
