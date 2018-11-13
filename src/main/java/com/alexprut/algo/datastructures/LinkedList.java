package com.alexprut.algo.datastructures;

public class LinkedList<T> {

  private Node<T> head;
  private int size;

  public LinkedList() {}

  public void insertFront(T value) {
    head = new Node<T>(value, head);
    size++;
  }

  public boolean empty() {
    return size == 0;
  }

  public T removeFront() throws Exception {
    size--;
    if (empty()) {
      throw new Exception("LinkedList underflow");
    }

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

    public void setNext(Node<T> next) {
      this.next = next;
    }

    public Node<T> getNext() {
      return this.next;
    }

    public T getValue() {
      return this.value;
    }
  }
}
