package com.alexprut.algo.datastructures;

import java.security.MessageDigest;

/** */
public class BloomFilter<T> {

  /** The number of bits. */
  protected int m;
  /** The number of hash functions. */
  protected int k;
  /** The number of elements expected to be inserted. */
  protected int n;
  /** The bit array. */
  protected boolean[] bitArray;

  /**
   * @param n expected number of elements to be inserted
   * @param p rate of false positives, 0 < p < 1
   */
  public BloomFilter(int n, double p) {
    this.n = n;
    this.m = computeOptimalM(n, p);
    this.k = computeOptimalK(n, m);
    bitArray = new boolean[m];
  }

  public boolean search(T value) {
    for (int i = 0; i < k; i++) {
      int index = hash((i + value.toString()).getBytes(), m);
      if (!bitArray[index]) {
        return false;
      }
    }

    return true;
  }

  /** @param element the element to insert */
  public void insert(T element) {
    for (int i = 0; i < k; i++) {
      int index = hash((i + element.toString()).getBytes(), m);
      bitArray[index] = true;
    }
  }

  /**
   * Computes the hash of an element. FIXME find something more efficient
   *
   * @param content the element value
   * @return the hash
   */
  protected static int hash(byte[] content, int m) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] d = digest.digest(content);
      int res = 0;
      for (byte b : d) {
        res += b;
        res = Math.abs(res);
      }
      return res % m;
    } catch (Exception e) {
      return 0;
    }
  }

  /**
   * Computes k, the optimal number of hashes, given the expected number of insertions and size of
   * the bit array.
   *
   * <p>Formula: (m / n) * log(2)
   *
   * @param n expected insertions
   * @param m total number of bits in Bloom filter
   */
  static int computeOptimalK(int n, int m) {
    return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
  }

  /**
   * Computes m, the number of bits, which is expected to achieve, for the specified expected
   * insertions, the required false positive probability.
   *
   * <p>Formula: -n * lnp / ((ln2) ^ 2)
   *
   * @param n number of expected insertions
   * @param p rate of false positives, 0 < p < 1
   */
  static int computeOptimalM(int n, double p) {
    return (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
  }
}
