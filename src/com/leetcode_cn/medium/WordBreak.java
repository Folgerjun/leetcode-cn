package com.leetcode_cn.medium;

import java.util.Arrays;
import java.util.List;

/***********************单词拆分****************/
/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 
 * 说明：
 * 
 * 拆分时可以重复使用字典中的单词。 你可以假设字典中没有重复的单词。 示例 1：
 * 
 * 输入: s = "leetcode", wordDict = ["leet", "code"] 输出: true
 * 
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 
 * 示例 2：
 * 
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"] 输出: true
 * 
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。 注意你可以重复使用字典中的单词。
 * 
 * 示例 3：
 * 
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"] 输出:
 * false
 * 
 * @author ffj
 *
 */
public class WordBreak {

	public static void main(String[] args) {
		String s = "abcd";
		List<String> wordDict = Arrays.asList("a", "abc", "b", "cd");
		System.out.println(wordBreak(s, wordDict));
	}

	static boolean bol = false;

	public static boolean wordBreak(String s, List<String> wordDict) {

		if (s.isEmpty()) {
			bol = true;
			return bol;
		}
		for (String str : wordDict) {
			if (s.startsWith(str)) {
				wordBreak(s.substring(str.length()), wordDict);
			}
		}
		return bol;
	}

	/**
	 * 讨论中解法 DP 动态规划
	 * 
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public static boolean wordBreak1(String s, List<String> wordDict) {
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;

		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) { // 为true说明之前的是可以匹配到的
				if (dp[j] && wordDict.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[s.length()];
	}

}
