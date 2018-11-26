<br />
<br />

<div align="center">
<img src="https://github.com/alexprut/Algo/raw/master/src/main/resources/logo.png" width="400" height="auto"/>

<br />
<br />

<h3>Classic Algorithms and Data Structures implemented in Java</h3>

[![Code Style](https://img.shields.io/badge/code%20style-google-green.svg?style=flat-square)](https://google.github.io/styleguide/javaguide.html)
[![MIT](https://img.shields.io/dub/l/vibe-d.svg)](https://github.com/alexprut/Algo/blob/master/LICENSE)
[![Build Status](http://img.shields.io/travis/alexprut/Algo/master.svg)](https://travis-ci.org/alexprut/Algo)

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
|[CountingSort](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/sorting/CountingSort.java)|Θ(k + n)|Θ(k + n)|-|-|
|RadixSort|Θ(d(k + n))|Θ(d(k + n))|-|-|
|BucketSort|Θ(n^2)|Θ(n)|-|-|
|[Floyd-Warshall](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/graph/FloydWarshall.java)|Θ(V^3)|Θ(V^3)|Θ(V^3)|Θ(V^2)|
|[Kruskal](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/graph/mst/Kruskal.java)|O(\|E\|log\|V\|)|-|-|-|
|[Prim](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/graph/mst/Prim.java)|O(\|E\|log\|V\|)|-|-|-|
|[Bellman–Ford](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/graph/BellmanFord.java)|Θ(\|E\|\|V\|)|-|-|Θ(V)|
|[Dijkstra](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/graph/Dijkstra.java)|O(\|E\| + \|V\|log\|V\|)|-|-|-|
|[Maximum SubArray](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/MaximumSubArray.java)|Θ(n)|Θ(n)|Θ(n)|Θ(1)|
|Knuth-Morris-Pratt|-|-|-|-|
|Rabin-Karp|-|-|-|-|
|[Greatest common divisor (gcd)](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/math/Math.java#L13)|-|-|-|-|
|[Binary Search](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/search/BinarySearch.java)|O(nlogn)|O(nlogn)|O(1)|Θ(1)|
|[Breadth First Search (BFS)](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/search/BreadthFirstSearch.java#L43)|O(\|E\|+\|V\|)|O(\|E\|+\|V\|)|-|-|
|[Depth First Search (DFS)](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/search/DepthFirstSearch.java#L10)|O(\|E\|+\|V\|)|O(\|E\|+\|V\|)|-|-|
|[Topological Sort (DFS)](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/algorithms/search/DepthFirstSearch.java#L91)|O(\|E\|+\|V\|)|O(\|E\|+\|V\|)|-|-|

Data Structures
===============
|Data Structure|Methods|
|--------------|-------|
|[Max Heap](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/MaxHeap.java)|```max()``` - Θ(1), ```extractMax()``` - O(nlogn), ```increaseKey()``` - O(logn), ```insert()``` - O(logn), ```heapify()``` - O(logn), ```heapsort()``` - O(nlogn)|
|[Min Heap](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/MinHeap.java)|```min()``` - Θ(1), ```extractMin()``` - O(nlogn), ```insert()``` - O(logn), ```heapify()``` - O(logn), ```heapsort()``` - O(nlogn)|
|[Disjoint Set](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/DisjointSet.java)|```makeSet()``` - Θ(1), ```findSet()``` - Θ(1), ```union()``` - Θ(1)|
|[Trie](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/Trie.java)|```insert()``` - O(\|s\|), ```contains()``` - O(\|s\|), ```containsWord()``` - O(\|s\|)|
|[Stack](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/Stack.java)|```push()``` - Θ(1), ```pop()``` - Θ(1), ```empty()``` - Θ(1), ```size()``` - Θ(1)|
|[Queue](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/Queue.java)|```enqueue()``` - Θ(1), ```dequeue()``` - Θ(1), ```empty()``` - Θ(1), ```size()``` - Θ(1)|
|[Binary Search Tree](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/BinarySearchTree.java)|```insert()``` - O(n), ```search()``` - O(n), ```remove()``` - O(n), ```contains()``` - O(n), ```minimum()``` - O(n), ```maximum()``` - O(n), ```size()``` - Θ(1)|
|[Double Linked List](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/DoubleLinkedList.java)|```insertFront()``` - Θ(1), ```removeFront()``` - Θ(1), ```insertBack()``` - Θ(1), ```removeBack()``` - Θ(1), ```head()``` - Θ(1), ```size()``` - Θ(1)|
|[Linked List](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/LinkedList.java)|```insertFront()``` - Θ(1), ```removeFront()``` - Θ(1), ```head()``` - Θ(1), ```size()``` - Θ(1)|
|[Graph](https://github.com/alexprut/Algo/blob/master/src/main/java/com/alexprut/algo/datastructures/Graph.java)|```buildAdjacencyMatrix()``` - Θ(\|V\|^2 + \|E\|), ```buildAdjacencyList()``` - Θ(\|V\|^2 + \|E\|), ```addEdge()``` - Θ(1)|
|AVL Tree|-|
|Red-Black Tree|-|
|Segment Tree|-|
|B-Tree|-|
|Fibonacci Heap|-|

License
=======
Licensed under [MIT](https://github.com/alexprut/Algo/blob/master/LICENSE).