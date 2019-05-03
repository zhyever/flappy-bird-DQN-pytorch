package circularOrbit;
import java.io.File;
import java.io.IOException;

import track.Track;

public interface CircularOrbit<L, E> extends CircularOrbitAggregate<L, E>{
	//L中心点物体类型
	//E轨道物体的类型
	
	
	/**
	 * 找到指定num的物体
	 * @param num
	 * @return 某个编号为num的物体
	 */
	public E getCertainObject(int num);
	
	/**
	 * 找到某个编号为num的轨道
	 * @param num
	 * @return 某个编号为num的轨道
	 */
	public Track getCertainTrack(int num);
	
	/**
	 * 增加轨道
	 * @param 要增加的轨道 t
	 */
	public void addTrack(Track t);
	
	/**
	 * 删除轨道
	 * @param 要删除的轨道 t
	 */
	public void deleteTrack(Track t);
	
	/**
	 * 设定中心点物体
	 * @param 中心点物体 object
	 */
	public void setCentralObject(L object);
	
	/**
	 * 向某个轨道上增添物体
	 * @param 轨道 t
	 * @param 某个物体 object
	 */
	public void addObjectToTrack(Track t, E object);
	
		
	/**
	 * 读取文件
	 * @param 文件路径 filename
	 * @throws IOException
	 */
	public void readFile(File filename) throws IOException;
	
	/**
	 * 一个物体改变轨道
	 * @param 物体 object
	 * @param 目标轨道 t
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


