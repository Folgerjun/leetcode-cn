package com.leetcode_cn.hard;

/*********************正则表达式匹配*********************/
/**
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 * 
 * '.' 匹配任意单个字符。
 * 
 * '*' 匹配零个或多个前面的元素。
 * 
 * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
 * 
 * 说明:
 * 
 * s 可能为空，且只包含从 a-z 的小写字母。
 * 
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 
 * 示例 1:
 * 
 * 输入: s = "aa" p = "a" 输出: false
 * 
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 
 * 示例 2:
 * 
 * 输入: s = "aa" p = "a*" 输出: true
 * 
 * 解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
 * 
 * 示例 3:
 * 
 * 输入: s = "ab" p = ".*" 输出: true
 * 
 * 解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
 * 
 * 示例 4:
 * 
 * 输入: s = "aab" p = "c*a*b" 输出: true
 * 
 * 解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
 * 
 * 示例 5:
 * 
 * 输入: s = "mississippi" p = "mis*is*p*." 输出: false
 * 
 * @author ffj
 *
 */
public class RegularExpressionMatching {

	public boolean isMatch(String s, String p) {
		if (p.isEmpty()) // 若p为空 s也为空就返回true否则返回false
			return s.isEmpty();
		if (p.length() == 1) // 若p长度为1
			return (s.length() == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
		if (p.charAt(1) != '*') {
			if (s.isEmpty()) // p第二个不为* 若是s为空返回false
				return false;
			// 首字母匹配后 递归匹配剩余字母
			return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
		}
		while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
			if (isMatch(s, p.substring(2)))
				return true;
			s = s.substring(1);
		}
		return isMatch(s, p.substring(2));

	}

	/**
	 * 官网解法
	 * 
	 * @param text
	 * @param pattern
	 * @return
	 */
	public boolean isMatch1(String text, String pattern) {
		if (pattern.isEmpty())
			return text.isEmpty();
		boolean first_match = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

		if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
			return (isMatch(text, pattern.substring(2)) || (first_match && isMatch(text.substring(1), pattern)));
		} else {
			return first_match && isMatch(text.substring(1), pattern.substring(1));
		}
	}

	/**
	 * dp解法
	 * 
	 * @param text
	 * @param pattern
	 * @return
	 */
	public boolean isMatch2(String text, String pattern) {
		boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
		dp[text.length()][pattern.length()] = true;

		for (int i = text.length(); i >= 0; i--) {
			for (int j = pattern.length() - 1; j >= 0; j--) {
				boolean first_match = (i < text.length()
						&& (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));
				if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
					dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
				} else {
					dp[i][j] = first_match && dp[i + 1][j + 1];
				}
			}
		}
		return dp[0][0];
	}
}
