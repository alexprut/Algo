package com.alexprut.algo.datastructures;

import java.util.HashMap;

/**
 * A Trie, or prefix tree, is an ordered tree data structure. Used for storing a set of strings and
 * searching efficiently the presence of an element within the set.
 *
 * <p>Example: ["walk", "was", "word", "worm"]
 *
 * <pre>
 *        w
 *       / \
 *      o   a
 *     /   / \
 *    r   s   l
 *   / \       \
 *  d   m       k
 * </pre>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Trie">https://en.wikipedia.org/wiki/Trie</a>
 */
public class Trie {

  private final Node root = new Node();

  public Trie() {}

  /**
   * Insert an element within the trie.
   *
   * <p>Time complexity: O(|s|)
   *
   * <p>Space complexity: O(|s|)
   *
   * @param elem the element to insert
   */
  public void insert(String elem) {
    if (!elem.isEmpty()) {
      Node tmp = root;
      for (int i = 0; i < elem.length(); i++) {
        tmp.size++;
        if (!tmp.children.containsKey(elem.charAt(i))) {
          tmp.children.put(elem.charAt(i), new Node());
        }
        tmp = tmp.children.get(elem.charAt(i));
      }
      tmp.size++;
      tmp.isEndOfWord = true;
    }
  }

  /**
   * Check if a prefix present is within the trie.
   *
   * <p>Time complexity: O(|s|)
   *
   * <p>Space complexity: O(1)
   *
   * @param prefix contained in a element to search
   * @return true if the element is contained
   */
  public boolean searchPrefix(String prefix) {
    return contains(prefix) != null;
  }

  /**
   * Check if an element is within the trie.
   *
   * <p>Time complexity: O(|s|)
   *
   * <p>Space complexity: O(1)
   *
   * @param elem the element to search
   * @return true if the element is contained
   */
  public boolean search(String elem) {
    Node node = contains(elem);
    return node != null && node.isEndOfWord;
  }

  /**
   * Returns the number of unique elements containing that prefix.
   *
   * <p>Time complexity: O(|s|)
   *
   * <p>Space complexity: O(1)
   *
   * @param prefix contained in a element to search
   * @return the number of unique elements containing that prefix
   */
  public int sizePrefix(String prefix) {
    Node node = contains(prefix);
    return node != null ? node.size : 0;
  }

  /**
   * Return the number of unique elements contained within the trie.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @return number of unique elements
   */
  public int size() {
    return root.size;
  }

  /**
   * Remove an element if contained within the trie.
   *
   * <p>Time complexity: O(|s|)
   *
   * <p>Space complexity: O(1)
   *
   * @param elem the element to find and remove
   * @return true if the element was removed
   */
  public boolean remove(String elem) {
    if (!elem.isEmpty() && search(elem)) {
      Node tmp = root;
      for (int i = 0; i < elem.length(); i++) {
        tmp.size--;
        if (tmp.children.get(elem.charAt(i)).size > 1) {
          tmp = tmp.children.get(elem.charAt(i));
        } else {
          tmp.children.remove(elem.charAt(i));
          break;
        }
      }
      tmp.size--;
      return true;
    }

    return false;
  }

  /**
   * Remove all the elements that contain the prefix within the trie.
   *
   * <p>Time complexity: O(|s|)
   *
   * <p>Space complexity: O(1)
   *
   * @param prefix the element prefix to find and remove
   * @return true if all the elements containing that prefix were removed
   */
  public boolean removePrefix(String prefix) {
    if (!prefix.isEmpty() && searchPrefix(prefix)) {
      root.size -= root.children.get(prefix.charAt(0)).size;
      root.children.remove(prefix.charAt(0));
      return true;
    }

    return false;
  }

  /**
   * Search for a element withing the trie if present.
   *
   * <p>Time complexity: O(|s|)
   *
   * <p>Space complexity: O(1)
   *
   * @param elem the element to search
   * @return if present, the node containing the last character of the element
   */
  protected Node contains(String elem) {
    if (!elem.isEmpty()) {
      Node tmp = root;
      for (int i = 0; i < elem.length(); i++) {
        if (!tmp.children.containsKey(elem.charAt(i))) {
          return null;
        }
        tmp = tmp.children.get(elem.charAt(i));
      }
      return tmp;
    }

    return null;
  }

  protected static class Node {
    boolean isEndOfWord = false;
    int size = 0;
    final HashMap<Character, Node> children = new HashMap<>();
  }
}
