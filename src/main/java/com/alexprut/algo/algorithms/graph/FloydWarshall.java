package com.alexprut.algo.algorithms.graph;

public class FloydWarshall {

  /**
   * Find the shortest path between any pair of nodes. Find shortest paths in a weighted graph with
   * positive or negative edge weights (but with no negative cycles).
   *
   * <p>Example:
   *
   * <pre>
   *          3           1
   *  node0 ←---→ node1 ----→ node4
   *    ↑           |
   *  2 |           | -1
   *    ↓           ↓
   *  node3 ←---→ node2
   *          2
   *
   * The minimum shortest path between any pair of nodes is:
   * {{0,3,2,2,4},
   * {3,0,-1,1,1},
   * {4,7,0,2,8},
   * {2,5,2,0,6},
   * {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0}}
   * </pre>
   *
   * <p>Time complexity: Θ(V^3)
   *
   * <p>Space complexity: Θ(V^2)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm">https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm</a>
   * @param adj the adjacency-matrix representation of the graph
   * @return the shortest path between any pair of nodes
   */
  public static int[][] floydWarshall(int[][] adj) {
    int n = adj.length;
    int[][] dp = adj;
    for (int i = 0; i < n; i++) {
      dp[i][i] = 0;
    }

    for (int k = 0; k < n; k++) {
      int[][] tmp = new int[n][n];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          tmp[i][j] =
              Math.min(
                  dp[i][j],
                  (Math.max(dp[i][k], dp[k][j]) == Integer.MAX_VALUE)
                      ? Integer.MAX_VALUE
                      : dp[i][k] + dp[k][j]);
        }
      }
      dp = tmp;
    }

    return dp;
  }
}
