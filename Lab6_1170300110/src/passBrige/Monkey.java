package passBrige;

public class Monkey{

  private final int ID;
  private final String direction;
  private final int v;

  private int start;
  private int end;

  private int state; // state: 0 waiting, 1 on the ladder, 2 stop.
  /*
  	AF: 
  	monkey's ID. one monkey have its own ID, it won't be changed anymore.
  	monkey's direction. L->R or R->L. no other condition.
  	monkey's v. monkey's v to pass the ladder.

  	RI:
  	ID. different Monkey objects have different ID.
  	Direction. L->R or R->L
  	v. [1,MV]. integer.

  	REP expose:
  	String and Int is naturally immutable. there is no rep expose.

  	THREAD SAFE:
  	ID, direction and v are all final.
  	this is nearly a immutable class. 
  	each thread has a monkey. all the monkeys are not same.
   */


  public Monkey(int ID, String direction, int v) {
    this.ID = ID;
    this.direction = direction;
    this.v  = v;
    this.state = 0;
  }

  public int getID() {
    return ID;
  }

  public String getDirection() {
    return direction;
  }

  public int getV() {
    return v;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  @Override
  public String toString() {
    StringBuffer s = new StringBuffer();
    s.append("ID: " + String.valueOf(this.ID) + ", ");
    s.append(this.direction + ", ");
    if(this.state == 0) {
      s.append("waiting");
    }else if(this.state == 1) {
      s.append("on the ladder" + ", ");
    }else {
      s.append("finish");
    }
    return s.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof Monkey) {
      if(((Monkey) obj).getID() == this.ID) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.ID;
  }

  public int getStart() {
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getEnd() {
    return end;
  }

  public void setEnd(int end) {
    this.end = end;
  }

  public int getTime() {
    return end - start;
  }
}
