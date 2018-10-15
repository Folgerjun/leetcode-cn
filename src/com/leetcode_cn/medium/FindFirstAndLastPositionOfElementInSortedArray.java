package com.leetcode_cn.medium;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 
 * 示例 1:
 * 
 * 输入: nums = [5,7,7,8,8,10], target = 8 输出: [3,4]
 * 
 * 示例 2:
 * 
 * 输入: nums = [5,7,7,8,8,10], target = 6 输出: [-1,-1]
 * 
 * @author ffj
 *
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

	public int[] searchRange(int[] nums, int target) {
		int[] result = { -1, -1 };
		if (nums.length == 0)
			return result;
		int length = nums.length;
		int lo = -1, hi = length;
		// 找到对应 target 出现的第一个下标
		while (lo + 1 != hi) {
			int mid = (lo + hi) / 2;
			if (nums[mid] < target)
				lo = mid;
			else
				hi = mid;
		}
		// 找不到直接返回 {-1, -1}
		if (hi >= length || nums[hi] != target)
			return result;
		// 找到则继续找
		if (nums[hi] == target) {
			result[0] = result[1] = hi;
			for (int i = hi + 1; i < length; i++) {
				if (nums[i] == target)
					result[1] = i;
			}
		}
		return result;
	}

	/**
	 * 官网解法一 ： 向前遍历 + 往后遍历
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] searchRange1(int[] nums, int target) {
		int[] targetRange = { -1, -1 };

		// find the index of the leftmost appearance of `target`.
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target) {
				targetRange[0] = i;
				break;
			}
		}

		// if the last loop did not find any index, then there is no valid range
		// and we return [-1, -1].
		if (targetRange[0] == -1) { // 说明不存在该数
			return targetRange;
		}

		// find the index of the rightmost appearance of `target` (by reverse
		// iteration). it is guaranteed to appear.
		for (int j = nums.length - 1; j >= 0; j--) {
			if (nums[j] == target) {
				targetRange[1] = j;
				break;
			}
		}

		return targetRange;
	}

	// returns leftmost (or rightmost) index at which `target` should be
	// inserted in sorted array `nums` via binary search.
	private int extremeInsertionIndex(int[] nums, int target, boolean left) {
		int lo = 0;
		int hi = nums.length;

		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (nums[mid] > target || (left && target == nums[mid])) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}

		return lo;
	}

	/**
	 * 官网解法二 ：二分法
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] searchRange2(int[] nums, int target) {
		int[] targetRange = { -1, -1 };
		// 找到与之相等的最左边的下标
		int leftIdx = extremeInsertionIndex(nums, target, true);

		// assert that `leftIdx` is within the array bounds and that `target`
		// is actually in `nums`.
		if (leftIdx == nums.length || nums[leftIdx] != target) {
			return targetRange;
		}

		targetRange[0] = leftIdx;
		// 与之相等的最右边下标
		targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;

		return targetRange;
	}

	public static void main(String[] args) {
		int[] nums = { 2, 2, 4, 4 };
		int target = 3;
		System.out.println("before : " + LocalDateTime.now().getNano());
		int[] result = new FindFirstAndLastPositionOfElementInSortedArray().searchRange(nums, target);
		System.out.println(Arrays.toString(result));
		System.out.println("after : " + LocalDateTime.now().getNano());
	}

}
