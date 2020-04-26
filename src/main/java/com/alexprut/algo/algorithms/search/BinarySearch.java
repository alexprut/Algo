package com.alexprut.algo.algorithms.search;

public class BinarySearch {

  /** Time complexity: O(logn) */
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

  /** Time complexity: O(logn) */
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

  /** Time complexity: O(logn) */
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
