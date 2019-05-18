package debug;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TopVotedTest {
	
	@Test
	public void Test1() {
		int[] persons = {0, 1, 1, 0, 0, 1, 0};
		int[] times = {0, 5, 10, 15, 20, 25, 30};
		TopVotedCandidate t = new TopVotedCandidate(persons, times);
		System.out.println(t.q(3));
		System.out.println(t.q(12));
		System.out.println(t.q(25));
		System.out.println(t.q(15));
		System.out.println(t.q(24));
		System.out.println(t.q(8));
		assertEquals(0, t.q(3));
		assertEquals(1, t.q(12));
		assertEquals(1, t.q(25));
		assertEquals(0, t.q(15));
		assertEquals(0, t.q(24));
		assertEquals(1, t.q(8));
		
	}
	
	@Test
	public void Test2() {
		int[] persons = {0, 1, 1, 1, 0, 1, 0};
		int[] times = {0, 5, 10, 15, 20, 25, 30};
		TopVotedCandidate t = new TopVotedCandidate(persons, times);		
		assertEquals(0, t.q(3));
		assertEquals(1, t.q(12));
		assertEquals(1, t.q(25));
		assertEquals(1, t.q(15));
		assertEquals(1, t.q(24));
		assertEquals(1, t.q(8));
	}
}
