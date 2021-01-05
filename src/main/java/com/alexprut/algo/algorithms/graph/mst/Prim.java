package com.alexprut.algo.algorithms.graph.mst;

import com.alexprut.algo.datastructures.FibonacciHeap;
import com.alexprut.algo.datastructures.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Prim {

  private Prim() {}

  /**
   * Finds the minimum spanning tree (MST) of a weighted undirected graph.
   *
   * <p>Example:
   *
   * <pre>
   *          3
   *  node0 ----- node1
   *    |           |
   *  2 |           | 6
   *    |           |
   *  node3 ----- node2
   *          1
   *
   * The Minimum Spanning Tree is:
   *
   *          3
   *  node0 ----- node1
   *    |
   *  2 |
   *    |
   *  node3 ----- node2
   *          1
   * </pre>
   *
   * <p>Time complexity: O(|E|+|V|log|V|)
   *
   * <p>Space complexity: O(|V|)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Prim%27s_algorithm">https://en.wikipedia.org/wiki/Prim%27s_algorithm</a>
   * @param adj the adjacency-matrix representation of the graph
   * @param n the number of nodes
   * @param start the root
   * @return the minimum spanning tree
   */
  public static int[] prim(List<List<Pair<Integer, Integer>>> adj, int n, int start) {
    int[] parent = new int[n];
    parent[start] = -1;
    ArrayList<FibonacciHeap.Node<Integer>> indexToNode = new ArrayList<>();
    HashMap<FibonacciHeap.Node<Integer>, Integer> nodeToIndex = new HashMap<>();
    FibonacciHeap<Integer> heap = new FibonacciHeap<>();
    for (int i = 0; i < n; i++) {
      FibonacciHeap.Node<Integer> node = heap.insert(Integer.MAX_VALUE);
      indexToNode.add(node);
      nodeToIndex.put(node, i);
    }
    heap.decreaseKey(indexToNode.get(start), 0);
    while (heap.size() > 0) {
      FibonacciHeap.Node<Integer> node = heap.extractMin();
      int x = nodeToIndex.get(node);
      nodeToIndex.remove(node);
      indexToNode.set(x, null);
      for (int i = 0; i < adj.get(x).size(); i++) {
        int y = adj.get(x).get(i).first();
        int w = adj.get(x).get(i).second();

        if (indexToNode.get(y) != null) {
          if (w < indexToNode.get(y).getKey()) {
            heap.decreaseKey(indexToNode.get(y), w);
            parent[y] = x;
          }
        }
      }
    }

    return parent;
  }
}
