package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*********************组合总和 II*********************/
/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 
 * 说明：
 * 
 * 所有数字（包括目标数）都是正整数。
 * 
 * 解集不能包含重复的组合。
 * 
 * 示例 1:
 * 
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 
 * 所求解集为:
 * 
 * [ [1, 7], [1, 2, 5], [2, 6], [1, 1, 6] ]
 * 
 * 示例 2:
 * 
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 
 * 所求解集为:
 * 
 * [ [1,2,2], [5] ]
 * 
 * @author ffj
 *
 */
public class CombinationSumII {

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(candidates);
		recursive(list, new ArrayList<>(), candidates, target, 0);
		return list;

	}

	/**
	 * DFS 添加每个满足条件的集合
	 * 
	 * @param list
	 *            最终返回集合
	 * @param tempList
	 *            每个满足条件的子集合
	 * @param candidates
	 *            数组
	 * @param remain
	 *            剩余值
	 * @param index
	 *            数组下标
	 */
	private void recursive(List<List<Integer>> list, List<Integer> tempList, int[] candidates, int remain, int index) {
		if (remain < 0)
			return;
		else if (remain == 0)
			list.add(new ArrayList<>(tempList));
		else {
			for (int i = index; i < candidates.length; i++) {
				if (i > index && candidates[i] == candidates[i - 1]) // 说明两个值相等且之前一个值已经返回
					continue;
				tempList.add(candidates[i]);
				recursive(list, tempList, candidates, remain - candidates[i], i + 1); // 规定数组中每个数字在每个组合中只能使用一次
				tempList.remove(tempList.size() - 1);
			}
		}
	}

}
