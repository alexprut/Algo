package com.alexprut.algo.algorithms.sorting;

import com.alexprut.algo.algorithms.RabinKarp;
import org.junit.Assert;
import org.junit.Test;

public class RabinKarpTest {

  @Test
  public void shouldFindMatch() {
    Assert.assertEquals(12, RabinKarp.rabinKarp("2359023141526739921", "67399", 10, 13));
    Assert.assertEquals(12, RabinKarp.rabinKarp("2359023141526739921", "67399", 10, 15));
    Assert.assertEquals(0, RabinKarp.rabinKarp("2359023141526739921", "235", 10, 15));
    Assert.assertEquals(-1, RabinKarp.rabinKarp("2359023141526739921", "6327", 10, 15));
    Assert.assertEquals(4, RabinKarp.rabinKarp("search here", "ch", 127, 893933));
    Assert.assertEquals(-1, RabinKarp.rabinKarp("search here", "hr", 127, 893933));
  }
}
