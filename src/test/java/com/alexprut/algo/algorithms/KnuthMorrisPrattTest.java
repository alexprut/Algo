package com.alexprut.algo.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KnuthMorrisPrattTest {

  @Test
  public void shouldSeachPattern() {
    Assertions.assertEquals(4, KnuthMorrisPratt.knuthMorrisPratt("bacbababaabcbab", "ababa"));
    Assertions.assertEquals(0, KnuthMorrisPratt.knuthMorrisPratt("bacbab", "b"));
    Assertions.assertEquals(0, KnuthMorrisPratt.knuthMorrisPratt("bacbab", "ba"));
    Assertions.assertEquals(-1, KnuthMorrisPratt.knuthMorrisPratt("bacbab", "x"));
    Assertions.assertEquals(-1, KnuthMorrisPratt.knuthMorrisPratt("bacbab", "xay"));
    Assertions.assertEquals(0, KnuthMorrisPratt.knuthMorrisPratt("bacbab", "bacbab"));
    Assertions.assertEquals(0, KnuthMorrisPratt.knuthMorrisPratt("", ""));
  }

  @Test
  public void shouldCreatePrefixFunction() {
    Assertions.assertArrayEquals(
        new int[] {0, 0, 1, 2, 3, 0, 1}, KnuthMorrisPratt.computePrefixFunction("ababaca"));
  }
}
