package debug;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindMsaTest {
  FindMedianSortedArrays findMsa = new FindMedianSortedArrays();

  @Test
  public void test1() {
    int[] a = { 1, 2, 3 };
    int[] b = { 1, 2, 3 };

    assertEquals(2.0, findMsa.findMedianSortedArrays(a, b), 0);
  }

  @Test
  public void test2() {
    int[] a = { 1, 2, 3 };
    int[] b = { 1, 2 };

    assertEquals(2.0, findMsa.findMedianSortedArrays(a, b), 0);
  }

  @Test
  public void test3() {
    int[] a = { 1, 2, 3 };
    int[] b = { 1 };

    assertEquals(1.5, findMsa.findMedianSortedArrays(a, b), 0);
  }

  @Test
  public void test4() {
    int[] a = null;
    int[] b = { 1, 2, 3 };

    assertEquals(2.0, findMsa.findMedianSortedArrays(a, b), 0);
  }

  @Test
  public void test5() {
    int[] a = { 1, 2, 3 };
    int[] b = null;

    assertEquals(2.0, findMsa.findMedianSortedArrays(a, b), 0);
  }
}
