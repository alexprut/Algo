package com.alexprut.algo.algorithms.graph.mst;

import com.alexprut.algo.datastructures.Pair;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prim {

  /**
   * Finds the minimum spanning tree (MST) of an undirected graph
   *
   * Time complexity: O(|E|log|V|)
   *
   * Space complexity: TODO
   */
  public static int[] prim(ArrayList<ArrayList<Integer>> adj, int n, int start) {
    class CostNodePair extends Pair<Integer, Integer> implements Comparable<CostNodePair> {

      CostNodePair(int cost, int node) {
        super(cost, node);
      }

      public int compareTo(CostNodePair b) {
        return (this.first() < b.first()) ? -1 : 1;
      }
    }

    int[] parent = new int[n];
    int[] key = new int[n];
    for (int i = 0; i < key.length; i++) {
      key[i] = Integer.MAX_VALUE;
    }
    boolean[] visited = new boolean[n];
    // TODO use MinHeap or FibonacciHeap
    PriorityQueue<CostNodePair> minHeap = new PriorityQueue<>();
    minHeap.add(new CostNodePair(0, start));
    parent[start] = -1;
    key[start] = 0;
    int counter = 0;
    while (!minHeap.isEmpty() && counter < n) {
      CostNodePair node = minHeap.poll();
      if (!visited[node.second()]) {
        visited[node.second()] = true;
        for (int i = 0; i < adj.get(node.second()).size(); i++) {
          if (!visited[adj.get(node.second()).get(i)]) {
            if (adj.get(node.second()).get(adj.get(node.second()).get(i)) < key[adj.get(node.second()).get(i)]) {
              key[adj.get(node.second()).get(i)] = adj.get(node.second()).get(adj.get(node.second()).get(i));
              parent[node.second()] = adj.get(node.second()).get(i);
            }
            minHeap.add(new CostNodePair(key[adj.get(node.second()).get(i)], adj.get(node.second()).get(i)));
          }
        }
      }
    }

    return parent;
  }
}
