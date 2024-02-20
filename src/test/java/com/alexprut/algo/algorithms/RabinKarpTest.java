package com.alexprut.algo.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RabinKarpTest {

  @Test
  public void shouldFindMatch() {
    Assertions.assertEquals(0, RabinKarp.rabinKarp("2359023141526739921", "", 10, 13));
    Assertions.assertEquals(12, RabinKarp.rabinKarp("2359023141526739921", "67399", 10, 13));
    Assertions.assertEquals(12, RabinKarp.rabinKarp("2359023141526739921", "67399", 10, 15));
    Assertions.assertEquals(0, RabinKarp.rabinKarp("2359023141526739921", "235", 10, 15));
    Assertions.assertEquals(-1, RabinKarp.rabinKarp("2359023141526739921", "6327", 10, 15));
    Assertions.assertEquals(4, RabinKarp.rabinKarp("search here", "ch", 127, 893933));
    Assertions.assertEquals(-1, RabinKarp.rabinKarp("search here", "hr", 127, 893933));
  }
}
