package com.alexprut.algo.algorithms.graph;

import com.alexprut.algo.datastructures.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {

  /**
   * Dijkstra’s algorithm solves the single-source shortest-paths problem on a weighted, directed
   * graph for the case in which all edge weights are nonnegative
   *
   * <p>Time complexity: O(|E| + |V|log|V|)
   *
   * <p>Space complexity: TODO
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm">https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm</a>
   * @param adj
   * @param n
   * @param start
   * @return
   */
  public static int[] dijkstra(ArrayList<ArrayList<Pair<Integer, Integer>>> adj, int n, int start) {
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
    int counter = 0;
    while (!minHeap.isEmpty() && counter < n) {
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
