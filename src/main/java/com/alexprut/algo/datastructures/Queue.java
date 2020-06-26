package com.alexprut.algo.datastructures;

/**
 * A Queue is a collection of entities that are maintained in a sequence and can be modified by the
 * addition of entities at one end of the sequence and the removal of entities from the other end of
 * the sequence. In a queue, the element deleted is always the one that has been in the set for the
 * longest time: the queue implements a first-in, first-out, or FIFO, policy.
 *
 * <p>Example:
 *
 * <pre>
 * ————                   ————————                 ————
 *  24  -> enqueue(42) ->  42, 24   -> dequeue() -> 42
 * ————                   ————————                 ————
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Queue_(abstract_data_type)">https://en.wikipedia.org/wiki/Queue_(abstract_data_type)</a>
 */
public class Queue<T> {

  private final DoubleLinkedList<T> list;

  public Queue() {
    list = new DoubleLinkedList<>();
  }

  /**
   * Check if the queue is empty.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return true if empty
   */
  public boolean empty() {
    return list.empty();
  }

  /**
   * Add the new element in front of the queue.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param value the value of the element to insert
   */
  public void enqueue(T value) {
    list.insertFront(value);
  }

  /**
   * Removes the last element from the queue.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the removed element
   * @throws Exception if the queue is empty
   */
  public T dequeue() throws Exception {
    return list.removeBack();
  }

  /**
   * The number of elements within the queue.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the size of the queue
   */
  public int size() {
    return list.size();
  }
}
