package com.alexprut.algo.algorithms.graph;

import com.alexprut.algo.datastructures.Pair;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {

  /**
   * Dijkstra’s algorithm solves the single-source shortest-paths problem on a weighted,
   * directed graph for the case in which all edge weights are nonnegative
   *
   * Time complexity: O(|E| + |V|log|V|)
   *
   * Space complexity:
   */
  public int[] dijkstra(ArrayList<ArrayList<Integer>> adj, int n, int start) {
    class CostNodePair extends Pair<Integer, Integer> implements Comparable<CostNodePair> {

      CostNodePair(int cost, int node) {
        super(cost, node);
      }

      public int compareTo(CostNodePair b) {
        return (this.first() < b.first()) ? -1 : 1;
      }
    }

    int[] distance = new int[n];
    for (int i = 0; i < distance.length; i++) {
      distance[i] = Integer.MAX_VALUE;
    }
    boolean[] visited = new boolean[n];
    // TODO use MinHeap or FibonacciHeap
    PriorityQueue<CostNodePair> minHeap = new PriorityQueue<>();
    minHeap.add(new CostNodePair(0, start));
    distance[start] = 0;
    int counter = 0;
    while (!minHeap.isEmpty() && counter < n) {
      CostNodePair node = minHeap.poll();
      if (!visited[node.second()]) {
        visited[node.second()] = true;
        for (int i = 0; i < adj.get(node.second()).size(); i++) {
          if (!visited[adj.get(node.second()).get(i)]) {
            if (adj.get(node.second()).get(adj.get(node.second()).get(i)) < distance[adj.get(node.second()).get(i)]) {
              distance[adj.get(node.second()).get(i)] = adj.get(node.second()).get(adj.get(node.second()).get(i));
            }
            minHeap.add(new CostNodePair(distance[adj.get(node.second()).get(i)], adj.get(node.second()).get(i)));
          }
        }
      }
    }

    return distance;
  }
}