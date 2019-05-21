package APIs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import applications.AtomStructure;
import applications.SocialNetworkCircle;
import applications.TrackGameRandom;
import applications.TrackGameSorted;
import circularOrbit.ConcreteCircularOrbit;
import physicalObject.Athlete;
import physicalObject.People;
import physicalObject.PhysicalObject;
import track.Track;

public class CircularOrbitAPIs<L, E> {
	
	/**
	 * 画图
	 * @param c
	 */
	public static void visualize(ConcreteCircularOrbit<?, ?> c) {
		PanelTest.start(c);
	}
	
	/**
	 * 计算熵
	 * 定义Σ(每条轨道上的物体数/总物体数 * ln 每条轨道上的物体数/总物体数)
	 * @param c
	 * @return 熵
	 */
	public double getObjectDistributionEntropy(ConcreteCircularOrbit<L, E> c) {
		double res = 0;
		int num = c.getObjectNum();
		Iterator<Map.Entry<Track, ArrayList<E>>> iterator = c.rel.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Track, ArrayList<E>> it = iterator.next();

			int num1 = it.getValue().size();
			double num2 = (double) num1 / (double) num;
			
			res += num2 * Math.abs(Math.log(num2));
		}
		return res;
	}
	
	/**
	 * 获得逻辑距离 for app5
	 * @param c
	 * @param e1
	 * @param e2
	 * @return 两者之间的逻辑距离
	 */
	public int getLogicalDistance (ConcreteCircularOrbit<L, E> c, E e1, E e2){
		int[][] distance = new int[c.getObjectNum()+1][c.getObjectNum()+1];
		
		for(int i = 0; i < c.getObjectNum() + 1; i++) {
			for(int j = 0; j < c.getObjectNum() + 1; j ++) {
				distance[i][j] = 0;
			}
		}
		
		int index1 = 0;
		int index2 = 0;
		Iterator<Map.Entry<E, List<E>>> iterator = c.relation.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<E, List<E>> it = iterator.next();
			for(int k = 0; k < c.objects.size(); k++) {
				if(c.objects.get(k).equals(it.getKey())) {
					index1 = k;
				}
			}
			for(int index = 0; index < it.getValue().size(); index++) {
				for(int k = 0; k < c.objects.size(); k++) {
					if(c.objects.get(k).equals(it.getValue().get(index))) {
						index2 = k;
						
						distance[index1][index2] = 1;
					}
				}
			}
		}
		
		Iterator<Entry<Track, ArrayList<E>>> iterator1 = c.rel.entrySet().iterator();
		while (iterator1.hasNext()) {
			Entry<Track, ArrayList<E>> it = iterator1.next();
			if(it.getKey().getNum() == 1) {
				for(int index = 0; index < it.getValue().size(); index++) {
					for(int k = 0; k < c.objects.size(); k++) {
						if(c.objects.get(k).equals(it.getValue().get(index))) {
							int index3 = k;
							
							distance[c.getObjectNum()][index3] = 1;
							distance[index3][c.getObjectNum()] = 1;
						}
					}
				}
			}
		}
		
		for(int k = 0; k < c.objects.size(); k++) {
			if(c.objects.get(k).equals(e1)) {
				index1 = k;
			}
		}
		
		for(int k = 0; k < c.objects.size(); k++) {
			if(c.objects.get(k).equals(e2)) {
				index2 = k;
			}
		}
		
		///找到坐标开始准备广度优先搜索
		
		int[] que = new int[c.getObjectNum()+2];
		int front = 0;
		int rear = 0;
		int start = index1;
		
		//标记数组
		boolean[] flag = new boolean[c.objects.size()+2];
		for(int i = 0; i < flag.length; i++) {
			flag[i] = false;
		}
				
		//记录搜索历史
		int[] trackNum = new int[c.objects.size()+2];
		
		///开始搜索
		que[front] = index1;
		trackNum[front] = 0;
		rear++;
		while(front < rear) {
			start = que[front];
			if(start == index2) {
				return trackNum[front];
			}
			for(int i = 0; i < c.objects.size() + 1; i++) {
				if(distance[start][i] != 0 && flag[i] == false) {
					flag[i] = true;
					trackNum[rear] = trackNum[front] + 1;
					que[rear] = i;
					rear++;
				}
			}
			front++;
		}
		return -1;
	}
	
	/**
	 * 获得物理距离
	 * @param c
	 * @param e1
	 * @param e2
	 * @return 两物体之间的物理距离
	 */
	public double getPhysicalDistance (ConcreteCircularOrbit<L, E> c, E e1, E e2) {
		if(e1 instanceof PhysicalObject && e2 instanceof PhysicalObject) {
			double e1x = ((PhysicalObject) e1).getR().getNum() * 
					Math.cos(Math.toRadians(((PhysicalObject) e1).getSitha()));
			double e1y = ((PhysicalObject) e1).getR().getNum() * 
					Math.sin(Math.toRadians(((PhysicalObject) e1).getSitha()));
			
			double e2x = ((PhysicalObject) e2).getR().getNum() * 
					Math.cos(Math.toRadians(((PhysicalObject) e2).getSitha()));
			double e2y = ((PhysicalObject) e2).getR().getNum() * 
					Math.sin(Math.toRadians(((PhysicalObject) e2).getSitha()));
			
			double dertax = Math.abs(e1x - e2x);
			double dertay = Math.abs(e1y - e2y);
			
			return Math.sqrt(Math.pow(dertax, 2) + Math.pow(dertay, 2));
		}else {
			return -1;
		}
	}
	
	/**
	 * 获得两个同种类型的轨道系统的差异
	 * @param c1
	 * @param c2
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Difference<E> getDifference (ConcreteCircularOrbit<L, E> c1, ConcreteCircularOrbit<L, E> c2) {
		
		
		if(c1 instanceof AtomStructure) {
			Difference<Integer> d = new Difference<Integer>();
			d.differentTracks = c1.getTrackNum() - c2.getTrackNum();
			
			List<E> arry1 = new ArrayList<E>();
			List<E> arry2 = new ArrayList<E>();
			
			int maxTrack = c1.getTrackNum() >= c2.getTrackNum() ? c1.getTrackNum() : c2.getTrackNum();
			d.maxTracks = maxTrack;
			for(int i = 0; i < maxTrack; i++) {
				int num1 = 0;
				int num2 = 0;
				
				boolean flag1 = false;
				Iterator<Map.Entry<Track, ArrayList<E>>> entries1 = c1.rel.entrySet().iterator();
				while(entries1.hasNext()) {
					Map.Entry<Track, ArrayList<E>> entry = entries1.next(); 
					if(entry.getKey().getNum() == i+1) {
						arry1 = entry.getValue();
						flag1 = true;
					}
				}
				if(!flag1) {
					num1 = 0;
				}else {
					num1 = arry1.size();
				}
				
				boolean flag2 = false;
				Iterator<Map.Entry<Track, ArrayList<E>>> entries2 = c2.rel.entrySet().iterator();
				while(entries2.hasNext()) {
					Map.Entry<Track, ArrayList<E>> entry = entries2.next(); 
					if(entry.getKey().getNum() == i+1) {
						arry2 = entry.getValue();
						flag2 = true;
					}
				}
				if(!flag2) {
					num2 = 0;
				}else {
					num2 = arry2.size();
				}
				
				if(num1 == 0 && num2 == 0) {
					d.differentNum.put(i + 1, 0);
				}else if(num1 != 0 && num2 == 0) {
					d.differentNum.put(i + 1, num1);
				}else if(num1 == 0 && num2 != 0) {
					d.differentNum.put(i + 1, -num2);
				}else{
					d.differentNum.put(i + 1, num1 - num2);
				}
			}
			
			return (Difference<E>) d;
		}
		
		if(c1 instanceof TrackGameSorted ||c1 instanceof TrackGameRandom) {
			///默认第一组
			Difference<Athlete> d = new Difference<Athlete>();
			d.differentTracks = c1.getTrackNum() - c2.getTrackNum();
			
			Athlete ath1 = null;
			Athlete ath2 = null;
			List<E> arry1 = new ArrayList<E>();
			List<E> arry2 = new ArrayList<E>();
			
			int maxTrack = c1.getTrackNum() >= c2.getTrackNum() ? c1.getTrackNum() : c2.getTrackNum();
			d.maxTracks = maxTrack;
			for(int i = 0; i < maxTrack; i++) {
				
				boolean flag1 = false;
				Iterator<Map.Entry<Track, ArrayList<E>>> entries1 = c1.rel.entrySet().iterator();
				while(entries1.hasNext()) {
					Map.Entry<Track, ArrayList<E>> entry = entries1.next(); 
					if(entry.getKey().getNum() == i+1) {
						arry1 = entry.getValue();
						ath1 = (Athlete) arry1.get(0);
						flag1 = true;
					}
				}
				if(!flag1) {
					arry1 = new ArrayList<E>();
				}
				
				boolean flag2 = false;
				Iterator<Map.Entry<Track, ArrayList<E>>> entries2 = c2.rel.entrySet().iterator();
				while(entries2.hasNext()) {
					Map.Entry<Track, ArrayList<E>> entry = entries2.next(); 
					if(entry.getKey().getNum() == i+1) {
						arry2 = entry.getValue();
						ath2 = (Athlete) arry2.get(0);
						flag2 = true;
					}
				}
				if(!flag2) {
					arry2 = new ArrayList<E>();
				}
				
				
				if(arry1.size() == 0 && arry2.size() == 0) {
					d.differentNum.put(i + 1, 0);
					d.left.put(i + 1, null);
					d.right.put(i + 1, null);
				}else if(arry1.size() == 0 && arry2.size() != 0) {
					d.differentNum.put(i + 1, -1);
					d.left.put(i + 1, null);
					d.right.put(i + 1, ath2);
				}else if(arry1.size() != 0 && arry2.size() == 0) {
					d.differentNum.put(i + 1, 1);
					d.left.put(i + 1, ath1);
					d.right.put(i + 1, null);
				}else{
					d.differentNum.put(i + 1, 0);
					d.left.put(i + 1, ath1);
					d.right.put(i + 1, ath2);
				}
			}
			return (Difference<E>) d;
		}
		
		if(c1 instanceof SocialNetworkCircle) {
			
			ArrayList<People> arry1 = new ArrayList<People>();
			ArrayList<People> arry2 = new ArrayList<People>();
			
			Difference<ArrayList<People>> d = new Difference<ArrayList<People>>();
			d.differentTracks = c1.getTrackNum() - c2.getTrackNum();
			int maxTrack = c1.getTrackNum() >= c2.getTrackNum() ? c1.getTrackNum() : c2.getTrackNum();
			d.maxTracks = maxTrack;
			for(int i = 0; i < maxTrack; i++) {
				
				boolean flag1 = false;
				Iterator<Map.Entry<Track, ArrayList<E>>> entries1 = c1.rel.entrySet().iterator();
				while(entries1.hasNext()) {
					Map.Entry<Track, ArrayList<E>> entry = entries1.next(); 
					if(entry.getKey().getNum() == i+1) {
						arry1 = (ArrayList<People>) entry.getValue();
						flag1 = true;
					}
				}
				if(!flag1) {
					arry1 = new ArrayList<People>();
				}
				
				boolean flag2 = false;
				Iterator<Map.Entry<Track, ArrayList<E>>> entries2 = c2.rel.entrySet().iterator();
				while(entries2.hasNext()) {
					Map.Entry<Track, ArrayList<E>> entry = entries2.next(); 
					if(entry.getKey().getNum() == i+1) {
						arry2 = (ArrayList<People>) entry.getValue();
						flag2 = true;
					}
				}
				if(!flag2) {
					arry2 = new ArrayList<People>();
				}
				
				
				if(arry1.size() == 0 && arry2.size() == 0) {
					d.differentNum.put(i + 1, 0);
					d.left.put(i + 1, null);
					d.right.put(i + 1, null);
				}else if(arry1.size() == 0 && arry2.size() != 0) {
					d.differentNum.put(i + 1, -arry2.size());
					d.left.put(i + 1, null);
					d.right.put(i + 1, (ArrayList<People>) arry2);
				}else if(arry1.size() != 0 && arry2.size() == 0) {
					d.differentNum.put(i + 1, arry1.size());
					d.left.put(i + 1, (ArrayList<People>) arry1);
					d.right.put(i + 1, null);
				}else{
					d.differentNum.put(i + 1, arry1.size() - arry2.size());
					ArrayList<People> arry3 = new ArrayList<People>();
					ArrayList<People> arry4 = new ArrayList<People>();
					for(int j = 0; j < arry1.size(); j++) {
						People p = (People) arry1.get(j);
						if(!arry2.contains(p)) {
							arry3.add(p);
						}
					}
					
					for(int j = 0; j < arry2.size(); j++) {
						People p = (People) arry2.get(j);
						if(!arry1.contains(p)) {
							arry4.add(p);
						}
					}
					
					if(arry3.size() == 0 && arry4.size() == 0) {
						d.left.put(i + 1, null);
						d.right.put(i + 1, null);
					}else if(arry3.size() == 0 && arry4.size() != 0) {
						d.left.put(i + 1, null);
						d.right.put(i + 1, arry4);
					}else if(arry3.size() != 0 && arry4.size() == 0) {
						d.left.put(i + 1, arry3);
						d.right.put(i + 1, null);
					}else {
						d.left.put(i + 1, arry3);
						d.right.put(i + 1, arry4);
					}
				}
			}
			return (Difference<E>) d;
		}
		
		return null;
		
	}
}
