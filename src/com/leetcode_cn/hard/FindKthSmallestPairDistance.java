package com.leetcode_cn.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*************************找出第 k 小的距离对*****************/
/**
 * 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 * 
 * 示例 1:
 * 
 * 输入： nums = [1,3,1] k = 1
 * 
 * 输出：0 解释： 所有数对如下：
 * 
 * (1,3) -> 2
 * 
 * (1,1) -> 0
 * 
 * (3,1) -> 2
 * 
 * 因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。
 * 
 * 提示:
 * 
 * 2 <= len(nums) <= 10000.
 * 
 * 0 <= nums[i] < 1000000.
 * 
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 * 
 * @author ffj
 *
 */
public class FindKthSmallestPairDistance {
	public static void main(String[] args) {

		int[] nums = { 1, 1, 1 };
		int k = 2;
		int result = smallestDistancePair1(nums, k);
		System.out.println(result);
	}

	public static int smallestDistancePair(int[] nums, int k) {
		if (nums.length < 2)
			return 0;
		int length = nums.length;
		List<Integer> numList = new ArrayList<>();
		for (int i = 0; i < length - 1; i++) {
			for (int j = i + 1; j < length; j++) {
				int val = Math.abs(nums[i] - nums[j]);
				// if (!numList.contains(val)) {
				numList.add(val);
				// }

			}
		}
		Collections.sort(numList);
		return numList.get(k - 1);

	}

	/**
	 * 利用数组来操作
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int smallestDistancePair2(int[] nums, int k) {
		if (nums.length < 2)
			return 0;
		int length = nums.length;
		int[] distanceArr = new int[1000000]; // 存储距离
		for (int i = 0; i < length - 1; i++) {
			for (int j = i + 1; j < length; j++) {
				int val = Math.abs(nums[i] - nums[j]);
				distanceArr[val] = distanceArr[val] == 0 ? 1 : distanceArr[val] + 1;
			}
		}
		for (int i = 0; i < distanceArr.length; i++) {
			if (distanceArr[i] >= k)
				return i;
			k -= distanceArr[i];
		}
		return -1;
	}

	/**
	 * 官网解法 二分搜索
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int smallestDistancePair1(int[] nums, int k) {
		Arrays.sort(nums);
		int WIDTH = 2 * nums[nums.length - 1];

		// multiplicity[i] = number of nums[j] == nums[i] (j < i)
		int[] multiplicity = new int[nums.length];
		for (int i = 1; i < nums.length; ++i) {
			if (nums[i] == nums[i - 1]) {
				multiplicity[i] = 1 + multiplicity[i - 1];
			}
		}

		// prefix[v] = number of values <= v
		int[] prefix = new int[WIDTH];
		int left = 0;
		for (int i = 0; i < WIDTH; ++i) {
			while (left < nums.length && nums[left] == i)
				left++;
			prefix[i] = left;
		}

		int lo = 0;
		int hi = nums[nums.length - 1] - nums[0];
		while (lo < hi) {
			int mi = (lo + hi) / 2;
			int count = 0;
			for (int i = 0; i < nums.length; ++i) {
				count += prefix[nums[i] + mi] - prefix[nums[i]] + multiplicity[i];
			}
			// count = number of pairs with distance <= mi
			if (count >= k)
				hi = mi;
			else
				lo = mi + 1;
		}
		return lo;
	}

	/**
	 * 官网解法 二分搜索
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int smallestDistancePair3(int[] nums, int k) {
		Arrays.sort(nums); // 先对数组进行排序

		int lo = 0; // 初始值
		int hi = nums[nums.length - 1] - nums[0]; // 最大距离
		while (lo < hi) {
			int mi = (lo + hi) / 2; // 中间值
			int count = 0, left = 0;
			for (int right = 0; right < nums.length; ++right) {
				while (nums[right] - nums[left] > mi)
					left++;
				count += right - left;
			}
			// count = number of pairs with distance <= mi
			if (count >= k)
				hi = mi;
			else
				lo = mi + 1;
		}
		return lo;
	}

}
