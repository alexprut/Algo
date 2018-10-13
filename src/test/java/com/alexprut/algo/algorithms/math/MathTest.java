package com.alexprut.algo.algorithms.math;

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
}
