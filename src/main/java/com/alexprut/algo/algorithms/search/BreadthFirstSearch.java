package com.alexprut.algo.algorithms.search;

import com.alexprut.algo.datastructures.Queue;
import java.util.Arrays;
import java.util.List;

/**
 * Breadth-first search (BFS) is an algorithm for traversing or searching tree or graph data
 * structures. It starts at the tree root (or some arbitrary node of a graph), and explores all of
 * the neighbor nodes at the present depth prior to moving on to the nodes at the next depth level.
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Breadth-first_search">https://en.wikipedia.org/wiki/Breadth-first_search</a>
 */
public class BreadthFirstSearch {

  private BreadthFirstSearch() {}

  /**
   * Given an unweighted directed or undirected graph, finds the shortest path between the root and
   * all other nodes.
   *
   * <p>Example:
   *
   * <pre>
   *     node3 — node2 — node1
   *       |
   *     node4 — node6
   *     /   \
   * node5   node7
   *
   * The shortest path between is [0,1,2,3,4,4,4]
   * </pre>
   *
   * <p>Time complexity: O(V+E)
   *
   * <p>Space complexity: O(V)
   *
   * @param adj the adjacency-matrix representation of the graph
   * @param start the root
   * @return the shortest path between the root and all other nodes
   */
  public static int[] shortestPath(List<List<Integer>> adj, int start) {
    try {
      int[] shortestPaths = new int[adj.size()];
      boolean[] isVisited = new boolean[adj.size()];
      Arrays.fill(shortestPaths, Integer.MAX_VALUE);
      Queue<Integer> queue = new Queue<>();
      queue.enqueue(start);
      isVisited[start] = true;
      shortestPaths[start] = 0;
      while (!queue.empty()) {
        int edge = queue.dequeue();
        for (int i = 0; i < adj.get(edge).size(); i++) {
          int y = adj.get(edge).get(i);
          if (!isVisited[y]) {
            isVisited[y] = true;
            shortestPaths[y] = shortestPaths[edge] + 1;
            queue.enqueue(y);
          }
        }
      }

      return shortestPaths;
    } catch (Exception e) {
      return new int[0];
    }
  }

  /**
   * Given an unweighted directed or undirected graph, computes the predecessor sub-graph.
   *
   * <p>Example:
   *
   * <pre>
   *     node3 — node2 — node1
   *       |
   *     node4 — node6
   *     /   \   /
   * node5 - node7
   *
   * The predecessor between is [0,1,2,3,4,4,4]
   *
   *     node3 — node2 — node1
   *       |
   *     node4 — node6
   *     /   \
   * node5  node7
   * </pre>
   *
   * <p>Time complexity: O(V+E)
   *
   * <p>Space complexity: O(V)
   *
   * @param adj the adjacency-matrix representation of the graph
   * @param start the root
   * @return return the predecessor sub-graph
   */
  public static int[] predecessorSubgraph(List<List<Integer>> adj, int start) {
    try {
      int[] parent = new int[adj.size()];
      boolean[] isVisited = new boolean[adj.size()];
      Arrays.fill(parent, Integer.MAX_VALUE);
      Queue<Integer> queue = new Queue<>();
      queue.enqueue(start);
      isVisited[start] = true;
      while (!queue.empty()) {
        int edge = queue.dequeue();
        for (int i = 0; i < adj.get(edge).size(); i++) {
          int y = adj.get(edge).get(i);
          if (!isVisited[y]) {
            isVisited[y] = true;
            parent[y] = edge;
            queue.enqueue(y);
          }
        }
      }

      return parent;
    } catch (Exception e) {
      return new int[0];
    }
  }
}
