package com.leetcode_cn.medium;

import java.util.Arrays;

/**********************寻找旋转排序数组中的最小值************/
/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * 
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 
 * 请找出其中最小的元素。
 * 
 * 你可以假设数组中不存在重复元素。
 * 
 * 示例 1:
 * 
 * 输入: [3,4,5,1,2] 输出: 1
 * 
 * 示例 2:
 * 
 * 输入: [4,5,6,7,0,1,2] 输出: 0
 * 
 * @author ffj
 *
 */
public class FindMinimumInRotatedSortedArray {

	public int findMin(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		Arrays.sort(nums);
		return nums[0];
	}

	/**
	 * 二分法检索
	 * 
	 * @param num
	 * @return
	 */
	public int findMin1(int[] num) {
		if (num == null || num.length == 0) {
			return 0;
		}
		if (num.length == 1) {
			return num[0];
		}
		int start = 0, end = num.length - 1;
		while (start < end) {
			int mid = (start + end) / 2;
			if (mid > 0 && num[mid] < num[mid - 1]) { // 说明是旋转的节点
				return num[mid];
			}
			if (num[start] <= num[mid] && num[mid] > num[end]) { // 说明最小值在后半段
				start = mid + 1;
			} else { // 最小值在前半段
				end = mid - 1;
			}
		}
		return num[start];
	}

}
