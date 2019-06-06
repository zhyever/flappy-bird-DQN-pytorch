package passBrige;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrategyFactory {
  /*
   * THREAD SAFE:
   * the map l2m is synchronizedMap.
   * the map is synchronized in the method choose.
   */
  public StrategyInterface strategy;
  Map<Ladder, List<Monkey>> l2m = Collections.synchronizedMap(new HashMap<Ladder, List<Monkey>>()); //ladder -> monkey
  Monkey monkey;

  public StrategyFactory(StrategyInterface strategy, Map<Ladder, List<Monkey>> l2m, Monkey monkey) {
    this.strategy = strategy;
    this.l2m = l2m;
    this.monkey = monkey;
  }

  public synchronized boolean choose() {
    return strategy.choose(l2m, monkey);
  }
}
