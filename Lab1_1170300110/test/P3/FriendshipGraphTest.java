package P3;
import P3.Person;


import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import P3.FriendshipGraph;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FriendshipGraphTest {
	
	Person A = new Person("A");
	Person B = new Person("B");
	Person C = new Person("C");
	Person D = new Person("D");
	Person E = new Person("E");
	
	FriendshipGraph FriendshipGraph = new FriendshipGraph();
	@Test
	public void Test1_addVertex()
	{
		
//		System.out.println(FriendshipGraph.per[1].getname());
		assertEquals(true,FriendshipGraph.addVertex(A));
		assertEquals(true,FriendshipGraph.addVertex(B));
		assertEquals(true,FriendshipGraph.addVertex(C));
		assertEquals(true,FriendshipGraph.addVertex(D));
		assertEquals(true,FriendshipGraph.addVertex(E));
	}
	
	@Test
	public void Test2_addEdge()
	{
		
		assertEquals(true,FriendshipGraph.addEdge(B,A));
		assertEquals(true,FriendshipGraph.addEdge(A,C));
		assertEquals(true,FriendshipGraph.addEdge(C,A));
		assertEquals(true,FriendshipGraph.addEdge(E,D));
		
	}
	
	@Test
	public void Test3_getDistance()
	{
		
		assertEquals(1,FriendshipGraph.getDistance(A,C));
		assertEquals(-1,FriendshipGraph.getDistance(B,E));
		assertEquals(2,FriendshipGraph.getDistance(B,C));
		assertEquals(0,FriendshipGraph.getDistance(A,A));
	}


}
