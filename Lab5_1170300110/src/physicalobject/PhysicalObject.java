package physicalobject;

import java.io.Serializable;

import circularorbits.Number;

public abstract class PhysicalObject implements Serializable {
  /**Serializable.
   * 
   */
  private static final long serialVersionUID = 1L;
  private int num;
  private Number ri;
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

  @Override
  public abstract boolean equals(Object o);

  public Number getR() {
    return this.ri;
  }

  public void setR(Number ri) {
    this.ri = ri;
  }

  @Override
  public int hashCode() {
    // TODO Auto-generated method stub
    return super.hashCode();
  }

  public void setSitha(double sitha) {
    this.sitha = sitha;
  }

  public double getSitha() {
    return this.sitha;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int i) {
    this.num = i;
  }

}
