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

  /**set saver.
   * 
   * @param m memento
   * @throws ClassNotFoundException exception
   * @throws IOException exception
   */
  public void set(AtomStructureMemento m) throws ClassNotFoundException, IOException {
    System.out.println("Save version: " + size);
    AtomStructureMemento copy = new AtomStructureMemento(m.getRel(), 
        m.getObjects(), m.getTracks(), m.getNumTracks());
    this.memento.add(copy);
    size++;
  }

  /**get saver.
   * 
   * @param index memento index
   * @return memento
   * @throws ClassNotFoundException exception
   * @throws IOException exception
   */

  public AtomStructureMemento get(int index) throws ClassNotFoundException, IOException {
    AtomStructureMemento copy = new AtomStructureMemento(memento.get(index).getRel(), 
        memento.get(index).getObjects(),
        memento.get(index).getTracks(), memento.get(index).getNumTracks());
    return copy;
  }

  public int getSize() {
    return this.size;
  }
}
