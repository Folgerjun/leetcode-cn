package com.leetcode_cn.easy;

/*************x 的平方根************/
/**
 * 实现 int sqrt(int x) 函数。
 * 
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 
 * 示例 1:
 * 
 * 输入: 4 输出: 2
 * 
 * 示例 2:
 * 
 * 输入: 8 输出: 2
 * 
 * 说明: 8 的平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 * 
 * @author ffj
 *
 */
public class Sqrtx {

	public static void main(String[] args) {
		int result = new Sqrtx().mySqrt(9);
		System.out.println("result:" + result);
	}

	/**
	 * 累加法
	 * 
	 * @param x
	 * @return
	 */
	public int mySqrt(int x) {
		if (x == 0)
			return 0;
		int result = 1;
		while (true) {
			if ((result + 1) <= x / (result + 1))
				result++;
			else
				break;
		}
		return result;
	}

	/**
	 * 二分法
	 * 
	 * @param x
	 * @return
	 */
	public int mySqrt1(int x) {
		if (x == 0)
			return 0;
		int l = 1, u = x;
		while (true) {
			int mid = (l + u) / 2;
			if (mid > x / mid)
				u = mid - 1;
			else if (mid + 1 > x / (mid + 1))
				return mid;
			else
				l = mid + 1;
		}
	}

}
