package com.alexprut.algo.datastructures;

import java.util.HashMap;

public class Trie {

  private Node root = new Node("", true);

  Trie() {
  }

  public void insert(String word) {
    root.insert("", word);
  }

  public boolean contains(String word) {
    return root.contains(word) != null;
  }

  public boolean containsWord(String word) {
    Node node = root.contains(word);
    return node != null && node.isWord;
  }

  // TODO implement: boolean remove(String word)
  // TODO implement: int countPrefix(String word)
  // TODO implement: int countWord(String word)

  class Node {

    String prefix = "";
    boolean isWord = false;
    int size = 0;
    HashMap<Character, Node> children = new HashMap<>();

    Node(String prefix) {
      this.prefix = prefix;
    }

    Node(String prefix, boolean isWord) {
      this.prefix = prefix;
      this.isWord = isWord;
    }

    public void insert(String prefix, String suffix) {
      if (!suffix.isEmpty()) {
        size++;
        Character c = suffix.charAt(0);
        String newPrefix = prefix + c;
        String newSuffix = suffix.substring(1, suffix.length());
        if (children.containsKey(c)) {
          children.get(c).insert(newPrefix, newSuffix);
        } else {
          children.put(c, new Node(newPrefix, newSuffix.isEmpty()));
          children.get(c).insert(newPrefix, newSuffix);
        }
      }
    }

    public Node contains(String word) {
      if (!word.isEmpty()) {
        Character c = word.charAt(0);
        if (!children.containsKey(c)) {
          return null;
        }

        return children.get(c).contains(word.substring(1, word.length()));
      }

      return this;
    }
  }
}