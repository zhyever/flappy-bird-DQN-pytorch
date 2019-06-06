package passBrige;

public class Rung {

  public int state;
  public Monkey monkey;

  /*
   * AF:
   * state: if there is a monkey on the rung, it will be 1. if there is no monkey on the rung, it will be 0.
   * monkey: the monkey on the rung.
   * 
   * RI:
   * state = 0 or 1.
   * 
   * REP:
   * mutable.
   * 
   */

  public Rung(int state) {
    this.state = state;
    this.monkey = null;
  }

  @Override
  public String toString() {
    StringBuffer s = new StringBuffer();
    if(this.monkey != null) {
      s.append(String.valueOf(this.state) + ", " + String.valueOf(this.monkey.getID()));
    }else {
      s.append(String.valueOf(this.state) + ", no monkey");
    }

    return s.toString();
  }
}
