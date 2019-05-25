package applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import centralobject.CentralAtom;
import circularorbits.ConcreteCircularOrbit;
import exception.MyException;
import logging.LogTest;
import physicalobject.Electron;
import track.Track;

public class AtomStructure extends ConcreteCircularOrbit<CentralAtom, Electron> {
  public String elementName;
  int numberOfTracks;
  int numberOfElectron;

  @Override
  public void addObjectToTrack(Track t, Electron object) {
    boolean flagA = false;
    for (int index = 0; index < tracks.size(); index++) {
      if (tracks.get(index).getNum() == t.getNum()) {
        flagA = true;
      }
    }
    assert flagA;

    ArrayList<Electron> array = new ArrayList<Electron>();
    LogTest.logger.info("向" + t.getNum() + "号轨道" + "添加" + object.getNum() + "号电子");
    Iterator<Map.Entry<Track, ArrayList<Electron>>> it = rel.entrySet().iterator();
    while (it.hasNext()) {
      /// 找到原来的轨道
      Map.Entry<Track, ArrayList<Electron>> entry = it.next();
      if (entry.getKey().equals(t)) {
        array = entry.getValue();
        if (array == null) {
          array = new ArrayList<Electron>();
        }
        array.add(object);
      }
    }

    rel.put(t, array);
    rel2.put(object, t);
  }

  private Track getTrack(Electron ele) {
    Iterator<Map.Entry<Electron, Track>> iterator = rel2.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<Electron, Track> it = iterator.next();
      if (it.getKey().getNum() == ele.getNum()) {
        return it.getValue();
      }
    }
    // can't get here
    return null;
  }

  /**delTrack.
   * 删除某一条轨道(以及其上物体
   * 
   * @param num num Track
   */

  public void delATrack(int num) {
    LogTest.logger.info("删除" + num + "号轨道");
    // pre
    boolean flag = false;
    for (int i = 0; i < tracks.size(); i++) {
      if (tracks.get(i).getNum() == num) {
        flag = true;
      }
    }
    assert flag;

    Iterator<Electron> iterator = objects.iterator();
    while (iterator.hasNext()) {
      Electron it = iterator.next();
      if (getTrack(it).getNum() == num) {
        iterator.remove();
      }
    }

    Iterator<Map.Entry<Track, ArrayList<Electron>>> iterator1 = rel.entrySet().iterator();
    while (iterator1.hasNext()) {
      Map.Entry<Track, ArrayList<Electron>> it1 = iterator1.next();
      if (it1.getKey().getNum() == num) {
        iterator1.remove();
      }
    }

    Iterator<Track> iterator2 = tracks.iterator();
    while (iterator2.hasNext()) {
      Track it2 = iterator2.next();
      if (it2.getNum() == num) {
        iterator2.remove();
      }
    }

    numberOfTracks--;
  }

  /**addTrack.
   * 新增轨道
   * 
   * @param t track t
   */

  public void addNewTrack(Track t) {
    LogTest.logger.info("增加" + t.getNum() + "号轨道");
    // pre
    boolean flagA = true;
    for (int index = 0; index < tracks.size(); index++) {
      if (tracks.get(index).getNum() == t.getNum()) {
        flagA = false;
      }
    }
    assert flagA;

    boolean flag = false;
    Iterator<Track> it = tracks.iterator();
    while (it.hasNext()) {
      if (it.next().getNum() == t.getNum()) {
        flag = true;
      }
    }

    if (flag) {
      // do nothing
    } else {
      rel.put(t, new ArrayList<Electron>());
      tracks.add(t);
    }
    numberOfTracks++;
  }

  /**GUIResult.
   * 
   * @return str show string in gui
   */

  public String guishowResult() {
    StringBuffer s = new StringBuffer();
    int counterTrack = 1;
    while (counterTrack <= numberOfTracks) {
      s.append(tracks.get(counterTrack - 1).getNum() + ":\t");
      Iterator<Entry<Track, ArrayList<Electron>>> iterator = rel.entrySet().iterator();
      while (iterator.hasNext()) {
        Entry<Track, ArrayList<Electron>> it = iterator.next();
        if (it.getKey().getNum() == tracks.get(counterTrack - 1).getNum()) {
          for (int i = 0; i < it.getValue().size(); i++) {
            if (String.valueOf(it.getValue().get(i).getNum()).length() > 10) {
              s.append(String.valueOf(it.getValue().get(i).getNum()) + "\t");
            } else {
              s.append(String.valueOf(it.getValue().get(i).getNum()) + "\t\t");
            }
          }

        }
      }
      s.append("\n");
      counterTrack++;
    }
    return s.toString();
  }

  /**show.
   * print
   */

  public void showResult() {

    int counterTrack = 1;

    while (counterTrack <= numberOfTracks) {
      System.out.print(tracks.get(counterTrack - 1).getNum() + ":\t");
      Iterator<Entry<Track, ArrayList<Electron>>> iterator = rel.entrySet().iterator();
      while (iterator.hasNext()) {
        Entry<Track, ArrayList<Electron>> it = iterator.next();
        if (it.getKey().getNum() == tracks.get(counterTrack - 1).getNum()) {
          for (int i = 0; i < it.getValue().size(); i++) {
            if (String.valueOf(it.getValue().get(i).getNum()).length() >= 8) {
              System.out.print(String.valueOf(it.getValue().get(i).getNum()) + "\t");
            } else {
              System.out.print(String.valueOf(it.getValue().get(i).getNum()) + "\t\t");
            }

          }

        }
      }
      System.out.print("\n");
      counterTrack++;
    }

  }

  /**tranall.
   * 移动某条轨道上的所有电子
   * 
   * @param trackNum1 Track num1
   * @param trackNum2 Track num2
   */

  public void tranAll(int trackNum1, int trackNum2) {
    LogTest.logger.info("将" + trackNum1 + "号轨道上电子转移至" + trackNum2 + "轨道");
    // pre
    boolean flagA = false;
    boolean flagB = false;
    boolean flagC = false;
    for (int i = 0; i < tracks.size(); i++) {
      if (tracks.get(i).getNum() == trackNum1) {
        flagA = true;
      }

      if (tracks.get(i).getNum() == trackNum2) {
        flagB = true;
      }
    }
    if (trackNum1 != trackNum2) {
      flagC = true;
    }
    assert flagA && flagB && flagC;

    List<Integer> saver = new ArrayList<Integer>();
    ArrayList<Electron> array = new ArrayList<Electron>();
    Iterator<Map.Entry<Track, ArrayList<Electron>>> it = rel.entrySet().iterator();

    while (it.hasNext()) {
      /// 找到原来的轨道
      Map.Entry<Track, ArrayList<Electron>> entry = it.next();
      if (entry.getKey().getNum() == trackNum1) {
        array = entry.getValue();
        for (int i = 0; i < array.size(); i++) {
          saver.add(array.get(i).getNum());
        }
      }
    }

    for (int i = 0; i < saver.size(); i++) {
      transit(saver.get(i).intValue(), trackNum2);

    }

  }

  /**transit.
   * 移动某个编号为objectNum的电子
   * 
   * @param objectNum object num
   * @param trackNum track num
   */

  public void transit(int objectNum, int trackNum) {
    LogTest.logger.info("将" + objectNum + "号电子转移到" + trackNum + "号轨道上");
    ArrayList<Electron> array = new ArrayList<Electron>();
    Track track = null;
    Electron e = getCertainObject(objectNum);
    Track t = getCertainTrack(trackNum);

    Iterator<Map.Entry<Track, ArrayList<Electron>>> it = rel.entrySet().iterator();
    while (it.hasNext()) {
      /// 找到原来的轨道
      Map.Entry<Track, ArrayList<Electron>> entry = it.next();
      if (entry.getValue().contains(e)) {
        track = entry.getKey();
        array = entry.getValue();
        array.remove(e);
        it.remove();
      }
    }
    rel.put(track, array);

    Iterator<Map.Entry<Track, ArrayList<Electron>>> its = rel.entrySet().iterator();
    while (its.hasNext()) {
      /// 找到新的轨道
      Map.Entry<Track, ArrayList<Electron>> entry = its.next();
      if (entry.getKey().equals(t)) {
        track = entry.getKey();
        array = entry.getValue();
        array.add(e);
        its.remove();
      }
    }
    rel.put(t, array);
    rel2.put(e, t);
    //e.setTrack(t);
  }

  /**save.
   * 存档
   * 
   * @return saving
   * @throws ClassNotFoundException exception
   * @throws IOException exception
   */

  public AtomStructureMemento save() throws ClassNotFoundException, IOException {
    LogTest.logger.info("存档");
    return (new AtomStructureMemento(rel, objects, tracks, numberOfTracks));
  }

  /**read saver.
   * 读档
   * 
   * @param memento 某存档
   * @throws ClassNotFoundException exception
   * @throws IOException exception
   */

  public void recover(AtomStructureMemento memento) throws ClassNotFoundException, IOException {
    LogTest.logger.info("读档");
    this.rel = memento.getRel();
    this.objects = memento.getObjects();
    this.tracks = memento.getTracks();
    this.numberOfTracks = memento.getNumTracks();
  }

  /**readfile.
   * 读取文件
   */
  @Override
  public void readFile(File filename) throws IOException, MyException {
    LogTest.logger.info("读取" + filename.toString() + "文件");
    FileReader fr = null;
    BufferedReader br = null;
    String read = null;

    fr = new FileReader(filename);
    br = new BufferedReader(fr);
    while ((read = br.readLine()) != null) {
      if (read.contains("ElementName")) {
        elementNameSet(read);
      }

      if (read.contains("NumberOfTracks")) {
        if (read.contains(".")) {
          throw new MyException("轨道数信息处不应该出现小数");
        }
        numberOfTracksSet(read);
      }

      if (read.contains("NumberOfElectron")) {
        if (read.contains(".")) {
          throw new MyException("电子/轨道信息处不应该出现小数");
        }
        electronSet(read);
      }
    }
    br.close();
  }

  public void electronSet(String read) throws MyException {
    int numberOfTracksCounter = 0;
    for (int i = 0; i < read.length(); i++) {
      if (read.charAt(i) == ';') {
        numberOfTracksCounter++;
      }
    }

    if (numberOfTracksCounter + 1 != numberOfTracks) {
      throw new MyException("输入轨道数不匹配(依赖关系错误)");
    }

    String[] dealRead = read.split(";");

    /// 匹配还有问题
    String regex = "([0-9]+)[\\\\/]([0-9]+)";
    Pattern p = Pattern.compile(regex);

    int counterElectron = 1;
    int counter = 1;
    while (counter <= numberOfTracks) {
      Matcher m = p.matcher(dealRead[counter - 1]);
      if (m.find()) {
        // 编号从1开始
        Track t = new Track(Integer.parseInt(m.group(1), 10));
        addTrack(t);

        int num = Integer.parseInt(m.group(2), 10);

        for (int i = 1; i <= num; i++) {
          //Electron e = new Electron(counterElectron, t);
          Electron e = new Electron(counterElectron);
          rel2.put(e, t);

          objects.add(e);
          addObjectToTrack(t, e);
          counterElectron++;
        }
        counter++;
      }
    }

  }

  public void numberOfTracksSet(String read) throws MyException {
    String dealRead = null;
    String regex = "[0-9]+";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(read);

    if (m.find()) {
      dealRead = m.group();
      numberOfTracks = Integer.parseInt(dealRead, 10);
    }

    if (numberOfTracks == 0) {
      throw new MyException("轨道数不应为0");
    }

  }

  public void elementNameSet(String read) throws MyException {
    String dealRead = read.split("=")[1];
    String regex = "[//s]([a-zA-Z]+)[//s]";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(read);

    if (m.find()) {
      dealRead = m.group(1);
    }

    String regex1 = "([a-zA-Z]+)";
    Pattern p1 = Pattern.compile(regex1);
    Matcher m1 = p1.matcher(dealRead);

    if (m1.find()) {
      dealRead = m1.group(0);
    }

    if (!(dealRead.charAt(0) < 'Z' && dealRead.charAt(0) > 'A')) {
      throw new MyException("元素名第一位应大写");
    }

    if (dealRead.length() != 2) {
      throw new MyException("元素名长度错误");
    }

    elementName = dealRead;
    setCentralObject(new CentralAtom(elementName));
  }

  public void outputWriter() {
    try { 
      File file =new File("src/circularOrbit/test/AtomicStructureOutput.txt");
      //if file doesnt exists, then create it


      if(!file.exists()){
        file.createNewFile();
      }

      //true = append file
      FileWriter fileWritter = new FileWriter(file ,true);
      FileWriter fileClean = new FileWriter(file);

      //flush
      fileClean.write("");
      fileClean.flush();
      fileClean.close();

      //write something
      fileWritter.write("ElementName ::= " + this.elementName + "\r\n");
      fileWritter.write("NumberOfTracks ::= " + String.valueOf(tracks.size()) + "\r\n");
      fileWritter.write("NumberOfElectron ::= ");

      for(int i = 0; i < tracks.size(); i++) {
        if(i != tracks.size() - 1) {
          fileWritter.write(tracks.get(i).getNum() + "/" + rel.get(tracks.get(i)).size() + ";");
        } else {
          fileWritter.write(tracks.get(i).getNum() + "/" + rel.get(tracks.get(i)).size());
        }
      }


      fileWritter.close();


    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
