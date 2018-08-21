package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*******************四数之和**************/
/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c
 * + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 
 * 注意：
 * 
 * 答案中不可以包含重复的四元组。
 * 
 * 示例：
 * 
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 
 * 满足要求的四元组集合为：
 * 
 * [ [-1, 0, 0, 1],
 * 
 * [-2, -1, 1, 2],
 * 
 * [-2, 0, 0, 2] ]
 * 
 * @author ffj
 *
 */
public class Sum4 {
	int len = 0;

	public List<List<Integer>> fourSum(int[] nums, int target) {
		len = nums.length;
		Arrays.sort(nums); // 先排序
		return kSum(nums, target, 4, 0); // 递归调用

	}

	/**
	 * 
	 * @param nums
	 *            排序后目标数组
	 * @param target
	 *            累加目标数值
	 * @param k
	 *            个数
	 * @param index
	 *            起始下标
	 * @return
	 */
	private ArrayList<List<Integer>> kSum(int[] nums, int target, int k, int index) {

		ArrayList<List<Integer>> res = new ArrayList<>();

		if (index >= len)
			return res;
		if (k == 2) { // 两数取和
			int i = index, j = len - 1;
			while (i < j) {
				if (target - nums[i] == nums[j]) {
					List<Integer> temp = new ArrayList<>();
					temp.add(nums[i]);
					temp.add(nums[j]);
					res.add(temp);
					while (i < j && nums[i] == nums[i + 1]) // 跳过重复数值
						i++;
					while (i < j && nums[j] == nums[j - 1]) // 跳过重复数值
						j--;
					i++;
					j--;
				} else if (target - nums[i] > nums[j])
					i++;
				else
					j--;
			}
		} else {
			for (int i = index; i < len - k + 1; i++) {
				// 调用递归 DFS
				ArrayList<List<Integer>> temp = kSum(nums, target - nums[i], k - 1, i + 1);
				if (temp != null && temp.size() > 0) {
					for (List<Integer> list : temp) {
						list.add(nums[i]); // 将满足条件数值塞入
					}
					res.addAll(temp);
				}
				while (i < len - 1 && nums[i] == nums[i + 1]) // 跳过重复数值
					i++;
			}
		}
		return res;
	}
}
