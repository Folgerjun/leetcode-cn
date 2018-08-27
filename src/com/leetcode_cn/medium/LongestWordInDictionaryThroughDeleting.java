package com.leetcode_cn.medium;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/********************通过删除字母匹配到字典里最长单词***********/
/**
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
 * 
 * 示例 1:
 * 
 * 输入: s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * 
 * 输出: "apple"
 * 
 * 示例 2:
 * 
 * 输入: s = "abpcplea", d = ["a","b","c"]
 * 
 * 输出: "a"
 * 
 * 说明:
 * 
 * 所有输入的字符串只包含小写字母。 字典的大小不会超过 1000。 所有输入的字符串长度不会超过 1000。
 * 
 * @author ffj
 *
 */
public class LongestWordInDictionaryThroughDeleting {

	public String findLongestWord(String s, List<String> d) {
		String longestWord = "";
		for (String str : d) {
			int l1 = longestWord.length();
			int l2 = str.length();
			if (l1 > l2 || (l1 == l2) && longestWord.compareTo(str) < 0)
				continue;
			if (helper(s, str)) {
				longestWord = str;
			}
		}
		return longestWord;
	}

	/**
	 * 判断是否可以匹配
	 * 
	 * @param s
	 * @param str
	 * @return
	 */
	public boolean helper(String s, String str) {

		int i = 0, j = 0;
		while (i < s.length() && j < str.length()) {
			if (s.charAt(i) == str.charAt(j)) {
				i++;
				j++;
			} else
				i++;
		}
		return j == str.length();
	}

	public boolean isSubsequence(String x, String y) {
		int j = 0;
		for (int i = 0; i < y.length() && j < x.length(); i++)
			if (x.charAt(j) == y.charAt(i))
				j++;
		return j == x.length();
	}

	/**
	 * 官网解法 先排序再判断
	 * 
	 * @param s
	 * @param d
	 * @return
	 */
	public String findLongestWord1(String s, List<String> d) {
		Collections.sort(d, new Comparator<String>() {
			// 长度降序 字典表升序 排序
			public int compare(String s1, String s2) {
				return s2.length() != s1.length() ? s2.length() - s1.length() : s1.compareTo(s2);
			}
		});
		for (String str : d) {
			if (isSubsequence(str, s))
				return str;
		}
		return "";
	}

	public boolean isSubsequence2(String x, String y) {
		int j = 0;
		for (int i = 0; i < y.length() && j < x.length(); i++)
			if (x.charAt(j) == y.charAt(i))
				j++;
		return j == x.length();
	}

	/**
	 * 官网解法 判断完再比较
	 * 
	 * @param s
	 * @param d
	 * @return
	 */
	public String findLongestWord2(String s, List<String> d) {
		String max_str = "";
		for (String str : d) {
			if (isSubsequence2(str, s)) {
				if (str.length() > max_str.length() || (str.length() == max_str.length() && str.compareTo(max_str) < 0))
					max_str = str;
			}
		}
		return max_str;
	}
}
