package com.alexprut.algo.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class SegmentTreeTest {

  @Test
  public void shouldBuildUpdateAndSearchMin() {
    // TODO
  }

  @Test
  public void shouldBuildUpdateAndSearchMax() {
    // TODO
  }

  @Test
  public void shouldBuildAndSearchAndUpdateSum() {
    int[] arr = new int[] {2, 5, 9, 3, 8};
    SegmentTree tree = new SegmentTree(arr);

    Assert.assertEquals(7, tree.search(0,1));
    Assert.assertEquals(5, tree.search(1,1));
    Assert.assertEquals(17, tree.search(1,3));
    Assert.assertEquals(27, tree.search(0,4));

    tree.update(0, 0);
    tree.update(2, 4);

    Assert.assertEquals(5, tree.search(0,1));
    Assert.assertEquals(5, tree.search(1,1));
    Assert.assertEquals(12, tree.search(1,3));
    Assert.assertEquals(20, tree.search(0,4));
  }
}
