package com.leetcode_cn.easy;

/****************最大子序和******************/
/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 
 * 示例:
 * 
 * 输入: [-2,1,-3,4,-1,2,1,-5,4], 输出: 6
 * 
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 
 * 进阶:
 * 
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * 
 * @author ffj
 *
 */
public class MaximumSubarray {

	/**
	 * 动态规划 DP ：
	 * 
	 * nums:[-2,1,-3,4,-1,2,1,-5,4]
	 * 
	 * dp : [-2,1,-2,4,3,5,6,1,5]
	 * 
	 * @param nums
	 * @return
	 */
	public int maxSubArray(int[] nums) {
		if (nums.length == 0)
			return 0;

		int len = nums.length;
		int[] dp = new int[len];
		dp[0] = nums[0];
		int max = nums[0];
		for (int i = 0; i < len; i++) {
			// 上一个数为负数说明之前的序列都作废 自身重新开始
			dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	/**
	 * 思想跟上一个方法一样
	 * 
	 * @param A
	 * @return
	 */
	public static int maxSubArray1(int[] A) {
		int maxSoFar = A[0], maxEndingHere = A[0];
		for (int i = 1; i < A.length; ++i) {
			// 之前的序列数之和与当前元素相比 取较大值
			maxEndingHere = Math.max(maxEndingHere + A[i], A[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}
		return maxSoFar;
	}

}
