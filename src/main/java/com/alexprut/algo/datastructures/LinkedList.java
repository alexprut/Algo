package com.alexprut.algo.datastructures;

public class LinkedList {

  private Node head;
  int size;

  LinkedList() {
  }

  public void insertFront(int value) {
    head = new Node(value, head);
    size++;
  }

  public boolean empty() {
    return size == 0;
  }

  public int removeFront() throws Exception {
    size--;
    if (empty()) {
      throw new Exception("LinkedList underflow");
    }

    Node node = head;
    head = node.next;
    return node.value;
  }

  public Node head() {
    return head;
  }

  public static class Node {

    private int value;
    private Node next;

    public Node(int value, Node next) {
      this.value = value;
      this.next = next;
    }

    public void setNext(Node next) {
      this.next = next;
    }

    public Node getNext() {
      return this.next;
    }

    public int getValue() {
      return this.value;
    }
  }
}
