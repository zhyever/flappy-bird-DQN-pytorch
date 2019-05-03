package applications;

import java.io.File;
import java.io.IOException;

import APIs.PanelTest;

import circularOrbit.label;


public class SocialNetworkCircleClient {
	public static void main(String[] args) throws IOException {
		File f = new File("src/circularOrbit/test/SocialNetworkCircle.txt");
		//_Medium
		SocialNetworkCircle client = new SocialNetworkCircle();
		client.readFile(f);
		
		client.showResult();
		
		client.addTie(new label("FrankLee"), new label("PonyMa"), 0.1);
		client.showResult();
		
		client.deleteTie(new label("JackMa"), new label("PonyMa"));
		client.showResult();
		client.addAPeople(new label("Lzy"), 20, 'F');
		client.addAPeople(new label("Lc"), 20, 'M');
		
		client.addTie(new label("Lzy"), new label("Lc"), 0.5);
		client.showResult();
		
		client.addAPeople(new label("Lzy"), 20, 'M');
		client.showResult();
		
		client.addTie(new label("Lzy"), new label("TomWong"), 0.5);
		client.showResult();
		
		PanelTest.start(client);
//		MyIterator<Person, People> it = client.iterator();
//		while (it.hasNext()) {
//			People p = it.next();
//			System.out.print(p.getName() + "\n");
//		}
//		
//		System.out.println(client.getPeopleTrack(new label("FrankLee")));
//		System.out.println(client.getCloseLevel(new label("DavidChen")));
//		
//		int n = client.getDistance(new label("FrankLee"), new label("TomWong"));
//		System.out.println(n);
//		
//		n = client.getDistance(new label("DavidChen"), new label("TomWong"));
//		System.out.println(n);
//		
//		client.addTie(new label("FrankLee"), new label("PonyMa"), 0.1);
//		MyIterator<Person, People> it1 = client.iterator();
//		while (it1.hasNext()) {
//			People p = it1.next();
//			System.out.print(p.getName() + "\n");
//		}
//		System.out.print("\n");
//		
//		n = client.getDistance(new label("DavidChen"), new label("JackMa"));
//		System.out.println(n);
//		
//		CircularOrbitAPIs<Person, People> api = new CircularOrbitAPIs<Person, People>();
//		double m = api.getObjectDistributionEntropy(client);
//		System.out.println(m);
//		
//		client.getObjects().get(1).setR(new number(10));
//		client.getObjects().get(1).setSitha(30);
//		
//		client.getObjects().get(2).setR(new number(10));
//		client.getObjects().get(2).setSitha(90);
//		
//		m = api.getPhysicalDistance(client, client.getObjects().get(1), client.getObjects().get(2));
//		System.out.println(m);
//		
//		
//		m = api.getLogicalDistance(client, client.getObjects().get(0), client.getObjects().get(2));
//		System.out.println(m);
//		
//		File f1 = new File("src/circularOrbit/test/SocialNetworkCircle.txt");
//		//_Medium
//		SocialNetworkCircle client1 = new SocialNetworkCircle();
//		client1.readFile(f1);
//		Difference<People> d = new Difference<People>();
//		d = api.getDifference(client, client1);
//		System.out.println(d.toString());
//		
//		d = api.getDifference(client1, client);
//		System.out.println(d.toString());
//		
//		client.deleteTie(new label("TommyWong"), new label("LisaWong"));
//		d = api.getDifference(client1, client);
//		System.out.println(d.toString());
	}
}
