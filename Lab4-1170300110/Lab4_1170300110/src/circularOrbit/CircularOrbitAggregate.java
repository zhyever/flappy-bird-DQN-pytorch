package circularOrbit;

//import java.util.Iterator;

public interface CircularOrbitAggregate<L, E> {
	 /**
     * 返回当前集合的迭代器
     *
     * @return 当前集合的迭代器
     */
    MyIterator<L, E> iterator();
}
