package applications;

import java.io.File;
import java.io.IOException;

import apis.CircularOrbitApis;
import centralobject.CentralAtom;
import exception.MyException;
import io.ReadContext;
import physicalobject.Electron;

public class AtomStructureClient {
  /**test.
   * 
   * @param args no means
   * @throws IOException exception 
   * @throws ClassNotFoundException exception
   */
  //  public static void main(String[] args) throws IOException, ClassNotFoundException {
  //
  //    File f = new File("src/circularOrbit/test/AtomicStructureNameLength.txt");
  //    AtomStructure test = new AtomStructure();
  //
  //    final CircularOrbitApis<CentralAtom, Electron> api = 
  //        new CircularOrbitApis<CentralAtom, Electron>();
  //
  //    try {
  //      test.readFile(f);
  //    } catch (MyException e) {
  //      e.printInfo();
  //      f = new File("src/circularOrbit/test/AtomicStructure.txt");
  //      test = new AtomStructure();
  //      try {
  //        test.readFile(f);
  //      } catch (MyException e1) {
  //        e1.printInfo();
  //        System.exit(0);
  //      } catch (IOException e2) {
  //        System.out.println("输入文件路径有误");
  //        System.exit(0);
  //      }
  //    } catch (IOException e) {
  //      System.out.println("输入文件路径有误");
  //      f = new File("src/circularOrbit/test/AtomicStructure.txt");
  //      test = new AtomStructure();
  //      try {
  //        test.readFile(f);
  //      } catch (MyException e1) {
  //        e1.printInfo();
  //        System.exit(0);
  //      } catch (IOException e2) {
  //        System.out.println("输入文件路径有误");
  //        System.exit(0);
  //      }
  //    }
  //
  //    test.showResult();
  //
  //    AtomStructureCaretaker caretaker = new AtomStructureCaretaker();
  //    caretaker.set(test.save());
  //
  //    test.tranAll(2, 3);
  //    test.showResult();
  //
  //    System.out.println(api.getObjectDistributionEntropy(test));
  //
  //    test.outputWriter();
  //    //Track t = new Track(6);
  //    //test.addNewTrack(t);
  //    //test.showResult();
  //    //caretaker.set(test.save());
  //    //
  //    //test.recover(caretaker.get(0));
  //    //test.showResult();
  //
  //    //PanelTest.start(test);
  //  }


  public static void main(String[] args) throws IOException, ClassNotFoundException {

    File f = new File("src/circularOrbit/test/AtomicStructureNameLength.txt");
    AtomStructure test = new AtomStructure();
    ReadContext read = new ReadContext();

    final CircularOrbitApis<CentralAtom, Electron> api = 
        new CircularOrbitApis<CentralAtom, Electron>();

    try {
      //test.readFile(f);
      read.readfileScanner2(f, test);
    } catch (MyException e) {
      e.printInfo();
      f = new File("src/circularOrbit/test/AtomicStructure.txt");
      test = new AtomStructure();
      try {
        //test.readFile(f);
        read.readfileScanner2(f, test);
      } catch (MyException e1) {
        e1.printInfo();
        System.exit(0);
      } catch (IOException e2) {
        System.out.println("输入文件路径有误");
        System.exit(0);
      }
    } catch (IOException e) {
      System.out.println("输入文件路径有误");
      f = new File("src/circularOrbit/test/AtomicStructure.txt");
      test = new AtomStructure();
      try {
        //test.readFile(f);
        read.readfileScanner2(f, test);
      } catch (MyException e1) {
        e1.printInfo();
        System.exit(0);
      } catch (IOException e2) {
        System.out.println("输入文件路径有误");
        System.exit(0);
      }
    }

    test.showResult();
  }
}
