package com.alexprut.algo.datastructures;

/**
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Linked_list">https://en.wikipedia.org/wiki/Linked_list</a>
 * @param <T>
 */
public class LinkedList<T> {

  private Node<T> head;
  private int size;

  public LinkedList() {}

  /** Time complexity: Θ(1) */
  public void insertFront(T value) {
    head = new Node<T>(value, head);
    size++;
  }

  /** Time complexity: Θ(1) */
  public boolean empty() {
    return size == 0;
  }

  /** Time complexity: Θ(1) */
  public T removeFront() throws Exception {
    if (empty()) {
      throw new Exception("LinkedList underflow");
    }
    size--;

    Node<T> node = head;
    head = node.next;
    return node.value;
  }

  // TODO implement the remove method
  // TODO clone method

  public Node<T> head() {
    return head;
  }

  public int size() {
    return size;
  }

  public static class Node<T> {

    private T value;
    private Node<T> next;

    public Node(T value, Node<T> next) {
      this.value = value;
      this.next = next;
    }

    /** Time complexity: Θ(1) */
    public void setNext(Node<T> next) {
      this.next = next;
    }

    /** Time complexity: Θ(1) */
    public Node<T> getNext() {
      return this.next;
    }

    /** Time complexity: Θ(1) */
    public T getValue() {
      return this.value;
    }
  }
}
