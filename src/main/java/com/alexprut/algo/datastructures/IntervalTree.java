package com.alexprut.algo.datastructures;

/**
 * Stores intervals as well, but optimized for "which of these intervals overlap with a given
 * interval" queries. It can also be used for point queries - similar to segment tree.
 *
 * <p>The Interval Tree data structure is implemented by using a Red-Black Tree The intervals are
 * closed TODO extend the Red-Black Tree data structure
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Interval_tree">https://en.wikipedia.org/wiki/Interval_tree</a>
 */
public class IntervalTree {

  protected IntervalNode root;
  private int size;

  IntervalTree() {}

  /**
   * Time complexity: O(logn) TODO check that the low is less than the high
   *
   * @param low
   * @param high
   */
  public void insert(int low, int high) {
    IntervalNode x = new IntervalNode(low, high, true);
    IntervalNode p = null;
    IntervalNode tmp = this.root;

    while (tmp != null) {
      p = tmp;
      if (x.value() < tmp.value()) {
        tmp = tmp.left;
      } else {
        tmp = tmp.right;
      }
    }
    x.parent = p;
    if (p == null) {
      root = x;
    } else if (x.value() < p.value()) {
      p.setLeft(x);
    } else {
      p.setRight(x);
    }
    maxFixup(x);
    insertFixup(x);
    size++;
  }

  /**
   * Time complexity: O(logn)
   *
   * @param x
   */
  protected void insertFixup(IntervalNode x) {
    while (x.parent != null && x.parent.isRed()) {
      if (x.parent == x.parent.parent.left) {
        IntervalNode y = x.parent.parent.right;
        if (y != null && y.isRed()) {
          // case 1
          x.parent.setBlackColor();
          y.setBlackColor();
          x.parent.parent.setRedColor();
          x = x.parent.parent;
        } else if (x == x.parent.right) {
          // case 2
          x = x.parent;
          leftRotation(x);
        } else {
          // case 3
          x.parent.setBlackColor();
          x.parent.parent.setRedColor();
          rightRotation(x.parent.parent);
        }
      } else {
        IntervalNode y = x.parent.parent.left;
        if (y != null && y.isRed()) {
          // case 1
          x.parent.setBlackColor();
          y.setBlackColor();
          x.parent.parent.setRedColor();
          x = x.parent.parent;
        } else if (x == x.parent.left) {
          // case 2
          x = x.parent;
          rightRotation(x);
        } else {
          // case 3
          x.parent.setBlackColor();
          x.parent.parent.setRedColor();
          leftRotation(x.parent.parent);
        }
      }
    }
    root.setBlackColor();
  }

  /** Time complexity: O(logn) */
  public boolean search(int low, int high) {
    return search(root, new IntervalNode(low, high)) != null;
  }

  /** Time complexity: O(logn) */
  public IntervalNode search(IntervalNode root, IntervalNode x) {
    IntervalNode tmp = root;
    while (tmp != null) {
      if (tmp.low == x.low && tmp.high == x.high) {
        return tmp;
      }
      tmp = (x.low < tmp.low) ? tmp.left : tmp.right;
    }

    return null;
  }

  /** Time complexity: O(logn) */
  public IntervalNode find(int low, int high) {
    return find(root, new IntervalNode(low, high));
  }

  /** Time complexity: O(logn) */
  public IntervalNode find(IntervalNode root, IntervalNode x) {
    IntervalNode tmp = root;
    while (tmp != null && !doOverlap(tmp, x)) {
      if (tmp.left() != null && tmp.left().max() >= x.value()) {
        tmp = tmp.left();
      } else {
        tmp = tmp.right();
      }
    }

    return tmp;
  }

  /** Time complexity: Θ(1) */
  public static boolean doOverlap(IntervalNode a, IntervalNode b) {
    return a.low <= b.high && b.low <= a.high;
  }

  // TODO implement findAll function

  /** Time complexity: O(logn) */
  public void delete(int low, int high) {
    delete(search(root, new IntervalNode(low, high)));
    size--;
  }

  /** Time complexity: O(logn) */
  public void delete(IntervalNode z) {
    IntervalNode x;
    IntervalNode y = z;
    Boolean isOriginalColorRed = y.color;
    if (z.left == null) {
      x = z.right;
      transplant(z, z.right);
    } else if (z.right == null) {
      x = z.left;
      transplant(z, z.left);
    } else {
      y = minimum(z.right);
      isOriginalColorRed = y.color;
      x = y.right;
      if (y.parent == z) {
        x.parent = y;
      } else {
        transplant(y, y.right);
        y.right = z.right;
        y.right.parent = y;
      }
      transplant(z, y);
      y.left = z.left;
      y.left.parent = y;
      y.color = z.color;
    }
    maxFixup(y);
    if (x != null && !isOriginalColorRed) {
      deleteFixup(x);
    }
  }

  protected void deleteFixup(IntervalNode x) {
    while (x != root && !x.isRed()) {
      if (x == x.parent.left) {
        IntervalNode w = x.parent.right;
        if (w.isRed()) {
          // Case 1
          w.setBlackColor();
          x.parent.setRedColor();
          leftRotation(x.parent);
          w = x.parent.right;
        }
        if (w.left != null && w.right != null && !w.left.isRed() && !w.right.isRed()) {
          // Case 2
          w.setRedColor();
          x = x.parent;
        } else if (w.right != null && !w.right.isRed()) {
          // Case 3
          w.left.setBlackColor();
          w.setRedColor();
          rightRotation(w);
          w = x.parent.right;
        } else {
          // Case 4
          w.color = x.parent.color;
          x.parent.setBlackColor();
          w.right.setBlackColor();
          leftRotation(x.parent);
          x = root;
        }
      } else {
        IntervalNode w = x.parent.left;
        if (w.isRed()) {
          // Case 1
          w.setBlackColor();
          x.parent.setRedColor();
          rightRotation(x.parent);
          w = x.parent.left;
        }
        if (w.left != null && w.right != null && !w.right.isRed() && !w.left.isRed()) {
          // Case 2
          w.setRedColor();
          x = x.parent;
        } else if (w.left != null && !w.left.isRed()) {
          // Case 3
          w.right.setBlackColor();
          w.setRedColor();
          leftRotation(w);
          w = x.parent.left;
        } else {
          // Case 4
          w.color = x.parent.color;
          x.parent.setBlackColor();
          w.left.setBlackColor();
          rightRotation(x.parent);
          x = root;
        }
      }
    }
    x.setBlackColor();
  }

  /**
   * Replaces the subtree rooted at node `u` with the subtree rooted at node `v`
   *
   * <p>Time complexity: Θ(1)
   */
  protected void transplant(IntervalNode u, IntervalNode v) {
    if (u.parent == null) {
      root = v;
    } else if (u == u.parent.left) {
      u.parent.left = v;
    } else if (u == u.parent.right) {
      u.parent.right = v;
    }
    if (v != null) {
      v.parent = u.parent;
    }
  }

  /** Time complexity: O(logn) if the tree is balanced */
  public IntervalNode minimum() {
    return minimum(root);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  public IntervalNode maximum() {
    return maximum(root);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  private IntervalNode minimum(IntervalNode node) {
    if (node == null || node.left == null) {
      return node;
    }
    return minimum(node.left);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  private IntervalNode maximum(IntervalNode node) {
    if (node == null || node.right == null) {
      return node;
    }
    return maximum(node.right);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  private IntervalNode successor(IntervalNode node) {
    if (node.right != null) {
      return minimum(node.right);
    }
    IntervalNode y = node.parent;
    while (y != null && node == y.right) {
      node = y;
      y = y.parent;
    }
    return y;
  }

  /** Time complexity: Θ(1) */
  public int size() {
    return this.size;
  }

  protected void maxCalculate(IntervalNode x) {
    x.max = x.high;
    if (x != null && x.left != null) {
      x.max = Math.max(x.max, x.left.max);
    }
    if (x != null && x.right != null) {
      x.max = Math.max(x.max, x.right.max);
    }
  }

  protected void maxFixup(IntervalNode x) {
    if (x != null) {
      maxCalculate(x);
      maxFixup(x.parent);
    }
  }

  /** Time complexity: Θ(1) */
  protected void leftRotation(IntervalNode x) {
    IntervalNode y = x.right;
    x.right = y.left;
    if (y.left != null) {
      y.left.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) {
      root = y;
    } else if (x == x.parent.left) {
      x.parent.left = y;
    } else {
      x.parent.right = y;
    }
    y.left = x;
    x.parent = y;
    maxCalculate(x);
    maxCalculate(x.parent);
  }

  /** Time complexity: Θ(1) */
  protected void rightRotation(IntervalNode x) {
    IntervalNode y = x.left;
    x.left = y.right;
    if (y.right != null) {
      y.right.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) {
      root = y;
    } else if (x == x.parent.right) {
      x.parent.right = y;
    } else {
      x.parent.left = y;
    }
    y.right = x;
    x.parent = y;
    maxCalculate(x);
    maxCalculate(x.parent);
  }

  // TODO implement functions: predecessor

  protected static class IntervalNode {

    private int low;
    private int high;
    private int max;
    private boolean color; // when true then color is Red, otherwise Black
    private IntervalNode parent;
    private IntervalNode left;
    private IntervalNode right;

    IntervalNode(int low, int high) {
      this.low = low;
      this.high = high;
      this.max = high;
    }

    IntervalNode(int low, int high, boolean isRed) {
      this.low = low;
      this.high = high;
      this.max = high;
      this.color = isRed;
    }

    public int value() {
      return this.low;
    }

    public IntervalNode left() {
      return this.left;
    }

    public IntervalNode right() {
      return this.right;
    }

    public int max() {
      return max;
    }

    public int setMax(int max) {
      return this.max = max;
    }

    public IntervalNode parent() {
      return this.parent;
    }

    public boolean isRed() {
      return this.color;
    }

    public void setLeft(IntervalNode left) {
      this.left = left;
      left.parent = this;
    }

    public void setRight(IntervalNode right) {
      this.right = right;
      right.parent = this;
    }

    public void setParent(IntervalNode parent) {
      this.parent = parent;
    }

    public void setRedColor() {
      this.color = true;
    }

    public void setBlackColor() {
      this.color = false;
    }

    public boolean equals(IntervalNode b) {
      return this.low == b.value() && this.high == b.high;
    }
  }
}
