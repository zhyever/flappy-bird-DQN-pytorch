package myTest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import applications.TrackGame;
import applications.TrackGameContext;
import applications.TrackGameRandom;
import applications.TrackGameSorted;
import circularOrbit.word;
import exception.MyException;
import physicalObject.Athlete;
import track.Track;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TrackGameTest {
	//File f = new File("src/circularOrbit/test/TrackGameNameSame.txt");
	TrackGameContext clientSorted = new TrackGameContext();
	TrackGameContext clientRandom = new TrackGameContext();

	TrackGame trackgameSorted = new TrackGameSorted();
	TrackGame trackgameRandom = new TrackGameRandom();
	
	@Test(expected = MyException.class)
    public void Test1() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/TrackGameDecimal.txt");
		trackgameSorted.readFile(f);
	}
	
	@Test(expected = MyException.class)
    public void Test2() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/TrackGameLarger.txt");
		trackgameSorted.readFile(f);
	}
	
	@Test(expected = MyException.class)
    public void Test3() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/TrackGameNameSame.txt");
		trackgameSorted.readFile(f);
	}
	
	@Test(expected = MyException.class)
    public void Test4() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/TrackGameNum.txt");
		trackgameSorted.readFile(f);
	}
	
	@Test(expected = MyException.class)
    public void Test5() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/TrackGameOrder1.txt");
		trackgameSorted.readFile(f);
	}
	
	@Test(expected = MyException.class)
    public void Test6() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/TrackGameOrder2.txt");
		trackgameSorted.readFile(f);
	}
	
	@Test
	public void Test7() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/TrackGame.txt");
		trackgameSorted.readFile(f);
		trackgameRandom.readFile(f);
		clientSorted.run(trackgameSorted);
		clientRandom.run(trackgameRandom);
		clientSorted.game.showResult();
		
		clientSorted.game.change(clientSorted.game.objects.get(2), clientSorted.game.objects.get(3));
		clientSorted.game.change(clientSorted.game.objects.get(4), clientSorted.game.objects.get(9));
		clientSorted.game.addNewTrack(new Track(6));
		
		clientSorted.game.delATrack(6);
		clientSorted.game.addObjectToTrack(clientSorted.game.tracks.get(4), 
				new Athlete(new word("Lzy"), 50, "CHN", 20, 10.01));
		clientSorted.game.showResult();
		
		clientSorted.game.GUIshowResult();
	}
	
	@Test(expected = AssertionError.class)
    public void Test8() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/TrackGame.txt");
		trackgameSorted.readFile(f);
		clientSorted.run(trackgameSorted);
		
		clientSorted.game.addNewTrack(new Track(1));
	}
	
	@Test(expected = AssertionError.class)
    public void Test9() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/TrackGame.txt");
		trackgameSorted.readFile(f);
		clientSorted.run(trackgameSorted);
		
		clientSorted.game.delATrack(10);
	}
	
	@Test(expected = AssertionError.class)
    public void Test10() throws IOException, MyException {
		File f = new File("src/circularOrbit/test/TrackGame.txt");
		trackgameSorted.readFile(f);
		clientSorted.run(trackgameSorted);
		
		clientSorted.game.addObjectToTrack(new Track(10), new Athlete(new word("Lzy"), 50, "CHN", 20, 10.01));
	}
	
		

}
