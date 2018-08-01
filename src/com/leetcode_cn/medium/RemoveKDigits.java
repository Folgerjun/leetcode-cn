package com.leetcode_cn.medium;

import java.util.Stack;

/****************移掉K位数字****************/
/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 
 * 注意:
 * 
 * num 的长度小于 10002 且 ≥ k。
 * 
 * num 不会包含任何前导零。
 * 
 * 示例 1 :
 * 
 * 输入: num = "1432219", k = 3 输出: "1219"
 * 
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 
 * 示例 2 :
 * 
 * 输入: num = "10200", k = 1 输出: "200"
 * 
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 
 * 示例 3 :
 * 
 * 输入: num = "10", k = 2 输出: "0"
 * 
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 * 
 * @author ffj
 *
 */
public class RemoveKDigits {

	public static void main(String[] args) {
		String num = "1432219";
		int k = 3;
		String value = removeKdigits(num, k);
		System.out.println(value);
	}

	public static String removeKdigits(String num, int k) {
		if (num.length() <= k)
			return "0";

		while (k-- > 0)
			num = newNum(num);

		while (num.charAt(0) == '0') // 去除前导零
			num = num.substring(1, num.length());

		return num;
	}

	/**
	 * 返回去除一个数字后数值最小的字符串
	 * 
	 * @param num
	 * @return
	 */
	public static String newNum(String num) {
		int length = num.length();
		for (int i = 0; i < length - 1; i++) {
			if (num.charAt(i) - '0' > num.charAt(i + 1) - '0') { // 只要后一位比前一位小 就移除满足条件
				num = num.substring(0, i) + num.substring(i + 1, length);
				break;
			}
			if (i == length - 2) // 遍历最后移除末尾
				num = num.substring(0, length - 1);
		}
		return num;
	}

	/**
	 * 社区讨论解答
	 * 
	 * @param num
	 * @param k
	 * @return
	 */
	public String removeKdigits1(String num, int k) {
		int len = num.length();
		// corner case
		if (k == len)
			return "0";

		Stack<Character> stack = new Stack<>();
		int i = 0;
		while (i < num.length()) {
			// whenever meet a digit which is less than the previous digit, discard the
			// previous one
			while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
				stack.pop();
				k--;
			}
			stack.push(num.charAt(i));
			i++;
		}

		// corner case like "1111"
		while (k > 0) {
			stack.pop();
			k--;
		}

		// construct the number from the stack
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty())
			sb.append(stack.pop());
		sb.reverse(); // stack 先进后出

		// remove all the 0 at the head
		while (sb.length() > 1 && sb.charAt(0) == '0') // 去除前导零
			sb.deleteCharAt(0);
		return sb.toString();
	}

}
