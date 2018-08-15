package com.leetcode_cn.easy;

/*********************非递减数列****************/
/**
 * 给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * 
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，满足 array[i] <= array[i + 1]。
 * 
 * 示例 1:
 * 
 * 输入: [4,2,3] 输出: True 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 
 * 示例 2:
 * 
 * 输入: [4,2,1] 输出: False 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。 说明: n 的范围为 [1, 10,000]。
 * 
 * @author ffj
 *
 */
public class NonDecreasingArray {

	public boolean checkPossibility(int[] nums) {
		int length = nums.length;
		if (length == 1)
			return true;
		int num = 0;
		for (int i = 0; i < length - 1; i++) {
			if (nums[i] > nums[i + 1]) {
				num++;
				if (i != 0 && nums[i - 1] > nums[i + 1] && i + 3 <= length
						&& nums[i + 2] < Math.max(nums[i - 1], nums[i])) // 前后数字都要比较
					return false;
			}
			if (num > 1)
				return false;
		}
		return true;
	}
}
