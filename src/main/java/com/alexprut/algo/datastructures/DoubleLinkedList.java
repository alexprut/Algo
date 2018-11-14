package com.alexprut.algo.datastructures;

public class DoubleLinkedList<T> {

  private Node<T> head;
  private Node<T> tail;
  private int size;

  public DoubleLinkedList() {}

  /**
   * Time complexity: Θ(1)
   */
  public void insertFront(T value) {
    Node<T> node = new Node<>(value, head, null);
    if (head == null) {
      head = node;
      tail = node;
    } else {
      node.next = head;
      head.prev = node;
      head = node;
    }

    size++;
  }

  /**
   * Time complexity: Θ(1)
   */
  public void insertBack(T value) {
    Node<T> node = new Node<>(value, null, tail);
    if (head == null) {
      head = node;
      tail = node;
    } else {
      node.prev = tail;
      tail.next = node;
      tail = node;
    }

    size++;
  }

  /**
   * Time complexity: Θ(1)
   */
  public boolean empty() {
    return size == 0;
  }

  /**
   * Time complexity: Θ(1)
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
   * Time complexity: Θ(1)
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

  // TODO implement the remove method

  public Node<T> head() {
    return head;
  }

  public Node<T> tail() {
    return tail;
  }

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

    public void setNext(Node<T> next) {
      this.next = next;
    }

    public void setPrev(Node<T> prev) {
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
  }
}
