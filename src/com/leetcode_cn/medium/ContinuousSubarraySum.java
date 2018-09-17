package com.leetcode_cn.medium;

import java.util.HashMap;
import java.util.Map;

/********************连续的子数组和************/
/**
 * 给定一个包含非负数的数组和一个目标整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，总和为 k 的倍数，即总和为 n*k，其中 n
 * 也是一个整数。
 * 
 * 示例 1:
 * 
 * 输入: [23,2,4,6,7], k = 6 输出: True
 * 
 * 解释: [2,4] 是一个大小为 2 的子数组，并且和为 6。
 * 
 * 示例 2:
 * 
 * 输入: [23,2,6,4,7], k = 6 输出: True
 * 
 * 解释: [23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 * 
 * 说明:
 * 
 * 数组的长度不会超过10,000。
 * 
 * 你可以认为所有数字总和在 32 位有符号整数范围内。
 * 
 * @author ffj
 *
 */
public class ContinuousSubarraySum {

	/**
	 * 两个循环每次比值
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public boolean checkSubarraySum(int[] nums, int k) {
		if (nums.length < 2)
			return false;
		k = Math.abs(k);
		int len = nums.length;
		for (int i = 0; i < len - 1; i++) {
			int sum = nums[i] + nums[i + 1];
			if ((k == 0 && sum == 0) || (k != 0 && sum % k == 0))
				return true;
			for (int j = i + 2; j < len; j++) {
				sum += nums[j];
				if ((k == 0 && sum == 0) || (k != 0 && sum % k == 0))
					return true;
			}
		}
		return false;
	}

	/**
	 * 讨论中解法
	 * 
	 * 相同余数之间和必然是 k 的倍数
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public boolean checkSubarraySum1(int[] nums, int k) {
		// 存储余数对应的下标
		Map<Integer, Integer> map = new HashMap<Integer, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -5214992371704755341L;

			{
				put(0, -1);
			}
		};
		int runningSum = 0;
		for (int i = 0; i < nums.length; i++) {
			runningSum += nums[i];
			if (k != 0)
				runningSum %= k;
			Integer prev = map.get(runningSum);
			if (prev != null) {
				// 因为要求其大小至少为 2
				if (i - prev > 1)
					return true;
			} else
				map.put(runningSum, i);
		}
		return false;
	}

	public static void main(String[] args) {
		int[] nums = { 23, 2, 4, 6, 7 };
		int k = 6;
		System.out.println(new ContinuousSubarraySum().checkSubarraySum1(nums, k));
	}
}
