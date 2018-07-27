package com.leetcode_cn.medium;

/************* 长度最小的子数组 *************************/
/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 * 
 * 示例:
 * 
 * 输入: s = 7, nums = [2,3,1,2,4,3] 输出: 2 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。 进阶:
 * 
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 * 
 * @author ffj
 *
 */
public class MinimumSizeSubarraySum {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5 };
		int s = 15;
		int result = minSubArrayLen(s, nums);
		System.out.println(result);
	}

	public static int minSubArrayLen(int s, int[] nums) {

		for (int j = 0; j < nums.length; j++) { // 存在单个元素满足条件
			int num = nums[j];
			if (num >= s)
				return 1;
		}
		int val = nums.length;
		boolean flag = false;
		for (int i = 0; i < nums.length - 1; i++) { // 连续元素
			int sum = nums[i];
			for (int k = i + 1; k < nums.length; k++) {
				sum += nums[k];
				if (sum >= s) {
					int value = k - i + 1;
					if (value <= val) {
						flag = true;
						val = value;
					}
					System.out.println("k :" + k + " i :" + i);
					break;
				}

			}
		}
		if (flag)
			return val;
		return 0;
	}

	/**
	 * 精简写法
	 * 
	 * @param s
	 * @param a
	 * @return
	 */
	public int minSubArrayLen1(int s, int[] a) {
		if (a == null || a.length == 0)
			return 0;

		int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;

		while (j < a.length) {
			sum += a[j++];

			while (sum >= s) {
				min = Math.min(min, j - i);
				sum -= a[i++];
			}
		}

		return min == Integer.MAX_VALUE ? 0 : min;
	}

}
