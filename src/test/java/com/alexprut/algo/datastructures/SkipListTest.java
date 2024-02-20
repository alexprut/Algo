package com.alexprut.algo.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SkipListTest {

  @Test
  public void shouldInsertDeleteSearch() {
    SkipList<Integer> skipList = new SkipList<>(0, 0.5f);
    Assertions.assertEquals(0, skipList.size());
    // [3]
    skipList.insert(3);
    Assertions.assertEquals(1, skipList.size());
    // []
    Assertions.assertTrue(skipList.delete(3));
    // [3]
    skipList.insert(3);
    Assertions.assertEquals(1, skipList.size());
    Assertions.assertFalse(skipList.search(2));
    Assertions.assertTrue(skipList.search(3));
    // [1,3]
    skipList.insert(1);
    Assertions.assertEquals(2, skipList.size());
    // [1,2,3]
    skipList.insert(2);
    Assertions.assertEquals(3, skipList.size());
    Assertions.assertTrue(skipList.search(2));
    // [1,2,2,3]
    skipList.insert(2);
    Assertions.assertEquals(4, skipList.size());
    Assertions.assertTrue(skipList.search(2));
    // [1,2,3]
    Assertions.assertTrue(skipList.delete(2));
    Assertions.assertEquals(3, skipList.size());
    Assertions.assertTrue(skipList.search(2));
    // [1,3]
    Assertions.assertTrue(skipList.delete(2));
    Assertions.assertEquals(2, skipList.size());
    // [1,3]
    Assertions.assertFalse(skipList.delete(2));
    Assertions.assertEquals(2, skipList.size());
    Assertions.assertFalse(skipList.search(2));
    skipList.insert(8);
    skipList.insert(9);
    skipList.insert(12);
    skipList.insert(4);
    skipList.insert(5);
    Assertions.assertTrue(skipList.search(12));
    skipList.delete(3);
    skipList.delete(3);
  }

  @Test
  public void shouldInsertAndSearch() {
    SkipList<Integer> skipList = new SkipList<>(4, 0.5f);
    skipList.insert(3);
    skipList.insert(1);
    skipList.insert(2);
    skipList.insert(8);
    skipList.insert(9);
    skipList.insert(12);
    skipList.insert(4);
    skipList.insert(5);

    Assertions.assertTrue(skipList.search(2));
    Assertions.assertTrue(skipList.search(1));
    Assertions.assertTrue(skipList.search(5));
  }

  @Test
  public void shouldDelete() {
    SkipList<Integer> skipList = new SkipList<>(4, 0.5f);
    skipList.insert(3);
    skipList.insert(1);
    skipList.insert(2);
    skipList.insert(2);
    skipList.insert(8);
    skipList.insert(9);
    skipList.insert(12);
    skipList.insert(4);
    skipList.insert(5);
    skipList.delete(2);
    skipList.delete(1);
    skipList.delete(5);

    Assertions.assertTrue(skipList.search(2));
    Assertions.assertFalse(skipList.search(1));
    Assertions.assertFalse(skipList.search(5));
  }
}
