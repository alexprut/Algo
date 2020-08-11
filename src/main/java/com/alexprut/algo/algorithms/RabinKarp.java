package com.alexprut.algo.algorithms;

public class RabinKarp {

  /**
   * A string matching algorithm proposed by Rabin and Karp. The algorithm makes use of elementary
   * number-theoretic notions such as the equivalence of two numbers modulo a third number.
   *
   * <p>Time complexity: Θ((n - m + 1)m)
   *
   * <p>Space complexity: Θ(n) total with Θ(1) auxiliary
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm">https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm</a>
   * @param text the text to search in
   * @param pattern the pattern to search within the text
   * @param d the size of the alphabet
   * @param q the modulo
   * @return the index of the first occurrence of the match, -1 if there is no occurrence
   */
  public static int rabinKarp(String text, String pattern, int d, int q) {
    if (pattern.length() == 0) {
      return 0;
    }
    int n = text.length();
    int m = pattern.length();
    // Value of the high-order digit position of an m-digit window
    int h = ((int) Math.pow(d, m - 1)) % q;
    int p = 0;
    int t = 0;
    for (int i = 0; i < m; i++) {
      // Using Horner's rule
      p = (d * p + pattern.charAt(i)) % q;
      t = (d * t + text.charAt(i)) % q;
    }
    for (int s = 0; s <= n - m; s++) {
      if (p == t) {
        // spurious hit
        boolean isMatch = true;
        for (int i = 0; i < m; i++) {
          if (pattern.charAt(i) != text.charAt(s + i)) {
            isMatch = false;
          }
          if (isMatch) {
            return s;
          }
        }
      }
      if (s < n - m) {
        int prev = text.charAt(s);
        int succ = text.charAt(s + m);
        t = (d * (t - prev * h) + succ) % q;
        if (t < 0) {
          t = q + t;
        }
      }
    }
    return -1;
  }
}
