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

  @Test
  public void shouldDoMultipleOperations() {
    /*
                        [16,21],30,B
                /                         \
          [8,9],23,R                    [25,30],30,R
         /         \                   /            \
       [5,8],10,B  [15,23],23,B    [17,19],20,B    [26,26],26,B
      /         \                            \
    [0,3],3,R  [6,10],10,R               [19,20],20,R
    */
    IntervalTree tree = new IntervalTree();
    tree.insert(16, 21);
    tree.insert(8, 9);
    tree.insert(25, 30);
    tree.insert(5, 8);
    tree.insert(15, 23);
    tree.insert(17, 19);
    tree.insert(26, 26);
    tree.insert(0, 3);
    tree.insert(6, 10);
    tree.insert(19, 20);

    Assert.assertEquals(1, tree.findAll(0, 2).size());
    Assert.assertEquals(0, tree.findAll(0, 2).get(0).value());
    Assert.assertEquals(4, tree.findAll(16, 21).size());
    Assert.assertEquals(4, tree.findAll(17, 19).size());
    Assert.assertEquals(0, tree.minimum().value());
    Assert.assertEquals(26, tree.maximum().value());
    Assert.assertNotNull(tree.find(4, 9));
    Assert.assertNotNull(tree.find(9, 9));
    Assert.assertNotNull(tree.find(10, 14));
    Assert.assertNull(tree.find(31, 32));
    Assert.assertNull(tree.find(-2, -1));
    Assert.assertEquals(
        17, tree.successor(tree.search(tree.root(), new IntervalNode(16, 21))).value());
    Assert.assertEquals(
        25, tree.successor(tree.search(tree.root(), new IntervalNode(19, 20))).value());

    tree.delete(16, 21);
    tree.delete(5, 8);
    tree.delete(17, 19);
    tree.delete(25, 30);
    tree.delete(0, 3);
    tree.delete(8, 9);
    tree.delete(15, 23);
    tree.delete(19, 20);
    tree.delete(26, 26);
    tree.delete(6, 10);

    Assert.assertEquals(0, tree.size());
  }
}
