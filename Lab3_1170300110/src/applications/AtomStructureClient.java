package applications;

import java.io.File;
import java.io.IOException;

import APIs.CircularOrbitAPIs;
import APIs.PanelTest;
import centralObject.CentralAtom;
import physicalObject.Electron;


public class AtomStructureClient {
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		File f = new File("src/circularOrbit/test/AtomicStructure.txt");
		AtomStructure test = new AtomStructure();
		
		CircularOrbitAPIs<CentralAtom, Electron> api = new CircularOrbitAPIs<CentralAtom, Electron>();

		test.readFile(f);
		test.showResult();
		
		AtomStructureCaretaker caretaker = new AtomStructureCaretaker();
		caretaker.set(test.save());
		
		test.tranAll(2, 3);
		test.showResult();
		
		System.out.println(api.getObjectDistributionEntropy(test));
		
//		Track t = new Track(6);
//		test.addNewTrack(t);
//		test.showResult();
//		caretaker.set(test.save());
//		
//		test.recover(caretaker.get(0));
//		test.showResult();
		
		
		
		PanelTest.start(test);
	}
}
