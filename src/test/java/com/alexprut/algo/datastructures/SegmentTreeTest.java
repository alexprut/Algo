package com.alexprut.algo.datastructures;

import com.alexprut.algo.datastructures.SegmentTree.MaxSegmentTree;
import com.alexprut.algo.datastructures.SegmentTree.MinSegmentTree;
import com.alexprut.algo.datastructures.SegmentTree.SumSegmentTree;
import org.junit.Assert;
import org.junit.Test;

public class SegmentTreeTest {

  @Test
  public void shouldBuildUpdateAndSearchAndUpdateMin() {
    int[] arr = new int[] {2, 5, 9, 3, 8};
    SegmentTree tree = new MinSegmentTree(arr);

    Assert.assertEquals(arr[0], tree.search(0, 1));
    Assert.assertEquals(arr[1], tree.search(1, 1));
    Assert.assertEquals(arr[3], tree.search(1, 3));
    Assert.assertEquals(arr[0], tree.search(0, 4));

    arr[0] = 0;
    arr[2] = 4;
    tree.update(0, arr[0]);
    tree.update(2, arr[2]);

    Assert.assertEquals(arr[0], tree.search(0, 1));
    Assert.assertEquals(arr[1], tree.search(1, 1));
    Assert.assertEquals(arr[3], tree.search(1, 3));
    Assert.assertEquals(arr[0], tree.search(0, 4));
  }

  @Test
  public void shouldBuildUpdateAndSearchAndUpdateMax() {
    int[] arr = new int[] {2, 5, 9, 3, 8};
    SegmentTree tree = new MaxSegmentTree(arr);

    Assert.assertEquals(arr[1], tree.search(0, 1));
    Assert.assertEquals(arr[1], tree.search(1, 1));
    Assert.assertEquals(arr[2], tree.search(1, 3));
    Assert.assertEquals(arr[2], tree.search(0, 4));

    arr[0] = 0;
    arr[2] = 4;
    tree.update(0, arr[0]);
    tree.update(2, arr[2]);

    Assert.assertEquals(arr[1], tree.search(0, 1));
    Assert.assertEquals(arr[1], tree.search(1, 1));
    Assert.assertEquals(arr[1], tree.search(1, 3));
    Assert.assertEquals(arr[4], tree.search(0, 4));
  }

  @Test
  public void shouldBuildAndSearchAndUpdateSum() {
    int[] arr = new int[] {2, 5, 9, 3, 8};
    SegmentTree tree = new SumSegmentTree(arr);

    Assert.assertEquals(7, tree.search(0, 1));
    Assert.assertEquals(5, tree.search(1, 1));
    Assert.assertEquals(17, tree.search(1, 3));
    Assert.assertEquals(27, tree.search(0, 4));

    tree.update(0, 0);
    tree.update(2, 4);

    Assert.assertEquals(5, tree.search(0, 1));
    Assert.assertEquals(5, tree.search(1, 1));
    Assert.assertEquals(12, tree.search(1, 3));
    Assert.assertEquals(20, tree.search(0, 4));
  }
}
