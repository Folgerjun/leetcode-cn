package com.leetcode_cn.medium;

import java.util.Arrays;

/***********************最接近的三数之和*****************/
/**
 * 
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target
 * 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 * 
 * @author ffj
 *
 */
public class Sum3Closest {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 4, 8, 16, 32, 64, 128 };
		int target = 82;
		System.out.println(threeSumClosest(nums, target));
	}

	public static int threeSumClosest(int[] nums, int target) {

		if (nums.length < 3)
			return 0;
		int length = nums.length;
		Arrays.sort(nums);
		int closeNum = nums[0] + nums[1] + nums[length - 1]; // 初始值
		for (int i = 0; i < length - 2; i++) {
			int lo = i + 1, hi = length - 1;
			while (lo < hi) {
				int num = nums[i] + nums[lo] + nums[hi];
				if (num > target)
					hi--;
				else if (num < target)
					lo++;
				else
					return num;

				if (Math.abs(num - target) < Math.abs(closeNum - target))
					closeNum = num;
			}
		}
		return closeNum;
	}
}
