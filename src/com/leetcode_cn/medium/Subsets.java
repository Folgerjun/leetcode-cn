package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.List;

/*********子集*********/
/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 
 * 说明：解集不能包含重复的子集。
 * 
 * 示例:
 * 
 * 输入: nums = [1,2,3]
 * 
 * 输出: [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
 * 
 * @author ffj
 *
 */
public class Subsets {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		List<List<Integer>> subsets = new Subsets().subsets(nums);
		System.out.println(subsets);
	}

	/**
	 * 遍历一个元素添加到原先的组合中组成新组合
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsets1(int[] nums) {
		List<List<Integer>> ret = new ArrayList<List<Integer>>();// 最后输出的结果
		int n = nums.length;
		if (n == 0)
			return ret;
		ret.add(new ArrayList<Integer>());// 初始化
		for (int i : nums) {
			List<Integer> tmp;// 放第二层的list
			List<List<Integer>> ttmp = new ArrayList<>();// ，第一层list，放之前的list加上新的之后的list
			for (List<Integer> j : ret) {
				tmp = new ArrayList<>(j);
				tmp.add(i);
				ttmp.add(tmp);
			}
			// 拼合
			ret.addAll(ttmp);
		}
		return ret;
	}

	/**
	 * 回溯法
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> subs = new ArrayList<>();
		int len = nums.length;
		for (int i = 0; i <= len; i++)
			subsets(subs, new ArrayList<>(), 0, nums, i);
		return subs;
	}

	private static void subsets(List<List<Integer>> subs, List<Integer> sub, int start, int[] nums, int n) {
		if (n == 0) {
			subs.add(new ArrayList<>(sub));
			return;
		}
		for (int i = start; i < nums.length; i++) {
			sub.add(nums[i]);
			subsets(subs, sub, i + 1, nums, n - 1);
			sub.remove(sub.size() - 1);
		}
	}

}
