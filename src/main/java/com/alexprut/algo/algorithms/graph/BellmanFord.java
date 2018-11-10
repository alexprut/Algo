package com.alexprut.algo.algorithms.graph;

import com.alexprut.algo.datastructures.Graph.Edge;
import java.util.ArrayList;

public class BellmanFord {

  /**
   * Finds the shortest path between a source and all other nodes in the graph
   *
   * Time complexity: Θ(|V||E|)
   *
   * Space complexity: Θ(|V| + |E|) total
   *
   */
  public static int[] bellmanFord(ArrayList<Edge> edges, int n, int start) throws Exception {
    int[] distance = new int[n];
    int[] parent = new int[n];
    for (int i = 0; i < n; i++) {
      distance[i] = Integer.MAX_VALUE;
      parent[i] = -1;
    }
    distance[start] = 0;
    for (int i = 0; i < n; i++) {
      for (Edge edge : edges) {
        if (distance[edge.y.value()] > distance[edge.x.value()] + edge.w) {
          distance[edge.y.value()] = distance[edge.x.value()] + edge.w;
          parent[edge.y.value()] = edge.x.value();
        }
      }
    }

    for (Edge edge : edges) {
      if (distance[edge.y.value()] > distance[edge.x.value()] + edge.w) {
        throw new Exception("Graph contains a negative cycle");
      }
    }

    return distance;
  }
}
