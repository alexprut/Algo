package com.alexprut.algo.algorithms.search;

public class BinarySearch {

  private BinarySearch() {}

  /**
   * Binary Search is a search algorithm that finds the position of a target value within a sorted
   * array.
   *
   * <p>Example: given the sorted elements [1, 2, 3, 4, 5, 6] if we search for value 4 the the
   * returning index would be 3. Otherwise if we search for a value that is not in the list, such as
   * 7, then the returning index would be -1.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Binary_search_algorithm">https://en.wikipedia.org/wiki/Binary_search_algorithm</a>
   * @param a the list of sorted elements
   * @param v the value to search
   * @return the index of the searched values within the list, -1 otherwise
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
   * Similar to {@link #binarySearch(int[], int)}, instead of returning -1 in case the element is
   * not within the list, it returns the index of the first element that is lower than the searched
   * value.
   *
   * <p>Example: given the elements [1, 2, 3, 5, 6, 7], if we search for value 4, since the value is
   * not within the list of elements, it will return the index of the first lower value, which is 2.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param a the list of sorted elements
   * @param v the value to search
   * @return the index of the lowest first searched values within the list, -1 otherwise
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
   * Similar to {@link #binarySearch(int[], int)}, instead of returning -1 in case the element is
   * not within the list, it returns the index of the first element that is higher than the searched
   * value.
   *
   * <p>Example: given the elements [1, 2, 3, 5, 6, 7], if we search for value 4, since the value is
   * not within the list of elements, it will return the index of the first highest value, which is
   * 3.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param a the list of sorted elements
   * @param v the value to search
   * @return the index of the highest first searched values within the list, -1 otherwise
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
