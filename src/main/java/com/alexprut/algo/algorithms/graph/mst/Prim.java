package com.alexprut.algo.algorithms.graph.mst;

import com.alexprut.algo.datastructures.Pair;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prim {

  /**
   * Finds the minimum spanning tree (MST) of an undirected graph.
   *
   * <p>Time complexity: O(|E|log|V|)
   *
   * <p>Space complexity: TODO
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Prim%27s_algorithm">https://en.wikipedia.org/wiki/Prim%27s_algorithm</a>
   * @param adj
   * @param n
   * @param start
   * @return
   */
  public static int[] prim(ArrayList<ArrayList<Pair<Integer, Integer>>> adj, int n, int start) {
    class CostNodePair extends Pair<Integer, Integer> implements Comparable<CostNodePair> {

      CostNodePair(int cost, int node) {
        super(cost, node);
      }

      public int compareTo(CostNodePair b) {
        return (this.first() < b.first()) ? -1 : 1;
      }
    }

    int[] parent = new int[n];
    parent[start] = -1;
    int[] key = new int[n];
    for (int i = 0; i < key.length; i++) {
      key[i] = Integer.MAX_VALUE;
    }
    key[start] = 0;
    boolean[] visited = new boolean[n];
    // TODO use MinHeap or FibonacciHeap
    PriorityQueue<CostNodePair> minHeap = new PriorityQueue<>();
    minHeap.add(new CostNodePair(0, start));
    int counter = 0;
    while (!minHeap.isEmpty() && counter < n) {
      CostNodePair node = minHeap.poll();
      int x = node.second();
      counter++;
      if (!visited[x]) {
        visited[x] = true;

        for (int i = 0; i < adj.get(x).size(); i++) {
          int y = adj.get(x).get(i).first();
          int w = adj.get(x).get(i).second();

          if (!visited[y]) {
            if (w < key[y]) {
              key[y] = w;
              parent[y] = x;
            }
            minHeap.add(new CostNodePair(key[y], y));
          }
        }
      }
    }

    return parent;
  }
}
