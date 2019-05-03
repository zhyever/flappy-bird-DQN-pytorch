package applications;

import java.io.IOException;
import java.util.ArrayList;

public class AtomStructureCaretaker {
	private ArrayList<AtomStructureMemento> memento;
	private int size;
	
	public AtomStructureCaretaker() {
		size = 0;
		memento = new ArrayList<AtomStructureMemento>();
	}
	
	public void set(AtomStructureMemento m) throws ClassNotFoundException, IOException {
		System.out.println("Save version: " + size);
		AtomStructureMemento mCopy = new AtomStructureMemento(m.getRel(), m.getObjects(), m.getTracks(), m.getNumTracks());
		this.memento.add(mCopy);
		size++;
	}
	
	public AtomStructureMemento get(int index) throws ClassNotFoundException, IOException {
		AtomStructureMemento mCopy = new AtomStructureMemento(memento.get(index).getRel()
				, memento.get(index).getObjects(), memento.get(index).getTracks(), memento.get(index).getNumTracks());
		return mCopy;
	}
	
	public int getSize() {
		return this.size;
	}
}
