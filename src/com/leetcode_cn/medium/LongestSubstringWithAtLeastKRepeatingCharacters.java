package com.leetcode_cn.medium;

import java.util.Arrays;

/*******************至少有K个重复字符的最长子串************/
/**
 * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
 * 
 * 示例 1:
 * 
 * 输入: s = "aaabb", k = 3 输出: 3
 * 
 * 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 
 * 示例 2:
 * 
 * 输入: s = "ababbc", k = 2
 * 
 * 输出: 5
 * 
 * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 * 
 * @author ffj
 *
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {

	public static void main(String[] args) {
		String s = "aabcbcw";
		int k = 2;
		System.out.println(new LongestSubstringWithAtLeastKRepeatingCharacters().longestSubstring(s, k));
	}

	public int longestSubstring(String s, int k) {
		char[] str = s.toCharArray();
		int[] counts = new int[26];
		int h, i, j, idx, max = 0, unique, noLessThanK;

		for (h = 1; h <= 26; h++) { // 满足子串中字母的个数
			// 初始都为 0
			Arrays.fill(counts, 0);
			// 首尾两指针
			i = 0;
			j = 0;
			// 子串中字母个数
			unique = 0;
			// 子串中满足条件的字母个数
			noLessThanK = 0;
			while (j < str.length) {
				if (unique <= h) {
					// 26 字母位置
					idx = str[j] - 'a';
					if (counts[idx] == 0)
						// 首次出现 加一
						unique++;
					counts[idx]++;
					if (counts[idx] == k)
						// 已经满足要求
						noLessThanK++;
					j++;
				} else { // 从头开始删减字母 保持子串中字母个数
					idx = str[i] - 'a';
					if (counts[idx] == k) // 说明该字母原先满足了条件
						noLessThanK--;
					counts[idx]--;
					if (counts[idx] == 0) // 说明原先该字母只有一个
						unique--; // 将该字母剔除
					i++;
				}
				if (unique == h && unique == noLessThanK) // 子串中字母个数 = 满足条件的字母个数
					max = Math.max(j - i, max);
			}
		}

		return max;
	}

}
