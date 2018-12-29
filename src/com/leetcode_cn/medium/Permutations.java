package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.List;

/************全排列***********/
/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,2,3]
 * 
 * 输出: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1] ]
 * 
 * @author ffj
 *
 */
public class Permutations {

	/**
	 * nums = 1,2,3
	 * 
	 * start = 0, permutation = []
	 * 
	 * i = 0, newPermutation = [1]
	 * 
	 * --start = 1, permutation = [1]
	 * 
	 * --i = 0, newPermutation = [2, 1]
	 * 
	 * ----start = 2, permutation = [2, 1]
	 * 
	 * ----i = 0, newPermutation = [3, 2, 1]
	 * 
	 * ----i = 1, newPermutation = [2, 3, 1]
	 * 
	 * ----i = 2, newPermutation = [2, 1, 3]
	 * 
	 * --i = 1, newPermutation = [1, 2]
	 * 
	 * ----start = 2, permutation = [1, 2]
	 * 
	 * ----i = 0, newPermutation = [3, 1, 2]
	 * 
	 * ----i = 1, newPermutation = [1, 3, 2]
	 * 
	 * ----i = 2, newPermutation = [1, 2, 3]
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permute(int[] nums) {

		List<List<Integer>> permutations = new ArrayList<>();
		if (nums.length == 0)
			return permutations;
		dfs(nums, 0, new ArrayList<>(), permutations);
		return permutations;

	}

	/**
	 * 
	 * @param nums
	 *            原数组
	 * @param start
	 *            选择填充数字下标
	 * @param permutation
	 *            单个集合
	 * @param permutations
	 *            目标返回集合
	 */
	private void dfs(int[] nums, int start, List<Integer> permutation, List<List<Integer>> permutations) {

		// 满足条件添加 返回
		if (permutation.size() == nums.length) {
			permutations.add(permutation);
			return;
		}
		// 分别插入不同的位置
		for (int i = 0; i <= permutation.size(); i++) {
			// 避免在原集合上操作 需新集合
			List<Integer> newPermutation = new ArrayList<>(permutation);
			newPermutation.add(i, nums[start]);
			dfs(nums, start + 1, newPermutation, permutations);
		}
	}

}
