package myTest;

import java.io.File;
import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import applications.SocialNetworkCircle;
import circularOrbit.label;
import exception.MyException;
import track.Track;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SocialTest {

	SocialNetworkCircle client = new SocialNetworkCircle();
	
	@Test(expected = MyException.class)
	public void Test1() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/SocialNetworkCircleAge.txt");
		client = new SocialNetworkCircle();
		client.readFile(f);
	}
	
	@Test(expected = MyException.class)
	public void Test2() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/SocialNetworkCircleCentralAge.txt");
		client = new SocialNetworkCircle();
		client.readFile(f);
	}
	
	@Test(expected = MyException.class)
	public void Test3() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/SocialNetworkCircleCentralGender.txt");
		client = new SocialNetworkCircle();
		client.readFile(f);
	}
	
	@Test(expected = MyException.class)
	public void Test4() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/SocialNetworkCircleDecimal.txt");
		client = new SocialNetworkCircle();
		client.readFile(f);
	}
	
	@Test(expected = MyException.class)
	public void Test5() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/SocialNetworkCircleGender.txt");
		client = new SocialNetworkCircle();
		client.readFile(f);
	}
	
	@Test(expected = MyException.class)
	public void Test6() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/SocialNetworkCircleRel.txt");
		client = new SocialNetworkCircle();
		client.readFile(f);
	}
	
	@Test(expected = MyException.class)
	public void Test7() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/SocialNetworkCircleTieSame.txt");
		client = new SocialNetworkCircle();
		client.readFile(f);
	}
	
	@Test
	public void Test8() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/SocialNetworkCircle.txt");
		client = new SocialNetworkCircle();
		client.readFile(f);
		
		System.out.println(client.getCloseLevel(new label("DavidChen")));
		System.out.println(client.getDistance(new label("DavidChen"), new label("TomWong")));
		client.deleteTie(new label("FrankLee"), new label("TomWong"));
		client.deleteTie(new label("FrankLee"), new label("DavidChen"));	
		client.addTie(new label("FrankLee"), new label("PonyMa"), 0.1);		
		client.deleteTie(new label("JackMa"), new label("PonyMa"));
		client.showResult();
		client.addAPeople(new label("Lzy"), 20, 'F');
		client.addAPeople(new label("Lc"), 20, 'M');
		client.addTie(new label("Lzy"), new label("Lc"), 0.5);
		client.addTie(new label("Lzy"), new label("TomWong"), 0.5);
		
		client.showResult();
		client.GUIshowResult();
		client.GUIremoveObjects();
		client.GUIties();
	}
	
	@Test(expected = AssertionError.class)
	public void Test9() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/SocialNetworkCircle.txt");
		client = new SocialNetworkCircle();
		client.readFile(f);
		client.getDistance(new label("asdf"), new label("LisaWong"));
		
	}
	
	@Test(expected = AssertionError.class)
	public void Test10() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/SocialNetworkCircle.txt");
		client = new SocialNetworkCircle();
		client.readFile(f);
		client.addTie(new label("TomWong"), new label("asdf"), 0.1);
	}
}
