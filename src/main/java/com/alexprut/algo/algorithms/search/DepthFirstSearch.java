package com.alexprut.algo.algorithms.search;

import com.alexprut.algo.datastructures.Stack;
import java.util.ArrayList;

/**
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Depth-first_search">https://en.wikipedia.org/wiki/Depth-first_search</a>
 */
public class DepthFirstSearch {

  // TODO stronglyConnectedComponents

  /**
   * TODO
   *
   * @param adj
   * @param start
   * @return
   * @throws Exception
   */
  public int countForest(ArrayList<ArrayList<Integer>> adj, int start) throws Exception {
    boolean[] visited = new boolean[adj.size()];
    int counter = 0;
    for (boolean node : visited) {
      if (!node) {
        counter++;
        dfsCountForest(adj, start, visited);
      }
    }
    return counter;
  }

  /**
   * TODO
   *
   * @param adj
   * @param start
   * @param visited
   * @throws Exception
   */
  private void dfsCountForest(ArrayList<ArrayList<Integer>> adj, int start, boolean[] visited)
      throws Exception {
    Stack<Integer> stack = new Stack<>();
    stack.push(start);
    visited[start] = true;
    while (!stack.empty()) {
      int node = stack.pop();
      for (int i = 0; i < adj.get(node).size(); i++) {
        int y = adj.get(node).get(i);
        if (!visited[y]) {
          visited[y] = true;
          stack.push(y);
        }
      }
    }
  }

  /**
   * TODO
   *
   * @param adj
   * @param start
   * @return
   * @throws Exception
   */
  public boolean hasCycle(ArrayList<ArrayList<Integer>> adj, int start) throws Exception {
    boolean[] visited = new boolean[adj.size()];
    for (boolean node : visited) {
      if (!node) {
        boolean hasCycle = dfsHasCycle(adj, start, visited);
        if (hasCycle) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * TODO
   *
   * @param adj
   * @param start
   * @param visited
   * @return
   * @throws Exception
   */
  private boolean dfsHasCycle(ArrayList<ArrayList<Integer>> adj, int start, boolean visited[])
      throws Exception {
    Stack<Integer> stack = new Stack<>();
    stack.push(start);
    while (!stack.empty()) {
      int node = stack.pop();
      if (visited[node]) {
        return true;
      }
      visited[node] = true;
      for (int i = 0; i < adj.get(node).size(); i++) {
        int y = adj.get(node).get(i);
        if (!visited[y]) {
          stack.push(y);
        }
      }
    }
    return false;
  }

  /**
   * TODO
   *
   * @param adj
   * @return
   * @throws Exception
   */
  public boolean isDAG(ArrayList<ArrayList<Integer>> adj) throws Exception {
    return !hasCycle(adj, 0);
  }

  /**
   * TODO
   *
   * @param adj
   * @return
   * @throws Exception
   */
  public ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) throws Exception {
    if (!isDAG(adj)) {
      throw new Exception("Graph is not a DAG");
    }

    boolean[] visited = new boolean[adj.size()];
    ArrayList<Integer> topologicalSort = new ArrayList<>();
    for (int i = 0; i < adj.size(); i++) {
      if (!visited[i]) {
        dfsTopologicalSort(adj, i, visited, topologicalSort);
      }
    }
    return topologicalSort;
  }

  /**
   * TODO
   *
   * @param adj
   * @param node
   * @param visited
   * @param topologicalSort
   * @throws Exception
   */
  private void dfsTopologicalSort(
      ArrayList<ArrayList<Integer>> adj,
      int node,
      boolean visited[],
      ArrayList<Integer> topologicalSort)
      throws Exception {
    for (int i = 0; i < adj.get(node).size(); i++) {
      int y = adj.get(node).get(i);
      if (!visited[y]) {
        dfsTopologicalSort(adj, y, visited, topologicalSort);
      }
    }
    topologicalSort.add(node);
    visited[node] = true;
  }
}
