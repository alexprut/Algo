package com.alexprut.algo.algorithms.search;

public class BinarySearch {

  public static int binarySearch(int[] a, int v) {
    int start = 0;
    int end = a.length - 1;

    while (start <= end) {
      int middle = (start + end) / 2;
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
}
