package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/****************三数之和****************/
/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0
 * ？找出所有满足条件且不重复的三元组。
 * 
 * 注意：答案中不可以包含重复的三元组。
 * 
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 
 * 满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2] ]
 * 
 * @author ffj
 *
 */
public class Sum3 {

	public List<List<Integer>> threeSum(int[] nums) {

		Arrays.sort(nums); // 排序
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int i = 0; i < nums.length - 2; i++) {
			if ((i == 0) || (i > 0 && nums[i] != nums[i - 1])) {
				int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
				while (lo < hi) {
					int n = nums[lo] + nums[hi];
					if (n == sum) {
						result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
						while (lo < hi && nums[lo] == nums[lo + 1]) // 跳过相同值的数
							lo++;
						while (lo < hi && nums[hi] == nums[hi - 1])
							hi--;
						lo++;
						hi--;
					} else if (n < sum)
						lo++;
					else
						hi--;
				}
			}
		}

		return result;

	}

}
