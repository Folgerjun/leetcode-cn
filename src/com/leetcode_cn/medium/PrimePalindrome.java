package com.leetcode_cn.medium;

/*******************回文素数*************/
/**
 * 求出大于或等于 N 的最小回文素数。
 * 
 * 回顾一下，如果一个数大于 1，且其因数只有 1 和它自身，那么这个数是素数。
 * 
 * 例如，2，3，5，7，11 以及 13 是素数。
 * 
 * 回顾一下，如果一个数从左往右读与从右往左读是一样的，那么这个数是回文数。
 * 
 * 例如，12321 是回文数。
 * 
 * 示例 1： 输入：6 输出：7
 * 
 * 示例 2： 输入：8 输出：11
 * 
 * 示例 3： 输入：13 输出：101
 * 
 * 提示：
 * 
 * 1 <= N <= 10^8
 * 
 * 答案肯定存在，且小于 2 * 10^8。
 * 
 * @author ffj
 *
 */
public class PrimePalindrome {

	public int primePalindrome(int N) {
		for (;; N++) {
			if (Palindrome(N) && Prime(N))
				return N;
		}

	}

	/**
	 * 判断是否是素数
	 * 
	 * @param N
	 * @return
	 */
	private boolean Palindrome(int N) {
		if (N == 1)
			return false;
		for (int k = 2; k <= Math.sqrt(N); k++) {
			if (N % k == 0)
				return false;
		}
		return true;
	}

	/**
	 * 判断是否是回文数
	 * 
	 * @param N
	 * @return
	 */
	private boolean Prime(int N) {
		int NN = 0, M = N;
		while (N > 0) {
			int n = N % 10;
			NN = NN * 10 + n;
			N /= 10;
		}
		return NN == M;
	}

	/**
	 * 官网解法一 迭代回文
	 * 
	 * @param N
	 * @return
	 */
	public int primePalindrome1(int N) {
		for (int L = 1; L <= 5; ++L) {
			// Check for odd-length palindromes
			for (int root = (int) Math.pow(10, L - 1); root < (int) Math.pow(10, L); ++root) {
				StringBuilder sb = new StringBuilder(Integer.toString(root));
				for (int k = L - 2; k >= 0; --k)
					sb.append(sb.charAt(k));
				int x = Integer.valueOf(sb.toString());
				if (x >= N && isPrime(x))
					return x;
				// If we didn't check for even-length palindromes:
				// return N <= 11 ? min(x, 11) : x
			}

			// Check for even-length palindromes
			for (int root = (int) Math.pow(10, L - 1); root < (int) Math.pow(10, L); ++root) {
				StringBuilder sb = new StringBuilder(Integer.toString(root));
				for (int k = L - 1; k >= 0; --k)
					sb.append(sb.charAt(k));
				int x = Integer.valueOf(sb.toString());
				if (x >= N && isPrime(x))
					return x;
			}
		}

		throw null;
	}

	/**
	 * 是否是素数
	 * 
	 * @param N
	 * @return
	 */
	public boolean isPrime(int N) {
		if (N < 2)
			return false;
		int R = (int) Math.sqrt(N);
		for (int d = 2; d <= R; ++d)
			if (N % d == 0)
				return false;
		return true;
	}

	/**
	 * 官网解法二 数学捷径的蛮力
	 * 
	 * @param N
	 * @return
	 */
	public int primePalindrome2(int N) {
		while (true) {
			if (N == reverse(N) && isPrime(N))
				return N;
			N++;
			if (10_000_000 < N && N < 100_000_000)
				N = 100_000_000;
		}
	}

	public int reverse(int N) {
		int ans = 0;
		while (N > 0) {
			ans = 10 * ans + (N % 10);
			N /= 10;
		}
		return ans;
	}

	public static void main(String[] args) {

		// System.out.println(new PrimePalindrome().Palindrome(1));
		// System.out.println(new PrimePalindrome().Prime(1251));

		System.out.println(new PrimePalindrome().primePalindrome(6));
		System.out.println(new PrimePalindrome().primePalindrome(8));
		System.out.println(new PrimePalindrome().primePalindrome(13));

		// int N = 1, M = 1;
		// for (;; N++) {
		// System.out.println("N :" + N);
		// if (N == 5)
		// break;
		// }
		// while (M++ > 0) {
		// System.out.println("M :" + M);
		// if (M == 5)
		// break;
		// }
	}
}
