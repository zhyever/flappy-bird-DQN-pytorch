package debug;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TopVotedTest {

  @Test
  public void test1() {
    int[] persons = { 0, 1, 1, 0, 0, 1, 0 };
    int[] times = { 0, 5, 10, 15, 20, 25, 30 };
    TopVotedCandidate t = new TopVotedCandidate(persons, times);
    System.out.println(t.quary(3));
    System.out.println(t.quary(12));
    System.out.println(t.quary(25));
    System.out.println(t.quary(15));
    System.out.println(t.quary(24));
    System.out.println(t.quary(8));
    assertEquals(0, t.quary(3));
    assertEquals(1, t.quary(12));
    assertEquals(1, t.quary(25));
    assertEquals(0, t.quary(15));
    assertEquals(0, t.quary(24));
    assertEquals(1, t.quary(8));

  }

  @Test
  public void test2() {
    int[] persons = { 0, 1, 1, 1, 0, 1, 0 };
    int[] times = { 0, 5, 10, 15, 20, 25, 30 };
    TopVotedCandidate t = new TopVotedCandidate(persons, times);
    assertEquals(0, t.quary(3));
    assertEquals(1, t.quary(12));
    assertEquals(1, t.quary(25));
    assertEquals(1, t.quary(15));
    assertEquals(1, t.quary(24));
    assertEquals(1, t.quary(8));
  }
}
