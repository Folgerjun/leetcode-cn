package com.leetcode_cn.medium;

/************************乘积小于K的子数组*******************/
/**
 * 给定一个正整数数组 nums。
 * 
 * 找出该数组内乘积小于 k 的连续的子数组的个数。
 * 
 * 示例 1:
 * 
 * 输入: nums = [10,5,2,6], k = 100 输出: 8
 * 
 * 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * 
 * 说明:
 * 
 * 0 < nums.length <= 50000
 * 
 * 0 < nums[i] < 1000
 * 
 * 0 <= k < 10^6
 * 
 * @author ffj
 *
 */
public class SubarrayProductLessThanK {

	public static void main(String[] args) {
		int[] nums = { 10, 5, 2, 6 };
		int k = 100;
		int result = numSubarrayProductLessThanK(nums, k);
		System.out.println(result);
	}

	public static int numSubarrayProductLessThanK(int[] nums, int k) {
		if (k <= 1) // 不存在比1还小的正整数
			return 0;
		int length = nums.length;
		int left = 0, result = 0, value = 1;
		for (int right = 0; right < length; right++) {
			value *= nums[right];
			while (value >= k)
				value /= nums[left++]; // 乘积大于K 左下标往右移即除以最左数
			result += right - left + 1; // 右下标每往右移一个即新增一个数 满足的新的组数就是区间个数
		}
		return result;
	}

}
