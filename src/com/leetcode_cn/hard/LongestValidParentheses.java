package com.leetcode_cn.hard;

import java.util.Stack;

/*******************最长有效括号***************/
/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * 
 * 示例 1:
 * 
 * 输入: "(()" 输出: 2
 * 
 * 解释: 最长有效括号子串为 "()"
 * 
 * 示例 2:
 * 
 * 输入: ")()())" 输出: 4
 * 
 * 解释: 最长有效括号子串为 "()()"
 * 
 * @author ffj
 *
 */
public class LongestValidParentheses {

	/**
	 * 判断是否是符合字符串
	 * 
	 * @param s
	 * @return
	 */
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			// 不是 '(' 就是 ')'，当 '(' 时直接放入栈中，当 ')' 时若栈顶是 '(' 则刚好满足，移除栈顶元素，否则就是不满足条件
			if (s.charAt(i) == '(') {
				stack.push('(');
			} else if (!stack.empty() && stack.peek() == '(') {
				stack.pop();
			} else {
				return false;
			}
		}
		return stack.empty();
	}

	public int longestValidParentheses(String s) {
		int maxlen = 0;
		for (int i = 0; i < s.length(); i++) {
			// 只有偶数长度字符串才可能满足要求
			for (int j = i + 2; j <= s.length(); j += 2) {
				if (isValid(s.substring(i, j))) {
					// 如果符合 就取较大值
					maxlen = Math.max(maxlen, j - i);
				}
			}
		}
		return maxlen;
	}

	/**
	 * DP
	 * 
	 * @param s
	 * @return
	 */
	public int longestValidParentheses1(String s) {
		int maxans = 0;
		int dp[] = new int[s.length()];
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				if (s.charAt(i - 1) == '(') {
					dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
				} else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
					dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
				}
				maxans = Math.max(maxans, dp[i]);
			}
		}
		return maxans;
	}

	/**
	 * Stack
	 * 
	 * @param s
	 * @return
	 */
	public int longestValidParentheses2(String s) {
		int maxans = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (stack.empty()) {
					stack.push(i);
				} else {
					maxans = Math.max(maxans, i - stack.peek());
				}
			}
		}
		return maxans;
	}

	/**
	 * 从左到右 从右到左 循环两遍
	 * 
	 * @param s
	 * @return
	 */
	public int longestValidParentheses3(String s) {
		int left = 0, right = 0, maxlength = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				left++;
			} else {
				right++;
			}
			if (left == right) {
				maxlength = Math.max(maxlength, 2 * right);
			} else if (right >= left) {
				left = right = 0;
			}
		}
		left = right = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == '(') {
				left++;
			} else {
				right++;
			}
			if (left == right) {
				maxlength = Math.max(maxlength, 2 * left);
			} else if (left >= right) {
				left = right = 0;
			}
		}
		return maxlength;
	}
}
