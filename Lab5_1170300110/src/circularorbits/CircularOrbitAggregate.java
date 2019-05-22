package circularorbits;

//import java.util.Iterator;

public interface CircularOrbitAggregate<L, E> {
  /** iterator.
   * 返回当前集合的迭代器
   *
   * @return 当前集合的迭代器
   */
  MyIterator<L, E> iterator();
}
