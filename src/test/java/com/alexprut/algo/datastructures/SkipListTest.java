package com.alexprut.algo.datastructures;

import org.junit.Assert;
import org.junit.Test;
public class SkipListTest {

    @Test
    public void shouldInsertDeleteSearch() {
        SkipList<Integer> skipList = new SkipList<>(0, 0.5f);
        Assert.assertEquals(0, skipList.size());
        // [3]
        skipList.insert(3);
        Assert.assertEquals(1, skipList.size());
        // []
        Assert.assertTrue(skipList.delete(3));
        // [3]
        skipList.insert(3);
        Assert.assertEquals(1, skipList.size());
        Assert.assertFalse(skipList.search(2));
        Assert.assertTrue(skipList.search(3));
        // [1,3]
        skipList.insert(1);
        Assert.assertEquals(2, skipList.size());
        // [1,2,3]
        skipList.insert(2);
        Assert.assertEquals(3, skipList.size());
        Assert.assertTrue(skipList.search(2));
        // [1,2,2,3]
        skipList.insert(2);
        Assert.assertEquals(4, skipList.size());
        Assert.assertTrue(skipList.search(2));
        // [1,2,3]
        Assert.assertTrue(skipList.delete(2));
        Assert.assertEquals(3, skipList.size());
        Assert.assertTrue(skipList.search(2));
        // [1,3]
        Assert.assertTrue(skipList.delete(2));
        Assert.assertEquals(2, skipList.size());
        // [1,3]
        Assert.assertFalse(skipList.delete(2));
        Assert.assertEquals(2, skipList.size());
        Assert.assertFalse(skipList.search(2));
        skipList.insert(8);
        skipList.insert(9);
        skipList.insert(12);
        skipList.insert(4);
        skipList.insert(5);
        Assert.assertTrue(skipList.search(12));
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

        Assert.assertTrue(skipList.search(2));
        Assert.assertTrue(skipList.search(1));
        Assert.assertTrue(skipList.search(5));
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

        Assert.assertTrue(skipList.search(2));
        Assert.assertFalse(skipList.search(1));
        Assert.assertFalse(skipList.search(5));
    }
}
