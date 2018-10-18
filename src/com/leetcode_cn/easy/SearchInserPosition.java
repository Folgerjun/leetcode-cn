package com.leetcode_cn.easy;

/*************搜索插入位置***************/
/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 
 * 你可以假设数组中无重复元素。
 * 
 * 示例 1:
 * 
 * 输入: [1,3,5,6], 5 输出: 2
 * 
 * 示例 2:
 * 
 * 输入: [1,3,5,6], 2 输出: 1
 * 
 * 示例 3:
 * 
 * 输入: [1,3,5,6], 7 输出: 4
 * 
 * 示例 4:
 * 
 * 输入: [1,3,5,6], 0 输出: 0
 * 
 * @author ffj
 *
 */
public class SearchInserPosition {

	/**
	 * 二分搜索
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int searchInsert(int[] nums, int target) {
		if (nums.length == 0)
			return 0;
		int length = nums.length;
		int mid = 0, lo = 0, hi = length - 1;
		while (lo < hi) {
			mid = (lo + hi) / 2;
			if (nums[mid] > target)
				hi = mid - 1;
			else if (nums[mid] < target)
				lo = mid + 1;
			else
				return mid;
		}
		System.out.println("hi :" + hi + " lo :" + lo);
		return target <= nums[lo] ? lo : lo + 1;
	}

	public static void main(String[] args) {
		int[] nums = { 1 };
		int target = 1;
		System.out.println(new SearchInserPosition().searchInsert(nums, target));
	}

}
