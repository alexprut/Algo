package com.alexprut.algo.algorithms.search;

import com.alexprut.algo.datastructures.Queue;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Breadth-first_search">https://en.wikipedia.org/wiki/Breadth-first_search</a>
 */
public class BreadthFirstSearch {

  /**
   * TODO
   *
   * <p>Time complexity: O(V + E)
   *
   * <p>Space complexity: O(V)
   *
   * @param adj
   * @param start
   * @return
   * @throws Exception
   */
  public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int start) throws Exception {
    int[] shortestPaths = new int[adj.size()];
    boolean[] isVisited = new boolean[adj.size()];
    Arrays.fill(shortestPaths, Integer.MAX_VALUE);
    Queue<Integer> queue = new Queue<>();
    queue.enqueue(start);
    isVisited[start] = true;
    shortestPaths[start] = 0;
    while (queue.empty()) {
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
  }

  /**
   * TODO
   *
   * <p>Time complexity: O(V + E)
   *
   * <p>Space complexity: O(V)
   *
   * @param adj
   * @param start
   * @return
   * @throws Exception
   */
  public int[] predecessorSubgraph(ArrayList<ArrayList<Integer>> adj, int start) throws Exception {
    int[] parent = new int[adj.size()];
    boolean[] isVisited = new boolean[adj.size()];
    Arrays.fill(parent, Integer.MAX_VALUE);
    Queue<Integer> queue = new Queue<>();
    queue.enqueue(start);
    isVisited[start] = true;
    while (queue.empty()) {
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
  }
}
