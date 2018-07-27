package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/***************** 无重复字符的最长子串 ********************************/
/**
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * 
 * 示例：
 * 
 * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
 * 
 * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
 * 
 * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列 而不是子串。
 * 
 * @author ffj
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {

	public static void main(String[] args) {
		int result = lengthOfLongestSubstring3("abcabcbb");
		System.out.println(result);
	}

	/**
	 * 我的解法 （比较暴力..）
	 * 
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring(String s) {
		if ("".equals(s.trim()) || s == null) // 为空串返回0
			return 0;
		char[] charS = s.toCharArray();
		int length = charS.length;
		int value = 1;
		boolean flag = false;
		for (int i = 0; i < length - 1; i++) {
			char ch1 = charS[i];
			ArrayList<Character> charList = new ArrayList<>();
			charList.add(ch1);
			for (int j = i + 1; j < length; j++) {
				char ch2 = charS[j];
				if (charList.contains(ch2)) { // 若是出现重复值
					flag = true;
					int num = j - i;
					if (num > value) {
						value = num;
					}
					break;
				}
				if (j == (length - 1)) { // 取到最后一个还不出现重复值
					int num = j - i + 1;
					if (num > value) {
						value = num;
					}
					break;
				}
				charList.add(ch2);

			}
		}
		if (!flag) {
			return length;
		}

		return value;
	}

	/**
	 * 官网解法一 暴力解法 大致思路与我解法一致
	 * 
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring1(String s) {
		int n = s.length();
		int ans = 0;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j <= n; j++)
				if (allUnique(s, i, j))
					ans = Math.max(ans, j - i);
		return ans;
	}

	public static boolean allUnique(String s, int start, int end) {
		Set<Character> set = new HashSet<>();
		for (int i = start; i < end; i++) {
			Character ch = s.charAt(i);
			if (set.contains(ch))
				return false;
			set.add(ch);
		}
		return true;
	}

	/**
	 * 官网解法二
	 * 
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring2(String s) {
		int n = s.length();
		Set<Character> set = new HashSet<>();
		int ans = 0, i = 0, j = 0;
		while (i < n && j < n) {
			// try to extend the range [i, j]
			if (!set.contains(s.charAt(j))) { // set集合中没有该元素
				set.add(s.charAt(j++)); // 添加
				ans = Math.max(ans, j - i); // 并与之前比较选择最大值
			} else {
				set.remove(s.charAt(i++)); // 若有该元素 从头部开始移除 直到不存有相同元素
			}
		}
		return ans;
	}

	/**
	 * 官网解法三
	 * 
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring3(String s) { // abcabcbb
		int n = s.length(), ans = 0;
		Map<Character, Integer> map = new HashMap<>(); // current index of character
		// try to extend the range [i, j]
		for (int j = 0, i = 0; j < n; j++) {
			if (map.containsKey(s.charAt(j))) {
				i = Math.max(map.get(s.charAt(j)), i); // 获取重复值的value值 即 index+1
			}
			ans = Math.max(ans, j - i + 1); // 取最大值
			map.put(s.charAt(j), j + 1); // put新value
		}
		return ans;
	}

	/**
	 * 官网解法四 Assuming ASCII 128 与解法三差不多 相同字母的ascii码一样 不同的不一样
	 * 
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring4(String s) {
		int n = s.length(), ans = 0;
		int[] index = new int[128]; // current index of character
		// try to extend the range [i, j]
		for (int j = 0, i = 0; j < n; j++) {
			i = Math.max(index[s.charAt(j)], i);
			ans = Math.max(ans, j - i + 1);
			index[s.charAt(j)] = j + 1;
		}
		return ans;
	}
}
