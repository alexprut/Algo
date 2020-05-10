package com.alexprut.algo.algorithms;

import org.junit.Assert;
import org.junit.Test;

public class KnuthMorrisPrattTest {

  @Test
  public void shouldSeachPattern() {
    Assert.assertEquals(4, KnuthMorrisPratt.knuthMorrisPratt("bacbababaabcbab", "ababa"));
    Assert.assertEquals(0, KnuthMorrisPratt.knuthMorrisPratt("bacbab", "b"));
    Assert.assertEquals(0, KnuthMorrisPratt.knuthMorrisPratt("bacbab", "ba"));
    Assert.assertEquals(-1, KnuthMorrisPratt.knuthMorrisPratt("bacbab", "x"));
    Assert.assertEquals(-1, KnuthMorrisPratt.knuthMorrisPratt("bacbab", "xay"));
    Assert.assertEquals(0, KnuthMorrisPratt.knuthMorrisPratt("bacbab", "bacbab"));
    Assert.assertEquals(0, KnuthMorrisPratt.knuthMorrisPratt("", ""));
  }

  @Test
  public void shouldCreatePrefixFunction() {
    Assert.assertArrayEquals(
        new int[] {0, 0, 1, 2, 3, 0, 1}, KnuthMorrisPratt.computePrefixFunction("ababaca"));
  }
}
