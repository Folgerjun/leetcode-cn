package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**************全排列 II***********/
/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,1,2]
 * 
 * 输出: [ [1,1,2], [1,2,1], [2,1,1] ]
 * 
 * @author ffj
 *
 */
public class PermutationsII {

	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> permuteUniques = new ArrayList<>();
		if (nums == null || nums.length == 0)
			return permuteUniques;
		// 要去重 先排序
		Arrays.sort(nums);
		boolean[] used = new boolean[nums.length];
		dfs(nums, used, permuteUniques, new ArrayList<>());
		return permuteUniques;
	}

	/**
	 * 
	 * @param nums
	 *            原数组
	 * @param used
	 *            标记数字使用状态
	 * @param permuteUniques
	 *            目标集合
	 * @param permuteUnique
	 *            单个集合
	 */
	private void dfs(int[] nums, boolean[] used, List<List<Integer>> permuteUniques, List<Integer> permuteUnique) {
		if (permuteUnique.size() == nums.length) {
			permuteUniques.add(new ArrayList<>(permuteUnique));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (used[i]) // 已经标记过的略过
				continue;
			if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) // 数字有重复的 前几个先略过
				continue;
			used[i] = true; // 标记使用
			permuteUnique.add(nums[i]); // 该数字加入集合
			// 重复操作 选择剩余数字
			dfs(nums, used, permuteUniques, permuteUnique);
			// 当 出栈时将最后一个数从集合中删除 同时该数恢复未使用状态 继续操作
			used[i] = false;
			permuteUnique.remove(permuteUnique.size() - 1);
		}

	}

}
