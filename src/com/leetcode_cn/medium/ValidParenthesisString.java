package com.leetcode_cn.medium;

/*******************有效的括号字符串*******************/
/**
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。
 * 
 * 有效字符串具有如下规则：
 * 
 * 任何左括号 ( 必须有相应的右括号 )。
 * 
 * 任何右括号 ) 必须有相应的左括号 ( 。
 * 
 * 左括号 ( 必须在对应的右括号之前 )。
 * 
 * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 * 
 * 一个空字符串也被视为有效字符串。
 * 
 * 示例 1:
 * 
 * 输入: "()" 输出: True
 * 
 * 示例 2:
 * 
 * 输入: "(*)" 输出: True
 * 
 * 示例 3:
 * 
 * 输入: "(*))" 输出: True
 * 
 * 注意: 字符串大小将在 [1，100] 范围内。
 * 
 * @author ffj
 *
 */
public class ValidParenthesisString {

	public static void main(String[] args) {
		String s = "(**()";
		boolean value = checkValidString(s);
		System.out.println(value);
	}

	public static boolean checkValidString(String s) {
		int low = 0, high = 0;
		for (int i = 0; i < s.length(); i++) {
			char charvalue = s.charAt(i);
			if (charvalue == '(') {
				low++; // 计数都加一
				high++;
			} else if (charvalue == ')') {
				if (low > 0) {
					low--; // 之前出现过'('还未抵消的 计数减一
				}
				high--; // 计数')'
			} else { // '*'
				if (low > 0) {
					low--;
				}
				high++;
			}
			if (high < 0) {
				// 说明')'出现次数大于'(' 和 '*'之和，不可能校验成功
				return false;
			}
		}
		return low == 0;
	}

	/**
	 * 官网解法一
	 * 
	 * @param s
	 * @return
	 */
	public boolean checkValidString1(String s) {
		int n = s.length();
		if (n == 0)
			return true;
		boolean[][] dp = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == '*')
				dp[i][i] = true;
			if (i < n - 1 && (s.charAt(i) == '(' || s.charAt(i) == '*')
					&& (s.charAt(i + 1) == ')' || s.charAt(i + 1) == '*')) {
				dp[i][i + 1] = true;
			}
		}

		for (int size = 2; size < n; size++) {
			for (int i = 0; i + size < n; i++) {
				if (s.charAt(i) == '*' && dp[i + 1][i + size] == true) {
					dp[i][i + size] = true;
				} else if (s.charAt(i) == '(' || s.charAt(i) == '*') {
					for (int k = i + 1; k <= i + size; k++) {
						if ((s.charAt(k) == ')' || s.charAt(k) == '*') && (k == i + 1 || dp[i + 1][k - 1])
								&& (k == i + size || dp[k + 1][i + size])) {
							dp[i][i + size] = true;
						}
					}
				}
			}
		}
		return dp[0][n - 1];
	}

	/**
	 * 官网解法二
	 * 
	 * @param s
	 * @return
	 */
	public boolean checkValidString2(String s) {
		int lo = 0, hi = 0;
		for (char c : s.toCharArray()) {
			lo += c == '(' ? 1 : -1;
			hi += c != ')' ? 1 : -1;
			if (hi < 0)
				break;
			lo = Math.max(lo, 0);
		}
		return lo == 0;
	}

}
