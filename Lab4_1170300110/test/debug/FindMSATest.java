package debug;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindMSATest {
	FindMedianSortedArrays findMSA = new FindMedianSortedArrays();
	
	
	@Test
	public void test1() {
		int[] A = {1, 2, 3};
		int[] B = {1, 2, 3};
		
		assertEquals(2.0, findMSA.findMedianSortedArrays(A, B),0);
	}
	
	@Test
	public void test2() {
		int[] A = {1, 2, 3};
		int[] B = {1, 2};
		
		assertEquals(2.0, findMSA.findMedianSortedArrays(A, B),0);
	}
	
	@Test
	public void test3() {
		int[] A = {1, 2, 3};
		int[] B = {1};
		
		assertEquals(1.5, findMSA.findMedianSortedArrays(A, B),0);
	}
	
	@Test
	public void test4() {
		int[] A = null;
		int[] B = {1, 2, 3};
		
		assertEquals(2.0, findMSA.findMedianSortedArrays(A, B),0);
	}
	
	
	@Test
	public void test5() {
		int[] A = {1, 2, 3};
		int[] B = null;
		
		assertEquals(2.0, findMSA.findMedianSortedArrays(A, B),0);
	}
}
