package com.alexprut.algo.datastructures;

import java.util.ArrayList;

public class BTree<T extends Comparable> {

	protected Node<T> root = null;
	protected int t;

	public BTree(int t) {
		// By definition of a B-Tree: the minimum allowed degree is 2
		this.t = Math.min(2, t);
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

	/**
	 * Time complexity: O(1)
	 */
	protected Node<T> allocateNode() {
		return new Node<>();
	}

	/**
	 * Time complexity: O(1)
	 */
	protected void create() {
		Node<T> x = allocateNode();
		x.isLeaf = true;
		x.nKeys = 0;
		diskWrite(x);
		root = x;
	}

	/**
	 * Time complexity: O(th) = O(tlog_t(n))
	 */
	public Pair<Node<T>, Integer> search(Node<T> x, T k) {
		int i = 0;
		while (i < x.nKeys && k.compareTo(x.key.get(i)) > 0) {
			i++;
		}
		if (i < x.nKeys && k.compareTo(x.key.get(i)) == 0) {
			return new Pair<>(x, i);
		}
		if (x.isLeaf) {
			return null;
		}
		// TODO implement diskRead(x.children.get(i);
		return search(x.children.get(i), k);
	}

	/**
	 * O(h) disk access and O(th) = O(tlog_tn) time complexity
	 */
	public void insert(T k) {
		Node<T> tmp = root;
		if (root.nKeys == 2 * t - 1) {
			Node<T> s = allocateNode();
			root = s;
			s.isLeaf = false;
			s.nKeys = 0;
			s.children.add(tmp);
			s.children.add(null);
			s.key.add(null);
			splitChild(s, 0);
			insertNonFull(s, k);
		} else {
			insertNonFull(tmp, k);
		}
	}

	public void splitChild(Node<T> x, int i) {
		Node<T> newNode = allocateNode();
		Node<T> toSplitNode = x.children.get(i);
		newNode.isLeaf = toSplitNode.isLeaf;
		newNode.nKeys = t - 1;
		for (int j = 0; j < t - 1; j++) {
			newNode.key.add(
					toSplitNode.key.get(j + t)
			);
		}
		if (!toSplitNode.isLeaf) {
			for (int j = 0; j < t; j++) {
				newNode.children.add(
						toSplitNode.children.get(j + t)
				);
			}
		}
		toSplitNode.nKeys = t - 1;
		for (int j = x.nKeys; j > i + 1; j--) {
			x.children.set(
					j + 1,
					x.children.get(j)
			);
		}
		x.children.set(i + 1, newNode);
		for (int j = x.nKeys; j > i; j--) {
			x.key.set(j + 1, x.key.get(j));
		}
		x.key.set(i, toSplitNode.key.get(t));
		x.nKeys++;
		diskWrite(toSplitNode);
		diskWrite(newNode);
		diskWrite(x);
	}

	public void insertNonFull(Node<T> x, T k) {
		int i = x.nKeys - 1;
		if (x.isLeaf) {
			x.key.add(null);
			while (i >= 0 && k.compareTo(x.key.get(i)) < 0) {
				x.key.set(
						i + 1,
						x.key.get(i)
				);
				i--;
			}
			x.key.set(i + 1, k);
			x.nKeys++;
			diskWrite(x);
		} else {
			while (i >= 1 && k.compareTo(x.key.get(i)) < 0) {
				i--;
			}
			i++;
			diskRead(x.children.get(i));
			if (x.children.get(i).nKeys == 2 * t - 1) {
				splitChild(x, i);
				if (k.compareTo(x.key.get(i)) > 0) {
					i++;
				}
			}
			insertNonFull(x.children.get(i), k);
		}
	}

	protected static class Node<T> {

		private int nKeys;
		private boolean isLeaf;
		private ArrayList<T> key = new ArrayList<>();
		private ArrayList<Node<T>> children = new ArrayList<>();

		Node() {
		}
	}
}
