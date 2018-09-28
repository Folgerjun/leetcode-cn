package com.leetcode_cn.medium;

/***************搜索旋转排序数组************/
/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * 
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 
 * 你可以假设数组中不存在重复的元素。
 * 
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 
 * 示例 1:
 * 
 * 输入: nums = [4,5,6,7,0,1,2], target = 0 输出: 4
 * 
 * 示例 2:
 * 
 * 输入: nums = [4,5,6,7,0,1,2], target = 3 输出: -1
 * 
 * @author ffj
 *
 */
public class SearchInRotatedSortedArray {

	public static void main(String[] args) {
		int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
		int target = 0;
		System.out.println(new SearchInRotatedSortedArray().search1(nums, target));
	}

	public int search(int[] nums, int target) {

		int index = -1;

		if (nums.length == 0)
			return index;

		if (target > nums[0]) {
			for (int i = 1; i < nums.length; i++) {
				if (nums[i - 1] > nums[i]) { // 没找到
					index = -1;
					break;
				}
				if (nums[i] == target) { // 找到了
					index = i;
					break;
				}
			}

		} else if (target < nums[0]) {
			for (int i = nums.length - 1; i > 0; i--) {
				if (nums[i] == target) { // 找到了
					index = i;
					break;
				}
				if (nums[i - 1] > nums[i]) { // 没找到
					index = -1;
					break;
				}
			}
		} else
			index = 0;

		return index;

	}

	/**
	 * 二分法
	 * 
	 * @param A
	 * @param target
	 * @return
	 */
	public int search1(int[] A, int target) {
		int lo = 0;
		int hi = A.length - 1;
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (A[mid] == target)
				return mid;

			if (A[lo] <= A[mid]) { // 如果在有序数据段中
				if (target >= A[lo] && target < A[mid]) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			} else {
				if (target > A[mid] && target <= A[hi]) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
		}
		return A[lo] == target ? lo : -1;
	}

}
