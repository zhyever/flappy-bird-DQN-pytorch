package passBrige;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonkeyGenerator {
  /**
   * 
   * @param num    how many monkeys have had.
   * @param k    num of monkeys once build.
   * @param MAX  V.
   * @param start  monkeys build time.
   * @return
   */

  public static List<Monkey> buildMonkey(int k, int MV, int num, int start) {
    List<Monkey> res = new ArrayList<Monkey>();
    int i = 1;
    while (i <= k) {
      // new monkeys
      Random random = new Random();
      int randomDirection = random.nextInt(2);

      int randomV = random.nextInt(MV) + 1;

      if (randomDirection == 0) {
        Monkey m = new Monkey(num + 1, "L->R", randomV); // 0 -- L -> R
        m.setStart(start);
        res.add(m);
      } else {
        Monkey m = new Monkey(num + 1, "R->L", randomV); // 1 -- R -> L
        m.setStart(start);
        res.add(m);
        //res.add(new Monkey(num + 1, "R->L", randomV)); // 1 -- R -> L
      }

      i++;
      num++;
    }
    return res;
  }

}
