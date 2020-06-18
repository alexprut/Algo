package com.alexprut.algo.datastructures;

/**
 * A red-black tree is a binary tree that satisfies the following red-black properties: 1) Every
 * node is either red or black 2) The root is black 3) Every leaf (NULL) is black 4) If a node is
 * red, then both its children are black 5) For each node, all simple paths from the node to
 * descendant leaves contain the same number of black nodes.
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Red-black_tree">https://en.wikipedia.org/wiki/Red-black_tree</a>
 */
public class RedBlackTree<T extends Comparable> {

  protected Node<T> root;
  private int size;

  RedBlackTree() {}

  /**
   * Time complexity: O(logn)
   *
   * @param value
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
   * Time complexity: O(logn)
   *
   * @param x
   */
  protected void insertFixup(Node<T> x) {
    while (x.parent != null && x.parent.isRed()) {
      if (x.parent == x.parent.parent.left) {
        Node y = x.parent.parent.right;
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
   * Time complexity: O(logn)
   *
   * @param value
   * @return
   */
  public boolean search(T value) {
    return search(root, value) != null;
  }

  /**
   * Time complexity: O(logn)
   *
   * @param root
   * @param value
   * @return
   */
  public Node<T> search(Node<T> root, T value) {
    Node tmp = root;
    while (tmp != null) {
      if (tmp.value.compareTo(value) == 0) {
        return tmp;
      }
      tmp = (value.compareTo(tmp.value) < 0) ? tmp.left : tmp.right;
    }

    return null;
  }

  /**
   * Time complexity: O(logn)
   *
   * @param value
   */
  public void delete(T value) {
    delete(search(root, value));
    size--;
  }

  /**
   * Time complexity: O(logn)
   *
   * @param z
   */
  public void delete(Node<T> z) {
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
    if (x != null && !isOriginalColorRed) {
      deleteFixup(x);
    }
  }

  /**
   * TODO
   *
   * @param x
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
   * Replaces the subtree rooted at node `u` with the subtree rooted at node `v`
   *
   * <p>Time complexity: O(1)
   *
   * @param u
   * @param v
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
   * Time complexity: O(logn) if the tree is balanced
   *
   * @return
   */
  public Node<T> minimum() {
    return minimum(root);
  }

  /**
   * Time complexity: O(logn) if the tree is balanced
   *
   * @return
   */
  public Node<T> maximum() {
    return maximum(root);
  }

  /**
   * Time complexity: O(logn) if the tree is balanced
   *
   * @param node
   * @return
   */
  private Node<T> minimum(Node<T> node) {
    if (node == null || node.left == null) {
      return node;
    }
    return minimum(node.left);
  }

  /**
   * Time complexity: O(logn) if the tree is balanced
   *
   * @param node
   * @return
   */
  private Node<T> maximum(Node<T> node) {
    if (node == null || node.right == null) {
      return node;
    }
    return maximum(node.right);
  }

  /**
   * Time complexity: O(logn) if the tree is balanced
   *
   * @param node
   * @return
   */
  private Node<T> successor(Node<T> node) {
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
   * Time complexity: Θ(1)
   *
   * @return
   */
  public int size() {
    return this.size;
  }

  /**
   * Time complexity: Θ(1)
   *
   * @param x
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
   * Time complexity: Θ(1)
   *
   * @param x
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

  protected static class Node<T extends Comparable> {

    private final T value;
    private boolean color; // when true then color is Red, otherwise Black
    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;

    Node(T value) {
      this.value = value;
    }

    Node(T value, boolean isRed) {
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

    public void setLeft(Node<T> left) {
      this.left = left;
      left.parent = this;
    }

    public void setRight(Node<T> right) {
      this.right = right;
      right.parent = this;
    }

    public void setParent(Node<T> parent) {
      this.parent = parent;
    }

    public void setRedColor() {
      this.color = true;
    }

    public void setBlackColor() {
      this.color = false;
    }

    public boolean equals(Node<T> b) {
      return this.value.compareTo(b.value()) == 0;
    }
  }
}
