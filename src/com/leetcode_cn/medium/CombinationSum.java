package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/********************组合总和****************/
/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 
 * candidates 中的数字可以无限制重复被选取。
 * 
 * 说明：
 * 
 * 所有数字（包括 target）都是正整数。
 * 
 * 解集不能包含重复的组合。
 * 
 * 示例 1:
 * 
 * 输入: candidates = [2,3,6,7], target = 7,
 * 
 * 所求解集为:
 * 
 * [ [7], [2,2,3] ]
 * 
 * 示例 2:
 * 
 * 输入: candidates = [2,3,5], target = 8,
 * 
 * 所求解集为:
 * 
 * [ [2,2,2,2], [2,3,3], [3,5] ]
 * 
 * @author ffj
 *
 */
public class CombinationSum {

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(candidates);
		recursive(list, new ArrayList<>(), candidates, target, 0);
		return list;
	}

	/**
	 * 
	 * @param list
	 *            总的输出 list
	 * 
	 * @param tempList
	 *            存放的 list
	 * @param nums
	 *            数组
	 * 
	 * @param remain
	 *            剩余值
	 * @param index
	 *            数组下标
	 */
	private void recursive(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int index) {

		if (remain < 0) // return 或者进行 add 操作后就开始执行弹出尾部元素 塞入下个元素
			return;
		else if (remain == 0)
			list.add(tempList);
		else {
			for (int i = index; i < nums.length; i++) {
				tempList.add(nums[i]); // 挨个塞入
				recursive(list, tempList, nums, remain - nums[i], i); // 由于元素可重复 所以是 i
				tempList.remove(tempList.size() - 1); // 挨个弹出
			}
		}
	}

}
