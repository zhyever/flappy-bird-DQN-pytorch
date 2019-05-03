package physicalObject;
import java.io.Serializable;

import circularOrbit.number;
public abstract class PhysicalObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int num;
	private number r;
	private double sitha;
	
	//RI:
	//num >= 1
	//sitha [0, 360)
	//AF:
	//轨道系统中的物体类
	//包含编号、半径、角度的属性
	//防止REP暴露:
	//使用private属性
	//set方法只留给其子类初始化时使用
	
	
	public number getR() {
		return this.r;
	}
	
	public void setSitha(double sitha) {
		this.sitha = sitha;
	}
	
	public double getSitha() {
		return this.sitha;
	}
	
	@Override
	abstract public boolean equals(Object o);

	public int getNum() {
		return num;
	}

	public void setNum(int i) {
		this.num = i;
	}

}
