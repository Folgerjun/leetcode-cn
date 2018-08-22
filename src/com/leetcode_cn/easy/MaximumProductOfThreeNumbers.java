package com.leetcode_cn.easy;

import java.util.Arrays;

/********************三个数的最大乘积*************/
/**
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * 
 * 示例 1:
 * 
 * 输入: [1,2,3] 输出: 6 示例 2:
 * 
 * 输入: [1,2,3,4] 输出: 24 注意:
 * 
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 * 
 * @author ffj
 *
 */
public class MaximumProductOfThreeNumbers {

	public int maximumProduct(int[] nums) {

		Arrays.sort(nums);
		int len = nums.length;
		// 若第一第二为负数 则第一第二和最后相乘 否则倒数三数相乘最大
		return Math.max(nums[len - 1] * nums[len - 2] * nums[len - 3], nums[0] * nums[1] * nums[len - 1]);
	}

}
