package com.alexprut.algo.datastructures;

/**
 * A DoubleLinkedList is a data structure in which objects are linked in a linear order. The order
 * in a linked list is determined by a pointer in each object. A DoubleLinkedList is a {@link
 * LinkedList} where each node has an additional pointer to the previous element.
 *
 * <p>Example:
 *
 * <pre>
 * [Alice] ←-→ [Bob] ←-→ [Mike] ←-→ null
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Doubly_linked_list">https://en.wikipedia.org/wiki/Doubly_linked_list</a>
 * @param <T>
 */
public class DoubleLinkedList<T> {

  private Node<T> head;
  private Node<T> tail;
  private int size;

  public DoubleLinkedList() {}

  /**
   * Insert a new element at the front of the DoubleLinkedList.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param value the element to insert
   */
  public void insertFront(T value) {
    Node<T> node = new Node<>(value, head, null);
    if (head == null) {
      head = node;
      tail = node;
    } else {
      head.prev = node;
      head = node;
    }

    size++;
  }

  /**
   * Insert a new element at the end of the DoubleLinkedList.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param value the element to insert
   */
  public void insertBack(T value) {
    Node<T> node = new Node<>(value, null, tail);
    if (head == null) {
      head = node;
    } else {
      tail.next = node;
    }
    tail = node;

    size++;
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
   * Search for an element.
   *
   * <p>Time complexity: O(n)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param element to be searched
   * @return true if the element is present
   */
  public boolean search(T element) {
    Node<T> current = head;
    while (current != null) {
      if (current.value == element) {
        return true;
      }
      current = current.next;
    }

    return false;
  }

  /**
   * Removes the element in front of the DoubleLinkedList.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the element in front of the DoubleLinkedList
   * @throws Exception if the list is empty
   */
  public T removeFront() throws Exception {
    if (empty()) {
      throw new Exception("DoubleLinkedList underflow");
    }
    size--;

    if (head == tail) {
      Node<T> node = head;
      head = null;
      tail = null;
      return node.value;
    }

    Node<T> node = head;
    head = node.next;
    head.prev = null;
    return node.value;
  }

  /**
   * Removes the element at the end of the DoubleLinkedList.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the element at the end of the DoubleLinkedList
   * @throws Exception if the list is empty
   */
  public T removeBack() throws Exception {
    if (empty()) {
      throw new Exception("DoubleLinkedList underflow");
    }
    size--;

    if (head == tail) {
      Node<T> node = head;
      head = null;
      tail = null;
      return node.value;
    }

    Node<T> node = tail;
    node.prev.next = null;
    tail = node.prev;
    return node.value;
  }

  // TODO implement the remove node method

  /**
   * Returns the element in head of the LinkedList.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the head element of the list
   */
  public Node<T> head() {
    return head;
  }

  /**
   * Returns the element at the tail of the DoubleLinkedList.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the tail element of the list
   */
  public Node<T> tail() {
    return tail;
  }

  /**
   * Returns the size of the DoubleLinkedList.
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

  public static class Node<T> {

    private T value;
    private Node<T> next;
    private Node<T> prev;

    public Node(T value, Node<T> next, Node<T> prev) {
      this.value = value;
      this.next = next;
      this.next = prev;
    }

    protected void setNext(Node<T> next) {
      this.next = next;
    }

    protected void setPrev(Node<T> prev) {
      this.prev = prev;
    }

    public Node<T> getNext() {
      return this.next;
    }

    public Node<T> getPrev() {
      return this.prev;
    }

    public T getValue() {
      return this.value;
    }

    protected T setValue(T value) {
      return this.value = value;
    }
  }
}
