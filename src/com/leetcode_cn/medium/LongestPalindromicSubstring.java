package com.leetcode_cn.medium;

/****************最长回文子串****************/
/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * 
 * 示例 1：
 * 
 * 输入: "babad" 输出: "bab" 注意: "aba"也是一个有效答案。
 * 
 * 示例 2：
 * 
 * 输入: "cbbd" 输出: "bb"
 * 
 * @author ffj
 *
 */
public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		String s = "asdhdfa";
		String result = longestPalindrome(s);
		System.out.println(result);
	}

	public static String longestPalindrome(String s) {
		if (s == "" || s.length() == 0)
			return "";
		if (s.length() == 1)
			return s;
		String result = "";
		for (int i = 0; i < s.length() - 1; i++) {
			for (int j = i + 1; j < s.length(); j++) {
				String str = s.substring(i, j + 1);
				if (isPalindromic(str)) {
					// 回文字符串
					if (str.length() > result.length()) { // 长度比之前要长
						result = str;
					}
				}
			}
		}
		return result == "" ? String.valueOf(s.charAt(0)) : result;

	}

	/**
	 * 判断字符串是不是回文字符串
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isPalindromic(String s) {

		if (s == "" || s.length() == 0)
			return false;
		if (s.length() == 1)
			return true;
		int length = s.length();
		boolean flag = true;
		for (int i = 0; i < length / 2; i++) {
			if (s.charAt(i) != s.charAt(length - 1 - i)) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * 官网解法
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome1(String s) {
		if (s == null || s.length() < 1)
			return "";
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i); // 奇数
			int len2 = expandAroundCenter(s, i, i + 1); // 偶数
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2; // 回文起始下标
				end = i + len / 2; // 回文结束下标
			}
		}
		return s.substring(start, end + 1);
	}

	/**
	 * 从回文中心点出发 两边扩散
	 * 
	 * @param s
	 * @param left
	 * @param right
	 * @return
	 */
	private int expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}
		return R - L - 1;
	}

}
