package com.leetcode_cn.medium;

import java.util.Arrays;

/*************************组合总和IV****************/
/**
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 * 
 * 示例:
 * 
 * nums = [1, 2, 3] target = 4
 * 
 * 所有可能的组合为：
 * 
 * (1, 1, 1, 1)
 * 
 * (1, 1, 2)
 * 
 * (1, 2, 1)
 * 
 * (1, 3)
 * 
 * (2, 1, 1)
 * 
 * (2, 2)
 * 
 * (3, 1)
 * 
 * 请注意，顺序不同的序列被视作不同的组合。
 * 
 * 因此输出为 7。
 * 
 * 进阶：
 * 
 * 如果给定的数组中含有负数会怎么样？
 * 
 * 问题会产生什么变化？
 * 
 * 我们需要在题目中添加什么限制来允许负数的出现？
 * 
 * @author ffj
 *
 */
public class CombinationSumIV {

	/**
	 * DFS 超时
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int combinationSum4(int[] nums, int target) {
		if (target == 0) {
			return 1;
		}
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			if (target >= nums[i]) { // 还有差值
				res += combinationSum4(nums, target - nums[i]);
			}
		}
		return res;
	}

	private int[] dp;

	/**
	 * DP
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int combinationSum41(int[] nums, int target) {
		dp = new int[target + 1];
		Arrays.fill(dp, -1);
		dp[0] = 1;
		return helper(nums, target);
	}

	private int helper(int[] nums, int target) {
		if (dp[target] != -1) {
			return dp[target]; // 若是之前的已经计算过了 直接返回
		}
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			if (target >= nums[i]) { // 所有组合的种数相加
				res += helper(nums, target - nums[i]);
			}
		}
		dp[target] = res; // 计算后赋值用于之后的数字使用
		return res;
	}

	/**
	 * DP
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int combinationSum42(int[] nums, int target) {
		int[] comb = new int[target + 1];
		comb[0] = 1;
		for (int i = 1; i < comb.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (i - nums[j] >= 0) {
					comb[i] += comb[i - nums[j]];
				}
			}
		}
		return comb[target];
	}
}
