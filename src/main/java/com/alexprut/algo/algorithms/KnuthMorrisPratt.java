package com.alexprut.algo.algorithms;

public class KnuthMorrisPratt {

  /**
   * Linear time string matching algorithm by Knuth, Morris and Pratt.
   *
   * <p>Example: given the text "market" and the pattern "ark" the returning value will be `1`, i.e
   * the index where the first match occurs ("m ark et").
   *
   * <p>Time complexity: Θ(n)
   *
   * <p>Space complexity: Θ(n) total with Θ(1) auxiliary
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm">https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm</a>
   * @param text the text to search in
   * @param pattern the pattern to search within the text
   * @return the index of the first occurrence of the match, -1 if there is no occurrence
   */
  public static int knuthMorrisPratt(String text, String pattern) {
    if (pattern.length() == 0) {
      return 0;
    }
    int n = text.length();
    int m = pattern.length();
    int[] p = computePrefixFunction(pattern);
    int q = 0;
    for (int i = 0; i < n; i++) {
      while (q > 0 && pattern.charAt(q) != text.charAt(i)) {
        q = p[q];
      }
      if (pattern.charAt(q) == text.charAt(i)) {
        q++;
      }
      if (q == m) {
        // Pattern occurs
        return i - m + 1;
      }
    }
    return -1;
  }

  /**
   * Utility function for the Knuth Morris Pratt algorithm {@link #knuthMorrisPratt(String,
   * String)}. Computes the prefix function.
   *
   * <p>Time complexity: Θ(m)
   *
   * <p>Space complexity: Θ(m) total with Θ(1) auxiliary
   *
   * @param pattern the pattern for the the prefix function
   * @return the prefix function
   */
  protected static int[] computePrefixFunction(String pattern) {
    int m = pattern.length();
    int[] p = new int[m];
    p[0] = 0;
    int k = 0;
    for (int q = 1; q < m; q++) {
      while (k > 0 && pattern.charAt(k) != pattern.charAt(q)) {
        k = p[k];
      }
      if (pattern.charAt(k) == pattern.charAt(q)) {
        k++;
      }
      p[q] = k;
    }
    return p;
  }
}
