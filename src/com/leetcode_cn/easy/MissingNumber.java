package com.leetcode_cn.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/********************缺失数字*****************/
/**
 * 
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 * 
 * 示例 1:
 * 
 * 输入: [3,0,1] 输出: 2
 * 
 * 示例 2:
 * 
 * 输入: [9,6,4,2,3,5,7,0,1] 输出: 8
 * 
 * 说明: 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
 * 
 * @author ffj
 *
 */
public class MissingNumber {

	public int missingNumber(int[] nums) {

		if (nums == null || nums.length <= 0)
			return 0;
		Arrays.sort(nums); // 排序
		int length = nums.length;
		int lo = 0, hi = length - 1;

		while (lo < hi) {
			if (nums[lo] == nums[lo + 1])
				lo++;
			if (nums[hi] == nums[hi - 1])
				hi--;
			if (nums[lo] + 1 != nums[lo + 1])
				return nums[lo] + 1;
			if (nums[hi] - 1 != nums[hi - 1])
				return nums[hi] - 1;
			lo++;
			hi--;
		}
		return nums[0] == 1 ? 0 : nums[length - 1] + 1; // 第一个数字是1那就缺失0
	}

	/**
	 * 官网解法一
	 * 
	 * @param nums
	 * @return
	 */
	public int missingNumber1(int[] nums) {
		Arrays.sort(nums);

		// Ensure that n is at the last index
		if (nums[nums.length - 1] != nums.length) {
			return nums.length;
		}
		// Ensure that 0 is at the first index
		else if (nums[0] != 0) {
			return 0;
		}

		// If we get here, then the missing number is on the range (0, n)
		for (int i = 1; i < nums.length; i++) {
			int expectedNum = nums[i - 1] + 1;
			if (nums[i] != expectedNum) {
				return expectedNum;
			}
		}

		// Array was not missing any numbers
		return -1;
	}

	/**
	 * 官网解法二
	 * 
	 * @param nums
	 * @return
	 */
	public int missingNumber2(int[] nums) {
		Set<Integer> numSet = new HashSet<Integer>(); // 塞入set中
		for (int num : nums)
			numSet.add(num);

		int expectedNumCount = nums.length + 1;
		for (int number = 0; number < expectedNumCount; number++) {
			if (!numSet.contains(number)) { // 匹配不到就说明没有
				return number;
			}
		}
		return -1;
	}

	/**
	 * 官网解法三
	 * 
	 * Index 0 1 2 3
	 * 
	 * Value 0 1 3 4
	 * 
	 * @param nums
	 * @return
	 */
	public int missingNumber3(int[] nums) {
		int missing = nums.length;
		for (int i = 0; i < nums.length; i++) {
			missing ^= i ^ nums[i];
		}
		/**
		 * missing = 4∧(0∧0)∧(1∧1)∧(2∧3)∧(3∧4)
		 * 
		 * = (4∧4)∧(0∧0)∧(1∧1)∧(3∧3)∧2
		 * 
		 * = 0∧0∧0∧0∧2
		 * 
		 * = 2
		 */
		return missing;
	}

	/**
	 * 官网解法四
	 * 
	 * 求累加和 ：n * (n + 1) / 2
	 * 
	 * @param nums
	 * @return
	 */
	public int missingNumber4(int[] nums) {
		int expectedSum = nums.length * (nums.length + 1) / 2;
		int actualSum = 0;
		for (int num : nums)
			actualSum += num;
		return expectedSum - actualSum;
	}
}
