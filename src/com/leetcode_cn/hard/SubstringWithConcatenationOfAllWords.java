package com.leetcode_cn.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*********************与所有单词相关联的字串****************/
/**
 * 给定一个字符串 s 和一些长度相同的单词 words。在 s 中找出可以恰好串联 words 中所有单词的子串的起始位置。
 * 
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * 
 * 示例 1:
 * 
 * 输入: s = "barfoothefoobarman", words = ["foo","bar"]
 * 
 * 输出: [0,9]
 * 
 * 解释: 从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。 输出的顺序不重要, [9,0] 也是有效答案。
 * 
 * 示例 2:
 * 
 * 输入: s = "wordgoodstudentgoodword", words = ["word","student"] 输出: []
 * 
 * @author ffj
 *
 */
public class SubstringWithConcatenationOfAllWords {

	public List<Integer> findSubstring(String s, String[] words) {

		if (s == null || words == null || words.length == 0 || s.length() < words[0].length() * words.length)
			return new ArrayList<>();

		final Map<String, Integer> counts = new HashMap<>();

		for (final String word : words) // 统计每个 word 的数量
			counts.put(word, counts.getOrDefault(word, 0) + 1);

		final List<Integer> indexes = new ArrayList<>();
		final int n = s.length(), num = words.length, len = words[0].length();
		for (int i = 0; i < n - num * len + 1; i++) {
			// 统计每次循环中匹配到的 word 数量
			final Map<String, Integer> seen = new HashMap<>();
			int j = 0;
			while (j < num) {
				final String word = s.substring(i + j * len, i + (j + 1) * len);
				if (counts.containsKey(word)) {
					seen.put(word, seen.getOrDefault(word, 0) + 1);
					// 匹配到多余的 word 直接退出循环
					if (seen.get(word) > counts.getOrDefault(word, 0)) {
						break;
					}
				} else {
					// 匹配到 words 中没出现过的 word 直接退出循环
					break;
				}
				j++; // 成功匹配到一个word
			}
			if (j == num) { // 都能连续匹配到就满足条件
				indexes.add(i);
			}
		}
		return indexes;
	}

}
