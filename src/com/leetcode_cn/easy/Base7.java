package com.leetcode_cn.easy;

/***********************七进制数*********************/
/**
 * 
 * 给定一个整数，将其转化为7进制，并以字符串形式输出。
 * 
 * 示例 1:
 * 
 * 输入: 100 输出: "202"
 * 
 * 示例 2:
 * 
 * 输入: -7 输出: "-10"
 * 
 * 注意: 输入范围是 [-1e7, 1e7] 。
 * 
 * @author ffj
 *
 */
public class Base7 {

	public static void main(String[] args) {
		int num = -7;
		String result = convertToBase7(num);
		System.out.println(result);
	}

	public static String convertToBase7(int num) {

		if (num == 0)
			return "0";

		boolean negative = false;

		if (num < 0)
			negative = true;
		num = Math.abs(num);
		StringBuilder sb = new StringBuilder();
		while (num > 0) {
			int n = num % 7;
			sb.append(String.valueOf(n));
			num /= 7;
		}
		sb.reverse();
		return negative ? "-" + sb.toString() : sb.toString();
	}

	/**
	 * 讨论中解法
	 * 
	 * @param num
	 * @return
	 */
	public String convertTo7(int num) {
		if (num < 0)
			return '-' + convertTo7(-num);
		if (num < 7)
			return num + "";
		return convertTo7(num / 7) + num % 7;
	}

	// WHY Integer.toString(num, 7);
}
