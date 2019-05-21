package APIs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import applications.AtomStructure;
import applications.SocialNetworkCircle;
import applications.TrackGame;
import circularOrbit.ConcreteCircularOrbit;
import physicalObject.Athlete;
import physicalObject.Electron;
import physicalObject.People;
import track.Track;

/**
 * 定义一个继承自JPanel的类，重写它的paint方法 *
 */
class MyPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private ConcreteCircularOrbit system;
	
	public MyPanel(@SuppressWarnings("rawtypes") ConcreteCircularOrbit system) {
		this.system = system;
	}
	
	@SuppressWarnings("unchecked")
	public void paint(Graphics g)
	{
		super.paint(g);	

		int numOfTrack = system.getTrackNum();
		g.setColor(Color.red);
		g.drawOval(300, 300, 0, 0);
		g.drawOval(299, 299, 2, 2);
		g.drawOval(298, 298, 4, 4);
		
		
		//设置轨道
		g.setColor(Color.black);
		for(int i = 1; i <= numOfTrack; i++) {
			g.drawOval(300 - 50*i, 300 - 50*i, 100*i, 100*i);
			//300 300 0 0 中心点
			//半径 100/200/300 递增
			//左上角坐标 250,250 / 200,200 / 150,150 递减
		}
		
		//设置物体
		
		int indexTrack = 0;
		int objectsNumOnTrack = 0;
		g.setColor(Color.red);
		if(system instanceof TrackGame) {
			Collections.sort(system.tracks, new Comparator<Track>() {

				@Override
				public int compare(Track o1, Track o2) {
					int num1 = (int) (o1.getNum() * 100);
					int num2 = (int) (o2.getNum() * 100);
					
					return num1 - num2;
				}
	 			
			});
			
			for(int i = 0; i < system.getTrackNum(); i++) {
				Track t = ((TrackGame) system).tracks.get(indexTrack);
				indexTrack++;
				ArrayList<Athlete> aths = ((TrackGame) system).rel.get(t);
				objectsNumOnTrack = aths.size();
				double angle = 360 / (double) objectsNumOnTrack;
				for(int j = 0; j < aths.size(); j++) {
					//左上角 300-50*indexTrack , 300-50*indexTrack
					//中心 300, 300 //R = 50*indexTrack
					//paint at 300 + 50*indexTrack*cos(angle*j) , 300 + 50*indexTrack*sin(angle*j)
					g.drawOval((int) (300 + 50*indexTrack*Math.cos(angle*j/180 * Math.PI)), 
							(int) (300 + 50*indexTrack*Math.sin(angle*j/180 * Math.PI)), 0, 0);
					g.drawOval((int) (300 + 50*indexTrack*Math.cos(angle*j/180 * Math.PI) - 1), 
							(int) (300 + 50*indexTrack*Math.sin(angle*j/180 * Math.PI) - 1), 2, 2);
					g.drawOval((int) (300 + 50*indexTrack*Math.cos(angle*j/180 * Math.PI) - 2), 
							(int) (300 + 50*indexTrack*Math.sin(angle*j/180 * Math.PI) - 2), 4, 4);				
				}
			}
			
			
			
			
		}
		
		if(system instanceof AtomStructure) {
			
			Collections.sort(system.tracks, new Comparator<Track>() {

				@Override
				public int compare(Track o1, Track o2) {
					int num1 = (int) (o1.getNum() * 100);
					int num2 = (int) (o2.getNum() * 100);
					
					return num1 - num2;
				}
	 			
			});
			
			for(int i = 0; i < system.getTrackNum(); i++) {
				Track t = ((AtomStructure) system).tracks.get(indexTrack);
				indexTrack++;
				ArrayList<Electron> aths = ((AtomStructure) system).rel.get(t);
				objectsNumOnTrack = aths.size();
				double angle = 360 / (double) objectsNumOnTrack;
				for(int j = 0; j < aths.size(); j++) {
					//左上角 300-50*indexTrack , 300-50*indexTrack
					//中心 300, 300 //R = 50*indexTrack
					//paint at 300 + 50*indexTrack*cos(angle*j) , 300 + 50*indexTrack*sin(angle*j)
					g.drawOval((int) (300 + 50*indexTrack*Math.cos(angle*j/180 * Math.PI)), 
							(int) (300 + 50*indexTrack*Math.sin(angle*j/180 * Math.PI)), 0, 0);
					g.drawOval((int) (300 + 50*indexTrack*Math.cos(angle*j/180 * Math.PI) - 1), 
							(int) (300 + 50*indexTrack*Math.sin(angle*j/180 * Math.PI) - 1), 2, 2);
					g.drawOval((int) (300 + 50*indexTrack*Math.cos(angle*j/180 * Math.PI) - 2), 
							(int) (300 + 50*indexTrack*Math.sin(angle*j/180 * Math.PI) - 2), 4, 4);				
				}
			}
			
		} 
		
		if(system instanceof SocialNetworkCircle) {
			
			Collections.sort(system.tracks, new Comparator<Track>() {

				@Override
				public int compare(Track o1, Track o2) {
					int num1 = (int) (o1.getNum() * 100);
					int num2 = (int) (o2.getNum() * 100);
					
					return num1 - num2;
				}
	 			
			});
			
			for(int i = 0; i < system.getTrackNum(); i++) {
				Track t = ((SocialNetworkCircle) system).tracks.get(indexTrack);
				indexTrack++;
				ArrayList<People> aths = ((SocialNetworkCircle) system).rel.get(t);
				objectsNumOnTrack = aths.size();
				double angle = 360 / (double) objectsNumOnTrack;
				for(int j = 0; j < aths.size(); j++) {
					//左上角 300-50*indexTrack , 300-50*indexTrack
					//中心 300, 300 //R = 50*indexTrack
					//paint at 300 + 50*indexTrack*cos(angle*j) , 300 + 50*indexTrack*sin(angle*j)
					g.drawOval((int) (300 + 50*indexTrack*Math.cos(angle*j/180 * Math.PI)), 
							(int) (300 + 50*indexTrack*Math.sin(angle*j/180 * Math.PI)), 0, 0);
					g.drawOval((int) (300 + 50*indexTrack*Math.cos(angle*j/180 * Math.PI) - 1), 
							(int) (300 + 50*indexTrack*Math.sin(angle*j/180 * Math.PI) - 1), 2, 2);
					g.drawOval((int) (300 + 50*indexTrack*Math.cos(angle*j/180 * Math.PI) - 2), 
							(int) (300 + 50*indexTrack*Math.sin(angle*j/180 * Math.PI) - 2), 4, 4);				
				}
			}
			
		}
		
		
		//绘制relation
		int counterColor = 1;
		if(system instanceof SocialNetworkCircle) {
			//遍历图
			Iterator<Map.Entry<People, List<People>>> iterator = ((SocialNetworkCircle) system).relation.entrySet().iterator();
			while(iterator.hasNext()) {
				Map.Entry<People, List<People>> it = iterator.next();
				People start = it.getKey();
				for(int i = 0; i < it.getValue().size(); i++) {
					People end = it.getValue().get(i);
					//需要找到这两个的轨道
					Track t1 = null;
					Track t2 = null;
					
					double angle1 = 0;
					double angle2 = 0;
					
					int j1 = 0;
					int j2 = 0;
					
					Iterator<Map.Entry<Track, ArrayList<People>>> iterator1 = ((SocialNetworkCircle) system).rel.entrySet().iterator();
					while(iterator1.hasNext()) {
						Map.Entry<Track, ArrayList<People>> it1 = iterator1.next();
						if(it1.getValue().contains(start)) {
							t1 = it1.getKey();
							angle1 = 360 / (double) it1.getValue().size();
							for(int j = 0; j < it1.getValue().size(); j++) {
								if(it1.getValue().get(j).equals(start)) {
									j1 = j;
								}
							}
						}
						
						if(it1.getValue().contains(end)) {
							t2 = it1.getKey();
							angle2 = 360 / (double) it1.getValue().size();
							for(int j = 0; j < it1.getValue().size(); j++) {
								if(it1.getValue().get(j).equals(end)) {
									j2 = j;
								}
							}
						}
					}
					
					int indexTrack1 = 0;
					int indexTrack2 = 0;
					
					for(int k = 0; k < system.tracks.size(); k++) {
						if(system.tracks.get(k).equals(t1)) {
							indexTrack1 = k + 1;
						}
						
						if(system.tracks.get(k).equals(t2)) {
							indexTrack2 = k + 1;
						}
					}
					
					if(counterColor % 4 == 0) {
						g.setColor(Color.BLACK);
					}else if(counterColor % 4 == 1) {
						g.setColor(Color.BLUE);
					}else if(counterColor % 4 == 2) {
						g.setColor(Color.GREEN);
					}else {
						g.setColor(Color.RED);
						counterColor = 0;
					}
					g.drawLine((int) (300 + 50*indexTrack1*Math.cos(angle1*j1/180 * Math.PI)), 
							(int) (300 + 50*indexTrack1*Math.sin(angle1*j1/180 * Math.PI)),
							(int) (300 + 50*indexTrack2*Math.cos(angle2*j2/180 * Math.PI)), 
							(int) (300 + 50*indexTrack2*Math.sin(angle2*j2/180 * Math.PI)));
					counterColor++;
				}
			}
		}
		
		
	}
}
 
public class PanelTest
{
	public static void start(@SuppressWarnings("rawtypes") ConcreteCircularOrbit system)
	{
		JFrame  jf = new JFrame();
		MyPanel jp = new MyPanel(system);
		
		jf.setBounds(100, 100, 600, 600);
		//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(jp);
		jf.setVisible(true);
		
		
	}

}