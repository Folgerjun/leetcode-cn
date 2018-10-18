package com.leetcode_cn.easy;

import java.util.HashSet;
import java.util.Set;

/*************数组中的K-diff数对************/
/**
 * 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和
 * j 都是数组中的数字，且两数之差的绝对值是 k.
 * 
 * 示例 1:
 * 
 * 输入: [3, 1, 4, 1, 5], k = 2 输出: 2
 * 
 * 解释: 数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。 尽管数组中有两个1，但我们只应返回不同的数对的数量。
 * 
 * 示例 2:
 * 
 * 输入:[1, 2, 3, 4, 5], k = 1 输出: 4
 * 
 * 解释: 数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
 * 
 * 示例 3:
 * 
 * 输入: [1, 3, 1, 5, 4], k = 0 输出: 1
 * 
 * 解释: 数组中只有一个 0-diff 数对，(1, 1)。
 * 
 * 注意:
 * 
 * 数对 (i, j) 和数对 (j, i) 被算作同一数对。
 * 
 * 数组的长度不超过10,000。
 * 
 * 所有输入的整数的范围在 [-1e7, 1e7]。
 * 
 * @author ffj
 *
 */
public class KDiffPairsInAnArray {

	public int findPairs(int[] nums, int k) {
		int size = 0;
		if (nums.length <= 1 || k < 0)
			return size;
		// 已经记录过的值
		Set<Integer> visited = new HashSet<>();
		int length = nums.length;
		for (int i = 0; i < length - 1; i++) {
			for (int j = i + 1; j < length; j++) {
				int abs = Math.abs(nums[i] - nums[j]);
				if (abs == k) {
					// 找到一个数对判断是否已经记录
					if (visited.add(nums[i] + nums[j])) {
						// 未记录
						System.out.println("i :" + i + " ,j :" + j);
						size++;
					}
				}
			}
		}
		return size;
	}

	/**
	 * 讨论中解法
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findPairs1(int[] nums, int k) {
		if (k < 0)
			return 0;
		// 定义两个 set
		Set<Integer> starters = new HashSet<Integer>();
		Set<Integer> uniqs = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			// starters 中之存储数对中较小的值 以此防止了重复放入数对
			if (uniqs.contains(nums[i] - k))
				starters.add(nums[i] - k);
			if (uniqs.contains(nums[i] + k))
				starters.add(nums[i]);
			uniqs.add(nums[i]);
		}

		return starters.size();
	}

	public static void main(String[] args) {
		int[] nums = { 3, 1, 4, 1, 5 };
		int k = 2;
		System.out.println(new KDiffPairsInAnArray().findPairs(nums, k));
	}

}
