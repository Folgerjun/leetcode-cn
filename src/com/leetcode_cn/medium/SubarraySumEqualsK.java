package com.leetcode_cn.medium;

import java.util.HashMap;
import java.util.Map;

/************和为 K 的子数组**********/
/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * 
 * 示例 1 :
 * 
 * 输入:nums = [1,1,1], k = 2 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 
 * 说明 :
 * 
 * 数组的长度为 [1, 20,000]。
 * 
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 * 
 * @author ffj
 *
 */
public class SubarraySumEqualsK {

	public static void main(String[] args) {
		int[] nums = { 1, 1, 1 };
		int k = 2;
		int result = new SubarraySumEqualsK().subarraySum(nums, k);
		System.out.println("result :" + result);
	}

	public int subarraySum(int[] nums, int k) {
		int sum = 0, result = 0;
		// 存放连续子数组的和
		Map<Integer, Integer> preSum = new HashMap<>();
		// 子数组个数为 0
		preSum.put(0, 1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			// 满足条件统计
			if (preSum.containsKey(sum - k)) {
				result += preSum.get(sum - k);
			}
			// 子数组之和相同也算不同子数组 统计子数组和的个数
			preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
		}
		return result;
	}
}
