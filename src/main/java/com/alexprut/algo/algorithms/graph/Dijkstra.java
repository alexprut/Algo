package com.alexprut.algo.algorithms.graph;

import com.alexprut.algo.datastructures.Pair;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

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
    class CostNodePair extends Pair<Integer, Integer> implements Comparable<CostNodePair> {

      CostNodePair(int cost, int node) {
        super(cost, node);
      }

      public int compareTo(CostNodePair b) {
        return (this.first() < b.first()) ? -1 : 1;
      }
    }

    int[] distance = new int[n];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[start] = 0;
    boolean[] visited = new boolean[n];
    // TODO use MinHeap or FibonacciHeap
    PriorityQueue<CostNodePair> minHeap = new PriorityQueue<>();
    minHeap.add(new CostNodePair(0, start));
    while (!minHeap.isEmpty()) {
      CostNodePair node = minHeap.poll();
      int x = node.second();
      if (!visited[x]) {
        visited[x] = true;
        for (int i = 0; i < adj.get(x).size(); i++) {
          int y = adj.get(x).get(i).first();
          int w = adj.get(x).get(i).second();

          if (w + distance[x] < distance[y]) {
            distance[y] = w + distance[x];
          }

          if (!visited[y]) {
            minHeap.add(new CostNodePair(distance[y], y));
          }
        }
      }
    }

    return distance;
  }
}
