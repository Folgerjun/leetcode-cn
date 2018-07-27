package com.leetcode_cn.hard;

/****************两个排序数组的中位数******************/
/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
 * 
 * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 * 
 * 示例 1:
 * 
 * nums1 = [1, 3] nums2 = [2]
 * 
 * 中位数是 2.0
 * 
 * 示例 2:
 * 
 * nums1 = [1, 2] nums2 = [3, 4]
 * 
 * 中位数是 (2 + 3)/2 = 2.5
 * 
 * @author ffj
 *
 */
public class MedianOfTwoSortedArrays {

	public static void main(String[] args) {
		int[] A = { 1, 2 };
		int[] B = { 3, 4 };
		double result = findMedianSortedArrays(A, B);
		System.out.println(result);
	}

	public static double findMedianSortedArrays(int[] A, int[] B) {
		int m = A.length;
		int n = B.length;
		if (m > n) { // to ensure m<=n 使得A数组长度小于等于B数组长度
			int[] temp = A;
			A = B;
			B = temp;
			int tmp = m;
			m = n;
			n = tmp;
		}
		int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2; // 取整 总数的一半
		while (iMin <= iMax) {
			int i = (iMin + iMax) / 2;
			int j = halfLen - i;
			// 确定 i j 的值，将A B都分割成两部分
			if (i < iMax && B[j - 1] > A[i]) {
				iMin = iMin + 1; // i is too small
			} else if (i > iMin && A[i - 1] > B[j]) {
				iMax = iMax - 1; // i is too big
			} else { // i is perfect
				int maxLeft = 0;
				if (i == 0) {
					maxLeft = B[j - 1];
				} else if (j == 0) {
					maxLeft = A[i - 1];
				} else {
					maxLeft = Math.max(A[i - 1], B[j - 1]);
				}
				if ((m + n) % 2 == 1) { // 个数是奇数的话 中位数就是中间这个数
					return maxLeft;
				}

				int minRight = 0;
				if (i == m) {
					minRight = B[j];
				} else if (j == n) {
					minRight = A[i];
				} else {
					minRight = Math.min(B[j], A[i]);
				}

				return (maxLeft + minRight) / 2.0;
			}
		}
		return 0.0;
	}

}
