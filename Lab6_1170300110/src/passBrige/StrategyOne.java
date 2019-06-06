package passBrige;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StrategyOne implements StrategyInterface{

  @Override
  public boolean choose(Map<Ladder, List<Monkey>> l2m, Monkey monkey) {
    synchronized(l2m) {
      boolean res = false;

      Iterator<Map.Entry<Ladder, List<Monkey>>> iterator = l2m.entrySet().iterator();
      while(iterator.hasNext()) {

        Map.Entry<Ladder, List<Monkey>> it = iterator.next();
        if(it.getValue().size() == 0) {
          if(monkey.getDirection().equals("L->R")) {
            monkey.setState(1);
            //monkey.setPositon(1);
            it.getValue().add(monkey);
            it.getKey().monkey.add(monkey);
            it.getKey().rungs.get(0).state = 1;
            it.getKey().rungs.get(0).monkey = monkey;
            res = true;
            break;
          }

          if(monkey.getDirection().equals("R->L")) {
            monkey.setState(1);
            //monkey.setPositon(it.getKey().length - 1);
            it.getValue().add(monkey);
            it.getKey().monkey.add(monkey);
            it.getKey().rungs.get(it.getKey().length - 1).state = 1;
            it.getKey().rungs.get(it.getKey().length - 1).monkey = monkey;
            res = true;
            break;
          }

        }
      }

      System.out.println(monkey.getID() + ":" + "choose method 1" );
      return res;
    }

    //    Iterator<Map.Entry<Ladder, List<Monkey>>> iterat = l2m.entrySet().iterator();
    //    while(iterat.hasNext()) {
    //      Map.Entry<Ladder, List<Monkey>> it = iterat.next();
    //      for(int i = 0; i < it.getValue().size(); i++) {
    //        System.out.print(monkey.getID() + ":::" + it.getValue().get(i).getID() + ", ");
    //      }
    //    }
    //
    //    boolean flag = false;
    //
    //    for(List<Monkey> list : l2m.values()) {
    //      if(list.contains(monkey)) {
    //        flag = true;
    //      }
    //    }

  }
}
