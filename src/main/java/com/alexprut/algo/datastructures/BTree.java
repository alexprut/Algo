package com.alexprut.algo.datastructures;

import java.util.ArrayList;

/**
 * @see <a href="https://en.wikipedia.org/wiki/B-tree">https://en.wikipedia.org/wiki/B-tree</a>
 * @param <T>
 */
public class BTree<T extends Comparable> {

  protected Node<T> root = null;
  protected int t;

  public BTree(int t) {
    // By definition of a B-Tree: the minimum allowed degree is 2
    this.t = Math.max(2, t);
    create();
  }

  /**
   * If the object referred to by x resides on disk, however, then we must perform the operation
   * DISK-READ.x/ to read object x into main memory before we can refer to its attributes. (We as-
   * sume that if x is already in main memory, then DISK-READ.x/ requires no disk accesses; it is a
   * “no-op.”
   */
  protected void diskRead(Node x) {
    // TODO implement method or abstract method
  }

  protected void diskWrite(Node x) {
    // TODO implement method or abstract method
  }

  /** Time complexity: O(1) */
  protected Node<T> allocateNode() {
    return new Node<>();
  }

  /** Time complexity: O(1) */
  protected void create() {
    Node<T> x = allocateNode();
    x.isLeaf = true;
    diskWrite(x);
    root = x;
  }

  /** Time complexity: O(th) = O(tlog_t(n)) */
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
    // TODO implement
    // diskRead(x.children.get(i);
    return search(x.children.get(i), k);
  }

  /** Time complexity: O(th) = O(tlog_t(n)) */
  protected Pair<Node<T>, Integer> getPredecessor(Node<T> x) {
    if (x.isLeaf) {
      return new Pair<>(x, x.key.size() - 1);
    }

    // TODO implement diskRead();
    return getPredecessor(x.children.get(x.children.size() - 1));
  }

  /** Time complexity: O(th) = O(tlog_t(n)) */
  protected Pair<Node<T>, Integer> getSuccessor(Node<T> x) {
    if (x.isLeaf) {
      return new Pair<>(x, 0);
    }

    // TODO implement diskRead();
    return getSuccessor(x.children.get(0));
  }

  /** Time complexity: O(th) = O(tlog_t(n)) */
  public boolean search(T k) {
    return search(root, k) != null;
  }

  /** Time complexity: O(th) = O(tlog_t(n)) */
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

  /** Time complexity: O(th) = O(tlog_t(n)) */
  public boolean delete(T k) {
    return delete(root, k);
  }

  /** O(h) disk access and O(th) = O(tlog_tn) time complexity */
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
