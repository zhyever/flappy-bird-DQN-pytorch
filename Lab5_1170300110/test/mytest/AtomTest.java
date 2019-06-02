package mytest;

import java.io.File;
import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import applications.AtomStructure;
import applications.AtomStructureCaretaker;
import exception.MyException;
import physicalobject.Electron;
import track.Track;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AtomTest {

  // File f = new File("src/circularOrbit/test/AtomicStructureNameLength.txt");
  AtomStructure test = new AtomStructure();
  AtomStructureCaretaker caretaker = new AtomStructureCaretaker();

  @Test(expected = MyException.class)
  public void test1() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/AtomicStructureDecimalElec.txt");
    test.readFile(f);
  } 

  @Test(expected = MyException.class)
  public void test2() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/AtomicStructureDecimalTrack.txt");
    test.readFile(f);
  }

  @Test(expected = MyException.class)
  public void test3() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/AtomicStructureNameLarge.txt");
    test.readFile(f);
  }

  @Test(expected = MyException.class)
  public void test4() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/AtomicStructureNameLength.txt");
    test.readFile(f);
  }

  @Test(expected = MyException.class)
  public void test5() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/AtomicStructureNumsTrack.txt");
    test.readFile(f);
  }

  @Test(expected = MyException.class)
  public void test6() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/AtomicStructureRel.txt");
    test.readFile(f);
  }

  @Test
  public void test7() throws IOException, MyException, ClassNotFoundException {
    File f = new File("src/circularOrbit/test/AtomicStructure.txt");
    test.readFile(f);

    caretaker.set(test.save());
    test.tranAll(2, 3);
    test.addNewTrack(new Track(6));
    test.delATrack(6);
    test.recover(caretaker.get(0));

    test.showResult();
    test.guishowResult();
  }

  @Test(expected = AssertionError.class)
  public void test8() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/AtomicStructure.txt");
    test.readFile(f);
    test.addNewTrack(new Track(1));
  }

  @Test(expected = AssertionError.class)
  public void test9() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/AtomicStructure.txt");
    test.readFile(f);
    test.delATrack(10);
  }

  @Test(expected = AssertionError.class)
  public void test10() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/AtomicStructure.txt");
    test.readFile(f);
    test.addObjectToTrack(new Track(10), new Electron(100));
  }

}
