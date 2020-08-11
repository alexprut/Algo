package com.alexprut.algo.datastructures;

import java.util.ArrayList;

/**
 * A B-tree is a self-balancing tree data structure that maintains sorted data and allows searches,
 * sequential access, insertions, and deletions in logarithmic time. The B-tree generalizes the
 * binary search tree, allowing for nodes with more than two children. B-trees are designed to work
 * well on disks or other direct-access secondary storage devices. B-trees are similar to {@link
 * RedBlackTree}, but they are better at minimizing disk I/O operations.
 *
 * <p>A B-tree T is a rooted tree (whose root is T.root) having the following properties:
 *
 * <ol>
 *   <li>Every node x has the following attributes:
 *       <ol>
 *         <li>x.n, the number of keys currently stored in node x
 *         <li>the x.n keys themselves, x.key1,x.key2,...,x.keyn, stored in increasing order, so
 *             that x.key1 &lt;= x:key2 &lt;= ... &lt;= x.keyn
 *         <li>x.leaf, a boolean value that is TRUE if x is a leaf and FALSE if x is an internal
 *             node
 *       </ol>
 *   <li>Each internal node x also contains x.n + 1 pointers x.c0,x.c1,...,x.cn to its children.
 *       Leaf nodes have no children, and so their 'c' attributes are undefined
 *   <li>The keys x.keyi separate the ranges of keys stored in each subtree: if ki is any key stored
 *       in the subtree with root x.ci, then: k1 &lt;= x.key1 &lt;= k2 &lt;= x.key2 &lt;= ... &lt;=
 *       x.keyn
 *   <li>All leaves have the same depth, which is the tree’s height h
 *   <li>Nodes have lower and upper bounds on the number of keys they can contain. We express these
 *       bounds in terms of a fixed integer t =&gt; 2 called the minimum degree of the B-tree:
 *       <ol>
 *         <li>Every node other than the root must have at least t - 1 keys. Every internal node
 *             other than the root thus has at least t children. If the tree is nonempty, the root
 *             must have at least one key
 *         <li>Every node may contain at most 2t - 1 keys. Therefore, an internal node may have at
 *             most 2t children. We say that a node is full if it contains exactly 2t - 1 keys
 *       </ol>
 * </ol>
 *
 * <p>Example:
 *
 * <pre>
 *                -------- [P] --------
 *               /                     \
 *       ---[C,G,M]-----             --[T,X]----
 *      /     |  \      \           /    |      \
 * [A,B] [D,E,F] [J,K,L] [N,O]   [Q,R,S] [U,V] [Y,Z]
 * </pre>
 *
 * @see <a href="https://en.wikipedia.org/wiki/B-tree">https://en.wikipedia.org/wiki/B-tree</a>
 * @param <T>
 */
public class BTree<T extends Comparable<T>> {

  protected Node<T> root = null;
  /**
   * The minimum degree of the B-tree. By definition of a B-Tree: the minimum allowed degree is
   * =&gt; 2.
   */
  protected int t;

  public BTree(int t) {
    // By definition of a B-Tree: the minimum allowed degree is 2
    this.t = Math.max(2, t);
    create();
  }

  /**
   * If the object is currently in the computer’s main memory, then we can refer to the attributes
   * of the object as usual (for example x.key). If the object referred to by x resides on disk,
   * however, then we must perform the operation diskRead(x) to read object x into main memory
   * before we can refer to its attributes.
   *
   * @param x the node to read
   */
  protected void diskRead(Node<T> x) {
    // FIXME implement method or abstract method
  }

  /**
   * Similar to {@link #diskRead(Node)}, used to save any changes that have been made to the
   * attributes of object x.
   *
   * @param x the node to write
   */
  protected void diskWrite(Node<T> x) {
    // FIXME implement method or abstract method
  }

  /**
   * Allocates one disk page to be used.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @return the new allocated node
   */
  protected Node<T> allocateNode() {
    // TODO implement method or abstract method
    return new Node<>();
  }

  /**
   * Create the newly tree.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   */
  protected void create() {
    Node<T> x = allocateNode();
    x.isLeaf = true;
    diskWrite(x);
    root = x;
  }

  /**
   * Searches if a key is within the tree and return the node containing that key if present.
   *
   * <p>Time complexity: O(th) = O(tlog_t(n))
   *
   * <p>Space complexity: O(h) = O(log_t(n))
   *
   * @param x the node to start the search
   * @param k the key to search
   * @return the ordered pair (y,i) consisting of a node y and an index i such that y.keyi = k
   */
  protected Pair<Node<T>, Integer> search(Node<T> x, T k) {
    int i = 0;
    while (i < x.key.size() && k.compareTo(x.key.get(i)) > 0) {
      i++;
    }
    if (i < x.key.size() && k.compareTo(x.key.get(i)) == 0) {
      return new Pair<>(x, i);
    }
    if (x.isLeaf) {
      return null;
    }
    // TODO implement diskRead(x.children.get(i);
    return search(x.children.get(i), k);
  }

  /**
   * Helper method used in {@link #delete(Node, Comparable)} Finds and returns the predecessor of a
   * node.
   *
   * <p>Time complexity: O(th) = O(tlog_t(n))
   *
   * <p>Space complexity: O(h)
   *
   * @param x the node
   * @return the predecessor node
   */
  protected Pair<Node<T>, Integer> getPredecessor(Node<T> x) {
    if (x.isLeaf) {
      return new Pair<>(x, x.key.size() - 1);
    }

    diskRead(x);
    return getPredecessor(x.children.get(x.children.size() - 1));
  }

  /**
   * Helper method used in {@link #delete(Node, Comparable)} Finds and returns the successor of a
   * node.
   *
   * <p>Time complexity: O(th) = O(tlog_t(n))
   *
   * <p>Space complexity: O(h)
   *
   * @param x the node
   * @return the successor node
   */
  protected Pair<Node<T>, Integer> getSuccessor(Node<T> x) {
    if (x.isLeaf) {
      return new Pair<>(x, 0);
    }

    diskRead(x);
    return getSuccessor(x.children.get(0));
  }

  /**
   * Searches if a key is within the tree and return the node containing that key if present.
   *
   * <p>Time complexity: O(th) = O(tlog_t(n))
   *
   * <p>Space complexity: O(h) = O(log_t(n))
   *
   * @param k the key to search
   * @return true is the key is within the tree
   */
  public boolean search(T k) {
    return search(root, k) != null;
  }

  /**
   * Delete a key from the root node.
   *
   * <p>Time complexity: O(th) = O(tlog_t(n))
   *
   * <p>Space complexity: O(h)
   *
   * @param node the root node
   * @param k the key to delete
   * @return true if the key was deleted
   */
  protected boolean delete(Node<T> node, T k) {
    boolean isInRoot = false;
    int index = 0;
    int childIndex = 0;
    for (int i = 0; i < node.key.size(); i++) {
      if (node.key.get(i).compareTo(k) < 0) {
        childIndex++;
      }
      if (node.key.get(i).equals(k)) {
        isInRoot = true;
        index = i;
      }
    }

    if (node.isLeaf) {
      if (isInRoot) {
        // Case 1
        node.key.remove(index);
        return true;
      }
      return false;
    }

    // Case 2
    if (isInRoot) {
      Node<T> leftChild = node.children.get(index);
      Node<T> rightChild = node.children.get(index + 1);

      // Case 2a
      if (leftChild.key.size() >= t) {
        Pair<Node<T>, Integer> predecessor = getPredecessor(leftChild);
        Node<T> predNode = predecessor.first();
        int predIndex = predecessor.second();
        node.key.set(index, predNode.key.get(predIndex));

        return delete(predecessor.first(), predNode.key.get(predIndex));
      }

      // Case 2b
      if (rightChild.key.size() >= t) {
        Pair<Node<T>, Integer> successor = getSuccessor(rightChild);
        Node<T> succNode = successor.first();
        int succIndex = successor.second();
        node.key.set(index + 1, succNode.key.get(succIndex));

        return delete(succNode, succNode.key.get(succIndex));
      }

      // Case 2c
      leftChild.key.addAll(rightChild.key);
      leftChild.children.addAll(rightChild.children);
      node.key.remove(index);
      node.children.remove(index + 1);

      return true;
    }

    Node<T> child = node.children.get(childIndex);

    // Case 3
    if (child.key.size() <= t - 1) {
      // Case 3a
      if (childIndex - 1 >= 0 && node.children.get(childIndex - 1).key.size() >= t) {
        Node<T> leftChild = node.children.get(childIndex - 1);
        child.key.add(null);
        for (int i = child.key.size() - 1; i > 0; i--) {
          child.key.set(i, child.key.get(i - 1));
        }
        child.key.set(0, node.key.get(childIndex - 1));

        if (!child.isLeaf) {
          child.children.add(null);
          for (int i = child.children.size() - 1; i > 0; i--) {
            child.children.set(i, child.children.get(i - 1));
          }
          child.children.set(0, leftChild.children.get(leftChild.children.size() - 1));
        }

        node.key.set(childIndex - 1, leftChild.key.get(leftChild.key.size() - 1));
        leftChild.key.remove(leftChild.key.size() - 1);
        if (!leftChild.isLeaf) {
          leftChild.children.remove(leftChild.children.size() - 1);
        }

        return delete(child, k);
      }

      if (childIndex + 1 < node.children.size()
          && node.children.get(childIndex + 1).key.size() >= t) {
        Node<T> rightChild = node.children.get(childIndex + 1);
        child.key.add(node.key.get(childIndex));
        if (!rightChild.children.isEmpty()) {
          child.children.add(rightChild.children.get(0));
        }
        node.key.set(childIndex, rightChild.key.get(0));
        rightChild.key.remove(0);
        if (!rightChild.children.isEmpty()) {
          rightChild.children.remove(0);
        }

        return delete(child, k);
      }

      // Case 3b
      if (childIndex - 1 >= 0 && node.children.get(childIndex - 1).key.size() <= t - 1) {
        Node<T> leftChild = node.children.get(childIndex - 1);
        leftChild.key.add(node.key.get(childIndex - 1));
        leftChild.key.addAll(child.key);
        leftChild.children.addAll(child.children);
        node.key.remove(childIndex - 1);
        node.children.remove(childIndex);
        if (node.key.isEmpty()) {
          node.key.addAll(leftChild.key);
          node.children.clear();
          node.children.addAll(leftChild.children);
          if (node.children.isEmpty()) {
            node.isLeaf = true;
          }
          return delete(node, k);
        }
        return delete(leftChild, k);
      }

      if (childIndex + 1 < node.children.size()
          && node.children.get(childIndex + 1).key.size() <= t - 1) {
        Node<T> rightChild = node.children.get(childIndex + 1);
        child.key.add(node.key.get(childIndex));
        child.key.addAll(rightChild.key);
        child.children.addAll(rightChild.children);
        node.key.remove(childIndex);
        node.children.remove(childIndex);
        if (node.key.isEmpty()) {
          node.key.addAll(child.key);
          node.children.clear();
          node.children.addAll(child.children);
          if (node.children.isEmpty()) {
            node.isLeaf = true;
          }
          return delete(node, k);
        }

        return delete(child, k);
      }
    }

    return delete(child, k);
  }

  /**
   * Delete a key from the tree.
   *
   * <p>Time complexity: O(th) = O(tlog_t(n))
   *
   * <p>Space complexity: O(h)
   *
   * @param k the key to delete
   * @return true if the key was deleted
   */
  public boolean delete(T k) {
    return delete(root, k);
  }

  /**
   * Insert a new key value.
   *
   * <p>Time Complexity: O(th) = O(tlog_tn)
   *
   * <p>Space Complexity: O(h)
   *
   * @param k the new key value to insert
   */
  public void insert(T k) {
    Node<T> tmp = root;
    if (root.key.size() == 2 * t - 1) {
      Node<T> s = allocateNode();
      root = s;
      s.isLeaf = false;
      s.children.add(tmp);
      splitChild(s, 0);
      insertNonFull(s, k);
    } else {
      insertNonFull(tmp, k);
    }
  }

  /**
   * Helper method used for {@link #insert(Comparable)}. Since we cannot insert a key into a leaf
   * node that is full, we introduce an operation that splits a full node y (having 2t-1 keys)
   * around its median key.
   *
   * @param parent the parent node
   * @param index the index to do the split
   */
  protected void splitChild(Node<T> parent, int index) {
    Node<T> newNodeToRight = allocateNode();
    Node<T> toSplitNode = parent.children.get(index);
    newNodeToRight.isLeaf = toSplitNode.isLeaf;
    for (int i = 0; i < t - 1; i++) {
      newNodeToRight.key.add(toSplitNode.key.get(i + t));
    }
    for (int i = 0; i < t - 1; i++) {
      toSplitNode.key.remove(toSplitNode.key.size() - 1);
    }
    if (!toSplitNode.isLeaf) {
      for (int i = 0; i < t; i++) {
        newNodeToRight.children.add(toSplitNode.children.get(i + t));
      }
      for (int i = 0; i < t; i++) {
        toSplitNode.children.remove(toSplitNode.children.size() - 1);
      }
    }

    parent.children.add(null);
    for (int i = parent.children.size() - 1; i > index + 1; i--) {
      parent.children.set(i, parent.children.get(i - 1));
    }
    parent.children.set(index + 1, newNodeToRight);

    parent.key.add(null);
    for (int i = parent.key.size() - 1; i > index; i--) {
      parent.key.set(i, parent.key.get(i - 1));
    }
    parent.key.set(index, toSplitNode.key.get(t - 1));
    toSplitNode.key.remove(t - 1);
    diskWrite(toSplitNode);
    diskWrite(newNodeToRight);
    diskWrite(parent);
  }

  /**
   * Helper method used in {@link #insert(Comparable)}. Insert key k into the tree rooted at the non
   * full root node.
   *
   * @param x the node where to insert
   * @param k the key to insert
   */
  public void insertNonFull(Node<T> x, T k) {
    int i = x.key.size() - 1;
    if (x.isLeaf) {
      x.key.add(null);
      while (i >= 0 && k.compareTo(x.key.get(i)) < 0) {
        x.key.set(i + 1, x.key.get(i));
        i--;
      }
      x.key.set(i + 1, k);
      diskWrite(x);
    } else {
      while (i >= 1 && k.compareTo(x.key.get(i)) < 0) {
        i--;
      }
      i++;
      diskRead(x.children.get(i));
      if (x.children.get(i).key.size() == 2 * t - 1) {
        splitChild(x, i);
        if (k.compareTo(x.key.get(i)) > 0) {
          i++;
        }
      }
      insertNonFull(x.children.get(i), k);
    }
  }

  protected static class Node<T> {

    protected boolean isLeaf;
    protected ArrayList<T> key = new ArrayList<>();
    protected ArrayList<Node<T>> children = new ArrayList<>();

    Node() {}
  }
}
