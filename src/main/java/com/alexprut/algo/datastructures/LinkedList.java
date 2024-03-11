package com.alexprut.algo.datastructures;

/**
 * LinkedList is a data structure in which objects are linked in a linear order. The order in a
 * linked list is determined by a pointer in each object. You may use a linked list for storing the
 * linear order of elements.
 *
 * <p>Example:
 *
 * <pre>
 * [Alice] → [Bob] → [Mike] → null
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Linked_list">https://en.wikipedia.org/wiki/Linked_list</a>
 * @param <T>
 */
public class LinkedList<T> {

  private Node<T> head;
  private int size;

  public LinkedList() {}

  /**
   * Insert a new element at the front of the LinkedList.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param value the element to insert
   */
  public void insertFront(T value) {
    head = new Node<>(value, head);
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
   * Removes the element in front of the LinkedList.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the element in front of the LinkedList
   * @throws Exception if the list is empty
   */
  public T removeFront() throws Exception {
    if (empty()) {
      throw new Exception("LinkedList underflow");
    }
    size--;

    Node<T> node = head;
    head = node.next;
    return node.getValue();
  }

  /**
   * If present, removes the element from the LinkedList.
   *
   * <p>Time complexity: Θ(n)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param element to be removed
   * @return boolean true if the element was removed
   */
  public boolean remove(T element) {
    if (!search(element)) {
      return false;
    }

    Node<T> prev = null;
    Node<T> current = head;
    while (current.value != element) {
      prev = current;
      current = current.next;
    }

    // Case Head node
    if (current == head) {
      head = current.next;
    } else {
      // Case Tail node
      // Case Internal Node
      prev.next = current.next;
    }

    size--;
    return true;
  }

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

  protected static class Node<T> {

    private final T value;
    private Node<T> next;

    public Node(T value, Node<T> next) {
      this.value = value;
      this.next = next;
    }

    /**
     * Change the pointer to the next element.
     *
     * <p>Time complexity: Θ(1)
     *
     * <p>Space complexity: Θ(1)
     *
     * @param next pointer to the next element
     */
    protected void setNext(Node<T> next) {
      this.next = next;
    }

    /**
     * Returns the next element in the LinkedList from the current element.
     *
     * <p>Time complexity: Θ(1)
     *
     * <p>Space complexity: Θ(1)
     *
     * @return the next element
     */
    public Node<T> getNext() {
      return this.next;
    }

    /**
     * Get the value of the current element.
     *
     * <p>Time complexity: Θ(1)
     *
     * <p>Space complexity: Θ(1)
     *
     * @return value of the element
     */
    public T getValue() {
      return this.value;
    }
  }
}
