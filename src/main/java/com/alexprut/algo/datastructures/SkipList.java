package com.alexprut.algo.datastructures;

/**
 * A skip list (or skiplist) is a probabilistic data structure that allows O(log n) average
 * complexity for search as well as O(log n) average complexity for insertion within an ordered
 * sequence of n elements. Thus, it can get the best features of a sorted array (for searching)
 * while maintaining a linked list-like structure that allows insertion, which is not possible with
 * a static array.
 *
 * <p>Example:
 *
 * <pre>
 * 1| [12] ←----------→ [25] ←----------→ null
 *  |   |
 * 0| [12] ←-→ [15] ←-→ [25] ←-→ [34] ←-→ null
 * </pre>
 *
 * @param <T>
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Skip_list">https://en.wikipedia.org/wiki/Skip_list</a>
 */
public class SkipList<T extends Comparable<T>> {
  // Maximum level of the skip list
  private final int maxLevel;

  // Fraction of the nodes with level i pointers also having level i+1 pointers
  private final float p;

  private int currentLevel;

  private Node<T> header;

  private int size;

  public SkipList(int maxLevel) {
    this(maxLevel, 0.5f);
  }

  public SkipList(int maxLevel, float p) {
    size = 0;
    this.maxLevel = maxLevel;
    this.p = p;
    currentLevel = 0;
    header = new Node<>(null, maxLevel);
  }

  /**
   * Returns the size of the LinkedList.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return list size
   */
  public int size() {
    return size;
  }

  /**
   * Check if the list is empty.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return true if the list is empty
   */
  public boolean empty() {
    return size == 0;
  }

  /**
   * Return the level where the new element should be added. Randomized coin flips function.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return level of insertion
   */
  private int randomLevel() {
    float r = (float) Math.random();
    int lvl = 0;
    while (r < p && lvl < maxLevel) {
      lvl++;
      r = (float) Math.random();
    }
    return lvl;
  }

  /**
   * Insert a new element.
   *
   * <p>Time complexity: O(logn) with high probability, O(n) in the worst case
   *
   * <p>Space complexity: Θ(1)
   *
   * @param element to be inserted
   */
  public void insert(T element) {
    size++;
    Node<T> current = header;
    Node<T>[] update = new Node[maxLevel + 1];

    for (int i = currentLevel; i >= 0; i--) {
      while (current.next[i] != null && current.next[i].value.compareTo(element) < 0) {
        current = current.next[i];
      }
      update[i] = current;
    }

    int randomLevel = randomLevel();

    if (randomLevel > currentLevel) {
      for (int i = currentLevel + 1; i < randomLevel + 1; i++) {
        update[i] = header;
      }
      currentLevel = randomLevel;
    }

    Node<T> n = new Node<>(element, randomLevel);

    for (int i = 0; i <= randomLevel; i++) {
      n.next[i] = update[i].next[i];
      update[i].next[i] = n;
    }
  }

  /**
   * Delete an element from.
   *
   * <p>Time complexity: O(logn) with high probability, O(n) in the worst case
   *
   * <p>Space complexity: Θ(1)
   *
   * @param element to be searched
   * @return true if the element was deleted
   */
  public boolean delete(T element) {
    Node<T> current = header;
    Node<T>[] update = new Node[maxLevel + 1];

    for (int i = currentLevel; i >= 0; i--) {
      while (current.next[i] != null && current.next[i].value.compareTo(element) < 1) {
        current = current.next[i];
      }
      update[i] = current;
    }

    current = current.next[0];

    boolean isFound = current != null && current.value == element;
    if (isFound) {
      size--;
    }

    if (current != null && current.value.compareTo(element) == 0) {
      for (int i = 0; i < currentLevel; i++) {
        if (update[i].next[i] != current) break;
        update[i].next[i] = current.next[i];
      }

      while (currentLevel > 0 && header.next[currentLevel] == null) {
        currentLevel--;
      }
    }

    return isFound;
  }

  /**
   * Search for an element.
   *
   * <p>Time complexity: O(logn) with high probability, O(n) in the worst case
   *
   * <p>Space complexity: Θ(1)
   *
   * @param element to be searched
   * @return true if the element is present
   */
  boolean search(T element) {
    Node<T> current = header;

    for (int i = currentLevel; i >= 0; i--) {
      while (current.next[i] != null && current.next[i].value.compareTo(element) < 0) {
        current = current.next[i];
      }
    }
    current = current.next[0];

    if (current != null && current.value.compareTo(element) == 0) {
      return true;
    } else {
      return false;
    }
  }

  protected static class Node<T extends Comparable<T>> {
    T value;

    /** Hold pointers to node of different level */
    public Node<T>[] next;

    Node(T value, int level) {
      this.value = value;
      next = new Node[level + 1];
    }
  }
}
