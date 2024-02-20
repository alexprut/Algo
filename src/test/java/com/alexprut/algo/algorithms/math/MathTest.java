package com.alexprut.algo.algorithms.math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MathTest {

  @Test
  public void shoudCalculateGcd() {
    Assertions.assertEquals(Math.gcd(0, 1), 1);
    Assertions.assertEquals(Math.gcd(1, 0), 1);
    Assertions.assertEquals(Math.gcd(10, 2), 2);
    Assertions.assertEquals(Math.gcd(2, 10), 2);
    Assertions.assertEquals(Math.gcd(6, 10), 2);
    Assertions.assertEquals(Math.gcd(10, 10), 10);
  }

  @Test
  public void primalityTest() {
    Assertions.assertTrue(Math.isPrime(2));
    Assertions.assertTrue(Math.isPrime(3));
    Assertions.assertTrue(Math.isPrime(5));
    Assertions.assertFalse(Math.isPrime(-1));
    Assertions.assertFalse(Math.isPrime(0));
    Assertions.assertFalse(Math.isPrime(1));
    Assertions.assertFalse(Math.isPrime(6));
    Assertions.assertFalse(Math.isPrime(9));
    Assertions.assertFalse(Math.isPrime(10));
  }

  @Test
  public void factorialTest() {
    Assertions.assertEquals(BigInteger.valueOf(1), Math.factorial(0));
    Assertions.assertEquals(BigInteger.valueOf(1), Math.factorial(1));
    Assertions.assertEquals(BigInteger.valueOf(2), Math.factorial(2));
    Assertions.assertEquals(BigInteger.valueOf(24), Math.factorial(4));
    Assertions.assertEquals(BigInteger.valueOf(3628800), Math.factorial(10));
    Assertions.assertEquals(new BigInteger("121645100408832000"), Math.factorial(19));
  }

  @Test
  public void permuteTest() {
    List<List<Integer>> expected = new ArrayList<>();
    expected.add(
        new ArrayList<>() {
          {
            add(1);
            add(2);
            add(3);
          }
        });
    expected.add(
        new ArrayList<>() {
          {
            add(1);
            add(3);
            add(2);
          }
        });
    expected.add(
        new ArrayList<>() {
          {
            add(2);
            add(1);
            add(3);
          }
        });
    expected.add(
        new ArrayList<>() {
          {
            add(2);
            add(3);
            add(1);
          }
        });
    expected.add(
        new ArrayList<>() {
          {
            add(3);
            add(1);
            add(2);
          }
        });
    expected.add(
        new ArrayList<>() {
          {
            add(3);
            add(2);
            add(1);
          }
        });
    List<Integer> elements = new ArrayList<>();
    elements.add(1);
    elements.add(2);
    elements.add(3);

    Assertions.assertArrayEquals(expected.toArray(), Math.permute(elements).toArray());
    Assertions.assertArrayEquals(
        (new ArrayList<ArrayList<Character>>()).toArray(),
        Math.permute(new ArrayList<>()).toArray());
  }

  @Test
  public void permuteUniqueTest() {
    List<List<Integer>> expected = new ArrayList<>();
    expected.add(Arrays.asList(1, 1, 2));
    expected.add(Arrays.asList(1, 2, 1));
    expected.add(Arrays.asList(2, 1, 1));
    Assertions.assertEquals(expected, Math.permuteUnique(new Integer[] {1, 1, 2}));

    Assertions.assertEquals(new ArrayList<>(), Math.permuteUnique(new Integer[] {}));
  }
}
