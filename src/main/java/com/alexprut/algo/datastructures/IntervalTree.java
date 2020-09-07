package com.alexprut.algo.datastructures;

/**
 * An interval tree is a tree data structure to hold intervals. It allows one to efficiently find
 * all intervals that overlap with any given interval or point. A closed interval is an ordered pair
 * of real numbers [t1;t2], with t1 less or equal t2. This implementation uses closed intervals. It
 * can also be used for point queries - similar to {@link SegmentTree}. The Interval Tree data
 * structure is implemented by using a {@link RedBlackTree}.
 *
 * <p>Example:
 *
 * <pre>
 * B = Black
 * R = Red
 * a = interval start
 * b = interval end
 * m = max
 * c = R or B
 *
 * [a,b],m,c
 *
 *                     [16,21],30,B
 *             /                         \
 *       [8,9],23,R                    [25,30],30,R
 *      /         \                   /            \
 *    [5,8],10,B  [15,23],23,B    [17,19],20,B    [26,26],26,B
 *   /         \                            \
 * [0,3],3,R  [6,10],10,R               [19,20],20,R
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Interval_tree">https://en.wikipedia.org/wiki/Interval_tree</a>
 */
public class IntervalTree {

  private IntervalNode root;
  private int size;

  IntervalTree() {}

  /**
   * Get the root node.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @return the root node
   */
  public IntervalNode root() {
    return this.root;
  }

  /**
   * Insert a new interval.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param low start of the interval
   * @param high end of the interval
   */
  public void insert(int low, int high) {
    if (low > high) {
      return;
    }
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
   * Utility method for {@link #insert(int, int)}}. Fixes the red black properties if a violation
   * occurs.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param x the node to apply the fix
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

  /**
   * Search if an interval is within the tree.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param low the start of the interval
   * @param high the end of the interval
   * @return true if the interval is within the tree
   */
  public boolean search(int low, int high) {
    return search(root, new IntervalNode(low, high)) != null;
  }

  /**
   * Given a root node, search if an interval is within the rooted tree.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param root the root node where to start the search
   * @param x the interval to search
   * @return the node containing the interval, otherwise null
   */
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

  /**
   * Search if the interval overlaps with an interval contained within the tree.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param low the start of the interval
   * @param high the end of the interval
   * @return the node that is within the interval
   */
  public IntervalNode find(int low, int high) {
    return find(root, new IntervalNode(low, high));
  }

  /**
   * Search if the interval overlaps with an interval contained within the tree.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param root the root node where to start the search
   * @param x the interval to search
   * @return the node containing the interval, otherwise null
   */
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

  /**
   * Check if two intervals overlap.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * <p>Example:
   *
   * <pre>
   * a: |——|   |—————————|    |——————|      |——————|          |———————|
   * b: |——|    |————|      |—————————|       |—————|   |——————|
   * </pre>
   *
   * @param a first interval
   * @param b second interval
   * @return true if intervals overlap
   */
  public static boolean doOverlap(IntervalNode a, IntervalNode b) {
    return a.low <= b.high && b.low <= a.high;
  }

  // TODO implement findAll function

  /**
   * Delete the specified interval from the tree if exists.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * <p>Time complexity: O(logn)
   *
   * @param low start of the interval
   * @param high end of the interval
   */
  public void delete(int low, int high) {
    delete(search(root, new IntervalNode(low, high)));
    size--;
  }

  /**
   * Utility method for {@link #delete(int, int)}.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param z the node to delete
   */
  public void delete(IntervalNode z) {
    IntervalNode x;
    IntervalNode y = z;
    boolean isOriginalColorRed = y.color;
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
      if (x != null && y.parent == z) {
        x.parent = y;
      } else {
        transplant(y, y.right);
        y.right = z.right;
        if (y.right != null) {
          y.right.parent = y;
        }
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

  /**
   * Utility method for {@link #delete(IntervalNode)}}. Fixes the red black property if it is
   * violated after the deletion of an interval.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param x the node where the fix need to be applied
   */
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
   * Replaces the subtree rooted at node `u` with the subtree rooted at node `v`.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param u to be replaced
   * @param v the one that replaces
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

  /**
   * Get the interval with the minimum lower bound.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @return the minimum interval
   */
  public IntervalNode minimum() {
    return minimum(root);
  }

  /**
   * Get the interval with the maximum lower bound.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @return the maximum element
   */
  public IntervalNode maximum() {
    return maximum(root);
  }

  /**
   * Get the minimum element node.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param node the minimum in the rooted node
   * @return the minimum node
   */
  private IntervalNode minimum(IntervalNode node) {
    if (node == null || node.left == null) {
      return node;
    }
    return minimum(node.left);
  }

  /**
   * Get the maximum element node.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param node the maximum in the rooted node
   * @return the maximum node
   */
  private IntervalNode maximum(IntervalNode node) {
    if (node == null || node.right == null) {
      return node;
    }
    return maximum(node.right);
  }

  /**
   * Get the successor of element node.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param node the current node
   * @return the successor node
   */
  public IntervalNode successor(IntervalNode node) {
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

  /**
   * Get the number of elements contained within the tree.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the size of the tree
   */
  public int size() {
    return this.size;
  }

  /**
   * Computes the max value of a node.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param x the node to compute the max
   */
  protected void maxCalculate(IntervalNode x) {
    x.max = x.high;
    if (x != null && x.left != null) {
      x.max = Math.max(x.max, x.left.max);
    }
    if (x != null && x.right != null) {
      x.max = Math.max(x.max, x.right.max);
    }
  }

  /**
   * Utility method. Fixes the max value given a node.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param x the node to compute the max
   */
  protected void maxFixup(IntervalNode x) {
    if (x != null) {
      maxCalculate(x);
      maxFixup(x.parent);
    }
  }

  /**
   * Utility method. Use the specified element as a pivot and left rotate.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * <p>Example:
   *
   * <pre>
   *   x                           y
   *  / \     leftRotate(x)       / \
   * w   y    ------------→      x   z
   *    / \                     / \
   *   u   z                   w   u
   * </pre>
   *
   * @param x the pivot element
   */
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

  /**
   * Utility method. Use the specified element as a pivot and right rotate.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * <p>Example:
   *
   * <pre>
   *   x                            y
   *  / \     rightRotate(y)       / \
   * w   y    ←-------------      x   z
   *    / \                      / \
   *   u   z                    w   u
   * </pre>
   *
   * @param x the pivot element
   */
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

  // TODO reuse / extend the Red-Black Tree data structure

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

    protected int setMax(int max) {
      return this.max = max;
    }

    public IntervalNode parent() {
      return this.parent;
    }

    public boolean isRed() {
      return this.color;
    }

    protected void setLeft(IntervalNode left) {
      this.left = left;
      left.parent = this;
    }

    protected void setRight(IntervalNode right) {
      this.right = right;
      right.parent = this;
    }

    protected void setParent(IntervalNode parent) {
      this.parent = parent;
    }

    protected void setRedColor() {
      this.color = true;
    }

    protected void setBlackColor() {
      this.color = false;
    }

    public boolean equals(IntervalNode b) {
      return this.low == b.value() && this.high == b.high;
    }
  }
}
