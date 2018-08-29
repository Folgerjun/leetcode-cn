package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.List;

/**************************括号生成*********************/
/**
 * 
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * 
 * 例如，给出 n = 3，生成结果为：
 * 
 * [
 * 
 * "((()))",
 * 
 * "(()())",
 * 
 * "(())()",
 * 
 * "()(())",
 * 
 * "()()()"
 * 
 * ]
 * 
 * @author ffj
 *
 */
public class GenerateParentheses {

	public static void main(String[] args) {
		System.out.println(new GenerateParentheses().generateParenthesis(3));
	}

	public List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList<>();
		helper("(", 1, n * 2 - 1, list);
		return list;
	}

	/**
	 * 
	 * @param str
	 *            挨个添加 "(" 或者 ")"
	 * @param sum
	 *            添加"(" : sum + 1, 添加 ")" : sum - 1
	 * @param num
	 *            剩余括号个数
	 * @param list
	 *            存放符合条件的String字符串
	 */
	private void helper(String str, int sum, int num, List<String> list) {
		// 不符合
		if (sum < 0)
			return;
		// 次数到 但不符合条件
		if (num == 0 && sum != 0)
			return;
		if (num == 0 && sum == 0) {
			// 次数到了 且符合条件
			list.add(str);
			return;
		}
		// 递归 DFS
		helper(str + "(", sum + 1, num - 1, list);
		helper(str + ")", sum - 1, num - 1, list);
	}

}
