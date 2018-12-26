package com.leetcode_cn.hard;

/*************通配符匹配**************/
/**
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * 
 * '?' 可以匹配任何单个字符。
 * 
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 
 * 两个字符串完全匹配才算匹配成功。
 * 
 * 说明:
 * 
 * s 可能为空，且只包含从 a-z 的小写字母。
 * 
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 
 * 示例 1:
 * 
 * 输入:
 * 
 * s = "aa" p = "a"
 * 
 * 输出: false
 * 
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 
 * 示例 2:
 * 
 * 输入:
 * 
 * s = "aa" p = "*"
 * 
 * 输出: true
 * 
 * 解释: '*' 可以匹配任意字符串。
 * 
 * 示例 3:
 * 
 * 输入:
 * 
 * s = "cb" p = "?a"
 * 
 * 输出: false
 * 
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 
 * 示例 4:
 * 
 * 输入: s = "adceb" p = "*a*b"
 * 
 * 输出: true
 * 
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 
 * 示例 5:
 * 
 * 输入:
 * 
 * s = "acdcb" p = "a*c?b"
 * 
 * 输入: false
 * 
 * @author ffj
 *
 */
public class WildcardMatching {

	/**
	 * DP
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p) {
		if (s == null || p == null)
			return s == p;
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;
		for (int row = 0, col = 1; col < dp[0].length; col++) {
			// p 的第一个字符为 '*' 的情况 因为 '*' 可匹配空字符
			if (p.charAt(col - 1) == '*')
				dp[row][col] = true;
			else
				break;
		}
		for (int row = 1; row < dp.length; row++) {
			for (int col = 1; col < dp[0].length; col++) {
				char str = s.charAt(row - 1);
				char pattn = p.charAt(col - 1);
				if (str == pattn || pattn == '?') { // 匹配成功
					dp[row][col] = dp[row - 1][col - 1];
				} else if (pattn == '*') {
					dp[row][col] = dp[row][col - 1] || dp[row - 1][col];
				}
			}
		}
		return dp[s.length()][p.length()];
	}

}
