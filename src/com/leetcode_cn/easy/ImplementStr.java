package com.leetcode_cn.easy;

/*************实现strStr()*************/
/**
 * 
 * 实现 strStr() 函数。
 * 
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置
 * (从0开始)。如果不存在，则返回 -1。
 * 
 * 示例 1:
 * 
 * 输入: haystack = "hello", needle = "ll" 输出: 2
 * 
 * 示例 2:
 * 
 * 输入: haystack = "aaaaa", needle = "bba" 输出: -1
 * 
 * 说明:
 * 
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * 
 * @author ffj
 *
 */
public class ImplementStr {

	public int strStr(String haystack, String needle) {
		if (needle.isEmpty())
			return 0;
		if (haystack.length() >= needle.length()) {
			for (int i = 0; i < haystack.length(); i++) {
				if (haystack.charAt(i) == needle.charAt(0) && haystack.substring(i).startsWith(needle))
					return i;
			}
		}
		return -1;
	}

	/**
	 * 讨论中解法 chatAt 挨个字符比较
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int strStr1(String haystack, String needle) {
		for (int i = 0;; i++) {
			for (int j = 0;; j++) {
				if (j == needle.length()) // 安全渡过了 needle 长度的字符，就认为是满足的字符串
					return i;
				if (i + j == haystack.length()) // 超出 haystack 字符串的长度说明无该子字符串
					return -1;
				if (needle.charAt(j) != haystack.charAt(i + j)) // 不相等就退出里循环
					break;
			}
		}
	}

	/**
	 * 讨论中解法 substring 截取整段比较
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int strStr2(String haystack, String needle) {
		int l1 = haystack.length(), l2 = needle.length();
		if (l1 < l2) {
			return -1;
		} else if (l2 == 0) {
			return 0;
		}
		int threshold = l1 - l2;
		for (int i = 0; i <= threshold; ++i) {
			if (haystack.substring(i, i + l2).equals(needle)) {
				return i;
			}
		}
		return -1;
	}

}
