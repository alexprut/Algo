package com.alexprut.algo.datastructures;

/** LIFO */
public class Stack<T> {

  private LinkedList<T> list;

  /** Time complexity: Θ(1) */
  public T peek() throws Exception {
    T tmp = pop();
    push(tmp);
    return tmp;
  }

  public Stack() {
    list = new LinkedList<>();
  }

  /** Time complexity: Θ(1) */
  public void push(T value) {
    list.insertFront(value);
  }

  /** Time complexity: Θ(1) */
  public int size() {
    return list.size();
  }

  /** Time complexity: Θ(1) */
  public boolean empty() {
    return list.empty();
  }

  /** Time complexity: Θ(1) */
  public T pop() throws Exception {
    return list.removeFront();
  }

  /** Time complexity: Θ(1) */
  public void insert(T value) {
    push(value);
  }

  /** Time complexity: Θ(1) */
  public void remove() throws Exception {
    pop();
  }
}
