package com.leetcode_cn.easy;

import java.util.HashMap;
import java.util.Map;

/******************找不同*************/
/**
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * 
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 
 * 请找出在 t 中被添加的字母。
 * 
 * 示例:
 * 
 * 输入：
 * 
 * s = "abcd" t = "abcde"
 * 
 * 输出： e
 * 
 * 解释： 'e' 是那个被添加的字母。
 * 
 * @author ffj
 *
 */
public class FindTheDifference {

	public static void main(String[] args) {
		String s = "abcd", t = "abcde";
		System.out.println(new FindTheDifference().findTheDifference1(s, t));
	}

	public char findTheDifference(String s, String t) {
		// key: 字符 ， value: 出现的次数
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		for (char c : t.toCharArray()) {
			if (map.get(c) != null) {
				if (map.get(c) > 0)
					map.put(c, map.get(c) - 1);
				else
					return c;
			} else
				return c;
		}
		return 0;
	}

	/**
	 * 位操作 由于只有一个不同 char，所以其余对称操作后值不变最后只会剩下多余的那个 char
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public char findTheDifference1(String s, String t) {
		int n = t.length();
		char c = t.charAt(n - 1);
		for (int i = 0; i < n - 1; ++i) {
			c ^= s.charAt(i);
			c ^= t.charAt(i);
		}
		return c;
	}

}
