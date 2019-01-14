package com.leetcode_cn.medium;

/******************Pow(x,n)*********/
/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * 
 * 示例 1:
 * 
 * 输入: 2.00000, 10 输出: 1024.00000
 * 
 * 示例 2:
 * 
 * 输入: 2.10000, 3 输出: 9.26100
 * 
 * 示例 3:
 * 
 * 输入: 2.00000, -2 输出: 0.25000
 * 
 * 解释: 2^-2 = 1/22 = 1/4 = 0.25
 * 
 * 说明:
 * 
 * -100.0 < x < 100.0
 * 
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 * 
 * @author ffj
 *
 */
public class Powxn {

	public static void main(String[] args) {
		double x = 2.0;
		int n = 10;
		double result = new Powxn().myPow(x, n);
		System.out.println("result: " + result);
	}

	public double myPow(double x, int n) {
		if (n == 0)
			return 1;
		double t = myPow(x, n / 2);
		// 避免多余的乘计算
		if (n % 2 != 0)
			return n < 0 ? 1 / x * t * t : x * t * t;
		else
			return t * t;
	}
}
