package com.alexprut.algo.algorithms.graph;

import com.alexprut.algo.datastructures.Graph.Edge;
import java.util.List;

public class BellmanFord {

  private BellmanFord() {}

  /**
   * Finds the shortest path between a source and all other nodes in the graph. The graph can be
   * directed or undirected, he weight of the edges may be negative. In case of a negative cycle
   * there is no solution.
   *
   * <p>Example:
   *
   * <pre>
   *          3
   *  node0 ----- node1
   *    |           |
   *  2 |           | -1
   *    |           |
   *  node3 ----- node2
   *          1
   *
   * The shortest path is: [-1,2,3,2]
   *
   *  node0       node1
   *    |           |
   *  2 |           | -1
   *    |           |
   *  node3 ----- node2
   *          1
   * </pre>
   *
   * <p>Time complexity: Θ(|V||E|)
   *
   * <p>Space complexity: Θ(|V|+|E|)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm">https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm</a>
   * @param edges the list of edges
   * @param n the number of nodes
   * @param start the root
   * @return the shortest path between the root and all nodes
   * @throws Exception if there is a negative cycle
   */
  public static int[] bellmanFord(List<Edge> edges, int n, int start) throws Exception {
    int[] distance = new int[n];
    int[] parent = new int[n];
    for (int i = 0; i < n; i++) {
      distance[i] = Integer.MAX_VALUE;
      parent[i] = -1;
    }
    distance[start] = 0;
    for (int i = 0; i < n; i++) {
      for (Edge edge : edges) {
        if (distance[edge.y.value()] > distance[edge.x.value()] + edge.w) {
          distance[edge.y.value()] = distance[edge.x.value()] + edge.w;
          parent[edge.y.value()] = edge.x.value();
        }
      }
    }

    for (Edge edge : edges) {
      if (distance[edge.y.value()] > distance[edge.x.value()] + edge.w) {
        throw new Exception("Graph contains a negative cycle");
      }
    }

    return distance;
  }
}
