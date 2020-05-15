package com.alexprut.algo.datastructures;

import java.util.HashMap;

/**
 * A Trie, or prefix tree, is an ordered tree data structure. Used for storing a set of strings and
 * searching efficiently the presence of an element within the set.
 *
 * <p>TODO add an example
 *
 * @see <a href="https://en.wikipedia.org/wiki/Trie">https://en.wikipedia.org/wiki/Trie</a>
 */
public class Trie {

  private Node root = new Node("", true);

  public Trie() {}

  /**
   * Insert an element within the trie.
   *
   * <p>Time complexity: O(|s|)
   *
   * @param word the element to insert
   */
  public void insert(String word) {
    root.insert("", word);
  }

  /**
   * Check if a prefix is within the trie.
   *
   * <p>Time complexity: O(|s|)
   *
   * @param word the element to search
   * @return true if the element is contained
   */
  public boolean contains(String word) {
    return root.contains(word) != null;
  }

  /**
   * Check if an element is withing the trie.
   *
   * <p>Time complexity: O(|s|)
   *
   * @param word the element to search
   * @return true if the element is contained
   */
  public boolean containsWord(String word) {
    Node node = root.contains(word);
    return node != null && node.isWord;
  }

  // TODO implement: boolean remove(String word)
  // TODO implement: int countPrefix(String word)
  // TODO implement: int countWord(String word)

  protected class Node {

    String prefix;
    boolean isWord = false;
    int size = 0;
    HashMap<Character, Node> children = new HashMap<>();

    protected Node(String prefix) {
      this.prefix = prefix;
    }

    protected Node(String prefix, boolean isWord) {
      this.prefix = prefix;
      this.isWord = isWord;
    }

    /**
     * Time complexity: O(|s|)
     *
     * @param prefix
     * @param suffix
     */
    protected void insert(String prefix, String suffix) {
      if (!suffix.isEmpty()) {
        size++;
        Character c = suffix.charAt(0);
        String newPrefix = prefix + c;
        String newSuffix = suffix.substring(1);
        if (children.containsKey(c)) {
          children.get(c).insert(newPrefix, newSuffix);
        } else {
          children.put(c, new Node(newPrefix, newSuffix.isEmpty()));
          children.get(c).insert(newPrefix, newSuffix);
        }
      }
    }

    /**
     * Time complexity: O(|s|)
     *
     * @param word
     * @return
     */
    protected Node contains(String word) {
      if (!word.isEmpty()) {
        Character c = word.charAt(0);
        if (!children.containsKey(c)) {
          return null;
        }

        return children.get(c).contains(word.substring(1));
      }

      return this;
    }
  }
}
