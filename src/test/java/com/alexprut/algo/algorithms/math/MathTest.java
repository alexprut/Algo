package com.alexprut.algo.algorithms.math;

import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;

public class MathTest {

  @Test
  public void shoudCalculateGcd() {
    Assert.assertEquals(Math.gcd(0, 1), 1);
    Assert.assertEquals(Math.gcd(1, 0), 1);
    Assert.assertEquals(Math.gcd(10, 2), 2);
    Assert.assertEquals(Math.gcd(10, 10), 10);
    Assert.assertEquals(Math.gcd(2, 10), 2);
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
}
