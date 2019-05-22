package applications;

import circularorbits.Label;

public class SocialNetworkCircleTie {
  private Label name1;
  private Label name2;
  private double close;

  /**constructor.
   * 
   * @param name1 name1
   * @param name2 name2
   * @param close close level
   */
  public SocialNetworkCircleTie(Label name1, Label name2, double close) {
    this.name1 = name1;
    this.name2 = name2;
    this.close = close;
  }

  public Label getName1() {
    return name1;
  }

  public void setName1(Label name1) {
    this.name1 = name1;
  }

  public Label getName2() {
    return name2;
  }

  public void setName2(Label name2) {
    this.name2 = name2;
  }

  public double getClose() {
    return close;
  }

  public void setClose(double close) {
    this.close = close;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (((SocialNetworkCircleTie) o).name1.equals(this.name1)
        && ((SocialNetworkCircleTie) o).name2.equals(this.name2)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    // TODO Auto-generated method stub
    return super.hashCode();
  }
}
