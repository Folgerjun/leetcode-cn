package com.leetcode_cn.easy;

/***********合并两个有序数组********/
/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * 
 * 说明:
 * 
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 
 * 示例:
 * 
 * 输入: nums1 = [1,2,3,0,0,0], m = 3
 * 
 * nums2 = [2,5,6], n = 3
 * 
 * 输出: [1,2,2,3,5,6]
 * 
 * @author ffj
 *
 */
public class MergeSortedArray {

	public static void main(String[] args) {
		int[] nums1 = { 1, 2, 3, 0, 0, 0 }, nums2 = { 2, 5, 6 };
		int m = 3, n = 3;
		new MergeSortedArray().merge(nums1, m, nums2, n);
	}

	/**
	 * 从后往前比较 尾数谁大放谁
	 * 
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int nums1index = m - 1, nums2index = n - 1, index = m + n - 1;
		while (nums1index >= 0 && nums2index >= 0) {
			if (nums1[nums1index] > nums2[nums2index])
				nums1[index--] = nums1[nums1index--];
			else
				nums1[index--] = nums2[nums2index--];
		}
		while (nums2index >= 0)// nums2 中还有元素剩余
			nums1[index--] = nums2[nums2index--];
	}
}
