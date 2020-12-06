package com.alexprut.algo.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class BloomFilterTest {

  @Test
  public void shouldInsertAndSearch() {
    BloomFilter<String> filter = new BloomFilter<>(5, 0.03);
    filter.insert("bl");
    filter.insert("bloom");
    filter.insert("fil");
    filter.insert("filter");
    filter.insert("bloom filter");

    Assert.assertTrue(filter.search("bl"));
    Assert.assertTrue(filter.search("bloom"));
    Assert.assertTrue(filter.search("fil"));
    Assert.assertTrue(filter.search("filter"));
    Assert.assertTrue(filter.search("bloom filter"));

    Assert.assertFalse(filter.search("blo"));
    Assert.assertFalse(filter.search("loom"));
    Assert.assertFalse(filter.search("fi"));
    Assert.assertFalse(filter.search("filte"));
    Assert.assertFalse(filter.search("om filter"));
  }

  @Test
  public void shouldValidateFailingPercentageRate() {
    BloomFilter<Integer> filter = new BloomFilter<>(10000, 0.01);
    for (int i = 0; i < 10000; i++) {
      filter.insert(i);
    }

    for (int i = 0; i < 10000; i++) {
      Assert.assertTrue(filter.search(i));
    }

    int c = 0;
    for (int i = 10000; i < 20000; i++) {
      c += filter.search(i) ? 1 : 0;
    }

    Assert.assertTrue(c / 10000 <= 0.01);
  }
}
