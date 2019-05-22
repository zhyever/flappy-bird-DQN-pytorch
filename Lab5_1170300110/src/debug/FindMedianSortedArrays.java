package debug;

/**
 * Given two ordered integer arrays nums1 and nums2, with size m and n
 * Find out the median (double) of the two arrays.
 * You may suppose nums1 and nums2 cannot be null at the same time.
 * Example 1:
 * nums1 = [1, 3] 
 * nums2 = [2]
 * The output would be 2.0

 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The output would be 2.5

 * Example 3:
 * nums1 = [1, 1, 1]
 * nums2 = [5, 6, 7]
 * The output would be 3.0

 * Example 4:
 * nums1 = [1, 1]
 * nums2 = [1, 2, 3]
 * The output would be 1.0
 */

public class FindMedianSortedArrays {
  /**find median.
   * 
   * @param a arrayA
   * @param b arrayB
   * @return A + B median
   */

  public double findMedianSortedArrays(int[] a, int[] b) {
    if (a == null) {
      int len = b.length;
      if (len % 2 == 1) {
        return b[len / 2];
      } else {
        return (b[len / 2 - 1] + b[len / 2]) / 2;
      }
    }

    if (b == null) {
      int len = a.length;
      if (len % 2 == 1) {
        return a[len / 2];
      } else {
        return (a[len / 2 - 1] + a[len / 2]) / 2;
      }
    }


    int m = a.length;
    int n = b.length;
    //让B比A长
    if (m > n) {
      int[] temp = a;
      a = b;
      b = temp;
      int tmp = m;
      m = n;
      n = tmp;
    }
    int imin = 0; 
    int imax = m;
    int halfLen = (m + n) / 2;
    while (imin <= imax) {
      int i = (imin + imax + 1) / 2; //A的中间值 
      int j = halfLen - i;//B的中间位置 
      if (i < imax && b[j - 1] > a[i]) { //B中间左边边的那个比A中间值大
        imin = i + 1; //更新A索引
      } else if (i > imin && a[i - 1] > b[j]) { //A中间左边那个的比B中间值大
        imax = i - 1; //更新A索引
      } else {
        int maxLeft = 0;
        if (i == 0) {
          maxLeft = b[j - 1];
        } else if (j == 0) {
          maxLeft = a[i - 1];
        } else {
          maxLeft = Math.max(a[i - 1], b[j - 1]);
        }
        /*
         * 
         */

        int minRight = 0;
        if (i == m) {
          minRight = b[j];
        } else if (j == n) {
          minRight = a[i];
        } else {
          minRight = Math.min(b[j], a[i]);
        }

        //移动了位置
        //1改成了0
        if ((m + n + 1) % 2 == 0) {
          return minRight;
        }

        return (maxLeft + minRight) / 2.0;
      }
    }
    return 0.0;
  }
  /**main.
   * 
   * @param args no means
   */

  public static void main(String[] args) {
    FindMedianSortedArrays findMsa = new FindMedianSortedArrays();

    int[] a = {1, 2, 4, 5};
    int[] b = {6, 7, 8, 9, 10, 11, 12};
    // 11223445567
    // 7
    System.out.println(findMsa.findMedianSortedArrays(a, b));

    int[] c = {1, 2, 4};
    int[] d = {1, 2, 3, 4, 5, 6, 7};
    // 11 22 344567
    // 3.5
    System.out.println(findMsa.findMedianSortedArrays(c, d));

  }

}
