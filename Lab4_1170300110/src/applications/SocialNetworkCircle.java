package applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import apis.CircularOrbitApis;
import centralobject.Person;
import circularorbits.ConcreteCircularOrbit;
import circularorbits.Label;
import exception.MyException;
import logging.LogTest;
import physicalobject.People;
import track.Track;

public class SocialNetworkCircle extends ConcreteCircularOrbit<Person, People> {

  public double[][] distance;
  public List<SocialNetworkCircleTie> ties = new ArrayList<SocialNetworkCircleTie>();
  public List<People> removeObjects = new ArrayList<People>();
  public int numOfTracks;
  /**add people.
   * 
   * @param name people name
   * @param age age
   * @param gender gender
   */

  public void addAPeople(Label name, int age, char gender) {
    LogTest.logger.info("增加" + name.toString());
    // pre
    boolean flag = true;
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).getName().equals(name)) {
        flag = false;
      }
    }

    for (int i = 0; i < removeObjects.size(); i++) {
      if (removeObjects.get(i).getName().equals(name)) {
        flag = false;
      }
    }
    assert flag;

    for (int i = 0; i < removeObjects.size(); i++) {
      if (!objects.contains(removeObjects.get(i))) {
        objects.add(removeObjects.get(i));
      } else {
        // do nothing
      }
    }

    People p = new People(central.getName(), central.getAge(), central.getGender());
    objects.add(p);
    People p1 = new People(name, age, gender);
    objects.add(p1);
    distance = 
        new double[objects.size() + removeObjects.size() + 1][objects.size() 
                                                              + removeObjects.size() + 1];
    cleanDistance();
    rel.clear();
    tracks.clear();
    relation.clear();
    buildSystem();
  }

  /**getdistance.
   * 
   * @param name1 name1
   * @param name2 name2
   * @return disatance
   */

  public int getDistance(Label name1, Label name2) {
    LogTest.logger.info("计算" + name1.toString() + ", " + name2.toString() + "距离");
    // pre
    boolean flagA = false;
    boolean flagB = false;
    boolean flagC = false;
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).getName().equals(name1)) {
        flagA = true;
      }
      if (objects.get(i).getName().equals(name2)) {
        flagB = true;
      }
    }
    if (!name1.equals(name2)) {
      flagC = true;
    }

    assert flagA && flagB && flagC;

    CircularOrbitApis<Person, People> api = new CircularOrbitApis<Person, People>();
    People e1 = getCertainFriend(name1);
    People e2 = getCertainFriend(name2);
    int n = api.getLogicalDistance(this, e1, e2);
    return n;
  }

  private People getCertainFriend(Label str) {
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).getName().equals(str)) {
        return objects.get(i);
      }
    }
    return null;
  }

  /**addTie.
   * 增加一条关系后重构
   * 
   * @param name1 关系起始
   * @param name2 关系结束
   * @param close 亲密度
   */

  public void addTie(Label name1, Label name2, double close) {
    LogTest.logger.info("增加" + name1.toString() + ", " + name2.toString() + "社会关系");
    // pre
    boolean flagA = false;
    boolean flagB = false;
    boolean flagC = false;
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).getName().equals(name1)) {
        flagA = true;
      }
      if (objects.get(i).getName().equals(name2)) {
        flagB = true;
      }
    }
    for (int i = 0; i < removeObjects.size(); i++) {
      if (removeObjects.get(i).getName().equals(name1)) {
        flagA = true;
      }
      if (removeObjects.get(i).getName().equals(name2)) {
        flagB = true;
      }
    }
    if (!name1.equals(name2)) {
      flagC = true;
    }
    assert flagA && flagB && flagC;

    for (int i = 0; i < removeObjects.size(); i++) {
      if (!objects.contains(removeObjects.get(i))) {
        objects.add(removeObjects.get(i));
      } else {
        // do nothing
      }
    }
    People p = new People(central.getName(), central.getAge(), central.getGender());
    objects.add(p);
    distance = new double[objects.size() + 1][objects.size() + 1];
    cleanDistance();
    rel.clear();
    tracks.clear();
    SocialNetworkCircleTie t = new SocialNetworkCircleTie(name1, name2, close);
    ties.add(t);
    relation.clear();
    buildSystem();
    // 向objects中加中间物体
    // buildSystem
  }

  /**deletetie.
   * 删除一条关系后重构
   * 
   * @param name1 关系起始
   * @param name2 关系结束
   */

  public void deleteTie(Label name1, Label name2) {

    LogTest.logger.info("删除" + name1.toString() + ", " + name2.toString() + "社会关系");
    boolean flagA = false;
    boolean flagB = false;
    for (int i = 0; i < ties.size(); i++) {
      if (ties.get(i).getName1().equals(name1) && ties.get(i).getName2().equals(name2)
          || ties.get(i).getName1().equals(name2) && ties.get(i).getName2().equals(name1)) {
        flagA = true;
      }
    }
    if (!name1.equals(name2)) {
      flagB = true;
    }
    assert flagA && flagB;

    for (int i = 0; i < removeObjects.size(); i++) {
      if (!objects.contains(removeObjects.get(i))) {
        objects.add(removeObjects.get(i));
      } else {
        // do nothing
      }
    }
    People p = new People(central.getName(), central.getAge(), central.getGender());
    objects.add(p);
    distance = new double[objects.size() + 1][objects.size() + 1];
    cleanDistance();
    Iterator<SocialNetworkCircleTie> iterator = ties.iterator();
    while (iterator.hasNext()) {
      SocialNetworkCircleTie it = iterator.next();
      if (it.getName1().equals(name1) && it.getName2().equals(name2)
          || it.getName1().equals(name2) && it.getName2().equals(name1)) {
        iterator.remove();
      }
    }
    rel.clear();
    tracks.clear();
    relation.clear();
    rel2.clear();
    buildSystem();

    // 向objects中加中间物体
    // 将二维数组那个位置设置为0
    // buildSystem
  }

  private void cleanDistance() {
    for (int i = 0; i < objects.size(); i++) {
      for (int j = 0; j < objects.size(); j++) {
        distance[i][j] = 0;
      }
    }
  }

  /**closelevel.
   * 计算信息扩散度
   * 
   * @param name 提供一个位于第一个轨道上的人的名字
   * @return 信息扩散度 定义等于其链接的其他人的亲密度的平方和
   */

  public double getCloseLevel(Label name) {
    LogTest.logger.info("计算" + name.toString() + "信息扩散度");
    // pre
    boolean flag = false;
    Track t = null;
    for (int i = 0; i < tracks.size(); i++) {
      if (tracks.get(i).getNum() == 1) {
        t = tracks.get(i);
      }
    }
    for (int i = 0; i < rel.get(t).size(); i++) {
      if (rel.get(t).get(i).getName().equals(name)) {
        flag = true;
      }
    }
    assert flag;

    int index = 0;
    int centralIndex = 0;
    double res = 0;

    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).getName().equals(name)) {
        index = i;
      }
      if (objects.get(i).getName().equals(central.getName())) {
        centralIndex = i;
      }
    }

    for (int i = 0; i < objects.size(); i++) {
      if (i != centralIndex) {
        if (distance[index][i] != 0) {
          res += Math.pow(distance[index][i], 2);
        }
      } else {
        // do nothing
      }
    }

    return res;
  }

  /**getTrack.-*
   * 判定某人处于哪个轨道上
   * 
   * @param str 提供某个人的名字
   * @return tracknum
   */

  public int getPeopleTrack(Label str) {
    Iterator<Map.Entry<Track, ArrayList<People>>> iterator = rel.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<Track, ArrayList<People>> it = iterator.next();
      if (it.getValue().contains(new People(str, 0, 'F'))) {
        return it.getKey().getNum();
      }
    }
    // can't get here
    return -1;
  }

  /**readfile.
   * 读取文件
   */
  @Override
  public void readFile(File filename) throws IOException, MyException {
    LogTest.logger.info("读取" + filename.toString() + "文件");
    LogTest.logger.info("构造轨道系统");
    FileReader fr = null;
    BufferedReader br = null;
    String read = null;

    fr = new FileReader(filename);
    br = new BufferedReader(fr);
    while ((read = br.readLine()) != null) {
      if (read.contains("CentralUser")) {
        if (read.contains(".")) {
          throw new MyException("中心物体年龄不应该为小数形式");
        }
        centralUserSet(read);
      }

      if (read.contains("SocialTie")) {
        socialTieSet(read);
      }

      if (read.contains("Friend")) {
        if (read.contains(".")) {
          throw new MyException("轨道物体年龄不应该为小数形式");
        }
        friendSet(read);
      }
    }

    br.close();

    // 初始化数组
    distance = new double[objects.size() + 1][objects.size() + 1];

    buildSystem();

  }

  private void buildSystem() {

    for (int i = 0; i < ties.size(); i++) {
      Label name1 = ties.get(i).getName1();
      Label name2 = ties.get(i).getName2();

      int index1 = getFriendIndex(name1);
      int index2 = getFriendIndex(name2);

      distance[index1][index2] = ties.get(i).getClose();
      distance[index2][index1] = ties.get(i).getClose();
    }

    // 轨道编号
    int[] trackNum = new int[objects.size() + 1];
    // 记录搜索历史
    int[] objectNum = new int[objects.size() + 1];

    // 标记数组
    boolean[] flag = new boolean[objects.size() + 1];
    for (int i = 0; i < flag.length; i++) {
      flag[i] = false;
    }

    int[] que = new int[objects.size() + 1];

    int rear = 0;

    int index = getFriendIndex(central.getName());
    flag[index] = true;
    trackNum[rear] = 0;
    que[rear] = index;
    rear++;

    int front = 0;

    while (front < rear) {
      int start = que[front];
      objectNum[front] = start;

      for (int i = 0; i < objects.size(); i++) {
        if (distance[start][i] != 0 && flag[i] == false) {
          flag[i] = true;
          trackNum[rear] = trackNum[front] + 1;
          que[rear] = i;
          rear++;
        }
      }
      front++;
    }

    int numOfTracks = trackNum[front - 1];
    for (int i = 1; i <= numOfTracks; i++) {
      addTrack(new Track(i));
    }

    for (int i = 1; i <= front - 1; i++) {
      addObjectToTrack(getCertainTrack(trackNum[i]), objects.get(objectNum[i]));
    }

    List<People> objectsNew = new ArrayList<People>();
    Iterator<People> it = objects.iterator();
    removeObjects.clear();
    while (it.hasNext()) {
      People p = it.next();
      if (flag[getFriendIndex(p.getName())] == false) {
        removeObjects.add(p);
      } else {
        objectsNew.add(p);
      }
    }
    objects = objectsNew;

    People p = new People(central.getName(), central.getAge(), central.getGender());
    objects.remove(p);
    setRelation();

    this.numOfTracks = tracks.size();
  }

  private int getFriendIndex(Label name) {
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).getName().equals(name)) {
        return i;
      }
    }
    return -1;
    // can't get here
  }

  private void centralUserSet(String str) throws MyException {

    String dealRead = null;

    String regex = "[\\<]([A-Za-z0-9\\s\\,]+)[\\>]";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(str);
    if (m.find()) {
      dealRead = m.group(1);
    }
    if (dealRead == null) {
      System.out.println("Read File Error!");
      System.exit(0);
    }
    String[] split =  dealRead.split(",");

    String regex1 = "[\\s]?([A-Za-z0-9]+)[\\s]?";
    Pattern p1 = Pattern.compile(regex1);
    String[] strList = new String[3];

    for (int i = 0; i < split.length; i++) {
      Matcher m1 = p1.matcher(split[i]);
      if (m1.find()) {
        strList[i] = m1.group(1);
      }
    }

    char gender = 'F';
    Label name = null;
    int age = 0;

    name = new Label(strList[0]);
    age = Integer.parseInt(strList[1], 10);
    gender = strList[2].charAt(0);

    if (gender != 'F' && gender != 'M') {
      throw new MyException("中心物体性别输入错误");
    }
    if (strList[2].length() != 1) {
      throw new MyException("中心物体性别输入错误");
    }

    Person central = new Person(name, age, gender);
    People person = new People(name, age, gender);
    objects.add(person);
    setCentralObject(central);

  }

  private void socialTieSet(String str) throws MyException {



    String dealRead = null;

    String regex = "[\\<]([\\.A-Za-z0-9\\s\\,]+)[\\>]";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(str);
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
    String[] strList = new String[3];

    for (int i = 0; i < split.length; i++) {
      Matcher m1 = p1.matcher(split[i]);
      if (m1.find()) {
        strList[i] = m1.group(1);
      }
    }

    Label name1 = null;
    Label name2 = null;


    name1 = new Label(strList[0]);
    name2 = new Label(strList[1]);

    if (name1.equals(name2)) {
      throw new MyException("关系不能起始终止于同一个人");
    }

    Boolean flagn1 = false;
    Boolean flagn2 = false;
    Boolean flagname1 = false;
    Boolean flagname2 = false;
    for (int index = 0; index < objects.size(); index++) {
      if (objects.get(index).getName().equals(name1)) {
        flagn1 = true;
      }
      if (objects.get(index).getName().equals(name2)) {
        flagn2 = true;
      }
    }
    if (central.getName().equals(name1)) {
      flagname1 = true;
    }
    if (central.getName().equals(name2)) {
      flagname2 = true;
    }

    if (!(flagn1 || flagname1)) {
      throw new MyException("依赖关系错误");
    }

    if (!(flagn2 || flagname2)) {
      throw new MyException("依赖关系错误");
    }

    String deal = strList[2];
    int len = 0;
    len = deal.split("\\.")[1].length();

    if (len > 3) {
      throw new MyException("输入数据小数点有误");
    }

    double close = 0;
    close = Double.parseDouble(strList[2]);

    SocialNetworkCircleTie tie = new SocialNetworkCircleTie(name1, name2, close);
    ties.add(tie);
  }

  private void friendSet(String str) throws MyException {

    String dealRead = null;

    String regex = "[\\<]([A-Za-z0-9\\s\\,]+)[\\>]";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(str);
    if (m.find()) {
      dealRead = m.group(1);
    }
    if (dealRead == null) {
      System.out.println("Read File Error!");
      System.exit(0);
    }
    String[] split = dealRead.split(",");

    String regex1 = "[\\s]?([A-Za-z0-9]+)[\\s]?";
    Pattern p1 = Pattern.compile(regex1);
    String[] strList = new String[3];

    for (int i = 0; i < split.length; i++) {
      Matcher m1 = p1.matcher(split[i]);
      if (m1.find()) {
        strList[i] = m1.group(1);
      }
    }

    char gender = 'F';
    Label name = null;
    int age = 0;

    name = new Label(strList[0]);
    age = Integer.parseInt(strList[1], 10);
    gender = strList[2].charAt(0);

    if (gender != 'F' && gender != 'M') {
      throw new MyException("轨道物体性别输入错误");
    }
    if (strList[2].length() != 1) {
      throw new MyException("轨道物体性别输入错误");
    }

    People person = new People(name, age, gender);
    objects.add(person);
  }
  /**GUIshow.
   * 
   * @return show String
   */

  public String guishowResult() {
    StringBuffer s = new StringBuffer();
    int counterTrack = 1;
    while (counterTrack <= numOfTracks) {
      s.append(tracks.get(counterTrack - 1).getNum() + ":\t");
      Iterator<Entry<Track, ArrayList<People>>> iterator = rel.entrySet().iterator();
      while (iterator.hasNext()) {
        Entry<Track, ArrayList<People>> it = iterator.next();
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
  /**showResult.
   * 
   */

  public void showResult() {
    int counterTrack = 1;
    while (counterTrack <= numOfTracks) {
      System.out.print(tracks.get(counterTrack - 1).getNum() + ":\t");
      Iterator<Entry<Track, ArrayList<People>>> iterator = rel.entrySet().iterator();
      while (iterator.hasNext()) {
        Entry<Track, ArrayList<People>> it = iterator.next();
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
  /**GUIremoveObjects.
   * 
   * @return removed objects
   */

  public String guiremoveObjects() {
    StringBuffer s = new StringBuffer();
    for (int i = 0; i < removeObjects.size(); i++) {
      s.append(removeObjects.get(i).getName().toString() + "\t");
    }
    return s.toString();
  }

  private void setRelation() {
    for (int i = 0; i < ties.size(); i++) {
      Label name1 = ties.get(i).getName1();
      Label name2 = ties.get(i).getName2();

      if (getPeopleTrack(name1) >= 1 && getPeopleTrack(name2) >= 1) {
        People p1 = getCertainFriend(name1);
        People p2 = getCertainFriend(name2);

        if (relation.get(p1) == null) {
          List<People> arry = new ArrayList<People>();
          relation.put(p1, arry);
        }

        if (relation.get(p2) == null) {
          List<People> arry = new ArrayList<People>();
          relation.put(p2, arry);
        }

        List<People> arry1 = relation.get(p1);
        List<People> arry2 = relation.get(p2);

        arry1.add(p2);
        arry2.add(p1);

        relation.put(p1, arry1);
        relation.put(p2, arry2);
      }
    }
  }
  /**showties.
   * 
   * @return ties
   */

  public String guities() {
    StringBuffer s = new StringBuffer();
    for (int i = 0; i < ties.size(); i++) {
      s.append(ties.get(i).getName1().toString() + "\t\t<-->\t"
          + ties.get(i).getName2().toString() + "\t\tclose level:"
          + String.valueOf(ties.get(i).getClose()));
      s.append("\n");
    }
    return s.toString();
  }
}
