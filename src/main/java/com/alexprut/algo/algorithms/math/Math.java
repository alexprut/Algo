package com.alexprut.algo.algorithms.math;

public class Math {

  /**
   * Greatest common divisor, Euclide algorithms
   * given gcd(a, b) the time complexity is O(b)
   */
  public static int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }

    return gcd(b, a % b);
  }

  // TODO binomial coefficient
  // TODO permutation
  // TODO factorial
}
