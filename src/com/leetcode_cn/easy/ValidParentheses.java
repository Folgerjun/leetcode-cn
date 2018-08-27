package com.leetcode_cn.easy;

import java.util.Stack;

/***************************有效的括号*****************/
/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 
 * 有效字符串需满足：
 * 
 * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。 注意空字符串可被认为是有效字符串。
 * 
 * 示例 1:
 * 
 * 输入: "()" 输出: true
 * 
 * 示例 2:
 * 
 * 输入: "()[]{}" 输出: true
 * 
 * 示例 3:
 * 
 * 输入: "(]" 输出: false
 * 
 * 示例 4:
 * 
 * 输入: "([)]" 输出: false
 * 
 * 示例 5:
 * 
 * 输入: "{[]}" 输出: true
 * 
 * @author ffj
 *
 */
public class ValidParentheses {

	/**
	 * stack 先进后出
	 * 
	 * @param s
	 * @return
	 */
	public boolean isValid(String s) {

		if (s.isEmpty())
			return true;
		int len = s.length();
		if (len % 2 != 0)
			return false;
		if (s.charAt(0) == ')' || s.charAt(0) == ']' || s.charAt(0) == '}')
			return false;
		Stack<Character> stack = new Stack<>();
		for (Character c : s.toCharArray()) {
			if (c == '(' || c == '{' || c == '[')
				stack.push(c);
			else if (c == ')') {
				if (stack.peek() == '(') {
					stack.pop();
				} else
					return false;
			} else if (c == ']') {
				if (stack.peek() == '[') {
					stack.pop();
				} else
					return false;
			} else if (c == '}') {
				if (stack.peek() == '{') {
					stack.pop();
				} else
					return false;
			}
		}
		return stack.empty();
	}

	/**
	 * 更简洁
	 * 
	 * @param s
	 * @return
	 */
	public boolean isValid1(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			if (c == '(')
				stack.push(')');
			else if (c == '{')
				stack.push('}');
			else if (c == '[')
				stack.push(']');
			else if (stack.isEmpty() || stack.pop() != c)
				return false;
		}
		return stack.isEmpty();
	}

}
