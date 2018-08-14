package com.leetcode_cn.easy;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*********************字母大小写全排列*****************/
/**
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * 
 * 示例: 输入: S = "a1b2" 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * 
 * 输入: S = "3z4" 输出: ["3z4", "3Z4"]
 * 
 * 输入: S = "12345" 输出: ["12345"]
 * 
 * 注意：
 * 
 * S 的长度不超过12。 S 仅由数字和字母组成。
 * 
 * @author ffj
 *
 */
public class LetterCasePermutation {

	/**
	 * 广度优先搜索 BFS
	 * 
	 * @param S
	 * @return
	 */
	public List<String> letterCasePermutation(String S) {
		if (S == null) {
			return new LinkedList<>();
		}
		Queue<String> queue = new LinkedList<>();
		queue.offer(S);

		for (int i = 0; i < S.length(); i++) {
			if (Character.isDigit(S.charAt(i))) // 判断是否是数字
				continue;
			int size = queue.size();
			for (int j = 0; j < size; j++) { // 只要是字母就两个分支
				String cur = queue.poll(); // poll原先字符串 offer新的两字符串
				char[] chs = cur.toCharArray();

				chs[i] = Character.toUpperCase(chs[i]);
				queue.offer(String.valueOf(chs));

				chs[i] = Character.toLowerCase(chs[i]);
				queue.offer(String.valueOf(chs));
			}
		}

		return new LinkedList<>(queue);

	}

	/**
	 * DFS 深度优先搜索
	 * 
	 * @param S
	 * @return
	 */
	public List<String> letterCasePermutation1(String S) {
		if (S == null) {
			return new LinkedList<>();
		}

		List<String> res = new LinkedList<>();
		helper(S, res, 0);
		return res;
	}

	public void helper(String s, List<String> res, int pos) {
		if (pos == s.length()) { // 最后再统一添加
			res.add(s);
			return;
		}
		if (s.charAt(pos) >= '0' && s.charAt(pos) <= '9') {
			helper(s, res, pos + 1);
			return;
		}

		char[] chs = s.toCharArray();
		chs[pos] = Character.toLowerCase(chs[pos]);
		helper(String.valueOf(chs), res, pos + 1);

		chs[pos] = Character.toUpperCase(chs[pos]);
		helper(String.valueOf(chs), res, pos + 1);
	}
}
