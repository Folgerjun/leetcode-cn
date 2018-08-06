package com.leetcode_cn.easy;

/*********************反转整数*******************/
/**
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 * 
 * 示例 1:
 * 
 * 输入: 123 输出: 321
 * 
 * 示例 2:
 * 
 * 输入: -123 输出: -321
 * 
 * 示例 3:
 * 
 * 输入: 120 输出: 21
 * 
 * 注意: 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
 * 
 * @author ffj
 *
 */
public class ReverseInteger {

	public static void main(String[] args) {

		System.out.println(reverse(-345));
	}

	public static int reverse(int x) {

		int rev = 0;

		while (x != 0) {
			int pop = x % 10; // 取余
			x /= 10; // 取整
			// Integer.MAX_VALUE = 2147483647
			if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
				return 0;
			// Integer.MIN_VALUE = -2147483648
			if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
				return 0;

			rev = rev * 10 + pop;
		}

		return rev;
	}

}
