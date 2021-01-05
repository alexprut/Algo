package com.alexprut.algo.algorithms.graph;

import com.alexprut.algo.datastructures.FibonacciHeap;
import com.alexprut.algo.datastructures.FibonacciHeap.Node;
import com.alexprut.algo.datastructures.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Dijkstra {

  private Dijkstra() {}

  /**
   * Dijkstra’s algorithm solves the single-source shortest-paths problem on a weighted, directed
   * graph for the case in which all edge weights are non-negative.
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
   * The shortest path is: [-1,3,3,2]
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
   * <p>Space complexity: O(|E|+|V|)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm">https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm</a>
   * @param adj the adjacency-matrix representation of the graph
   * @param n the number of nodes
   * @param start the root
   * @return the shortest path between the root and all nodes
   */
  public static int[] dijkstra(List<List<Pair<Integer, Integer>>> adj, int n, int start) {
    int[] distance = new int[n];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[start] = 0;
    ArrayList<Node<Integer>> indexToNode = new ArrayList<>();
    HashMap<Node<Integer>, Integer> nodeToIndex = new HashMap<>();
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

        if (indexToNode.get(y) != null && w + distance[x] < distance[y]) {
          distance[y] = w + distance[x];
          heap.decreaseKey(indexToNode.get(y), distance[y]);
        }
      }
    }

    return distance;
  }
}
