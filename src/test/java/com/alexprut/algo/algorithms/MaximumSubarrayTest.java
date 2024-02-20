package com.alexprut.algo.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaximumSubarrayTest {

  @Test
  public void shouldFindMaxSubarray() {
    Assertions.assertEquals(0, MaximumSubarray.maximumSubarray(new int[] {}));
    Assertions.assertEquals(6, MaximumSubarray.maximumSubarray(new int[] {1, -1, -2, 4, -1, 3}));
  }
}
