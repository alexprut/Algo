package com.alexprut.algo.datastructures;

import java.security.MessageDigest;

/**
 * A Bloom Filter is a space-efficient probabilistic data structure, that is used to test whether an
 * element is a member of a set. False positive matches are possible, but false negatives are not. A
 * query returns either "possibly in set" or "definitely not in set". Elements can be added to the
 * set, but not removed, the more items added, the larger the probability of false positives.
 *
 * <p>Example:
 *
 * <pre>
 * Given n = 2 elements and a rate of false positives p = 1% (0.01).
 * After inserting the elements: "algo" and "data" the bit array would be:
 * [1,1,1,1,1,1,0,0,0,1,0,0,0,0,1,1,0,0,1]
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Bloom_filter">https://en.wikipedia.org/wiki/Bloom_filter</a>
 */
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
   * Creates a Bloom Filter.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param n expected number of elements to be inserted
   * @param p rate of false positives, between 0 and 1
   */
  public BloomFilter(int n, double p) {
    this.n = n;
    this.m = computeOptimalM(n, p);
    this.k = computeOptimalK(n, m);
    bitArray = new boolean[m];
  }

  /**
   * Search and returns either "possibly in set" with a false positive rate p or "definitely not in
   * set".
   *
   * <p>Time complexity: Θ(k)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param element the element to search
   * @return if the element may be within the data structure or not
   */
  public boolean search(T element) {
    for (int i = 0; i < k; i++) {
      int index = hash((i + element.toString()).getBytes(), m);
      if (!bitArray[index]) {
        return false;
      }
    }

    return true;
  }

  /**
   * Insert a new element withing the filter.
   *
   * <p>Time complexity: Θ(k)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param element the element to insert
   */
  public void insert(T element) {
    for (int i = 0; i < k; i++) {
      int index =
          hash((i + element.toString()).getBytes(), m); // FIXME find something more efficient
      bitArray[index] = true;
    }
  }

  /**
   * Computes the hash of an element.
   *
   * <p>Time complexity: Θ(d)
   *
   * <p>Space complexity: Θ(d)
   *
   * @param content the element value in bytes
   * @return the hash
   */
  protected static int hash(byte[] content, int m) {
    // FIXME find something more efficient
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
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
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
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param n number of expected insertions
   * @param p rate of false positives, between 0 and 1
   */
  static int computeOptimalM(int n, double p) {
    return (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
  }
}
