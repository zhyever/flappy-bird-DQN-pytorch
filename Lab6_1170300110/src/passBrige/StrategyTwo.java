package passBrige;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StrategyTwo implements StrategyInterface{

  @Override
  public synchronized boolean choose(Map<Ladder, List<Monkey>> l2m, Monkey monkey) {
    synchronized(l2m) {
      boolean res = true;

      Iterator<Map.Entry<Ladder, List<Monkey>>> iterator = l2m.entrySet().iterator();
      while(iterator.hasNext()) {
        res = true;

        Map.Entry<Ladder, List<Monkey>> it = iterator.next();
        for(Monkey m : it.getValue()) {
          if(!m.getDirection().equals(monkey.getDirection())) {
            res = false;
          }
        }

        //find a ladder on which there is no Conflicting monkey or there is no monkey.
        if(res) {
          if(it.getValue().size() == 0) {
            //there is no monkey.
            if(monkey.getDirection().equals("L->R")) {
              //monkey.setPositon(1);
              monkey.setState(1);
              it.getValue().add(monkey);
              it.getKey().monkey.add(monkey);
              it.getKey().rungs.get(0).state = 1;
              it.getKey().rungs.get(0).monkey = monkey;

              break;
            }

            if(monkey.getDirection().equals("R->L")) {
              monkey.setState(1);
              //monkey.setPositon(it.getKey().length - 1);
              it.getValue().add(monkey);
              it.getKey().monkey.add(monkey);
              it.getKey().rungs.get(it.getKey().length - 1).state = 1;
              it.getKey().rungs.get(it.getKey().length - 1).monkey = monkey;

              break;
            }
          }else {
            //there are some monkeys.
            if(monkey.getDirection().equals("L->R") && it.getKey().rungs.get(0).state == 0) {
              monkey.setState(1);
              //monkey.setPositon(1);
              it.getValue().add(monkey);
              it.getKey().monkey.add(monkey);
              it.getKey().rungs.get(0).state = 1;
              it.getKey().rungs.get(0).monkey = monkey;

              break;
            }

            if(monkey.getDirection().equals("R->L") && it.getKey().rungs.get(it.getKey().length - 1).state == 0) {
              monkey.setState(1);
              //monkey.setPositon(it.getKey().length - 1);
              it.getValue().add(monkey);
              it.getKey().monkey.add(monkey);
              it.getKey().rungs.get(it.getKey().length - 1).state = 1;
              it.getKey().rungs.get(it.getKey().length - 1).monkey = monkey;

              break;
            }
          }
        }
      }
      System.out.println(monkey.getID() + ":" + "choose method 2" );
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

  public static void main(String[] args) {
    StrategyTwo s = new StrategyTwo();
    Map<Ladder, List<Monkey>> l2m = new HashMap<Ladder, List<Monkey>>();
    Ladder l1 = new Ladder(20);
    Ladder l2 = new Ladder(20);
    Ladder l3 = new Ladder(20);

    Monkey lr1 = new Monkey(1, "L->R", 1);
    Monkey lr2 = new Monkey(1, "L->R", 1);
    Monkey lr3 = new Monkey(1, "L->R", 1);

    Monkey lr4 = new Monkey(1, "L->R", 1);

    Monkey rl1 = new Monkey(1, "R->L", 1);
    Monkey rl2 = new Monkey(1, "R->L", 1);
    Monkey rl3 = new Monkey(1, "R->L", 1);

    l2m.put(l1, new ArrayList<Monkey>());
    l2m.put(l2, new ArrayList<Monkey>());
    l2m.put(l3, new ArrayList<Monkey>());

    s.choose(l2m, lr1);
    s.choose(l2m, lr2);
    s.choose(l2m, lr3);

    l3.rungs.get(0).state = 0;
    l3.rungs.get(1).state = 1;
    l3.rungs.get(0).monkey = null;
    l3.rungs.get(1).monkey = lr3;

    s.choose(l2m, lr4);

    s.choose(l2m, rl1);
    s.choose(l2m, rl2);
    s.choose(l2m, rl3);
  }

}
