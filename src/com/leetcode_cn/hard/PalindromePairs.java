package com.leetcode_cn.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*****************回文对************/
/**
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 * 
 * 示例 1:
 * 
 * 输入: ["abcd","dcba","lls","s","sssll"] 输出: [[0,1],[1,0],[3,2],[2,4]]
 * 
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 
 * 示例 2:
 * 
 * 输入: ["bat","tab","cat"] 输出: [[0,1],[1,0]]
 * 
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 * 
 * @author ffj
 *
 */
public class PalindromePairs {

	/**
	 * Case1: If s1 is a blank string, then for any string that is palindrome s2,
	 * s1+s2 and s2+s1 are palindrome.
	 * 
	 * Case 2: If s2 is the reversing string of s1, then s1+s2 and s2+s1 are
	 * palindrome.
	 * 
	 * Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing
	 * string of s1[cut+1:] , then s2+s1 is palindrome.
	 * 
	 * Case 4: Similiar to case3. If s1[cut+1: ] is palindrome and there exists s2
	 * is the reversing string of s1[0:cut] , then s1+s2 is palindrome.
	 * 
	 * @param words
	 * @return
	 */
	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (words == null || words.length == 0) {
			return res;
		}
		// build the map save the key-val pairs: String - idx
		// map 存放值
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			map.put(words[i], i);
		}

		// special cases: "" can be combine with any palindrome string
		// 如果存在空字符串则位置放前后都行
		if (map.containsKey("")) {
			int blankIdx = map.get("");
			for (int i = 0; i < words.length; i++) {
				if (isPalindrome(words[i])) {
					if (i == blankIdx)
						continue;
					res.add(Arrays.asList(blankIdx, i));
					res.add(Arrays.asList(i, blankIdx));
				}
			}
		}

		// find all string and reverse string pairs
		// 把所有镜像对称的字符串找出
		for (int i = 0; i < words.length; i++) {
			String cur_r = reverseStr(words[i]);
			if (map.containsKey(cur_r)) {
				int found = map.get(cur_r);
				if (found == i)
					continue;
				res.add(Arrays.asList(i, found));
			}
		}

		// find the pair s1, s2 that
		// case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1)
		// case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverse(s2) => (s1, s2)
		for (int i = 0; i < words.length; i++) {
			String cur = words[i];
			for (int cut = 1; cut < cur.length(); cut++) {
				// s1：assaftg s2：gtf --> gtfassaftg
				if (isPalindrome(cur.substring(0, cut))) {
					String cut_r = reverseStr(cur.substring(cut));
					if (map.containsKey(cut_r)) {
						int found = map.get(cut_r);
						if (found == i)
							continue;
						res.add(Arrays.asList(found, i));
					}
				}
				// s1：jklsaas s2：lkj --> jklsaaslkj
				if (isPalindrome(cur.substring(cut))) {
					String cut_r = reverseStr(cur.substring(0, cut));
					if (map.containsKey(cut_r)) {
						int found = map.get(cut_r);
						if (found == i)
							continue;
						res.add(Arrays.asList(i, found));
					}
				}
			}
		}

		return res;
	}

	public String reverseStr(String str) {
		StringBuilder sb = new StringBuilder(str);
		return sb.reverse().toString();
	}

	public boolean isPalindrome(String s) {
		int i = 0;
		int j = s.length() - 1;
		while (i <= j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
}
