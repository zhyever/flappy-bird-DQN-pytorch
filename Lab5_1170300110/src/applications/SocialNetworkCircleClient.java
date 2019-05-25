package applications;

import java.io.File;
import java.io.IOException;

import exception.MyException;
import io.ReadContext;
import io.WriteContext;

public class SocialNetworkCircleClient {
  /**main.
   * 
   * @param args no means
   * @throws IOException exception
   */

  //  public static void main(String[] args) throws IOException {
  //    File f = new File("src/circularOrbit/test/SocialNetworkCircleDecimal.txt");
  //    // _Medium
  //    SocialNetworkCircle client = new SocialNetworkCircle();
  //   
  //    try {
  //      client.readFile(f);
  //    } catch (MyException e) {
  //      e.printInfo();
  //      f = new File("src/circularOrbit/test/SocialNetworkCircle.txt");
  //      client = new SocialNetworkCircle();
  //      try {
  //        client.readFile(f);
  //      } catch (MyException e1) {
  //        e1.printInfo();
  //      } catch (IOException e1) {
  //        System.out.println("文件目录输入有误");
  //      }
  //    } catch (IOException e) {
  //      System.out.println("文件目录输入有误");
  //      f = new File("src/circularOrbit/test/SocialNetworkCircle.txt");
  //      client = new SocialNetworkCircle();
  //      try {
  //        client.readFile(f);
  //      } catch (MyException e1) {
  //        e1.printInfo();
  //      } catch (IOException e1) {
  //        System.out.println("文件目录输入有误");
  //      }
  //    }
  //
  //    client.showResult();
  //    client.outputWriter();
  //    // System.out.println(client.getCloseLevel(new label("DavidChen")));
  //    // client.deleteTie(new label("FrankLee"), new label("TomWong"));
  //    // client.deleteTie(new label("FrankLee"), new label("DavidChen"));
  //    // client.showResult();
  //    //
  //    // client.addTie(new label("FrankLee"), new label("PonyMa"), 0.1);
  //    // client.showResult();
  //    //
  //    // client.deleteTie(new label("JackMa"), new label("PonyMa"));
  //    // client.showResult();
  //    // client.addAPeople(new label("Lzy"), 20, 'F');
  //    // client.addAPeople(new label("Lc"), 20, 'M');
  //    //
  //    // client.addTie(new label("Lzy"), new label("Lc"), 0.5);
  //    // client.showResult();
  //    //
  //    // //client.addAPeople(new label("Lzy"), 20, 'M');
  //    // client.showResult();
  //    //
  //    // client.addTie(new label("Lzy"), new label("TomWong"), 0.5);
  //    // client.showResult();
  //    //
  //    // PanelTest.start(client);
  //    // client.addTrack(new Track(1));
  //  }

  public static void main(String[] args) {
    //    //File f = new File("src/circularOrbit/test/SocialNetworkCircle_Medium.txt");
    //    File f = new File("src/Spring2019_HITCS_SC_Lab5-master/SocialNetworkCircle.txt");
    //
    //    // _Medium
    //    SocialNetworkCircle client = new SocialNetworkCircle();
    //    ReadContext read = new ReadContext();
    //
    //    try {
    //      //client.readFile(f);
    //      read.readfileBuffer3(f, client);
    //    } catch (MyException e) {
    //      e.printInfo();
    //      f = new File("src/circularOrbit/test/SocialNetworkCircle.txt");
    //      client = new SocialNetworkCircle();
    //      try {
    //        //client.readFile(f);
    //        read.readfileBuffer3(f, client);
    //      } catch (MyException e1) {
    //        e1.printInfo();
    //      } catch (IOException e1) {
    //        System.out.println("文件目录输入有误");
    //      }
    //    } catch (IOException e) {
    //      System.out.println("文件目录输入有误");
    //      f = new File("src/circularOrbit/test/SocialNetworkCircle.txt");
    //      client = new SocialNetworkCircle();
    //      try {
    //        //client.readFile(f);
    //        read.readfileBuffer3(f, client);
    //      } catch (MyException e1) {
    //        e1.printInfo();
    //      } catch (IOException e1) {
    //        System.out.println("文件目录输入有误");
    //      }
    //    }
    //
    //    //client.showResult();
    //    WriteContext write = new WriteContext();
    //    write.writeFileWriter3(client);

    SocialNetworkCircleBigger client = new SocialNetworkCircleBigger();
    //File f = new File("src/circularOrbit/test/SocialNetworkCircle_Medium.txt");
    File f = new File("src/Spring2019_HITCS_SC_Lab5-master/SocialNetworkCircle.txt");
    ReadContext read = new ReadContext();
    WriteContext write = new WriteContext();

    try {
      read.readfileStream3(f, client);
    } catch (IOException | MyException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    //client.showResult();
    write.writeBufferWriter3(client);
  }
}
