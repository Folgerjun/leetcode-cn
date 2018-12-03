package com.leetcode_cn.medium;

/*****************字符串相乘****************/
/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 
 * 示例 1:
 * 
 * 输入: num1 = "2", num2 = "3" 输出: "6"
 * 
 * 示例 2:
 * 
 * 输入: num1 = "123", num2 = "456" 输出: "56088"
 * 
 * 说明：
 * 
 * num1 和 num2 的长度小于110。
 * 
 * num1 和 num2 只包含数字 0-9。
 * 
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * 
 * @author ffj
 *
 */
public class MultiplyStrings {

	public String multiply(String num1, String num2) {

		int m = num1.length(), n = num2.length();
		// 相乘结果的长度
		int[] pos = new int[m + n];

		// 从低位开始循环乘
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				// 相乘得值
				int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				// 两数相乘结果在 pos 中影响的数值下标
				int p1 = i + j, p2 = i + j + 1;
				// 加上之前的旧值
				int sum = mul + pos[p2];
				// 有进位则加上
				pos[p1] += sum / 10;
				// 取余
				pos[p2] = sum % 10;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int p : pos) {
			// 过滤 0 开头的数
			if (!(sb.length() == 0 && p == 0)) {
				sb.append(p);
			}
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}

}
