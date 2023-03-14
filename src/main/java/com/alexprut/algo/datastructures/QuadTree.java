package com.alexprut.algo.datastructures;


/**
 * A quadtree is a tree data structure in which each internal node has exactly four children. Quadtrees are the two-dimensional analog of octrees and are most often used to partition a two-dimensional space by recursively subdividing it into four quadrants or regions. The data associated with a leaf cell varies by application, but the leaf cell represents a "unit of interesting spatial information".
 *
 * @see <a "https://en.wikipedia.org/wiki/Quadtree">https://en.wikipedia.org/wiki/Quadtree</a>
 */
public class QuadTree {
}

class Node<T> {
    public T val;
    public boolean isLeaf;
    public Node<T> topLeft;
    public Node<T> topRight;
    public Node<T> bottomLeft;
    public Node<T> bottomRight;


    public Node() {
        this.val = null;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(T val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(T val, boolean isLeaf, Node<T> topLeft, Node<T> topRight, Node<T> bottomLeft, Node<T> bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
};