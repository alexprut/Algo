package com.alexprut.algo.algorithms.math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Math {

  /**
   * Greatest common divisor, Euclide algorithms.
   *
   * <p>Example: the greatest common divisor between 10 and 6 is 2.
   *
   * <p>Time complexity: O(b) given gcd(a, b)
   *
   * <p>Space complexity: O(1)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Greatest_common_divisor">https://en.wikipedia.org/wiki/Greatest_common_divisor</a>
   * @param a the first number
   * @param b the second number
   * @return the greatest common divisor
   */
  public static int gcd(int a, int b) {
    return (b == 0) ? a : gcd(b, a % b);
  }

  /**
   * Checks if a number is prime. A prime number is a natural number greater than 1 that is not a
   * product of two smaller natural numbers.
   *
   * <p>Example: numbers 2,3,5,7,11... are prime numbers, 4,6,8,9... are not prime numbers.
   *
   * <p>Time complexity: O(nloglogn)
   *
   * <p>Space complexity: O(1)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes">https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes</a>
   * @param n the number to check
   * @return true if the number is a prime
   */
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

  /**
   * Computes the factorial of a positive integer n (also known as n!).
   *
   * <p>Example: the factorial of 4 is 4*3*2*1=24.
   *
   * <p>Time complexity: O(n)
   *
   * <p>Space complexity: O(1)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Factorial">https://en.wikipedia.org/wiki/Factorial</a>
   * @param n the positive integer
   * @return the factorial number
   */
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

  /**
   * Given a collection of elements return all possible permutations.
   *
   * <p>Example: given the elements {3,1,2} the list of permutations are: [(1,2,3), (1,3,2),
   * (2,1,3), (2,3,1), (3,1,2), (3,2,1)].
   *
   * <p>TODO time and space complexity
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Permutation">https://en.wikipedia.org/wiki/Permutation</a>
   * @param elements the list of elements
   * @param <T> the type of the elements
   * @return the list of all permutations
   */
  public static <T> ArrayList<ArrayList<T>> permute(ArrayList<T> elements) {
    if (elements.size() == 0) {
      return new ArrayList<>();
    }

    if (elements.size() == 1) {
      ArrayList<ArrayList<T>> tmp = new ArrayList<>();
      tmp.add(elements);
      return tmp;
    }

    ArrayList<ArrayList<T>> perm = new ArrayList<>();
    for (int i = 0; i < elements.size(); i++) {
      T current = elements.get(i);
      ArrayList<T> remaining = new ArrayList<>(elements);
      remaining.remove(i);
      ArrayList<ArrayList<T>> recursive = permute(remaining);
      for (ArrayList<T> elem : recursive) {
        elem.add(0, current);
      }
      perm.addAll(recursive);
    }

    return perm;
  }

  /**
   * Given a collection of elements that might contain duplicates, return all possible unique
   * permutations.
   *
   * <p>Example: given the elements {1,1,2} the list of permutations are: [(1,1,2), (1,2,1),
   * (2,1,1)].
   *
   * <p>TODO time and space complexity
   *
   * @param elem the elements to permute
   * @param <T> type of the elements
   * @return all possible unique permutations
   */
  public static <T> List<List<T>> permuteUnique(T[] elem) {
    if (elem.length == 0) {
      return new ArrayList<>();
    }
    Arrays.sort(elem);
    ArrayList<T> tmp = new ArrayList<>();
    Collections.addAll(tmp, elem);
    List<List<T>> res = new ArrayList<>();
    permuteUnique(new ArrayList<>(), tmp, res);
    return res;
  }

  /**
   * Helper method used in {@link #permuteUnique(Object[])}
   *
   * @param c accumulator list
   * @param n remaining elements
   * @param res the list of permutations
   * @param <T> the type of the elements
   */
  protected static <T> void permuteUnique(
      final ArrayList<T> c, final ArrayList<T> n, List<List<T>> res) {
    if (n.size() == 0) {
      res.add(c);
      return;
    }
    for (int i = 0; i < n.size(); i++) {
      if (i == 0 || !n.get(i - 1).equals(n.get(i))) {
        ArrayList<T> ctmp = (ArrayList<T>) c.clone();
        ArrayList<T> ntmp = (ArrayList<T>) n.clone();
        ctmp.add(ntmp.get(i));
        ntmp.remove(i);
        permuteUnique(ctmp, ntmp, res);
      }
    }
  }

  // TODO binomial coefficient
}
