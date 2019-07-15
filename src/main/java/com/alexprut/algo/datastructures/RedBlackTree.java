package com.alexprut.algo.datastructures;

/**
 * A red-black tree is a binary tree that satisfies the following red-black properties: 1) Every
 * node is either red or black 2) The root is black 3) Every leaf (NULL) is black 4) If a node is
 * red, then both its children are black 5) For each node, all simple paths from the node to
 * descendant leaves contain the same number of black nodes
 */
public class RedBlackTree {

  protected Node root;
  private int size;

  RedBlackTree() {}

  /** Time complexity: O(logn) */
  public void insert(int value) {
    Node x = new Node(value, true);
    Node p = null;
    Node tmp = this.root;

    while (tmp != null) {
      p = tmp;
      if (x.value() < tmp.value) {
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
    insertFixup(x);
    size++;
  }

  /** Time complexity: O(logn) */
  protected void insertFixup(Node x) {
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
        Node y = x.parent.parent.left;
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
  public boolean search(int value) {
    return search(root, value) != null;
  }

  /** Time complexity: O(logn) */
  public Node search(Node root, int value) {
    Node tmp = root;
    while (tmp != null) {
      if (tmp.value == value) {
        return tmp;
      }
      tmp = (value < tmp.value) ? tmp.left : tmp.right;
    }

    return null;
  }

  /** Time complexity: O(logn) */
  public void delete(int value) {
    delete(search(root, value));
    size--;
  }

  /** Time complexity: O(logn) */
  public void delete(Node z) {
    Node x;
    Node y = z;
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
    if (x != null && !isOriginalColorRed) {
      deleteFixup(x);
    }
  }

  protected void deleteFixup(Node x) {
    while (x != root && !x.isRed()) {
      if (x == x.parent.left) {
        Node w = x.parent.right;
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
        Node w = x.parent.left;
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
   */
  protected void transplant(Node u, Node v) {
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
  public Node minimum() {
    return minimum(root);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  public Node maximum() {
    return maximum(root);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  private Node minimum(Node node) {
    if (node == null || node.left == null) {
      return node;
    }
    return minimum(node.left);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  private Node maximum(Node node) {
    if (node == null || node.right == null) {
      return node;
    }
    return maximum(node.right);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  private Node successor(Node node) {
    if (node.right != null) {
      return minimum(node.right);
    }
    Node y = node.parent;
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

  /** Time complexity: Θ(1) */
  protected void leftRotation(Node x) {
    Node y = x.right;
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

  /** Time complexity: Θ(1) */
  protected void rightRotation(Node x) {
    Node y = x.left;
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

  protected static class Node {

    private int value;
    private boolean color; // when true then color is Red, otherwise Black
    private Node parent;
    private Node left;
    private Node right;

    Node(int value) {
      this.value = value;
    }

    Node(int value, boolean isRed) {
      this.value = value;
      this.color = isRed;
    }

    public int value() {
      return this.value;
    }

    public Node left() {
      return this.left;
    }

    public Node right() {
      return this.right;
    }

    public Node parent() {
      return this.parent;
    }

    public boolean isRed() {
      return this.color;
    }

    public void setLeft(Node left) {
      this.left = left;
      left.parent = this;
    }

    public void setRight(Node right) {
      this.right = right;
      right.parent = this;
    }

    public void setParent(Node parent) {
      this.parent = parent;
    }

    public void setRedColor() {
      this.color = true;
    }

    public void setBlackColor() {
      this.color = false;
    }

    public boolean equals(Node b) {
      return this.value == b.value();
    }
  }
}
