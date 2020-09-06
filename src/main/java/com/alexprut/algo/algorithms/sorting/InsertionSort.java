package com.alexprut.algo.algorithms.sorting;

import com.alexprut.algo.Utils;

public class InsertionSort {

  private InsertionSort() {}

  /**
   * Insertion Sort is a sorting algorithm that is in-place. Insertion sort works the way many
   * people sort a hand of playing cards. We start with an empty left hand and the cards face down
   * on the table. We then remove one card at a time from the table and insert it into the correct
   * position in the left hand. To find the correct position for a card, we compare it with each of
   * the cards already in the hand, from right to left.
   *
   * <p>Example: given the elements [5, 2, 1, 9, 3, 3, 0] the sorted elements would be [0, 1, 2, 3,
   * 3, 5, 9]
   *
   * <p>Time complexity: O(n^2) in the worst case when the array is sorted in reverse order, O(n)
   * best case when the array is already sorted
   *
   * <p>Space complexity: O(n) total with O(1) auxiliary
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Insertion_sort">https://en.wikipedia.org/wiki/Insertion_sort</a>
   * @param arr the elements to sort
   * @return the sorted elements
   */
  public static int[] insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      int j = i;
      while (j > 0 && arr[j - 1] > arr[j]) {
        Utils.swap(arr, j, j - 1);
        j--;
      }
    }
    return arr;
  }
}
