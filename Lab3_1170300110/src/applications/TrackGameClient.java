package applications;

import java.io.File;
import java.io.IOException;


import APIs.PanelTest;



public class TrackGameClient {
	public static void main(String[] args) throws IOException {

		File f = new File("src/circularOrbit/test/TrackGame.txt");
		TrackGameContext client1 = new TrackGameContext();

		TrackGame trackgame1 = new TrackGameSorted();
		trackgame1.readFile(f);

		client1.run(trackgame1);

		client1.game.showResult();

		//client1.game.addNewTrack(new Track(6));
		
		client1.game.delATrack(5);
		client1.game.showResult();
		
		
		client1.game.delATrack(2);
		client1.game.showResult();

		PanelTest.start(client1.game);
		
	}
}
