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
   * <p>Time complexity: O(b) given gcd(a, b)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Greatest_common_divisor">https://en.wikipedia.org/wiki/Greatest_common_divisor</a>
   * @param a
   * @param b
   * @return
   */
  public static int gcd(int a, int b) {
    return (b == 0) ? a : gcd(b, a % b);
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

  /**
   * TODO
   *
   * @param n
   * @return
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
   * TODO search a more efficient algorithm
   *
   * @param elements
   * @param <T>
   * @return
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

  private static <T> void permuteUnique(
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
