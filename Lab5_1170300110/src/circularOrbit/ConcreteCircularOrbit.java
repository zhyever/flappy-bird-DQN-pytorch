package circularOrbit;

import track.Track;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import exception.MyException;
import physicalObject.PhysicalObject;


public class ConcreteCircularOrbit<L, E> implements CircularOrbit<L, E> {
	
	// RI:
	// relation\ rel中的E都应该属于objects
	// rel 中的Track 应属于tracks
	// E L分别要满足其对应的限定条件
	// AF:
	// 定义一个轨道系统
	// 该轨道系统由L 中心物体、Track 轨道、E 轨道上物体组成
	// tracks中存有轨道信息
	// relation中存有轨道上物体之间的关系
	// rel中存储某条轨道上的物体信息
	// objects中存有所有轨道上的物体
	// rel2存有物体到其所在轨道的映射关系
	// Safety from rep exposure: mutable none
	
	// 中心物体
	public L central;
	// 轨道
	public List<Track> tracks = new ArrayList<Track>();
	// 关系映射
	public Map<E, List<E>> relation = new HashMap<E, List<E>>();
	// track 到 物体的关系映射
	public Map<Track, ArrayList<E>> rel = new HashMap<Track, ArrayList<E>>();
	// 物体
	public List<E> objects = new ArrayList<E>();
	
	// 物体到轨道的映射
	public Map<E, Track> rel2 = new HashMap<E, Track>();
	
	
	@Override
	public E getCertainObject(int num) {
		E object;
		for(int i = 0; i < objects.size(); i++) {
			object = objects.get(i);
			if(object instanceof PhysicalObject) {
				if(((PhysicalObject) object).getNum() == num) {
					return object;
				}
			}
		}
		return null;
	}
	
	
	@Override
	public Track getCertainTrack(int num) {
		for(int i = 0; i < tracks.size(); i++) {
			if(tracks.get(i).getNum() == num) {
				return tracks.get(i);
			}
		}
		return null;
	}
	
	
	@Override
	public void addTrack(Track t) {
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
			rel.put(t, new ArrayList<E>());
			tracks.add(t);
		}
	}

	
	@Override
	public void deleteTrack(Track t) {
		Iterator<Track> it = tracks.iterator();
		while (it.hasNext()) {
			if (it.next().getNum() == t.getNum()) {
				it.remove();
				rel.remove(t);
			}
		}
	}

	
	@Override
	public void setCentralObject(L object) {
		this.central = object;
	}

	
	@Override
	public void addObjectToTrack(Track t, E object) {
		ArrayList<E> array = new ArrayList<E>();
		
		Iterator<Map.Entry<Track, ArrayList<E>>> it = rel.entrySet().iterator();
		while (it.hasNext()) {
			///找到原来的轨道
			Map.Entry<Track, ArrayList<E>> entry = it.next();
			if(entry.getKey().equals(t)) {
				array = entry.getValue();
				if(array == null) {
					array = new ArrayList<E>();
				}
				array.add(object);
			}
		}
		
		rel.put(t, array);
		rel2.put(object, t);
	}

	/**
	 * 读取文件
	 * @throws MyException 
	 */
	@Override
	public void readFile(File filename) throws IOException, MyException {
		// need to be override
	}

	
	@Override
	public void transit(E object, Track t) {
		ArrayList<E> array = new ArrayList<E>();
		Track track = null;
		
		Iterator<Map.Entry<Track, ArrayList<E>>> it = rel.entrySet().iterator();
		while (it.hasNext()) {
			///找到原来的轨道
			Map.Entry<Track, ArrayList<E>> entry = it.next();
			if(entry.getValue().contains(object)) {
				track = entry.getKey();
				array = entry.getValue();
				array.remove(object);
				it.remove();
			}
		}
		rel.put(track, array);
		
		Iterator<Map.Entry<Track, ArrayList<E>>> its = rel.entrySet().iterator();
		while (its.hasNext()) {
			///找到新的轨道
			Map.Entry<Track, ArrayList<E>> entry = its.next();
			if(entry.getKey().equals(t)) {
				track = entry.getKey();
				array = entry.getValue();
				array.add(object);
				its.remove();
			}
		}
		rel.put(t, array);
		
		if(object instanceof PhysicalObject) {
			rel2.put(object, t);
		}
	}
	
	/**
	 * 两个get方法
	 */
	@Override
	public int getTrackNum() {
		return tracks.size();
	}

	@Override
	public int getObjectNum() {
		return objects.size();
	}

	
	/**
	 * iterator设计
	 */
	@Override
	public MyIterator<L, E> iterator() {
		return new CircularOrbitIterator<>(this);
	}

	
}
