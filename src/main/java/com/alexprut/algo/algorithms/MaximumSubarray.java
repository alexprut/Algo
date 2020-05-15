package com.alexprut.algo.algorithms;

/** J.Kadane algorithm */
public class MaximumSubarray {

  /**
   * Finds the contiguous sub-array within a one-dimensional array, a[1...n], of numbers which has
   * the largest sum.
   *
   * <p>Time complexity: Θ(n)
   *
   * <p>Space complexity: Θ(n) total with Θ(1) auxiliary
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Maximum_subarray_problem">https://en.wikipedia.org/wiki/Maximum_subarray_problem</a>
   * @param elements
   * @return
   */
  public static int maximumSubarray(int[] elements) {
    if (elements.length == 0) {
      return 0;
    }

    int maxEndingHere = elements[0];
    int maxSoFar = elements[0];

    for (int i = 1; i < elements.length; i++) {
      maxEndingHere = Math.max(elements[i], maxEndingHere + elements[i]);
      maxSoFar = Math.max(maxSoFar, maxEndingHere);
    }

    return maxSoFar;
  }
}
