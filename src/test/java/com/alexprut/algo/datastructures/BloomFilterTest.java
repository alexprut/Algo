package com.alexprut.algo.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BloomFilterTest {

  @Test
  public void shouldInsertAndSearch() {
    BloomFilter<String> filter = new BloomFilter<>(5, 0.03);
    filter.insert("bl");
    filter.insert("bloom");
    filter.insert("fil");
    filter.insert("filter");
    filter.insert("bloom filter");

    Assertions.assertTrue(filter.search("bl"));
    Assertions.assertTrue(filter.search("bloom"));
    Assertions.assertTrue(filter.search("fil"));
    Assertions.assertTrue(filter.search("filter"));
    Assertions.assertTrue(filter.search("bloom filter"));

    Assertions.assertFalse(filter.search("blo"));
    Assertions.assertFalse(filter.search("loom"));
    Assertions.assertFalse(filter.search("fi"));
    Assertions.assertFalse(filter.search("filte"));
    Assertions.assertFalse(filter.search("om filter"));
  }

  @Test
  public void shouldValidateFailingPercentageRate() {
    BloomFilter<Integer> filter = new BloomFilter<>(10000, 0.01);
    for (int i = 0; i < 10000; i++) {
      filter.insert(i);
    }

    for (int i = 0; i < 10000; i++) {
      Assertions.assertTrue(filter.search(i));
    }

    int c = 0;
    for (int i = 10000; i < 20000; i++) {
      c += filter.search(i) ? 1 : 0;
    }

    Assertions.assertTrue(c / 10000 <= 0.01);
  }
}
