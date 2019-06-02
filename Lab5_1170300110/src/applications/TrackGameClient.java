package applications;

import java.io.File;
import java.io.IOException;

import exception.MyException;
import io.ReadContext;
import io.WriteContext;

public class TrackGameClient {
  /**
   * test.
   * 
   * @param args no means
   * @throws IOException exception
   */

  // public static void main(String[] args) throws IOException {
  //
  // File f = new File("src/circularOrbit/test/TrackGameOrder1.txt");
  // TrackGameContext client = new TrackGameContext();
  //
  // TrackGame trackgame = new TrackGameSorted();
  //
  // try {
  // trackgame.readFile(f);
  // } catch (MyException e) {
  // e.printInfo();
  // // try again
  // File f1 = new File("src/circularOrbit/test/TrackGame.txt");
  // try {
  // client = new TrackGameContext();
  // trackgame = new TrackGameSorted();
  // trackgame.readFile(f1);
  // } catch (MyException e1) {
  // e1.printInfo();
  // } catch (IOException e1) {
  // System.out.println("文件目录不存在");
  // }
  // } catch (IOException e) {
  // System.out.println("文件目录不存在");
  // File f1 = new File("src/circularOrbit/test/TrackGame.txt");
  // try {
  // client = new TrackGameContext();
  // trackgame = new TrackGameSorted();
  // trackgame.readFile(f1);
  // } catch (MyException e1) {
  // e1.printInfo();
  // } catch (IOException e1) {
  // System.out.println("文件目录不存在");
  // }
  // }
  //
  // client.run(trackgame);
  //
  // client.game.showResult();
  // client.game.delATrack(5);
  // client.game.addNewTrack(new Track(6));
  // // name, num, nation, age, grade
  // client.game.addObjectToTrack(client.game.tracks.get(2),
  // new Athlete(new Word("lz"), 20, "CHN", 18, 10.01));
  // client.game.change(client.game.objects.get(1), client.game.objects.get(3));
  // client.game.showResult();
  // client.game.outputWriter();
  //
  // }
  /**
   * main
   * 
   * @param args arges
   * @throws IOException file read error
   */
  public static void main(String[] args) throws IOException {
    //File f = new File("src/Spring2019_HITCS_SC_Lab5-master/TrackGame.txt");
    File f = new File("src/circularOrbit/test/TrackGame_Medium.txt");
    TrackGameContext client = new TrackGameContext();

    TrackGame trackgame = new TrackGameSorted();
    ReadContext read = new ReadContext();
    WriteContext write = new WriteContext();

    try {
      read.readfileStream1(f, trackgame);
    } catch (MyException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    client.run(trackgame);
    client.game.showResult();
    write.writeStream1(client.game);
  }
}
