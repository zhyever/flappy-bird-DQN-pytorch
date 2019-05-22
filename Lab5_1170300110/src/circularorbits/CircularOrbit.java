package circularorbits;

import java.io.File;
import java.io.IOException;

import exception.MyException;
import track.Track;

public interface CircularOrbit<L, E> extends CircularOrbitAggregate<L, E> {
  //L中心点物体类型
  //E轨道物体的类型

  /**getCertainObject.
   * 找到指定num的物体
   * @param num object num
   * @return 某个编号为num的物体
   */
  public E getCertainObject(int num);

  /**getCertainTrack.
   * 找到某个编号为num的轨道
   * @param num track num
   * @return 某个编号为num的轨道
   */
  public Track getCertainTrack(int num);

  /**addTrack.
   * 增加轨道
   * @param t 要增加的轨道 t
   */
  public void addTrack(Track t);

  /**deleteTrack.
   * 删除轨道
   * @param t 要删除的轨道 t
   */
  public void deleteTrack(Track t);

  /**setCentral.
   * 设定中心点物体
   * @param object 中心点物体 object
   */
  public void setCentralObject(L object);

  /**addObjecttoTrack.
   * 向某个轨道上增添物体
   * @param t 轨道 t
   * @param object 某个物体 object
   */
  public void addObjectToTrack(Track t, E object);


  /**readfile.
   * 读取文件
   * @param filename 文件路径 filename
   * @throws IOException IOE exception
   * @throws MyException  My exception
   */
  public void readFile(File filename) throws IOException, MyException;

  /**transit.
   * 一个物体改变轨道
   * @param object 物体 object
   * @param t 目标轨道 t
   */
  public void transit(E object, Track t);


  //得到轨道数目
  public int getTrackNum();

  //得到物体数目
  public int getObjectNum();


  //iterator
  @Override 
  public MyIterator<L, E> iterator();
}


