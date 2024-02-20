package com.alexprut.algo.algorithms.math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class MathTest {

  @Test
  public void shoudCalculateGcd() {
    Assert.assertEquals(Math.gcd(0, 1), 1);
    Assert.assertEquals(Math.gcd(1, 0), 1);
    Assert.assertEquals(Math.gcd(10, 2), 2);
    Assert.assertEquals(Math.gcd(2, 10), 2);
    Assert.assertEquals(Math.gcd(6, 10), 2);
    Assert.assertEquals(Math.gcd(10, 10), 10);
  }

  @Test
  public void primalityTest() {
    Assert.assertTrue(Math.isPrime(2));
    Assert.assertTrue(Math.isPrime(3));
    Assert.assertTrue(Math.isPrime(5));
    Assert.assertFalse(Math.isPrime(-1));
    Assert.assertFalse(Math.isPrime(0));
    Assert.assertFalse(Math.isPrime(1));
    Assert.assertFalse(Math.isPrime(6));
    Assert.assertFalse(Math.isPrime(9));
    Assert.assertFalse(Math.isPrime(10));
  }

  @Test
  public void factorialTest() {
    Assert.assertEquals(BigInteger.valueOf(1), Math.factorial(0));
    Assert.assertEquals(BigInteger.valueOf(1), Math.factorial(1));
    Assert.assertEquals(BigInteger.valueOf(2), Math.factorial(2));
    Assert.assertEquals(BigInteger.valueOf(24), Math.factorial(4));
    Assert.assertEquals(BigInteger.valueOf(3628800), Math.factorial(10));
    Assert.assertEquals(new BigInteger("121645100408832000"), Math.factorial(19));
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

    Assert.assertArrayEquals(expected.toArray(), Math.permute(elements).toArray());
    Assert.assertArrayEquals(
        (new ArrayList<ArrayList<Character>>()).toArray(),
        Math.permute(new ArrayList<>()).toArray());
  }

  @Test
  public void permuteUniqueTest() {
    List<List<Integer>> expected = new ArrayList<>();
    expected.add(Arrays.asList(1, 1, 2));
    expected.add(Arrays.asList(1, 2, 1));
    expected.add(Arrays.asList(2, 1, 1));
    Assert.assertEquals(expected, Math.permuteUnique(new Integer[] {1, 1, 2}));

    Assert.assertEquals(new ArrayList<>(), Math.permuteUnique(new Integer[] {}));
  }
}
