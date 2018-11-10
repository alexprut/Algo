package com.alexprut.algo.algorithms.math;

import java.math.BigInteger;

public class Math {

  /**
   * Greatest common divisor, Euclide algorithms
   *
   * Time complexity: O(b) given gcd(a, b)
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

  public static BigInteger factorial(int n) {
    if (n < 2) {
      return BigInteger.ONE;
    }

    BigInteger current = BigInteger.ONE;
    for (int i = 2; i <= n; i++) {
      current = current.multiply(BigInteger.valueOf(i));
    }

    return current;
  }

  // TODO binomial coefficient
  // TODO permutation
  // TODO factorial
}
