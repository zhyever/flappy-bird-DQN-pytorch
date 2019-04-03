package P2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FriendshipGraphTest {
	Person A = new Person("A");
	Person B = new Person("B");
	Person C = new Person("C");
	Person D = new Person("D");
	Person E = new Person("E");

	FriendshipGraph FriendshipGraph = new FriendshipGraph();

	@Test
	public void Test1_addVertex() {

//		System.out.println(FriendshipGraph.per[1].getname());
		assertEquals(true, FriendshipGraph.addVertex(A));
		assertEquals(true, FriendshipGraph.addVertex(B));
		assertEquals(true, FriendshipGraph.addVertex(C));
		assertEquals(true, FriendshipGraph.addVertex(D));
		assertEquals(true, FriendshipGraph.addVertex(E));
	}

	@Test
	public void Test2_addEdge() {

		Person A = new Person("A");
		Person B = new Person("B");
		Person C = new Person("C");
		Person D = new Person("D");
		Person E = new Person("E");
		
		FriendshipGraph.addVertex(A);
		FriendshipGraph.addVertex(B);
		FriendshipGraph.addVertex(C);
		FriendshipGraph.addVertex(D);
		FriendshipGraph.addVertex(E);
		
		assertEquals(true, FriendshipGraph.addEdge(B, A));
		assertEquals(true, FriendshipGraph.addEdge(A, C));
		assertEquals(true, FriendshipGraph.addEdge(C, A));
		assertEquals(true, FriendshipGraph.addEdge(E, D));

	}

	@Test
	public void Test3_getDistance() {

		Person A = new Person("A");
		Person B = new Person("B");
		Person C = new Person("C");
		Person D = new Person("D");
		Person E = new Person("E");
		
		FriendshipGraph.addVertex(A);
		FriendshipGraph.addVertex(B);
		FriendshipGraph.addVertex(C);
		FriendshipGraph.addVertex(D);
		FriendshipGraph.addVertex(E);
		
		FriendshipGraph.addEdge(B, A);
		FriendshipGraph.addEdge(A, C);
		FriendshipGraph.addEdge(C, A);
		FriendshipGraph.addEdge(E, D);
		
		assertEquals(1, FriendshipGraph.getDistance(A, C));
		assertEquals(-1, FriendshipGraph.getDistance(B, E));
		assertEquals(2, FriendshipGraph.getDistance(B, C));
		assertEquals(0, FriendshipGraph.getDistance(A, A));
	}

}
