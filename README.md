<br />
<br />

<div align="center">
<img src="https://github.com/alexprut/Algo/raw/master/src/main/resources/logo.png" width="400" height="auto"/>

<br />
<br />

<h3>Classic Algorithms and Data Structures implemented in Java</h3>

[![Code Style](https://img.shields.io/badge/code%20style-google-green.svg?style=flat-square)](https://google.github.io/styleguide/javaguide.html)
[![MIT](https://img.shields.io/dub/l/vibe-d.svg)](https://github.com/alexprut/Algo/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/alexprut/Algo.svg?branch=master)](https://travis-ci.org/alexprut/Algo)
[![Coverage Status](https://coveralls.io/repos/github/alexprut/Algo/badge.svg?branch=master)](https://coveralls.io/github/alexprut/Algo?branch=master)
[![GitHub release](https://img.shields.io/github/release/alexprut/Algo.svg)](https://github.com/alexprut/Algo/releases)
[![Maven Central](https://img.shields.io/maven-central/v/com.alexprut.algo/algo.svg)](https://search.maven.org/artifact/com.alexprut.algo/algo)


</div>
<br />

Algorithms
==========

|Algorithm|Worst-case time complexity|Average-case time complexity|Best-case time complexity|Auxiliary space complexity|
|:---|:---|:---|:---|:---|
|[Insertion Sort](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/sorting/InsertionSort.java)|Θ(n^2)|Θ(n^2)|O(n)|-|
|[BubbleSort](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/sorting/BubbleSort.java)|O(n^2)|O(n^2)|O(n)|-|
|[MergeSort](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/sorting/MergeSort.java)|Θ(nlogn)|Θ(nlogn)|Θ(nlogn)|-|
|[HeapSort](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/MaxHeap.java#L82)|Θ(nlogn)|-|-|-|
|[QuickSort](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/sorting/QuickSort.java)|Θ(n^2)|Θ(nlogn)|Θ(nlogn)|-|
|[CountingSort](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/sorting/CountingSort.java)|Θ(k + n)|Θ(k + n)|Θ(n)|Θ(n)|
|[RadixSort](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/sorting/RadixSort.java)|Θ(d(k + n))|Θ(d(k + n))|Θ(n)|-|
|[Floyd-Warshall](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/graph/FloydWarshall.java)|Θ(V^3)|Θ(V^3)|Θ(V^3)|Θ(V^2)|
|[Kruskal](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/graph/mst/Kruskal.java)|O(\|E\|log\|V\|)|-|-|-|
|[Prim](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/graph/mst/Prim.java)|O(\|E\|log\|V\|)|-|-|-|
|[Bellman–Ford](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/graph/BellmanFord.java)|Θ(\|E\|\|V\|)|-|-|Θ(V)|
|[Dijkstra](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/graph/Dijkstra.java)|O(\|E\| + \|V\|log\|V\|)|-|-|-|
|[Maximum SubArray](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/MaximumSubArray.java)|Θ(n)|Θ(n)|Θ(n)|Θ(1)|
|[Knuth-Morris-Pratt](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/KnuthMorrisPratt.java)|Θ(n + m)|Θ(n)|Θ(n)|Θ(n)|
|[Rabin-Karp](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/RabinKarp.java)|Θ((n - m + 1)m)|Θ(n)|Θ(n)|-|
|[Greatest common divisor (GCD)](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/math/Math.java#L13)|-|-|-|-|
|[Binary Search](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/search/BinarySearch.java)|O(nlogn)|O(nlogn)|O(1)|Θ(1)|
|[Breadth First Search (BFS)](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/search/BreadthFirstSearch.java#L43)|O(\|E\|+\|V\|)|O(\|E\|+\|V\|)|-|-|
|[Depth First Search (DFS)](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/search/DepthFirstSearch.java#L10)|O(\|E\|+\|V\|)|O(\|E\|+\|V\|)|-|-|
|[Topological Sort (DFS)](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/search/DepthFirstSearch.java#L91)|O(\|E\|+\|V\|)|O(\|E\|+\|V\|)|-|-|
|[Longest Increasing Subsequence (LIS)](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/Utils.java#L98)|Θ(n^2)|-|-|Θ(n)|
|[Longest Common Subsequence (LCS)](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/Utils.java#L128)|Θ(n^2)|-|-|Θ(n^2)|

Data Structures
===============
|Data Structure|Methods|
|--------------|-------|
|[Max Heap](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/MaxHeap.java)|```max()``` - Θ(1), ```extractMax()``` - O(nlogn), ```increaseKey()``` - O(logn), ```insert()``` - O(logn), ```heapify()``` - O(logn), ```heapsort()``` - O(nlogn)|
|[Min Heap](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/MinHeap.java)|```min()``` - Θ(1), ```extractMin()``` - O(nlogn), ```insert()``` - O(logn), ```heapify()``` - O(logn), ```heapsort()``` - O(nlogn)|
|[Disjoint Set](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/DisjointSet.java)|```makeSet()``` - Θ(1), ```findSet()``` - Θ(1), ```union()``` - Θ(1)|
|[Trie](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/Trie.java)|```insert()``` - O(\|s\|), ```search()``` - O(\|s\|), ```searchPrefix()``` - O(\|s\|), ```remove()``` - O(\|s\|), ```size()``` - O(1)|
|[Stack](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/Stack.java)|```push()``` - Θ(1), ```pop()``` - Θ(1), ```empty()``` - Θ(1), ```size()``` - Θ(1), ```peek()``` - Θ(1)|
|[Queue](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/Queue.java)|```enqueue()``` - Θ(1), ```dequeue()``` - Θ(1), ```empty()``` - Θ(1), ```size()``` - Θ(1)|
|[Binary Search Tree](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/BinarySearchTree.java)|```insert()``` - O(n), ```search()``` - O(n), ```delete()``` - O(n), ```contains()``` - O(n), ```minimum()``` - O(n), ```maximum()``` - O(n), ```size()``` - Θ(1), ```successor()``` - O(n), ```preOrderVisit()``` - O(n), ```inOrderVisit()``` - O(n), ```postOrderVisit()``` - O(n)|
|[Double Linked List](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/DoubleLinkedList.java)|```insertFront()``` - Θ(1), ```removeFront()``` - Θ(1), ```insertBack()``` - Θ(1), ```removeBack()``` - Θ(1), ```head()``` - Θ(1), ```size()``` - Θ(1)|
|[Linked List](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/LinkedList.java)|```insertFront()``` - Θ(1), ```removeFront()``` - Θ(1), ```head()``` - Θ(1), ```size()``` - Θ(1)|
|[Graph](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/Graph.java)|```buildAdjacencyMatrix()``` - Θ(\|V\|^2 + \|E\|), ```buildAdjacencyList()``` - Θ(\|V\|^2 + \|E\|), ```addEdge()``` - Θ(1)|
|[Red-Black Tree](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/RedBlackTree.java)|```insert()``` - O(logn), ```search()``` - O(logn), ```delete()``` - O(logn), ```minimum()``` - O(logn), ```maximum()``` - O(logn), ```successor()``` - O(logn)|
|[Interval Tree](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/IntervalTree.java)|```insert()``` - O(logn), ```search()``` - O(logn), ```find()``` - O(logn), ```delete()``` - O(logn), ```minimum()``` - O(logn), ```maximum()``` - O(logn), ```successor()``` - O(logn)|
|[Segment Tree](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/SegmentTree.java)|```build()``` - O(n), ```update()``` - O(logn), ```search()``` - O(logn)|
|[AVL Tree](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/AVLTree.java)|```insert()``` - O(logn), ```search()``` - O(logn), ```delete()``` - O(logn), ```minimum()``` - O(logn), ```maximum()``` - O(logn), ```successor()``` - O(logn)|
|[B-Tree](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/BTree.java)|```insert()``` - O(th), ```search()``` - O(th), ```delete()``` - O(th), ```successor()``` - O(th), ```predecessor()``` - O(th)|
|[Fibonacci Heap](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/FibonacciHeap.java)|```insert()``` - O(1), ```minimum()``` - O(1), ```extractMin()``` - O(logn), ```decreaseKey()``` - O(1), ```delete()``` - O(logn)|
|[Merkle Tree](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/MerkleTree.java)|```build()``` - O(n), ```verify()``` - O(logn), ```getProofPath()``` - O(logn)|

---

### Build
```
./gradlew clean build
```

### Test
```
./gradlew test
```

### Formatting style
```
./gradlew goJF
```

```
./gradlew verGJF
```


License
=======
Licensed under [MIT](https://github.com/alexprut/Algo/blob/master/LICENSE).