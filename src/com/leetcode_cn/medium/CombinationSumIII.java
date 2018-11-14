package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.List;

/*****************组合总和 III****************/
/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * 
 * 说明：
 * 
 * 所有数字都是正整数。
 * 
 * 解集不能包含重复的组合。
 * 
 * 示例 1:
 * 
 * 输入: k = 3, n = 7 输出: [[1,2,4]]
 * 
 * 示例 2:
 * 
 * 输入: k = 3, n = 9 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * 
 * @author ffj
 *
 */
public class CombinationSumIII {

	public List<List<Integer>> combinationSum3(int k, int n) {
		int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		List<List<Integer>> list = new ArrayList<>();
		dfs(list, new ArrayList<>(), nums, k, n, 0);
		return list;

	}

	/**
	 * 
	 * @param list
	 *            最终返回的 list
	 * @param tempList
	 *            作为寻求满足条件的暂存 list
	 * @param nums
	 *            数组
	 * @param reNum
	 *            剩余的元素个数
	 * @param reSum
	 *            剩余的总和
	 * @param index
	 *            数组下标
	 */
	private void dfs(List<List<Integer>> list, List<Integer> tempList, int[] nums, int reNum, int reSum, int index) {
		if (reNum < 0 || reSum < 0) // 两者有其一小于 0 就是不满足条件
			return;
		if (reNum == 0 && reSum == 0) // 两者都为 0 时满足条件，塞入
			list.add(new ArrayList<>(tempList));
		else if (reNum == 0 || reSum == 0) // 两者中有且只有一者等于 0 就不满足条件
			return;
		else {
			for (int i = index; i < nums.length; i++) {
				tempList.add(nums[i]);
				dfs(list, tempList, nums, reNum - 1, reSum - nums[i], i + 1); // 不能重复元素 所以 i + 1
				tempList.remove(tempList.size() - 1);
			}
		}
	}

}
