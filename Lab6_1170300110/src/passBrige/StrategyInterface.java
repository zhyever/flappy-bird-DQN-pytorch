package passBrige;

import java.util.List;
import java.util.Map;

public interface StrategyInterface {
  //successfully choose return 1
  //no choice but waiting return 0
  public boolean choose(Map<Ladder, List<Monkey>> l2m, Monkey monkey);
}
