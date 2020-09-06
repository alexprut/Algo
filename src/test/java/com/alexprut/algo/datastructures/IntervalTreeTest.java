package com.alexprut.algo.datastructures;

import com.alexprut.algo.datastructures.IntervalTree.IntervalNode;
import org.junit.Assert;
import org.junit.Test;

public class IntervalTreeTest {

  @Test
  public void shouldInsertAndDeleteAndSearch() {
    IntervalTree tree = new IntervalTree();
    tree.insert(0, 3);
    tree.insert(5, 8);
    tree.insert(6, 10);
    tree.insert(8, 9);
    tree.insert(15, 23);
    tree.insert(16, 21);
    tree.insert(17, 19);
    tree.insert(19, 20);
    tree.insert(25, 30);
    tree.insert(26, 26);
    tree.delete(8, 9);

    Assert.assertNotNull(tree.root());
    Assert.assertTrue(tree.search(0, 3));
    Assert.assertTrue(tree.search(5, 8));
    Assert.assertTrue(tree.search(6, 10));
    Assert.assertFalse(tree.search(8, 9));
    Assert.assertTrue(tree.search(15, 23));
    Assert.assertTrue(tree.search(16, 21));
    Assert.assertTrue(tree.search(17, 19));
    Assert.assertTrue(tree.search(19, 20));
    Assert.assertTrue(tree.search(25, 30));
    Assert.assertTrue(tree.search(26, 26));
  }

  @Test
  public void checkIfOverlap() {
    Assert.assertTrue(IntervalTree.doOverlap(new IntervalNode(1, 3), new IntervalNode(0, 5)));
    Assert.assertTrue(IntervalTree.doOverlap(new IntervalNode(0, 5), new IntervalNode(1, 3)));
    Assert.assertTrue(IntervalTree.doOverlap(new IntervalNode(1, 3), new IntervalNode(0, 2)));
    Assert.assertTrue(IntervalTree.doOverlap(new IntervalNode(0, 4), new IntervalNode(3, 5)));
    Assert.assertFalse(IntervalTree.doOverlap(new IntervalNode(0, 3), new IntervalNode(4, 5)));
    Assert.assertFalse(IntervalTree.doOverlap(new IntervalNode(4, 5), new IntervalNode(0, 3)));
  }
}
