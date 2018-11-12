package com.leetcode_cn.easy;

import java.util.ArrayList;
import java.util.List;

/**************找到字符串中所有字母异位词************/
/**
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * 
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * 
 * 说明：
 * 
 * 字母异位词指字母相同，但排列不同的字符串。 不考虑答案输出的顺序。
 * 
 * 示例 1:
 * 
 * 输入: s: "cbaebabacd" p: "abc"
 * 
 * 输出: [0, 6]
 * 
 * 解释:
 * 
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 * 
 * 示例 2:
 * 
 * 输入: s: "abab" p: "ab"
 * 
 * 输出: [0, 1, 2]
 * 
 * 解释:
 * 
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 
 * @author ffj
 *
 */
public class FindAllAnagramsInAString {

	/**
	 * 超时
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> list = new ArrayList<>();
		int len = p.length();

		for (int i = 0; i <= s.length() - len; i++) {
			String str = s.substring(i, i + len);
			if (isEctopic(str, p))
				list.add(i);
		}
		return list;
	}

	/**
	 * 是否是字母异位词
	 * 
	 * @param str
	 * @param p
	 * @return
	 */
	private boolean isEctopic(String str, String p) {
		List<Character> list = new ArrayList<>();
		for (char c : str.toCharArray()) {
			list.add(c);
		}
		for (char c : p.toCharArray()) {
			int i = list.indexOf(c);
			if (i != -1)
				list.remove(i);
			else
				return false;
			if (list.size() == 0)
				return true;
		}
		return false;
	}

	/**
	 * 讨论中解法 sliding window
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public List<Integer> findAnagrams1(String s, String p) {
		int[] chars = new int[26]; // 对应 26 个英文字母出现的次数
		List<Integer> result = new ArrayList<>();

		if (s == null || p == null || s.length() < p.length())
			return result;
		for (char c : p.toCharArray())
			chars[c - 'a']++;

		int start = 0, end = 0, count = p.length();
		// Go over the string
		while (end < s.length()) { // 满足其元素在 p 中条件才对 count 进行操作
			// If the char at start appeared in p, we increase count
			if (end - start == p.length() && chars[s.charAt(start++) - 'a']++ >= 0)
				count++;
			// If the char at end appeared in p (since it's not -1 after decreasing), we
			// decrease count
			if (--chars[s.charAt(end++) - 'a'] >= 0)
				count--;
			if (count == 0) // 表示 start 和 end 之间元素都在 p 中，且长度为 p 的长度
				result.add(start);
		}

		return result;
	}

}
