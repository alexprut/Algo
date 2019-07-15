package com.alexprut.algo.algorithms;

import org.junit.Assert;
import org.junit.Test;

public class MaximumSubarrayTest {

  @Test
  public void shouldFindMaxSubarray() {
    Assert.assertEquals(0, MaximumSubarray.maximumSubarray(new int[] {}));
    Assert.assertEquals(6, MaximumSubarray.maximumSubarray(new int[] {1, -1, -2, 4, -1, 3}));
  }
}
