package com.leetcode_cn.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/************同构字符串************/
/**
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * 
 * 示例 1:
 * 
 * 输入: s = "egg", t = "add" 输出: true
 * 
 * 示例 2:
 * 
 * 输入: s = "foo", t = "bar" 输出: false
 * 
 * 示例 3:
 * 
 * 输入: s = "paper", t = "title" 输出: true
 * 
 * 说明: 你可以假设 s 和 t 具有相同的长度。
 * 
 * @author ffj
 *
 */
public class IsomorphicStrings {

	/**
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isIsomorphic(String s, String t) {

		if (s.length() != t.length())
			return false;
		char[] sChar = s.toCharArray(), tChar = t.toCharArray();
		// 用来存放映射值 不能重复映射
		Set<Character> visit = new HashSet<>();
		Map<Character, Character> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char sch = sChar[i], tch = tChar[i];
			if (!map.containsKey(sch)) {
				// map 中还没放置该 key
				map.put(sch, tch);
				if (!visit.add(tch)) // 已经映射过了
					return false;
			} else {
				if (map.get(sch) != tch) // 映射的值不是同一个
					return false;
			}
		}
		return true;
	}

	/**
	 * 讨论区解法 转化为对应 ascii 码来确定唯一地址
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isIsomorphic1(String s, String t) {

		int m1[] = new int[256], m2[] = new int[256], n = s.length();
		char[] sChar = s.toCharArray(), tChar = t.toCharArray();
		for (int i = 0; i < n; ++i) {
			if (m1[sChar[i]] != m2[tChar[i]]) // 说明映射值不同
				return false;
			m1[sChar[i]] = i + 1; // 对应地址数字加 1
			m2[tChar[i]] = i + 1;
		}
		return true;

	}

}
