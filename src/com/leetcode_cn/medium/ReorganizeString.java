package com.leetcode_cn.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**********重构字符串**************/
/**
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * 
 * 示例 1:
 * 
 * 输入: S = "aab" 输出: "aba"
 * 
 * 示例 2:
 * 
 * 输入: S = "aaab" 输出: ""
 * 
 * 注意:
 * 
 * S 只包含小写字母并且长度在[1, 500]区间内。
 * 
 * @author ffj
 *
 */
public class ReorganizeString {

	public static void main(String[] args) {
		String S = "aab";
		System.out.println(new ReorganizeString().reorganizeString(S));
	}

	public String reorganizeString(String S) {
		// Create map of each char to its count
		// map 统计次数
		Map<Character, Integer> map = new HashMap<>();
		for (char c : S.toCharArray()) {
			int count = map.getOrDefault(c, 0) + 1;
			// Impossible to form a solution
			if (count > (S.length() + 1) / 2)
				return "";
			map.put(c, count);
		}
		// Greedy: fetch char of max count as next char in the result.
		// Use PriorityQueue to store pairs of (char, count) and sort by count DESC.
		// 优先级队列 根据次数倒序
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
		for (char c : map.keySet()) {
			pq.add(new int[] { c, map.get(c) });
		}
		// Build the result.
		// 次数最多的两个依次交替
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			int[] first = pq.poll();
			if (sb.length() == 0 || first[0] != sb.charAt(sb.length() - 1)) {
				sb.append((char) first[0]); // 拼接
				if (--first[1] > 0) {
					pq.add(first); // 不为 0 就接着塞回去
				}
			} else {
				int[] second = pq.poll();
				sb.append((char) second[0]);
				if (--second[1] > 0) {
					pq.add(second);
				}
				pq.add(first);
			}
		}
		return sb.toString();
	}
}
