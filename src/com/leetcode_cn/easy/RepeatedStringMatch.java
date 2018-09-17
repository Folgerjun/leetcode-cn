package com.leetcode_cn.easy;

import java.math.BigInteger;

/*****************重复叠加字符串匹配***********/
/**
 * 给定两个字符串 A 和 B, 寻找重复叠加字符串A的最小次数，使得字符串B成为叠加后的字符串A的子串，如果不存在则返回 -1。
 * 
 * 举个例子，A = "abcd"，B = "cdabcdab"。
 * 
 * 答案为 3， 因为 A 重复叠加三遍后为 “abcdabcdabcd”，此时 B 是其子串；A 重复叠加两遍后为"abcdabcd"，B 并不是其子串。
 * 
 * 注意:
 * 
 * A 与 B 字符串的长度在1和10000区间范围内。
 * 
 * @author ffj
 *
 */
public class RepeatedStringMatch {

	public static void main(String[] args) {
		String A = "abc";
		String B = "cabcabca";
		System.out.println(new RepeatedStringMatch().repeatedStringMatch(A, B));
	}

	public int repeatedStringMatch(String A, String B) {
		String AA = A;
		int len = AA.length();
		int count = 1;
		while (true) {
			if (A.contains(B))
				return count;
			count++;
			if (A.length() - B.length() > len)
				return -1;
			A += AA;
		}
	}

	/**
	 * Ad-Hoc
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public int repeatedStringMatch1(String A, String B) {
		int q = 1;
		StringBuilder S = new StringBuilder(A);
		for (; S.length() < B.length(); q++)
			S.append(A);
		if (S.indexOf(B) >= 0) // StringBuilder.indexOf(String str)
			return q;
		if (S.append(A).indexOf(B) >= 0)
			return q + 1;
		return -1;
	}

	public boolean check(int index, String A, String B) {
		for (int i = 0; i < B.length(); i++) {
			if (A.charAt((i + index) % A.length()) != B.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Rabin-Karp (Rolling Hash)
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public int repeatedStringMatch2(String A, String B) {
		int q = (B.length() - 1) / A.length() + 1;
		int p = 113, MOD = 1_000_000_007;
		int pInv = BigInteger.valueOf(p).modInverse(BigInteger.valueOf(MOD)).intValue();

		long bHash = 0, power = 1;
		for (int i = 0; i < B.length(); i++) {
			bHash += power * B.codePointAt(i);
			bHash %= MOD;
			power = (power * p) % MOD;
		}

		long aHash = 0;
		power = 1;
		for (int i = 0; i < B.length(); i++) {
			aHash += power * A.codePointAt(i % A.length());
			aHash %= MOD;
			power = (power * p) % MOD;
		}

		if (aHash == bHash && check(0, A, B))
			return q;
		power = (power * pInv) % MOD;

		for (int i = B.length(); i < (q + 1) * A.length(); i++) {
			aHash -= A.codePointAt((i - B.length()) % A.length());
			aHash *= pInv;
			aHash += power * A.codePointAt(i % A.length());
			aHash %= MOD;
			if (aHash == bHash && check(i - B.length() + 1, A, B)) {
				return i < q * A.length() ? q : q + 1;
			}
		}
		return -1;
	}
}
