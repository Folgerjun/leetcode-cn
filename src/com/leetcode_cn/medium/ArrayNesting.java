package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.List;

/******************数组嵌套*******************/
/**
 * 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到并返回最大的集合S，S[i] = {A[i], A[A[i]], A[A[A[i]]],
 * ... }且遵守以下的规则。
 * 
 * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]...
 * 以此类推，不断添加直到S出现重复的元素。
 * 
 * 示例 1:
 * 
 * 输入: A = [5,4,0,3,1,6,2] 输出: 4
 * 
 * 解释: A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 * 
 * 其中一种最长的 S[K]: S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 * 
 * 注意:
 * 
 * N是[1, 20,000]之间的整数。 A中不含有重复的元素。 A中的元素大小在[0, N-1]之间。
 * 
 * @author ffj
 *
 */
public class ArrayNesting {

	public static void main(String[] args) {
		int[] nums = new int[] { 5, 4, 0, 3, 1, 6, 2 };
		System.out.println(new ArrayNesting().arrayNesting(nums));
	}

	int[] nums;

	/**
	 * 浪费时间和空间
	 * 
	 * @param nums
	 * @return
	 */
	public int arrayNesting(int[] nums) {
		this.nums = nums;
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			List<Integer> list = new ArrayList<>();
			helper(i, list);
			max = Math.max(max, list.size());
		}
		return max;
	}

	private void helper(int num, List<Integer> list) {
		int n = nums[num];
		if (list.contains(n))
			return;
		else
			list.add(n);
		helper(n, list);
	}

	/**
	 * 官网解法一
	 * 
	 * @param nums
	 * @return
	 */
	public int arrayNesting1(int[] nums) {
		boolean[] visited = new boolean[nums.length];
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			if (!visited[i]) { // 若是已经遍历过的则无需再循环 因为定不是最长
				int start = nums[i], count = 0;
				do {
					start = nums[start];
					count++;
					visited[start] = true; // 已经遍历过的赋为true
				} while (start != nums[i]);
				res = Math.max(res, count);
			}
		}
		return res;
	}

	/**
	 * 官网解法二 用max替代boolean
	 * 
	 * @param nums
	 * @return
	 */
	public int arrayNesting2(int[] nums) {
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != Integer.MAX_VALUE) {
				int start = nums[i], count = 0;
				while (nums[start] != Integer.MAX_VALUE) {
					int temp = start;
					start = nums[start];
					count++;
					nums[temp] = Integer.MAX_VALUE;
				}
				res = Math.max(res, count);
			}
		}
		return res;
	}
}
