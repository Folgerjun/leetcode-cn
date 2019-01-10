package com.leetcode_cn.medium;

/*************整数拆分*************/
/**
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * 
 * 示例 1:
 * 
 * 输入: 2 输出: 1
 * 
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 
 * 示例 2:
 * 
 * 输入: 10 输出: 36
 * 
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 * 
 * @author ffj
 *
 */
public class IntegerBreak {
	/**
	 * 全部分解为 2 和 3
	 * 
	 * @param n
	 * @return
	 */
	public int integerBreak(int n) {
		int[] dp = new int[n + 1];
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= i / 2; j++)
				dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]));
		}
		return dp[n];
	}

	public int integerBreak1(int n) {
		int max = 1;
		if (n == 2) // 1 * 1
			return 1;
		if (n == 3) // 1 * 2
			return 2;
		while (n > 0) {
			if (n == 2 || n == 3)
				return max * n;
			if (n == 4) {
				n -= 2;
				max *= 2;
			} else {
				n -= 3;
				max *= 3;
			}
		}
		return max;
	}
}
