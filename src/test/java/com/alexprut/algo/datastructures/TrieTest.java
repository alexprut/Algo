package com.alexprut.algo.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class TrieTest {

  @Test
  public void shouldInsertFindAndRemove() {
    /*
     *                ""
     *               / | \
     *              d  s  a
     *             /   |   \
     *            a    t    l
     *           /     |     \
     *          t      r      g
     *         / \     |      |
     *       e*   a*   u      o*
     *                 |      |
     *                 c      r
     *                 |      |
     *                 t*     i
     *                 |      |
     *                 u      t
     *                 |      |
     *                 r      h
     *                 |      |
     *                 e*     m
     */
    Trie trie = new Trie();
    Assert.assertEquals(0, trie.size());
    trie.insert("data");
    trie.insert("date");
    trie.insert("structure");
    trie.insert("struct");
    trie.insert("algorithm");
    trie.insert("algo");

    Assert.assertEquals(6, trie.size());
    Assert.assertFalse(trie.search(""));
    Assert.assertFalse(trie.searchPrefix(""));
    Assert.assertTrue(trie.search("algo"));
    Assert.assertTrue(trie.search("algorithm"));
    Assert.assertTrue(trie.searchPrefix("algorithm"));
    Assert.assertTrue(trie.searchPrefix("algorith"));
    Assert.assertFalse(trie.searchPrefix("algorithms"));
    Assert.assertTrue(trie.searchPrefix("structure"));
    Assert.assertTrue(trie.searchPrefix("data"));
    Assert.assertEquals(2, trie.sizePrefix("alg"));
    Assert.assertEquals(2, trie.sizePrefix("algo"));

    Assert.assertFalse(trie.remove(""));
    Assert.assertFalse(trie.remove("alg"));
    Assert.assertFalse(trie.remove("algos"));
    Assert.assertEquals(6, trie.size());
    /*
     *                 ""
     *               / | \
     *              d  s  a
     *             /   |   \
     *            a    t    l
     *           /     |     \
     *          t      r      g
     *         / \     |      |
     *       e*   a*   u      o
     *                 |      |
     *                 c      r
     *                 |      |
     *                 t*     i
     *                 |      |
     *                 u      t
     *                 |      |
     *                 r      h
     *                 |      |
     *                 e*     m
     */
    Assert.assertTrue(trie.remove("algo"));
    Assert.assertEquals(5, trie.size());

    Assert.assertFalse(trie.removePrefix(""));
    /*
     *                 ""
     *                 | \
     *                 s  a
     *                 |   \
     *                 t    l
     *                 |     \
     *                 r      g
     *                 |      |
     *                 u      o*
     *                 |      |
     *                 c      r
     *                 |      |
     *                 t*     i
     *                 |      |
     *                 u      t
     *                 |      |
     *                 r      h
     *                 |      |
     *                 e*     m
     */
    Assert.assertTrue(trie.removePrefix("dat"));
    Assert.assertEquals(3, trie.size());

    Assert.assertTrue(trie.removePrefix("s"));
    Assert.assertTrue(trie.removePrefix("a"));
    Assert.assertEquals(0, trie.size());
  }
}
