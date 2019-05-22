package applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import centralobject.Sport;
import circularorbits.ConcreteCircularOrbit;
import circularorbits.Word;
import exception.MyException;
import logging.LogTest;
import physicalobject.Athlete;
import track.Track;

public abstract class TrackGame extends ConcreteCircularOrbit<Sport, Athlete> {

  int game;
  int numOfTracks;
  // List<Athlete> athletes = new ArrayList<Athlete>();

  abstract void setPlayer();

  @Override
  public void addObjectToTrack(Track t, Athlete object) {
    boolean flagA = false;
    for (int index = 0; index < tracks.size(); index++) {
      if (tracks.get(index).getNum() == t.getNum()) {
        flagA = true;
      }
    }
    assert flagA;

    LogTest.logger.info("向" + t.getNum() + "号轨道" + "添加运动员：" + object.getName().toString());
    ArrayList<Athlete> array = new ArrayList<Athlete>();

    Iterator<Map.Entry<Track, ArrayList<Athlete>>> it = rel.entrySet().iterator();
    while (it.hasNext()) {
      /// 找到原来的轨道
      Map.Entry<Track, ArrayList<Athlete>> entry = it.next();
      if (entry.getKey().equals(t)) {
        array = entry.getValue();
        if (array == null) {
          array = new ArrayList<Athlete>();
        }
        array.add(object);
      }
    }

    rel.put(t, array);
    rel2.put(object, t);
  }

  private Track getTrack(Athlete ath) {
    Iterator<Map.Entry<Athlete, Track>> iterator = rel2.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<Athlete, Track> it = iterator.next();
      if (it.getKey().getName().equals(ath.getName())) {
        return it.getValue();
      }
    }
    // can't get here
    return null;
  }

  /**delTrack.
   * 删除某一条轨道(以及其上物体
   * 
   * @param num Track num
   */
  public void delATrack(int num) {
    LogTest.logger.info("删除" + num + "号轨道");
    // pre
    boolean flag = false;
    for (int index = 0; index < tracks.size(); index++) {
      if (tracks.get(index).getNum() == num) {
        flag = true;
      }
    }
    assert flag == true;

    Iterator<Athlete> iterator = objects.iterator();
    while (iterator.hasNext()) {
      Athlete it = iterator.next();
      if (getTrack(it).getNum() == num) {
        iterator.remove();
      }
    }

    Iterator<Map.Entry<Track, ArrayList<Athlete>>> iterator1 = rel.entrySet().iterator();
    while (iterator1.hasNext()) {
      Map.Entry<Track, ArrayList<Athlete>> it1 = iterator1.next();
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

    numOfTracks--;
  }

  /**add Track.
   * 新增轨道
   * 
   * @param t Track t
   */

  public void addNewTrack(Track t) {
    LogTest.logger.info("新增" + t.getNum() + "号轨道");

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
      rel.put(t, new ArrayList<Athlete>());
      tracks.add(t);
    }
    numOfTracks++;
  }

  /**change.
   * 两名运动员互换轨道
   * 
   * @param ath1 ath1
   * @param ath2 ath2
   */

  public void change(Athlete ath1, Athlete ath2) {
    LogTest.logger.info("调换" + ath1.getName().toString() 
        + ", " + ath2.getName().toString() + "两名运动员位置");
    // pre
    boolean flagA = false;
    boolean flagB = false;
    boolean flagC = false;
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).getName().equals(ath1.getName())) {
        flagA = true;
      }

      if (objects.get(i).getName().equals(ath2.getName())) {
        flagB = true;
      }
    }

    if (!ath1.getName().equals(ath2.getName())) {
      flagC = true;
    }
    assert flagA && flagB && flagC;

    ArrayList<Athlete> ath = new ArrayList<Athlete>();

    Track temp1 = null;
    Track temp2 = null;

    for (Athlete a : objects) {
      if (a.equals(ath1)) {
        temp1 = getTrack(a);
      }
    }

    for (Athlete a : objects) {
      if (a.equals(ath2)) {
        temp2 = getTrack(a);
      }
    }

    Athlete newAth1 = new Athlete(ath2.getName(), ath2.getNum(), 
        ath2.getNation(), ath2.getAge(), ath2.getGrade());
    Athlete newAth2 = new Athlete(ath1.getName(), ath1.getNum(), 
        ath1.getNation(), ath1.getAge(), ath1.getGrade());

    rel2.put(newAth1, temp1);
    rel2.put(newAth2, temp2);

    // newAth1.setTrack(temp1);
    // newAth2.setTrack(temp2);

    Iterator<Map.Entry<Track, ArrayList<Athlete>>> iterator = rel.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<Track, ArrayList<Athlete>> it = iterator.next();

      if (it.getKey().equals(temp1) && it.getKey().equals(temp2)) {
        ath = it.getValue();
        for (int index = 0; index < ath.size(); index++) {
          if (ath.get(index).equals(ath1)) { // bolt
            ath.remove(ath1);
            ath.add(index, newAth1);// ro
            continue;
          }

          if (ath.get(index).equals(ath2)) {
            ath.remove(ath2);
            ath.add(index, newAth2);// bolt
          }
        }
        continue;
      }

      if (it.getKey().equals(temp1)) {
        ath = it.getValue();

        for (int index = 0; index < ath.size(); index++) {
          if (ath.get(index).equals(ath1)) { // bolt
            ath.remove(ath1);
            ath.add(index, newAth1);// ro
          }
        }

        it.setValue(ath);
      }

      if (it.getKey().equals(temp2)) {
        ath = it.getValue();

        for (int index = 0; index < ath.size(); index++) {
          if (ath.get(index).equals(ath2)) {
            ath.remove(ath2);
            ath.add(index, newAth2);// bolt
          }
        }

        it.setValue(ath);
      }

    }

    objects.remove(ath1);
    objects.remove(ath2);
    objects.add(newAth1);
    objects.add(newAth2);

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
      if (read.contains("Athlete")) {
        athleteAdd(read);
      }

      if (read.contains("Game")) {
        gameTypeSet(read);
      }

      if (read.contains("NumOfTracks")) {
        gameTracksSet(read);
      }
    }

    br.close();
  }

  private void athleteAdd(String read) throws MyException {


    String dealRead = null;

    String regex = "[\\<]([\\.A-Za-z0-9\\s\\,]+)[\\>]";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(read);
    if (m.find()) {
      dealRead = m.group(1);
    }
    if (dealRead == null) {
      System.out.println("Read File Error!");
      System.exit(0);
    }
    String[] split = dealRead.split(",");

    String regex1 = "[\\s]?([\\.A-Za-z0-9]+)[\\s]?";
    Pattern p1 = Pattern.compile(regex1);
    String[] str = new String[5];

    for (int i = 0; i < split.length; i++) {
      Matcher m1 = p1.matcher(split[i]);
      if (m1.find()) {
        str[i] = m1.group(1);
      }
    }

    boolean numFlagException = false;
    for (int index = 0; index <= 4; index++) {
      if (str[index] == null) {
        numFlagException = true;
        break;
      }
    }
    if (numFlagException) {
      throw new MyException("输入数据数量有误");
    }

    if (str[0].charAt(0) < '9' && str[0].charAt(0) > '0') {
      throw new MyException("输入名字顺序有误");
    }

    Word name = null;

    name = new Word(str[0]);
    for (int index = 0; index < objects.size(); index++) {
      if (objects.get(index).getName().equals(name)) {
        throw new MyException("输入了名字相同的运动员");
      }
    }

    int num = 0;
    String nation = null;


    num = Integer.parseInt(str[1], 10);
    nation = str[2];

    if (str[2].charAt(0) < '9' && str[2].charAt(0) > '0') {
      throw new MyException("输入国家顺序有误");
    }

    boolean flagException = false;
    for (int index = 0; index < nation.length(); index++) {
      if (nation.charAt(index) < 'z' && nation.charAt(index) > 'a') {
        flagException = true;
        break;
      }
    }
    if (flagException) {
      throw new MyException("输入国家大写有误");
    }

    int age = 0;
    double grade = 0;

    age = Integer.parseInt(str[3], 10);
    grade = Double.parseDouble(str[4]);

    int len = 0;
    len = str[4].split("\\.")[1].length();

    if (len != 2) {
      throw new MyException("输入小数点位数有误");
    }

    this.objects.add(new Athlete(name, num, nation, age, grade));
  }

  private void gameTypeSet(String read) {
    int game = 0;
    String dealRead = null;
    String regex = "[0-9]+";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(read);

    if (m.find()) {
      dealRead = m.group();
      game = Integer.parseInt(dealRead, 10);
    }

    this.game = game;
  }

  private void gameTracksSet(String read) {
    int numOfTracks = 0;
    String dealRead = null;
    String regex = "[0-9]+";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(read);

    if (m.find()) {
      dealRead = m.group();
      numOfTracks = Integer.parseInt(dealRead, 10);
      this.numOfTracks = numOfTracks;
    }

    // 轨道编号从1号开始
    for (int i = 1; i <= numOfTracks; i++) {
      addTrack(new Track(i));
    }
  }

  /**showResult.
   * 打印
   */
  public void showResult() {
    int counterTrack = 1;

    while (counterTrack <= numOfTracks) {
      System.out.print(tracks.get(counterTrack - 1).getNum() + ":\t");
      Iterator<Entry<Track, ArrayList<Athlete>>> iterator = rel.entrySet().iterator();
      while (iterator.hasNext()) {
        Entry<Track, ArrayList<Athlete>> it = iterator.next();
        if (it.getKey().getNum() == tracks.get(counterTrack - 1).getNum()) {
          for (int i = 0; i < it.getValue().size(); i++) {
            if (it.getValue().get(i).getName().toString().length() >= 8) {
              System.out.print(it.getValue().get(i).getName() + "\t");
            } else {
              System.out.print(it.getValue().get(i).getName() + "\t\t");
            }

          }

        }
      }
      System.out.print("\n");
      counterTrack++;
    }

  }
  /**showResult for GUI.
   * 
   * @return String to show
   */

  public String guishowResult() {
    StringBuffer s = new StringBuffer();
    int counterTrack = 1;
    while (counterTrack <= numOfTracks) {
      s.append(tracks.get(counterTrack - 1).getNum() + ":\t");
      Iterator<Entry<Track, ArrayList<Athlete>>> iterator = rel.entrySet().iterator();
      while (iterator.hasNext()) {
        Entry<Track, ArrayList<Athlete>> it = iterator.next();
        if (it.getKey().getNum() == tracks.get(counterTrack - 1).getNum()) {
          for (int i = 0; i < it.getValue().size(); i++) {
            if (it.getValue().get(i).getName().toString().length() > 10) {
              s.append(it.getValue().get(i).getName() + "\t");
            } else {
              s.append(it.getValue().get(i).getName() + "\t\t");
            }
          }

        }
      }
      s.append("\n");
      counterTrack++;
    }
    return s.toString();
  }

  public void outputWriter() {
    try { 
      File file =new File("src/circularOrbit/test/TrackGameOutput.txt");
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
      fileWritter.write("Game ::= " + this.game + "\r\n");
      fileWritter.write("NumOfTracks ::= " + String.valueOf(tracks.size()) + "\r\n");

      for(int i = 0; i < objects.size(); i++) {
        fileWritter.write("Athlete ::= <" + objects.get(i).getName().toString() + ","+ objects.get(i).getNum()+ "," 
            + objects.get(i).getNation() + ","+ objects.get(i).getAge()+ "," + objects.get(i).getGrade()+ ">\r\n");
      }


      fileWritter.close();


    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
