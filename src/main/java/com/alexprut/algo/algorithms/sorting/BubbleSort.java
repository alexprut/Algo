package com.alexprut.algo.algorithms.sorting;

import com.alexprut.algo.Utils;

public class BubbleSort {

  /**
   * Bubble sort is a simple sorting algorithm that repeatedly steps through the list, compares
   * adjacent elements and swaps them if they are in the wrong order. The pass through the list is
   * repeated until the list is sorted.
   *
   * <p>Example: given the elements [5, 2, 1, 9, 3, 3, 0] the sorted elements would be [0, 1, 2, 3,
   * 3, 5, 9]
   *
   * <p>Time complexity: O(n^2) in the worst case, O(n) in the best case when the array is sorted
   *
   * <p>Space complexity: O(n) total with O(1) auxiliary
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Bubble_sort">https://en.wikipedia.org/wiki/Bubble_sort</a>
   * @param arr the elements to sort
   * @return the sorted elements
   */
  public static int[] bubbleSort(int[] arr) {
    boolean swapped = true;
    while (swapped) {
      swapped = false;
      for (int i = 1; i < arr.length; i++) {
        if (arr[i - 1] > arr[i]) {
          Utils.swap(arr, i, i - 1);
          swapped = true;
        }
      }
    }

    return arr;
  }
}
