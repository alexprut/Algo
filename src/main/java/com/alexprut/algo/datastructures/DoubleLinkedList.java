package com.alexprut.algo.datastructures;

public class DoubleLinkedList {

  private Node head;
  private Node tail;
  private int size;

  DoubleLinkedList() {
  }

  public void insertFront(int value) {
    Node node = new Node(value, head, null);
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

  public void insertBack(int value) {
    Node node = new Node(value, null, tail);
    if (head == null) {
      head = node;
      tail = node;
    } else {
      tail.next = node;
      tail = node;
    }

    size++;
  }

  public boolean empty() {
    return size == 0;
  }

  public int removeFront() throws Exception {
    size--;
    if (empty()) {
      throw new Exception("DoubleLinkedList underflow");
    }

    if (head == tail) {
      Node node = head;
      head = null;
      tail = null;
      return node.value;
    }

    Node node = head;
    head = node.next;
    head.prev = null;
    return node.value;
  }

  public int removeBack() throws Exception {
    size--;
    if (empty()) {
      throw new Exception("DoubleLinkedList underflow");
    }

    if (head == tail) {
      Node node = head;
      head = null;
      tail = null;
      return node.value;
    }

    Node node = tail;
    node.prev.next = null;
    tail = node.prev;
    return node.value;
  }

  public Node head() {
    return head;
  }

  public Node tail() {
    return tail;
  }

  public int size() {
    return size;
  }

  public static class Node {

    private int value;
    private Node next;
    private Node prev;

    public Node(int value, Node next, Node prev) {
      this.value = value;
      this.next = next;
      this.next = prev;
    }

    public void setNext(Node next) {
      this.next = next;
    }

    public void setPrev(Node next) {
      this.prev = prev;
    }

    public Node getNext() {
      return this.next;
    }

    public Node getPrev() {
      return this.prev;
    }

    public int getValue() {
      return this.value;
    }
  }
}