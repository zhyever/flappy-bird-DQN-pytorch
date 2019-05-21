package circularorbits;

import java.util.List;

import track.Track;

public class CircularOrbitIterator<L, E> implements MyIterator<L, E> {

  private ConcreteCircularOrbit<L, E> circularOrbit;

  private int counter;
  private int index;
  private int indexTrack;
  /**constructor.
   * 
   * @param circularOrbit orbit System
   */

  public CircularOrbitIterator(ConcreteCircularOrbit<L, E> circularOrbit) {
    this.circularOrbit = circularOrbit;
    indexTrack = 0;
    counter = 0;
    index = 0;
  }

  @Override
  public boolean hasNext() {
    return counter < circularOrbit.getObjectNum();
  }

  @Override
  public E next() {

    Track t = null;

    t = this.circularOrbit.tracks.get(indexTrack);
    List<E> arry = this.circularOrbit.rel.get(t);

    while (true) {
      if (arry.size() != 0) {
        break;
      } else {
        indexTrack++;
        t = this.circularOrbit.tracks.get(indexTrack);
        arry = this.circularOrbit.rel.get(t);
      }
    }

    if (index == arry.size()) {
      index = 0;
      indexTrack++;
      t = this.circularOrbit.tracks.get(indexTrack);
      arry = this.circularOrbit.rel.get(t);
      while (true) {
        if (arry.size() != 0) {
          break;
        } else {
          indexTrack++;
          t = this.circularOrbit.tracks.get(indexTrack);
          arry = this.circularOrbit.rel.get(t);
        }
      }
    }

    index++;
    counter++;
    return arry.get(index - 1);

  }

}
