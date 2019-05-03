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

import APIs.CircularOrbitAPIs;
import centralObject.Person;
import circularOrbit.ConcreteCircularOrbit;
import circularOrbit.label;
import physicalObject.People;
import track.Track;

public class SocialNetworkCircle extends ConcreteCircularOrbit<Person, People>{
	
	public double[][] distance;
	public List<SocialNetworkCircleTie> ties = new ArrayList<SocialNetworkCircleTie>();
	public List<People> removeObjects = new ArrayList<People>();
	public int numOfTracks;
	
	
	
	public void addAPeople(label name, int age, char gender) {
		for(int i = 0; i < removeObjects.size(); i++) {
			if(!objects.contains(removeObjects.get(i))) {
				objects.add(removeObjects.get(i));
			}else {
				//do nothing
			}
		}
		
		People p = new People(central.getName(), central.getAge(), central.getGender());
		objects.add(p);
		People p1 = new People(name, age, gender);
		objects.add(p1);
		distance = new double[objects.size() + removeObjects.size() + 1][objects.size() + removeObjects.size() + 1];
		cleanDistance();
		rel.clear();
		tracks.clear();
		relation.clear();
		buildSystem();
	}
	
	
	public int getDistance(label name1, label name2) {
		CircularOrbitAPIs<Person, People> api = new CircularOrbitAPIs<Person, People>();
		People e1 = getCertainFriend(name1);
		People e2 = getCertainFriend(name2);
		int n = api.getLogicalDistance(this, e1 , e2);
		return n;
	}
	
	
	
	
	private People getCertainFriend(label str) {
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getName().equals(str)) {
				return objects.get(i);
			}
		}
		return null;
	}
	
	/**
	 * 增加一条关系后重构
	 * @param name1 关系起始
	 * @param name2 关系结束
	 * @param close 亲密度
	 */
	public void addTie(label name1, label name2, double close) {
		
		for(int i = 0; i < removeObjects.size(); i++) {
			if(!objects.contains(removeObjects.get(i))) {
				objects.add(removeObjects.get(i));
			}else {
				//do nothing
			}
		}
		People p = new People(central.getName(), central.getAge(), central.getGender());
		objects.add(p);
		distance = new double[objects.size()+1][objects.size()+1];
		cleanDistance();
		rel.clear();
		tracks.clear();
		SocialNetworkCircleTie t = new SocialNetworkCircleTie(name1, name2, close);
		ties.add(t);
		relation.clear();
		buildSystem();
		//向objects中加中间物体
		//buildSystem
	}
	/**
	 * 删除一条关系后重构
	 * @param name1 关系起始
	 * @param name2	关系结束
	 */
	public void deleteTie(label name1, label name2) {
		
		for(int i = 0; i < removeObjects.size(); i++) {
			if(!objects.contains(removeObjects.get(i))) {
				objects.add(removeObjects.get(i));
			}else {
				//do nothing
			}
		}
		People p = new People(central.getName(), central.getAge(), central.getGender());
		objects.add(p);
		distance = new double[objects.size()+1][objects.size()+1];
		cleanDistance();
		Iterator<SocialNetworkCircleTie> iterator = ties.iterator();
		while (iterator.hasNext()) {
			SocialNetworkCircleTie it = iterator.next();
			if(it.getName1().equals(name1) && it.getName2().equals(name2)||
					it.getName1().equals(name2) && it.getName2().equals(name1)) {
				iterator.remove();
			}
		}
		rel.clear();
		tracks.clear();
		relation.clear();
		buildSystem();
		//向objects中加中间物体
		//将二维数组那个位置设置为0
		//buildSystem
	}
	
	private void cleanDistance() {
		for(int i = 0; i < objects.size(); i++) {
			for(int j = 0; j < objects.size(); j++) {
				distance[i][j] = 0;
			}
		}
	}
	
	/**
	 * 计算信息扩散度
	 * @param 提供一个位于第一个轨道上的人的名字
	 * @return 信息扩散度 定义等于其链接的其他人的亲密度的平方和
	 */
	public double getCloseLevel(label name) {
		int index = 0;
		int centralIndex = 0;
		double res = 0;
		
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getName().equals(name)) {
				index = i;
			}
			if(objects.get(i).getName().equals(central.getName())) {
				centralIndex = i;
			}
		}
		
		for(int i = 0; i < objects.size(); i++) {
			if(i != centralIndex) {
				if(distance[index][i] != 0) {
					res += Math.pow(distance[index][i], 2);
				}
			}else {
				//do nothing
			}
		}
		
		return res;
	}
	
	/**
	 * 判定某人处于哪个轨道上
	 * @param str 提供某个人的名字
	 * @return
	 */
	public int getPeopleTrack(label str) {
		Iterator<Map.Entry<Track, ArrayList<People>>> iterator = rel.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Track, ArrayList<People>> it = iterator.next();
			if(it.getValue().contains(new People(str, 0, 'F'))) {
				return it.getKey().getNum();
			}
		}
		//can't get here
		return -1;
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
				if (read.contains("CentralUser")) {
					centralUserSet(read);
				}

				if (read.contains("SocialTie")) {
					socialTieSet(read);
				}

				if (read.contains("Friend")) {
					friendSet(read);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		br.close();
		
		//初始化数组
		distance = new double[objects.size() + 1][objects.size() + 1];
		
		buildSystem();
		
	}
	
	private void buildSystem() {
		
		for(int i = 0; i < ties.size(); i++) {
			label name1 = ties.get(i).getName1();
			label name2 = ties.get(i).getName2();
			
			int index1 = getFriendIndex(name1);
			int index2 = getFriendIndex(name2);
			
			distance[index1][index2] = ties.get(i).getClose();
			distance[index2][index1] = ties.get(i).getClose();
		}
		
		//轨道编号
		int[] trackNum = new int[objects.size() + 1];
		//记录搜索历史
		int[] objectNum = new int[objects.size() + 1];

		//标记数组
		boolean[] flag = new boolean[objects.size() + 1];
		for(int i = 0; i < flag.length; i++) {
			flag[i] = false;
		}
		
		
		int[] que = new int[objects.size() + 1];
		int front = 0;
		int rear = 0;
		
		int index = getFriendIndex(central.getName());
		flag[index] = true;
		trackNum[rear] = 0;
		que[rear] = index;
		rear++;
		
		while(front < rear) {
			int start = que[front];
			objectNum[front] = start;
			
			for(int i = 0; i < objects.size(); i++) {
				if(distance[start][i] != 0 && flag[i] == false) {
					flag[i] = true;
					trackNum[rear] = trackNum[front] + 1;
					que[rear] = i;
					rear++;
				}
			}
			front++;
		}
		
		int numOfTracks = trackNum[front - 1];
		for(int i = 1; i <= numOfTracks; i++) {
			addTrack(new Track(i));
		}
		
		for(int i = 1; i <= front - 1; i++) {
			addObjectToTrack(getCertainTrack(trackNum[i]), objects.get(objectNum[i]));
		}
		
		List<People> objectsNew = new ArrayList<People>();
		Iterator<People> it = objects.iterator();
		removeObjects.clear();
		while (it.hasNext()) {
			People p = it.next();
			if(flag[getFriendIndex(p.getName())] == false) {
				removeObjects.add(p);
			}else {
				objectsNew.add(p);
			}
		}
		objects = objectsNew;
		
		People p = new People(central.getName(),central.getAge(),central.getGender());
		objects.remove(p);
		setRelation();
		
		this.numOfTracks = tracks.size();
	}
	
	private int getFriendIndex(label name) {
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
		//can't get here
	}
	
	private void centralUserSet(String str) {
		char gender = 'F';
		label name = null;
		int age = 0;
		String deal_read = null;

		String regex = "[\\<]([A-Za-z0-9\\s\\,]+)[\\>]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if (m.find()) {
			deal_read = m.group(1);
		}
		if (deal_read == null) {
			System.out.println("Read File Error!");
			System.exit(0);
		}
		String[] split = deal_read.split(",");

		String regex_1 = "[\\s]?([A-Za-z0-9]+)[\\s]?";
		Pattern p_1 = Pattern.compile(regex_1);
		String[] strList = new String[3];

		try {
			for (int i = 0; i < split.length; i++) {
				Matcher m_1 = p_1.matcher(split[i]);
				if (m_1.find()) {
					strList[i] = m_1.group(1);
				}
			}

			name = new label(strList[0]);
			age = Integer.parseInt(strList[1], 10);
			gender = strList[2].charAt(0);
			if(strList[2].length() != 1) {
				System.out.println("Read File Error!");
				System.exit(0);
			}
			
		} catch (Exception e) {
			System.out.println("Read File Error!");
			System.out.println(e);
		}
		
		Person central = new Person(name, age, gender);
		People person = new People(name, age, gender);
		objects.add(person);
		setCentralObject(central);

	}
	
	private void socialTieSet(String str) {
		
		label name1 = null;
		label name2 = null;
		double close = 0;
		
		String deal_read = null;

		String regex = "[\\<]([\\.A-Za-z0-9\\s\\,]+)[\\>]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
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
		String[] strList = new String[3];

		try {
			for (int i = 0; i < split.length; i++) {
				Matcher m_1 = p_1.matcher(split[i]);
				if (m_1.find()) {
					strList[i] = m_1.group(1);
				}
			}

			name1 = new label(strList[0]);
			name2 = new label(strList[1]);
			close = Double.parseDouble(strList[2]);
		} catch (Exception e) {
			System.out.println("Read File Error!");
			System.out.println(e);
		}
		
		SocialNetworkCircleTie tie = new SocialNetworkCircleTie(name1, name2, close);
		ties.add(tie);
	}

	private void friendSet(String str) {
		char gender = 'F';
		label name = null;
		int age = 0;
		String deal_read = null;

		String regex = "[\\<]([A-Za-z0-9\\s\\,]+)[\\>]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if (m.find()) {
			deal_read = m.group(1);
		}
		if (deal_read == null) {
			System.out.println("Read File Error!");
			System.exit(0);
		}
		String[] split = deal_read.split(",");

		String regex_1 = "[\\s]?([A-Za-z0-9]+)[\\s]?";
		Pattern p_1 = Pattern.compile(regex_1);
		String[] strList = new String[3];

		try {
			for (int i = 0; i < split.length; i++) {
				Matcher m_1 = p_1.matcher(split[i]);
				if (m_1.find()) {
					strList[i] = m_1.group(1);
				}
			}

			name = new label(strList[0]);
			age = Integer.parseInt(strList[1], 10);
			gender = strList[2].charAt(0);
			if(strList[2].length() != 1) {
				System.out.println("Read File Error!");
				System.exit(0);
			}
			
		} catch (Exception e) {
			System.out.println("Read File Error!");
			System.out.println(e);
		}
		
		People person = new People(name, age, gender);
		objects.add(person);
	}

	public String GUIshowResult() {
		StringBuffer s = new StringBuffer();
		int counterTrack = 1;
		while(counterTrack <= numOfTracks) {
			s.append(tracks.get(counterTrack - 1).getNum()+ ":\t");
			Iterator<Entry<Track, ArrayList<People>>> iterator = rel.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<Track, ArrayList<People>> it = iterator.next();
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
	
	public void showResult() {
		int counterTrack = 1;
		while(counterTrack <= numOfTracks) {
			System.out.print(tracks.get(counterTrack - 1).getNum()+ ":\t" );
			Iterator<Entry<Track, ArrayList<People>>> iterator = rel.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<Track, ArrayList<People>> it = iterator.next();
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

	public String GUIremoveObjects() {
		StringBuffer s = new StringBuffer();
		for(int i = 0; i < removeObjects.size(); i++) {
			s.append(removeObjects.get(i).getName().toString() + "\t");
		}
		return s.toString();
	}

	private void setRelation() {
		for(int i = 0; i < ties.size(); i++) {
			label name1 = ties.get(i).getName1();
			label name2 = ties.get(i).getName2();
			
			if(getPeopleTrack(name1) >= 1 && getPeopleTrack(name2) >= 1) {
				People p1 = getCertainFriend(name1);
				People p2 = getCertainFriend(name2);
				
				if(relation.get(p1) == null) {
					List<People> arry = new ArrayList<People>();
					relation.put(p1, arry);
				}
				
				if(relation.get(p2) == null) {
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
	
	public String GUIties() {
		StringBuffer s = new StringBuffer();
		for(int i = 0; i < ties.size(); i++) {
			s.append(ties.get(i).getName1().toString() + "\t\t<-->\t" + ties.get(i).getName2().toString() + "\t\tclose level:" +
		String.valueOf(ties.get(i).getClose()));
			s.append("\n");
		}
		return s.toString();
	}
}
