package com.leetcode_cn.medium;

/***************************两数相除*********/
/**
 * 
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 
 * 示例 1:
 * 
 * 输入: dividend = 10, divisor = 3 输出: 3
 * 
 * 示例 2:
 * 
 * 输入: dividend = 7, divisor = -3 输出: -2
 * 
 * 说明:
 * 
 * 被除数和除数均为 32 位有符号整数。
 * 
 * 除数不为 0。
 * 
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 * 
 * @author ffj
 *
 */
public class DivideTwoIntegers {

	public static void main(String[] args) {
		System.out.println(new DivideTwoIntegers().divide(Integer.MIN_VALUE, 2));
	}

	/**
	 * 超时
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public int divide(int dividend, int divisor) {

		if (dividend == 0)
			return 0;

		if (dividend == Integer.MIN_VALUE && divisor == 1)
			return Integer.MIN_VALUE;

		if (dividend == Integer.MAX_VALUE && divisor == -1)
			return -Integer.MIN_VALUE;

		if ((dividend == Integer.MIN_VALUE && divisor == -1) || (dividend == Integer.MAX_VALUE && divisor == 1))
			return Integer.MAX_VALUE;

		double count = 0;
		double div = Math.abs((double) divisor);
		double divi = Math.abs((double) dividend);

		while (divi >= div) {
			divi -= div;
			count++;
		}
		return count > Integer.MAX_VALUE ? Integer.MAX_VALUE
				: (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? (int) count : (int) -count;
	}

	/**
	 * 讨论中解法
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public int divide1(int dividend, int divisor) {
		// Reduce the problem to positive long integer to make it easier.
		// Use long to avoid integer overflow cases.
		int sign = 1;
		if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
			sign = -1;
		long ldividend = Math.abs((long) dividend);
		long ldivisor = Math.abs((long) divisor);

		// Take care the edge cases.
		if (ldivisor == 0)
			return Integer.MAX_VALUE;
		if ((ldividend == 0) || (ldividend < ldivisor))
			return 0;

		long lans = ldivide(ldividend, ldivisor);

		int ans;
		if (lans > Integer.MAX_VALUE) { // Handle overflow.
			ans = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		} else {
			ans = (int) (sign * lans);
		}
		return ans;
	}

	/**
	 * 先用2的幂次方快速求值 剩余的数 递归解决
	 * 
	 * @param ldividend
	 * @param ldivisor
	 * @return
	 */
	private long ldivide(long ldividend, long ldivisor) {
		// Recursion exit condition
		if (ldividend < ldivisor)
			return 0;

		// Find the largest multiple so that (divisor * multiple <= dividend),
		// whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance
		// reason.
		// Think this as a binary search.
		long sum = ldivisor;
		long multiple = 1;
		while ((sum + sum) <= ldividend) {
			sum += sum;
			multiple += multiple;
		}
		// Look for additional value for the multiple from the reminder (dividend - sum)
		// recursively.
		// 先解决大的 再解决小的
		return multiple + ldivide(ldividend - sum, ldivisor);
	}

}
