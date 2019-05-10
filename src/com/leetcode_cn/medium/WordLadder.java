package com.leetcode_cn.medium;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/***********单词接龙**********/
/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord
 * 的最短转换序列的长度。转换需遵循如下规则：
 * 
 * 每次转换只能改变一个字母。
 * 
 * 转换过程中的中间单词必须是字典中的单词。
 * 
 * 说明:
 * 
 * 如果不存在这样的转换序列，返回 0。
 * 
 * 所有单词具有相同的长度。
 * 
 * 所有单词只由小写字母组成。
 * 
 * 字典中不存在重复的单词。
 * 
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 
 * 示例 1:
 * 
 * 输入:
 * 
 * beginWord = "hit",
 * 
 * endWord = "cog",
 * 
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * 输出: 5
 * 
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * 
 * 示例 2:
 * 
 * 输入:
 * 
 * beginWord = "hit"
 * 
 * endWord = "cog"
 * 
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * 输出: 0
 * 
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * 
 * @author ffj
 *
 */
public class WordLadder {

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {

		Set<String> wordlist = new LinkedHashSet<>(wordList);
		// 不包含 endWord 直接返回
		if (!wordlist.contains(endWord))
			return 0;
		// 若有 beginWord 移除
		if (wordlist.contains(beginWord))
			wordlist.remove(beginWord);
		// 至少是 2
		int res = 2;
		// 两个 set 集合
		Set<String> forward = new LinkedHashSet<>();
		forward.add(beginWord);
		Set<String> backward = new LinkedHashSet<>();
		backward.add(endWord);
		String zm = "qwertyuioplkjhgfdsazxcvbnm";
		char[] letters = zm.toCharArray();
		// endWord 长度
		int length = endWord.length();
		Set<String> temp = null;
		String x, y, z;
		while (forward.size() != 0) {
			// 满足个数少的在前 满足结果最小
			if (forward.size() > backward.size()) {
				temp = forward;
				forward = backward;
				backward = temp;
			}
			temp = new LinkedHashSet<>();
			for (String word : forward) {
				for (int i = 0; i < length; i++) {
					// 循环插入位置
					x = word.substring(0, i);
					y = word.substring(i + 1);
					for (char c : letters) {
						z = x + c + y;
						if (backward.contains(z)) // 找到满足 直接返回
							return res;
						if (wordlist.contains(z)) {
							temp.add(z); // 加入到 set 中
							wordlist.remove(z);
						}
					}
				}
			}
			res++; // 步数加 1
			forward = temp;
		}
		return 0;
	}
}
