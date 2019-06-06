package passBrige;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Cross implements Runnable {

  Monkey monkey;
  Map<Ladder, List<Monkey>> l2m = new HashMap<Ladder, List<Monkey>>(); // ladder -> monkey

  /*
   * AF:
   * monkey: every monkey is a thread.   
   * l2m: a map that ladder -> monkeys. present monkeys on the ladder.
   *  
   * RI:
   * monkey must be legal.
   * there is no monkeys on the ladders who have the same ID.
   *    
   * REP EXPOSE:
   * mutable.
   * 
   * THREAD SAFE:
   * Imnmutable and synchronized.
   * monkey is an immutable class.
   * the method run() is synchronized so that when monkeys move, there won't be dead lock (dealing with l2m).
   */
  public Cross(Monkey monkey, Map<Ladder, List<Monkey>> l2m) {
    this.monkey = monkey;
    this.l2m = l2m;
  }

  @Override
  public void run() {
    int counter = 0;
    while (monkey.getState() != 2) {
      if(monkey.getState() == 0) {
        if(monkey.getDirection().equals("L->R")) {
          Log.logger.info(monkey.getID() + " has been waiting on the left for "+ counter + "s.");
        }else {
          Log.logger.info(monkey.getID() + " has been waiting on the right for "+ counter + "s.");
        }
      }

      if(monkey.getState() == 1) {
        int index = -1;
        int position = -1;

        int k = 0;
        for (Map.Entry<Ladder, List<Monkey>> entry : l2m.entrySet()) { 
          if(entry.getValue().contains(monkey)) {
            for(int j = 0; j < entry.getKey().rungs.size(); j++) {
              if(entry.getKey().rungs.get(j).state == 1) {
                if(entry.getKey().rungs.get(j).monkey == monkey) {
                  position = j + 1;
                  index = k + 1;
                }
              }
            }
          }
          k++;
        }

        if(monkey.getDirection().equals("L->R")) {
          Log.logger.info(monkey.getID() + " is on the " + index + "th rung at the " 
              + position + "th ladder." + " L->R " + counter + "s.");
        }else {
          Log.logger.info(monkey.getID() + " is on the " + index + "th rung at the " 
              + position + "th ladder." + " R->L " + counter + "s.");
        }
      }




      if (monkey.getState() == 0) {
        Random random = new Random();
        int randomS = random.nextInt(2);

        StrategyInterface s;
        if (randomS == 0) {
          s = new StrategyOne();
        } else {
          s = new StrategyTwo();
        }

        StrategyFactory sf = new StrategyFactory(s, l2m, monkey);

        sf.choose();
      }

      if (monkey.getState() == 0) {
        System.out.println(monkey.getID() + ":waiting");
      }

      if (monkey.getState() == 1) {
        int start = 0;
        int end = 0;
        int moveTime = monkey.getV();

        Iterator<Map.Entry<Ladder, List<Monkey>>> iterator1 = l2m.entrySet().iterator();
        while (iterator1.hasNext()) {
          Map.Entry<Ladder, List<Monkey>> it = iterator1.next();
          if (it.getValue().contains(monkey)) {
            Ladder l = it.getKey();
            for (int i = 0; i < l.length; i++) {
              // find the monkey
              if (l.rungs.get(i).state == 1) {
                if (l.rungs.get(i).monkey.equals(monkey)) {
                  start = i;
                }
              }
            }
          }
        }

        int upres = -1;
        for (int j = 1; j <= moveTime; j++) {

          int res = move();
          upres = res;

          if (res == 2) {
            System.out.println(monkey.getID() + ":" + "Pass the ladder successfully");
            break;
          } else if (res == 1) {
            // success
          } else if (res == 0) {
            // stop
            System.out.println(monkey.getID() + ":" + "Be stopped");
            break;
          } else {
            // error
            System.out.println(monkey.getID() + ":Error occur------------------------------");
          }
        }

        if(upres == 1 || upres == 0) {
          Iterator<Map.Entry<Ladder, List<Monkey>>> iterator2 = l2m.entrySet().iterator();
          while (iterator2.hasNext()) {
            Map.Entry<Ladder, List<Monkey>> it = iterator2.next();
            if (it.getValue().contains(monkey)) {
              Ladder l = it.getKey();
              for (int i = 0; i < l.length; i++) {
                // find the monkey
                if (l.rungs.get(i).state == 1) {
                  if (l.rungs.get(i).monkey.equals(monkey)) {
                    end = i;
                  }
                }
              }
            }
          }
          if(upres == 1) {
            System.out.println(monkey.getID() + ":" + start + " to " + end);
          }else {
            System.out.println(monkey.getID() + ":be stopped at " + start + " to " + end);
          }
        }
      }

      //Ã¿¸ôÒ»Ãë
      try {
        Thread.sleep(1 * 1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      counter++;
    }
    monkey.setEnd(monkey.getStart() + counter);
    Log.logger.info(monkey.getID() +" passed the ladder in "+ counter + "s.");
  }

  public synchronized int move() {
    int direction = 0;
    if (monkey.getDirection().equals("L->R")) {
      direction = 0;
    } else {
      direction = 1;
    }

    if (direction == 0) {
      Iterator<Map.Entry<Ladder, List<Monkey>>> iterator = l2m.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry<Ladder, List<Monkey>> it = iterator.next();
        if (it.getValue().contains(monkey)) {
          Ladder l = it.getKey();
          for (int i = 0; i < l.length; i++) {
            // find the monkey
            if (l.rungs.get(i).state == 1) {
              if (l.rungs.get(i).monkey.equals(monkey)) {
                // move it
                if (i == l.length - 1) {
                  // pass the bridge
                  monkey.setState(2);

                  l.rungs.get(i).state = 0;
                  l.rungs.get(i).monkey = null;

                  l.monkey.remove(monkey);
                  it.getValue().remove(monkey);

                  // System.out.println("Pass the ladder successfully");
                  return 2; // success
                }

                if (l.rungs.get(i + 1).state == 1) {
                  // there is a monkey in front of me
                  // System.out.println("Be stopped");
                  return 0; // be stopped
                }

                if (l.rungs.get(i + 1).state == 0) {
                  l.rungs.get(i + 1).state = 1;
                  l.rungs.get(i + 1).monkey = monkey;

                  l.rungs.get(i).state = 0;
                  l.rungs.get(i).monkey = null;
                  // System.out.println(monkey.getID() + ":from "+ String.valueOf(i + 1) +" to " +
                  // String.valueOf(i + 2));
                  return 1; // successfully move
                }
              }
            }
          }
        }
      }
    }

    if (direction == 1) {
      Iterator<Map.Entry<Ladder, List<Monkey>>> iterator = l2m.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry<Ladder, List<Monkey>> it = iterator.next();
        if (it.getValue().contains(monkey)) {
          //why no such a monkey?
          Ladder l = it.getKey();
          for (int i = 0; i < l.length; i++) {
            // find the monkey
            if (l.rungs.get(i).state == 1) {
              if (l.rungs.get(i).monkey.equals(monkey)) {
                // move it
                if (i == 0) {
                  // pass the bridge
                  monkey.setState(2);

                  l.rungs.get(i).state = 0;
                  l.rungs.get(i).monkey = null;

                  l.monkey.remove(monkey);
                  it.getValue().remove(monkey);
                  // System.out.println("Pass the ladder successfully");
                  return 2; // success
                }

                if (l.rungs.get(i - 1).state == 1) {
                  // there is a monkey in front of me
                  // System.out.println("Be stopped");
                  return 0; // be stopped
                }

                if (l.rungs.get(i - 1).state == 0) {
                  l.rungs.get(i - 1).state = 1;
                  l.rungs.get(i - 1).monkey = monkey;

                  l.rungs.get(i).state = 0;
                  l.rungs.get(i).monkey = null;
                  // System.out.println(monkey.getID() + ":from "+ String.valueOf(i + 1) +" to " +
                  // String.valueOf(i));
                  return 1; // successfully move
                }
              }
            }
          }
        }
      }
    }

    return -1; // no such a monkey on the ladder.
  }

}
