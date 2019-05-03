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

import centralObject.CentralAtom;
import circularOrbit.ConcreteCircularOrbit;
import physicalObject.Electron;
import track.Track;

public class AtomStructure extends ConcreteCircularOrbit<CentralAtom, Electron>{
	String elementName;
	int numberOfTracks;
	int numberOfElectron;
	
	private Track getTrack(Electron ele) {
		Iterator<Map.Entry<Electron, Track>> iterator = rel2.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<Electron, Track> it = iterator.next();
			if(it.getKey().getNum() == ele.getNum()) {
				return it.getValue();
			}
		}
		//can't get here
		return null;
	}
	
	/**
	 * 删除某一条轨道(以及其上物体
	 * @param num
	 */
	public void delATrack(int num) {
		
		Iterator<Electron> iterator = objects.iterator();
		while(iterator.hasNext()) {
			Electron it = iterator.next();
			if(getTrack(it).getNum() == num) {
				iterator.remove();
			}
		}
		
		Iterator<Map.Entry<Track, ArrayList<Electron>>> iterator1 = rel.entrySet().iterator();
		while(iterator1.hasNext()) {
			Map.Entry<Track, ArrayList<Electron>> it1 = iterator1.next();
			if(it1.getKey().getNum() == num) {
				iterator1.remove();
			}
		}
		
		
		Iterator<Track> iterator2 = tracks.iterator();
		while(iterator2.hasNext()) {
			Track it2 = iterator2.next();
			if(it2.getNum() == num) {
				iterator2.remove();
			}
		}
		
		numberOfTracks--;
	}
	/**
	 * 新增轨道
	 * @param t
	 */
	public void addNewTrack(Track t) {
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
	public String GUIshowResult() {
		StringBuffer s = new StringBuffer();
		int counterTrack = 1;
		while(counterTrack <= numberOfTracks) {
			s.append(tracks.get(counterTrack - 1).getNum()+ ":\t");
			Iterator<Entry<Track, ArrayList<Electron>>> iterator = rel.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<Track, ArrayList<Electron>> it = iterator.next();
				if(it.getKey().getNum() == tracks.get(counterTrack - 1).getNum()) {
					for(int i = 0; i < it.getValue().size(); i++) {
						if(String.valueOf(it.getValue().get(i).getNum()).length() > 10) {
							s.append(String.valueOf(it.getValue().get(i).getNum()) + "\t");
						}else {
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
	
	public void showResult() {
		
		int counterTrack = 1;
		
		
		while(counterTrack <= numberOfTracks) {
			System.out.print(tracks.get(counterTrack - 1).getNum()+ ":\t" );
			Iterator<Entry<Track, ArrayList<Electron>>> iterator = rel.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<Track, ArrayList<Electron>> it = iterator.next();
				if(it.getKey().getNum() == tracks.get(counterTrack - 1).getNum()) {
					for(int i = 0; i < it.getValue().size(); i++) {
						if(String.valueOf(it.getValue().get(i).getNum()).length() >= 8) {
							System.out.print(String.valueOf(it.getValue().get(i).getNum()) + "\t");
						}else {
							System.out.print(String.valueOf(it.getValue().get(i).getNum()) + "\t\t");
						}
						
					}
					
				}
			}
			System.out.print("\n");
			counterTrack++;
		}

	}
	
	/**
	 * 移动某条轨道上的所有电子
	 * @param trackNum1
	 * @param trackNum2
	 */
	public void tranAll(int trackNum1, int trackNum2) {
		List<Integer> saver = new ArrayList<Integer>();
		ArrayList<Electron> array = new ArrayList<Electron>();
		Iterator<Map.Entry<Track, ArrayList<Electron>>> it = rel.entrySet().iterator();
		
		while (it.hasNext()) {
			///找到原来的轨道
			Map.Entry<Track, ArrayList<Electron>> entry = it.next();
			if(entry.getKey().getNum() == trackNum1) {
				array = entry.getValue();
				for(int i = 0; i < array.size(); i++) {
					saver.add(array.get(i).getNum());
				}
			}
		}
		
		for(int i = 0; i < saver.size(); i++) {
			transit(saver.get(i).intValue(), trackNum2);
			
		}
		
	}
	
	/**
	 * 移动某个编号为objectNum的电子
	 * @param objectNum
	 * @param trackNum
	 */
	public void transit(int objectNum, int trackNum) {
		ArrayList<Electron> array = new ArrayList<Electron>();
		Track track = null;
		Electron e = getCertainObject(objectNum);
		Track t = getCertainTrack(trackNum);
		
		Iterator<Map.Entry<Track, ArrayList<Electron>>> it = rel.entrySet().iterator();
		while (it.hasNext()) {
			///找到原来的轨道
			Map.Entry<Track, ArrayList<Electron>> entry = it.next();
			if(entry.getValue().contains(e)) {
				track = entry.getKey();
				array = entry.getValue();
				array.remove(e);
				it.remove();
			}
		}
		rel.put(track, array);
		
		Iterator<Map.Entry<Track, ArrayList<Electron>>> its = rel.entrySet().iterator();
		while (its.hasNext()) {
			///找到新的轨道
			Map.Entry<Track, ArrayList<Electron>> entry = its.next();
			if(entry.getKey().equals(t)) {
				track = entry.getKey();
				array = entry.getValue();
				array.add(e);
				its.remove();
			}
		}
		rel.put(t, array);
		rel2.put(e, t);
//		e.setTrack(t);
	}
	
	/**
	 * 存档
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public AtomStructureMemento save() throws ClassNotFoundException, IOException {
		 return (new AtomStructureMemento(rel, objects, tracks, numberOfTracks));
	}
	
	/**
	 * 读档
	 * @param memento 某存档
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void recover(AtomStructureMemento memento) throws ClassNotFoundException, IOException {
		this.rel = memento.getRel();
		this.objects = memento.getObjects();
		this.tracks = memento.getTracks();
		this.numberOfTracks = memento.getNumTracks();
	}
	
	/**
	 * 读取文件
	 */
	@Override
	public void readFile(File filename) throws IOException {
		FileReader fr = null;
		BufferedReader br = null;
		String read = null;

		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			while ((read = br.readLine()) != null) {
				if (read.contains("ElementName")) {
					elementNameSet(read);
				}

				if (read.contains("NumberOfTracks")) {
					numberOfTracksSet(read);
				}

				if (read.contains("NumberOfElectron")) {
					electronSet(read);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		br.close();
	}

	private void electronSet(String read) {
		numberOfTracks = 0;
		for(int i = 0; i < read.length(); i++) {
			if(read.charAt(i) == ';') {
				numberOfTracks++;
			}
		}
		numberOfTracks++;
		
		
		String[] deal_read = read.split(";");
		
		///匹配还有问题
		String regex = "([0-9]+)[\\\\/]([0-9]+)";
		Pattern p = Pattern.compile(regex);
		
	
		int counterElectron = 1;
		int counter = 1;
		while(counter <= numberOfTracks) {
			Matcher m = p.matcher(deal_read[counter - 1]);
			if (m.find()) {
				//编号从1开始
				Track t = new Track(Integer.parseInt(m.group(1), 10));
				addTrack(t);
				
				int num = Integer.parseInt(m.group(2), 10);
				
				for(int i = 1; i <= num; i++) {
//					Electron e = new Electron(counterElectron, t);
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

	private void numberOfTracksSet(String read) {
		String deal_read = null;
		String regex = "[0-9]+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(read);

		if (m.find()) {
			deal_read = m.group();
		}
		
		numberOfTracks = Integer.parseInt(deal_read, 10);
		
	}

	private void elementNameSet(String read) {
		String deal_read = read.split("=")[1];
		String regex = "[//s]([a-zA-Z]+)[//s]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(read);

		if (m.find()) {
			deal_read = m.group(1);
		}
		
		elementName = deal_read;
		setCentralObject(new CentralAtom(elementName));
	}
	
	
}
