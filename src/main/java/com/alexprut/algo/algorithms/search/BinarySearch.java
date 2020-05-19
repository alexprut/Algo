package com.alexprut.algo.algorithms.search;

/**
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Binary_search_algorithm">https://en.wikipedia.org/wiki/Binary_search_algorithm</a>
 */
public class BinarySearch {

  /**
   * TODO
   *
   * <p>Time complexity: O(logn)
   *
   * @param a
   * @param v
   * @return
   */
  public static int binarySearch(int[] a, int v) {
    int start = 0;
    int end = a.length - 1;

    while (start <= end) {
      int middle = start + (end - start) / 2;
      if (a[middle] == v) {
        return middle;
      }

      if (a[middle] > v) {
        end = middle - 1;
      } else {
        start = middle + 1;
      }
    }

    return -1;
  }

  /**
   * TODO
   *
   * <p>Time complexity: O(logn)
   *
   * @param a
   * @param v
   * @return
   */
  public static int binarySearchFirstLowest(int[] a, int v) {
    int start = 0;
    int end = a.length - 1;

    while (start <= end) {
      int middle = start + (end - start) / 2;
      if (a[middle] == v) {
        return middle;
      }

      if (v < a[middle]) {
        end = middle - 1;
      } else {
        start = middle + 1;
      }
    }

    return end;
  }

  /**
   * TODO
   *
   * <p>Time complexity: O(logn)
   *
   * @param a
   * @param v
   * @return
   */
  public static int binarySearchFirstHighest(int[] a, int v) {
    int start = 0;
    int end = a.length - 1;

    while (start <= end) {
      int middle = start + (end - start) / 2;
      if (a[middle] == v) {
        return middle;
      }

      if (v < a[middle]) {
        end = middle - 1;
      } else {
        start = middle + 1;
      }
    }

    return start;
  }
}
