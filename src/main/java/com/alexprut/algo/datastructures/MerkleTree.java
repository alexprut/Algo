package com.alexprut.algo.datastructures;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;

/**
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Merkle_tree">https://en.wikipedia.org/wiki/Merkle_tree</a>
 */
public class MerkleTree {
  private String[] hashNodes;

  public MerkleTree(final ArrayList<String> elements) {
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
   * Time complexity: Θ(n)
   *
   * @param i
   */
  protected void build(int i) {
    if (hashNodes[i] == null) {
      build(left(i));
      build(right(i));
      hashNodes[i] = hash(hashNodes[left(i)] + hashNodes[right(i)]);
    }
  }

  /**
   * Time complexity: Θ(1)
   *
   * @param i
   * @return
   */
  protected static int parent(int i) {
    return (i + 1) / 2 - 1;
  }

  /**
   * Time complexity: Θ(1)
   *
   * @param i
   * @return
   */
  protected static int left(int i) {
    return 2 * i + 1;
  }

  /**
   * Time complexity: Θ(1)
   *
   * @param i
   * @return
   */
  protected static int right(int i) {
    return 2 * i + 2;
  }

  /**
   * Time complexity: Θ(1)
   *
   * @return
   */
  public String root() {
    return hashNodes[0];
  }

  /**
   * Time complexity: Θ(logn)
   *
   * @param elementIndex
   * @return
   */
  public ArrayList<String> getProofPath(int elementIndex) {
    ArrayList<String> proofPath = new ArrayList<>();
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
   * Time complexity: Θ(logn)
   *
   * @param element
   * @param rootHash
   * @param proofPath
   * @return
   */
  public static boolean verify(String element, String rootHash, final ArrayList<String> proofPath) {
    String tmpHash = hash(element);
    for (String s : proofPath) {
      tmpHash = hash(tmpHash + s);
    }
    return tmpHash.equals(rootHash);
  }

  /**
   * TODO
   *
   * @param content
   * @return
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
   * TODO
   *
   * @param hash
   * @return
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
