package applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import centralObject.Sport;
import circularOrbit.ConcreteCircularOrbit;
import circularOrbit.word;
import physicalObject.Athlete;
import track.Track;

public abstract class TrackGame extends ConcreteCircularOrbit<Sport, Athlete> {

	int game;
	int numOfTracks;
	// List<Athlete> athletes = new ArrayList<Athlete>();

	abstract void setPlayer();
	
	private Track getTrack(Athlete ath) {
		Iterator<Map.Entry<Athlete, Track>> iterator = rel2.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<Athlete, Track> it = iterator.next();
			if(it.getKey().getName().equals(ath.getName())) {
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
		
		Iterator<Athlete> iterator = objects.iterator();
		while(iterator.hasNext()) {
			Athlete it = iterator.next();
			if(getTrack(it).getNum() == num) {
				iterator.remove();
			}
		}
		
		Iterator<Map.Entry<Track, ArrayList<Athlete>>> iterator1 = rel.entrySet().iterator();
		while(iterator1.hasNext()) {
			Map.Entry<Track, ArrayList<Athlete>> it1 = iterator1.next();
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
		
		numOfTracks--;
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
			rel.put(t, new ArrayList<Athlete>());
			tracks.add(t);
		}
		numOfTracks++;
	}
	
	/**
	 * 两名运动员互换轨道
	 * @param ath1
	 * @param ath2
	 */
	public void change(Athlete ath1, Athlete ath2) {
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

		Athlete newAth1 = new Athlete(ath2.getName(), ath2.getNum(), ath2.getNation(), ath2.getAge(), ath2.getGrade());
		Athlete newAth2 = new Athlete(ath1.getName(), ath1.getNum(), ath1.getNation(), ath1.getAge(), ath1.getGrade());
		
		
		rel2.put(newAth1, temp1);
		rel2.put(newAth2, temp2);
	
//		newAth1.setTrack(temp1);
//		newAth2.setTrack(temp2);

		Iterator<Map.Entry<Track, ArrayList<Athlete>>> iterator = rel.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Track, ArrayList<Athlete>> it = iterator.next();

			if (it.getKey().equals(temp1) && it.getKey().equals(temp2)) {
				ath = it.getValue();
				for (int index = 0; index < ath.size(); index++) {
					if (ath.get(index).equals(ath1)) {// bolt
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
					if (ath.get(index).equals(ath1)) {// bolt
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
		} catch (Exception e) {
			System.out.println(e);
		}

		br.close();
	}

	private void athleteAdd(String read) {
		word name = null;
		int num = 0;
		String nation = null;
		int age = 0;
		double grade = 0;

		String deal_read = null;

		String regex = "[\\<]([\\.A-Za-z0-9\\s\\,]+)[\\>]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(read);
		if (m.find()) {
			deal_read = m.group(1);
		}
		if (deal_read == null) {
			System.out.println("Read File Error!");
			System.exit(0);
		}
		String[] split = deal_read.split(",");

		String regex_1 = "[\\s]?([\\.A-Za-z0-9]+)[\\s]?";
		Pattern p_1 = Pattern.compile(regex_1);
		String[] str = new String[5];

		try {
			for (int i = 0; i < split.length; i++) {
				Matcher m_1 = p_1.matcher(split[i]);
				if (m_1.find()) {
					str[i] = m_1.group(1);
				}
			}

			name = new word(str[0]);
			num = Integer.parseInt(str[1], 10);
			nation = str[2];
			age = Integer.parseInt(str[3], 10);
			grade = Double.parseDouble(str[4]);

		} catch (Exception e) {
			System.out.println("Read File Error!");
			System.out.println(e);
		}

		this.objects.add(new Athlete(name, num, nation, age, grade));
	}

	private void gameTypeSet(String read) {
		int game = 0;
		String deal_read = null;
		String regex = "[0-9]+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(read);

		if (m.find()) {
			deal_read = m.group();
		}

		game = Integer.parseInt(deal_read, 10);
		this.game = game;
	}

	private void gameTracksSet(String read) {
		int numOfTracks = 0;
		String deal_read = null;
		String regex = "[0-9]+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(read);

		if (m.find()) {
			deal_read = m.group();
		}

		numOfTracks = Integer.parseInt(deal_read, 10);
		this.numOfTracks = numOfTracks;

		// 轨道编号从1号开始
		for (int i = 1; i <= numOfTracks; i++) {
			addTrack(new Track(i));
		}
	}

	/**
	 * 打印
	 */
	public void showResult() {
		int counterTrack = 1;
		
		
		while(counterTrack <= numOfTracks) {
			System.out.print(tracks.get(counterTrack - 1).getNum()+ ":\t" );
			Iterator<Entry<Track, ArrayList<Athlete>>> iterator = rel.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<Track, ArrayList<Athlete>> it = iterator.next();
				if(it.getKey().getNum() == tracks.get(counterTrack - 1).getNum()) {
					for(int i = 0; i < it.getValue().size(); i++) {
						if(it.getValue().get(i).getName().toString().length() >= 8) {
							System.out.print(it.getValue().get(i).getName() + "\t");
						}else {
							System.out.print(it.getValue().get(i).getName() + "\t\t");
						}
						
					}
					
				}
			}
			System.out.print("\n");
			counterTrack++;
		}


	}
	public String GUIshowResult() {
		StringBuffer s = new StringBuffer();
		int counterTrack = 1;
		while(counterTrack <= numOfTracks) {
			s.append(tracks.get(counterTrack - 1).getNum()+ ":\t");
			Iterator<Entry<Track, ArrayList<Athlete>>> iterator = rel.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<Track, ArrayList<Athlete>> it = iterator.next();
				if(it.getKey().getNum() == tracks.get(counterTrack - 1).getNum()) {
					for(int i = 0; i < it.getValue().size(); i++) {				
						if(it.getValue().get(i).getName().toString().length() > 10) {
							s.append(it.getValue().get(i).getName() + "\t");
						}else {
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

}
