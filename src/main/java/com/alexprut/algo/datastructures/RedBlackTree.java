package com.alexprut.algo.datastructures;

/**
 * A Red-Black Tree is a balanced binary search tree. It satisfies the following red-black
 * properties:
 *
 * <ol>
 *   <li>Every node is either red or black.
 *   <li>The root is black.
 *   <li>Every leaf (NULL) is black.
 *   <li>If a node is red, then both its children are black.
 *   <li>For each node, all simple paths from the node to descendant leaves contain the same number
 *       of black nodes.
 * </ol>
 *
 * <p>Example:
 *
 * <pre>
 * B = Black
 * R = Red
 *
 *         11,B
 *        /    \
 *     2,R      14,B
 *    /   \         \
 * 1,B     7,B       15,R
 *        /   \
 *     5,R     8,R
 *    /
 * 4,R
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Red-black_tree">https://en.wikipedia.org/wiki/Red-black_tree</a>
 */
public class RedBlackTree<T extends Comparable<T>> {

  protected Node<T> root;
  private int size;

  RedBlackTree() {}

  /**
   * Get the root node.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @return the root node
   */
  public Node<T> root() {
    return this.root;
  }

  /**
   * Insert a new element.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param value the new element value
   */
  public void insert(T value) {
    Node<T> x = new Node<>(value, true);
    Node<T> p = null;
    Node<T> tmp = this.root;

    while (tmp != null) {
      p = tmp;
      if (x.value().compareTo(tmp.value) < 0) {
        tmp = tmp.left;
      } else {
        tmp = tmp.right;
      }
    }
    x.parent = p;
    if (p == null) {
      root = x;
    } else if (x.value().compareTo(p.value()) < 0) {
      p.setLeft(x);
    } else {
      p.setRight(x);
    }
    insertFixup(x);
    size++;
  }

  /**
   * Utility method for {@link #insert(Comparable)}. Fixes the red black properties if a violation
   * occurs.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param x the node to apply the fix
   */
  protected void insertFixup(Node<T> x) {
    while (x.parent != null && x.parent.isRed()) {
      if (x.parent == x.parent.parent.left) {
        Node<T> y = x.parent.parent.right;
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
        Node<T> y = x.parent.parent.left;
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
   * Search if an element is within the tree.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param value the element value to search
   * @return true if element is within the tree
   */
  public boolean search(T value) {
    return search(root, value) != null;
  }

  /**
   * Given a root node, search if an element is within the rooted tree.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param root the root node where to start the search
   * @param value the element value to search
   * @return the node containing the element value, otherwise null
   */
  public Node<T> search(Node<T> root, T value) {
    Node<T> tmp = root;
    while (tmp != null) {
      if (tmp.value.compareTo(value) == 0) {
        return tmp;
      }
      tmp = (value.compareTo(tmp.value) < 0) ? tmp.left : tmp.right;
    }

    return null;
  }

  /**
   * Delete the specified element from the tree if exists.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param value the element value
   */
  public void delete(T value) {
    delete(search(root, value));
    size--;
  }

  /**
   * Utility method for {@link #delete(Comparable)}.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param z the node to delete
   */
  protected void delete(Node<T> z) {
    Node<T> x;
    Node<T> y = z;
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
    if (x != null && !isOriginalColorRed) {
      deleteFixup(x);
    }
  }

  /**
   * Utility method for {@link #delete(Comparable)}. Fixes the red black property if it is violated
   * after the deletion of an element.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param x the node where the fix need to be applied
   */
  protected void deleteFixup(Node<T> x) {
    while (x != root && !x.isRed()) {
      if (x == x.parent.left) {
        Node<T> w = x.parent.right;
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
        Node<T> w = x.parent.left;
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
  protected void transplant(Node<T> u, Node<T> v) {
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
   * Get the minimum element.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @return the minimum element
   */
  public Node<T> minimum() {
    return minimum(root);
  }

  /**
   * Get the maximum element.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @return the maximum element
   */
  public Node<T> maximum() {
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
  public Node<T> minimum(Node<T> node) {
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
  public Node<T> maximum(Node<T> node) {
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
  public Node<T> successor(Node<T> node) {
    if (node.right != null) {
      return minimum(node.right);
    }
    Node<T> y = node.parent;
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
  protected void leftRotation(Node<T> x) {
    Node<T> y = x.right;
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
  protected void rightRotation(Node<T> x) {
    Node<T> y = x.left;
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
  }

  // TODO implement functions: predecessor

  protected static class Node<T extends Comparable<T>> {

    private final T value;
    private boolean color; // when true then color is Red, otherwise Black
    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;

    protected Node(T value) {
      this.value = value;
    }

    protected Node(T value, boolean isRed) {
      this.value = value;
      this.color = isRed;
    }

    public T value() {
      return this.value;
    }

    public Node<T> left() {
      return this.left;
    }

    public Node<T> right() {
      return this.right;
    }

    public Node<T> parent() {
      return this.parent;
    }

    public boolean isRed() {
      return this.color;
    }

    protected void setLeft(Node<T> left) {
      this.left = left;
      left.parent = this;
    }

    protected void setRight(Node<T> right) {
      this.right = right;
      right.parent = this;
    }

    protected void setParent(Node<T> parent) {
      this.parent = parent;
    }

    protected void setRedColor() {
      this.color = true;
    }

    protected void setBlackColor() {
      this.color = false;
    }

    public boolean equals(Node<T> b) {
      return this.value.compareTo(b.value()) == 0;
    }
  }
}
