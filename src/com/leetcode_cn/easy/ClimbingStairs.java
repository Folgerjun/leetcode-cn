package com.leetcode_cn.easy;

/*************爬楼梯*********/
/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 
 * 注意：给定 n 是一个正整数。
 * 
 * 示例 1：
 * 
 * 输入： 2 输出： 2
 * 
 * 解释： 有两种方法可以爬到楼顶。
 * 
 * 1. 1 阶 + 1 阶
 * 
 * 2. 2 阶
 * 
 * 示例 2：
 * 
 * 输入： 3 输出： 3
 * 
 * 解释： 有三种方法可以爬到楼顶。
 * 
 * 1. 1 阶 + 1 阶 + 1 阶
 * 
 * 2. 1 阶 + 2 阶
 * 
 * 3. 2 阶 + 1 阶
 * 
 * @author ffj
 *
 */
public class ClimbingStairs {

	public static void main(String[] args) {
		int result = new ClimbingStairs().climbStairs(44);
		System.out.println("result:" + result);
	}

	/**
	 * 斐波那契数列 相似
	 * 
	 * @param n
	 * @return
	 */
	public int climbStairs1(int n) {

		if (n == 1 || n == 2 || n == 3)
			return n;
		int res = 0;
		int i = 1, j = 2;
		int z = 3;
		while (z++ <= n) {
			res = i + j;
			i = j;
			j = res;
		}
		return res;
	}

	/**
	 * 递归超时
	 * 
	 * @param n
	 * @return
	 */
	public int climbStairs(int n) {
		return helper(n, 0);
	}

	private int helper(int n, int num) {
		if (n == 0)
			return ++num;
		if (n < 0)
			return num;
		num = helper(n - 1, num);
		num = helper(n - 2, num);

		return num;
	}

}
