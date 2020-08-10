package com.alexprut.algo.algorithms.search;

import com.alexprut.algo.datastructures.Stack;
import java.util.ArrayList;

/**
 * Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data
 * structures. The algorithm starts at the root node (selecting some arbitrary node as the root node
 * in the case of a graph) and explores as far as possible along each branch before backtracking.
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Depth-first_search">https://en.wikipedia.org/wiki/Depth-first_search</a>
 */
public class DepthFirstSearch {

  /**
   * Counts the number of isolated sub-graphs.
   *
   * <p>Example:
   *
   * <pre>
   *     node2       node1 — node0
   *       |           |    /
   *     node3        node5
   *     /   \
   * node4   node6
   *
   * There are 2 isolated sub-graphs.
   * </pre>
   *
   * <p>Time complexity: O(V+E)
   *
   * <p>Space complexity: O(V)
   *
   * @param adj the adjacency-matrix representation of the graph
   * @return the number of isolated sub-graphs
   */
  public static int countForest(ArrayList<ArrayList<Integer>> adj) {
    boolean[] visited = new boolean[adj.size()];
    int counter = 0;
    for (int i = 0; i < visited.length; i++) {
      if (!visited[i]) {
        counter++;
        try {
          dfsCountForest(adj, i, visited);
        } catch (Exception e) {
        }
      }
    }
    return counter;
  }

  /**
   * Performs a DFS visit on a graph. Helper method used in {@link #countForest(ArrayList)}.
   *
   * <p>Time complexity: O(V+E)
   *
   * <p>Space complexity: O(V)
   *
   * @param adj the adjacency-matrix representation of the graph
   * @param start the root
   */
  protected static void dfsCountForest(
      ArrayList<ArrayList<Integer>> adj, int start, boolean[] visited) {
    Stack<Integer> stack = new Stack<>();
    stack.push(start);
    visited[start] = true;
    while (!stack.empty()) {
      try {
        int node = stack.pop();
        for (int i = 0; i < adj.get(node).size(); i++) {
          int y = adj.get(node).get(i);
          if (!visited[y]) {
            visited[y] = true;
            stack.push(y);
          }
        }
      } catch (Exception e) {
      }
    }
  }

  /**
   * Check if a directed graph has a cycle.
   *
   * <p>Example:
   *
   * <pre>
   * node1 → node0 → node3
   *    ↑    ↓
   *    node2
   *
   * the graph contains a cycle, i.e. node0 → node2 → node1 → node0 ...
   * </pre>
   *
   * <p>Time complexity: O(V+E)
   *
   * <p>Space complexity: O(V)
   *
   * @param adj the adjacency-matrix representation of the graph
   * @return true if the graph has a cycle
   */
  public static boolean hasCycle(ArrayList<ArrayList<Integer>> adj) {
    boolean[] visited = new boolean[adj.size()];
    int[] epoch = new int[adj.size()];
    for (int i = 0; i < visited.length; i++) {
      if (!visited[i]) {
        try {
          boolean hasCycle = dfsHasCycle(adj, i, visited, epoch);
          if (hasCycle) {
            return true;
          }
        } catch (Exception e) {
        }
      }
    }
    return false;
  }

  /**
   * Performs a DFS visit on a graph. Helper method used in {@link #hasCycle(ArrayList)}.
   *
   * <p>Time complexity: O(V+E)
   *
   * <p>Space complexity: O(V)
   *
   * @param adj the adjacency-matrix representation of the graph
   * @param start the root
   * @param visited flags if or not a node was visited
   * @param epoch of the dfs run
   */
  protected static boolean dfsHasCycle(
      ArrayList<ArrayList<Integer>> adj, int start, boolean[] visited, int[] epoch) {
    Stack<Integer> stack = new Stack<>();
    stack.push(start);
    while (!stack.empty()) {
      try {
        int node = stack.pop();
        visited[node] = true;
        epoch[node] = start;
        for (int i = 0; i < adj.get(node).size(); i++) {
          int y = adj.get(node).get(i);
          if (visited[y] && epoch[y] == start) {
            return true;
          }
          if (!visited[y]) {
            stack.push(y);
          }
        }
      } catch (Exception e) {
      }
    }
    return false;
  }

  /**
   * Checks if a graph is a direct acyclic graph. Uses {@link #hasCycle(ArrayList)}.
   *
   * <p>Example:
   *
   * <pre>
   *  node1 → node2 → node4
   *    ↓
   *  node3
   * </pre>
   *
   * <p>Time complexity: O(V+E)
   *
   * <p>Space complexity: O(V)
   *
   * @param adj the adjacency-matrix representation of the graph
   * @return true if the graph is a DAG
   */
  public static boolean isDAG(ArrayList<ArrayList<Integer>> adj) {
    return !hasCycle(adj);
  }

  /**
   * Get the topological order of a graph. A topological sort of a directed graph is a linear
   * ordering of its vertices such that for every directed edge 'uv' from vertex 'u' to vertex 'v',
   * 'u' comes before 'v' in the ordering.
   *
   * <p>Example:
   *
   * <pre>
   *  node1 → node2 → node4
   *    ↓
   *  node3
   *
   *  The topological sort is: [node1, node2, node4, node3].
   * </pre>
   *
   * <p>Time complexity: O(V+E)
   *
   * <p>Space complexity: O(V)
   *
   * @param adj the adjacency-matrix representation of the graph
   * @return the list of nodes sorted in topological order
   * @throws Exception if the graph is not a DAG
   */
  public static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj)
      throws Exception {
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
   * Performs a DFS visit on a graph. Helper method used in {@link #topologicalSort(ArrayList)}.
   *
   * @param adj the adjacency-matrix representation of the graph
   * @param node the node to visit
   * @param visited flags if or not a node was visited
   * @param topologicalSort the list of nodes sorted in topological order
   */
  protected static void dfsTopologicalSort(
      ArrayList<ArrayList<Integer>> adj,
      int node,
      boolean[] visited,
      ArrayList<Integer> topologicalSort) {
    for (int i = 0; i < adj.get(node).size(); i++) {
      int y = adj.get(node).get(i);
      if (!visited[y]) {
        dfsTopologicalSort(adj, y, visited, topologicalSort);
      }
    }
    topologicalSort.add(node);
    visited[node] = true;
  }

  // TODO stronglyConnectedComponents
}
