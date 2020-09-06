package com.alexprut.algo.datastructures;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Merkle tree is a tree in which every leaf node is labelled with the cryptographic hash of a data
 * block, and every non-leaf node is labelled with the cryptographic hash of the labels of its child
 * nodes. Hash trees allow efficient and secure verification of the contents of large data
 * structures. Hash trees are a generalization of hash lists and hash chains.
 *
 * <p>Example:
 *
 * <pre>
 *                ffb4
 *          /             \
 *        1f05           44ae
 *      /     \       /        \
 *    b96c   53b7   26ea      2448
 *     |      |      |         |
 * "Merkle" "Tree" "Data" "Structure"
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Merkle_tree">https://en.wikipedia.org/wiki/Merkle_tree</a>
 */
public class MerkleTree {
  private final String[] hashNodes;

  public MerkleTree(final List<String> elements) {
    if (elements.size() % 2 != 0) {
      elements.add(elements.get(elements.size() - 1));
    }
    hashNodes = new String[elements.size() * 2 - 1];
    for (int i = 1; i <= elements.size(); i++) {
      hashNodes[hashNodes.length - i] = hash(elements.get(elements.size() - i));
    }
    build(0);
  }

  /**
   * Builds the hash tree.
   *
   * <p>Time complexity: Θ(n)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param i the index to apply the build
   */
  protected void build(int i) {
    if (hashNodes[i] == null) {
      build(left(i));
      build(right(i));
      hashNodes[i] = hash(hashNodes[left(i)] + hashNodes[right(i)]);
    }
  }

  /**
   * Get the parent index.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param i the current index
   * @return the parent index
   */
  protected static int parent(int i) {
    return (i + 1) / 2 - 1;
  }

  /**
   * Given the parent index calculates the index of the left child.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param i parent index
   * @return index of the left child
   */
  protected static int left(int i) {
    return 2 * i + 1;
  }

  /**
   * Given the parent index calculates the index of the right child.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param i parent index
   * @return index of the right child
   */
  protected static int right(int i) {
    return 2 * i + 2;
  }

  /**
   * Get the root element.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the root element
   */
  public String root() {
    return hashNodes[0];
  }

  /**
   * Create and get the proof path for an element.
   *
   * <p>Time complexity: Θ(logn)
   *
   * <p>Space complexity: Θ(logn)
   *
   * @param elementIndex element to build the proof
   * @return the proof path for the element
   */
  public List<String> getProofPath(int elementIndex) {
    List<String> proofPath = new ArrayList<>();
    int elements = (hashNodes.length + 1) / 2;
    int tmpNodeIndex = hashNodes.length - elements + elementIndex;
    int tmpParentIndex = parent(tmpNodeIndex);
    while (tmpNodeIndex != 0) {
      if (left(tmpParentIndex) != tmpNodeIndex) {
        proofPath.add(hashNodes[left(tmpParentIndex)]);
      } else {
        proofPath.add(hashNodes[right(tmpParentIndex)]);
      }
      tmpNodeIndex = tmpParentIndex;
      tmpParentIndex = parent(tmpParentIndex);
    }
    return proofPath;
  }

  /**
   * Verifies if an element belongs to the tree.
   *
   * <p>Time complexity: Θ(logn)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param element the element value
   * @param rootHash root hash
   * @param proofPath the proof path
   * @return true if the element belongs to the tree
   */
  public static boolean verify(String element, String rootHash, final List<String> proofPath) {
    String tmpHash = hash(element);
    for (String s : proofPath) {
      tmpHash = hash(tmpHash + s);
    }
    return tmpHash.equals(rootHash);
  }

  /**
   * Computes the SHA-256 hash of an element.
   *
   * @param content the element value
   * @return the SHA-256 hash
   */
  public static String hash(String content) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      String tmpSha256hex = toHexString(digest.digest(content.getBytes(StandardCharsets.UTF_8)));
      return toHexString(digest.digest(tmpSha256hex.getBytes(StandardCharsets.UTF_8)));
    } catch (Exception e) {
      return "";
    }
  }

  /**
   * Utility function use in {@link #hash(String)}
   *
   * @param hash the byte array
   * @return the string hash
   */
  private static String toHexString(final byte[] hash) {
    BigInteger number = new BigInteger(1, hash);
    StringBuilder hexString = new StringBuilder(number.toString(16));
    while (hexString.length() < 32) {
      hexString.insert(0, '0');
    }
    return hexString.toString();
  }
}
