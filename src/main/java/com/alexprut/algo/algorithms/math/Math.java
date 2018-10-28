package com.alexprut.algo.algorithms.math;

public class Math {

  /**
   * Greatest common divisor, Euclide algorithms
   *
   * <p>Time complexity: O(b) given gcd(a, b)
   */
  public static int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }

    return gcd(b, a % b);
  }

  public static boolean isPrime(int n) {
    if (n < 2) {
      return false;
    }
    for (int i = 2; i <= java.lang.Math.sqrt(n); i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  // TODO binomial coefficient
  // TODO permutation
  // TODO factorial
}
