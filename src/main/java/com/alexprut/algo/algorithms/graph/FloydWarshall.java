package com.alexprut.algo.algorithms.graph;

public class FloydWarshall {

  /**
   * Find the shortest path between any pair of nodes.
   *
   * <p>Time complexity: Θ(V^3)
   *
   * <p>Space complexity: Θ(V^2)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm">https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm</a>
   * @param adj
   * @return
   */
  public static int[][] floydWarshall(int[][] adj) {
    int n = adj.length;
    int[][] dp = adj;

    for (int k = 0; k < n; k++) {
      int[][] tmp = adj.clone();
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
