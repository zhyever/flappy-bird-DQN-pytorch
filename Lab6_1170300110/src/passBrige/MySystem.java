package passBrige;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class MySystem {

  public int n; // n ladders. [1, 10]
  public int h; // ladder's length;
  public int t; // time interval. [1, 5]
  public int N; // number of monkeys. [1, 1000]
  public int k; // number of new monkeys when each interval time passed. [1, 50]
  public int MV; // max v. [5, 10]

  public List<Ladder> ladders = new ArrayList<Ladder>(); // all the ladders

  public List<Monkey> monkeys = Collections.synchronizedList(new ArrayList<Monkey>()); // all the monkeys

  Map<Ladder, List<Monkey>> l2m = Collections.synchronizedMap(new HashMap<Ladder, List<Monkey>>()); //ladder -> monkey

  /*
   * AF:
   * n: n ladders.
   * h: ladders's length.
   * t: time interval between monkeys born.
   * N: number of monkeys.
   * k: number of monkeys bore once.
   * MV: max move v.
   * ladders: List of all the ladders.
   * monkeys: List of all the monkeys.
   * l2m: a map from ladder to List of monkeys. representing the monkeys on the ladder.
   * 
   * RI:
   * n ladders. [1, 10]
   * time interval. [1, 5]
   * number of monkeys. [1, 1000]
   * number of new monkeys when each interval time passed. [1, 50]
   * max v. [5, 10]
   * all the ladders and monkeys are not the same in the monkeys and l2m.
   * ladders has their own Num from 1 to n.
   * 
   * REP:
   * mutable.
   * 
   * THREAD SAFE:
   * monkey class is nearly immutable.
   * the list of monkey and the map l2m are synchronizedMap
   * 
   */
  public MySystem(int n, int h, int t, int N, int k, int MV) {

    this.n = n;
    this.h = h;
    this.t = t;
    this.N = N;
    this.k = k;
    this.MV = MV;

    for(int i = 1; i <= n; i++) {
      ladders.add(new Ladder(h));
    }

    for(int i = 1; i <= n; i++) {
      l2m.put(ladders.get(i - 1), new ArrayList<Monkey>());
    }

  }

  public void systemStart() throws IOException {
    Vector<Thread> threads = new Vector<Thread>();

    //input n,h,t,N,k,MV
    //MySystem system = new MySystem(2, 10, 3, 5, 3, 5);

    //clean the log.log.
    File f = new File("logs/log.log");

    FileWriter fileWritter = new FileWriter(f, true);
    FileWriter fileClean = new FileWriter(f);

    fileClean.write("");
    fileClean.flush();
    fileClean.close();


    List<Monkey> newMonkeys =  new ArrayList<Monkey>();


    int monkeyCounter = 0;
    int counter = 0;
    boolean flag = true;

    while(true) {
      if(counter == 0) {
        newMonkeys = MonkeyGenerator.buildMonkey(this.k, this.MV, this.monkeys.size(), counter * t);
        flag = false;
      }

      if(flag) {
        if(this.N - monkeyCounter < this.k) {
          newMonkeys = MonkeyGenerator.buildMonkey(this.N - monkeyCounter, this.MV, this.monkeys.size(), counter * t);
        }else {
          newMonkeys = MonkeyGenerator.buildMonkey(this.k, this.MV, this.monkeys.size(), counter * t);
        }
      }

      for(int i = 0; i < newMonkeys.size(); i++) {
        this.monkeys.add(newMonkeys.get(i));
        monkeyCounter++;
        Cross c = new Cross(newMonkeys.get(i), this.l2m);
        Thread thread = new Thread(c);
        threads.add(thread);
        thread.start();
      }

      flag = true;

      try {
        Thread.sleep(this.t * 1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      counter++;

      if(monkeyCounter == this.N) {
        break;
      }
    }

    for (Thread iThread : threads) {
      try {
        // 等待所有线程执行完毕
        iThread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    double eat = 0;
    for(int i = 0; i < monkeys.size(); i++) {
      eat += monkeys.get(i).getTime();
    }
    eat = monkeys.size() / eat;
    System.out.println(eat);

    double fair = 0;
    double con = 0;
    for(int i = 0; i < monkeys.size() - 1; i++) {
      for(int j = i + 1; j < monkeys.size(); j++) {
        if(monkeys.get(i).getStart() < monkeys.get(j).getStart()) {
          if(monkeys.get(i).getEnd() < monkeys.get(j).getEnd()) {
            con += 1;
          }else {
            con -= 1;
          }
        }else {
          if(monkeys.get(i).getEnd() < monkeys.get(j).getEnd()) {   
            con -= 1;
          }else {
            con += 1;
          }
        }
      }
    }

    fair = con / ((monkeys.size() * (monkeys.size() - 1)) / 2);
    System.out.println(fair);

    fileWritter.write("吞吐率" + String.valueOf(eat) + "\r\n");
    fileWritter.write("公平性" + String.valueOf(fair));

    fileWritter.close();
  }

  public static void main(String[] args) throws IOException {
    //    MySystem s = new MySystem(2, 10, 3, 5, 3, 5);
    //    s.systemStart();

    //please input x,y,z,a,b,c!
    Scanner in = new Scanner(System.in);
    String str = in.nextLine();

    int n; // n ladders. [1, 10]
    int h; // ladder's length;
    int t; // time interval. [1, 5]
    int N; // number of monkeys. [1, 1000]
    int k; // number of new monkeys when each interval time passed. [1, 50]
    int MV; // max

    n = Integer.parseInt(str.split(",")[0]);
    h = Integer.parseInt(str.split(",")[1]);
    t = Integer.parseInt(str.split(",")[2]);
    N = Integer.parseInt(str.split(",")[3]);
    k = Integer.parseInt(str.split(",")[4]);
    MV = Integer.parseInt(str.split(",")[5]);

    MySystem s = new MySystem(n, h, t, N, k, MV);
    s.systemStart();


  }
}
