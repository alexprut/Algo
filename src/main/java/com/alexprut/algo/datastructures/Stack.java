package com.alexprut.algo.datastructures;

/**
 * A Stack is a data structure that serves as a collection of elements, with two principal
 * operations: "push", which adds an element to the collection, and "pop", which removes the most
 * recently added element that was not yet removed. The order in which elements come off a stack
 * gives rise to its alternative name, LIFO (last in, first out).
 */
public class Stack<T> {

  private LinkedList<T> list;

  public Stack() {
    list = new LinkedList<>();
  }

  /**
   * Get the element in peek of the stack.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the peek element
   * @throws Exception if the stack is empty
   */
  public T peek() throws Exception {
    T tmp = pop();
    push(tmp);
    return tmp;
  }

  /**
   * Add a new element on top of the stack.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param value the element to be added
   */
  public void push(T value) {
    list.insertFront(value);
  }

  /**
   * Get the number of elements within the stack.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return number of elements
   */
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
