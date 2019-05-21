package mytest;

import java.io.File;
import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import applications.SocialNetworkCircle;
import circularorbits.Label;
import exception.MyException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SocialTest {

  SocialNetworkCircle client = new SocialNetworkCircle();

  @Test(expected = MyException.class)
  public void test1() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/SocialNetworkCircleAge.txt");
    client = new SocialNetworkCircle();
    client.readFile(f);
  }

  @Test(expected = MyException.class)
  public void test2() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/SocialNetworkCircleCentralAge.txt");
    client = new SocialNetworkCircle();
    client.readFile(f);
  }

  @Test(expected = MyException.class)
  public void test3() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/SocialNetworkCircleCentralGender.txt");
    client = new SocialNetworkCircle();
    client.readFile(f);
  }

  @Test(expected = MyException.class)
  public void test4() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/SocialNetworkCircleDecimal.txt");
    client = new SocialNetworkCircle();
    client.readFile(f);
  }

  @Test(expected = MyException.class)
  public void test5() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/SocialNetworkCircleGender.txt");
    client = new SocialNetworkCircle();
    client.readFile(f);
  }

  @Test(expected = MyException.class)
  public void test6() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/SocialNetworkCircleRel.txt");
    client = new SocialNetworkCircle();
    client.readFile(f);
  }

  @Test(expected = MyException.class)
  public void test7() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/SocialNetworkCircleTieSame.txt");
    client = new SocialNetworkCircle();
    client.readFile(f);
  }

  @Test
  public void test8() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/SocialNetworkCircle.txt");
    client = new SocialNetworkCircle();
    client.readFile(f);

    System.out.println(client.getCloseLevel(new Label("DavidChen")));
    System.out.println(client.getDistance(new Label("DavidChen"), new Label("TomWong")));
    client.deleteTie(new Label("FrankLee"), new Label("TomWong"));
    client.deleteTie(new Label("FrankLee"), new Label("DavidChen"));
    client.addTie(new Label("FrankLee"), new Label("PonyMa"), 0.1);
    client.deleteTie(new Label("JackMa"), new Label("PonyMa"));
    client.showResult();
    client.addAPeople(new Label("Lzy"), 20, 'F');
    client.addAPeople(new Label("Lc"), 20, 'M');
    client.addTie(new Label("Lzy"), new Label("Lc"), 0.5);
    client.addTie(new Label("Lzy"), new Label("TomWong"), 0.5);

    client.showResult();
    client.guishowResult();
    client.guiremoveObjects();
    client.guities();
  }

  @Test(expected = AssertionError.class)
  public void test9() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/SocialNetworkCircle.txt");
    client = new SocialNetworkCircle();
    client.readFile(f);
    client.getDistance(new Label("asdf"), new Label("LisaWong"));

  }

  @Test(expected = AssertionError.class)
  public void test10() throws IOException, MyException {
    File f = new File("src/circularOrbit/test/SocialNetworkCircle.txt");
    client = new SocialNetworkCircle();
    client.readFile(f);
    client.addTie(new Label("TomWong"), new Label("asdf"), 0.1);
  }
}
