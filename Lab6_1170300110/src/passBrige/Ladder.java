package passBrige;

import java.util.ArrayList;
import java.util.List;

public class Ladder {

  public int length;
  public List<Rung> rungs;

  public List<Monkey> monkey;

  /*
   * AF:
   * length: the length of the ladder.
   * monkey: the monkeys on the ladder.
   * rungs: the rungs of the ladder.
   * 
   * RI:
   * there are no monkeys have the same ID in the List monkey.
   * rungs.size() = length.
   * 
   * REP EXPOSE:
   * mutable.
   */

  public Ladder(int length) {
    this.length = length;

    rungs = new ArrayList<Rung>();
    monkey = new ArrayList<Monkey>();

    for(int i = 1; i <= length; i++) {
      rungs.add(new Rung(0));
    }
  }

  @Override
  public String toString() {
    StringBuffer s = new StringBuffer();
    s.append(String.valueOf(monkey.size()) + " monkey(s)");
    return s.toString();
  }
}
