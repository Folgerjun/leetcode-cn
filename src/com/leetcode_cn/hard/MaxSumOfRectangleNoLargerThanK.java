package com.leetcode_cn.hard;

import java.util.TreeSet;

/*****************矩形区域不超过 K 的最大数值和**********/
/**
 * 给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。
 * 
 * 示例:
 * 
 * 输入: matrix = [[1,0,1],[0,-2,3]], k = 2 输出: 2
 * 
 * 解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * 
 * 说明：
 * 
 * 矩阵内的矩形区域面积必须大于 0。
 * 
 * 如果行数远大于列数，你将如何解答呢？
 * 
 * @author ffj
 *
 */
public class MaxSumOfRectangleNoLargerThanK {

	/*
	 * first consider the situation matrix is 1D we can save every sum of
	 * 0~i(0<=i<len) and binary search previous sum to find possible result for
	 * every index, time complexity is O(NlogN). so in 2D matrix, we can sum up all
	 * values from row i to row j and create a 1D array to use 1D array solution. If
	 * col number is less than row number, we can sum up all values from col i to
	 * col j then use 1D array solution.
	 */
	/**
	 * 讨论区中解法一
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
	public int maxSumSubmatrix(int[][] matrix, int target) {
		int row = matrix.length;
		if (row == 0)
			return 0;
		int col = matrix[0].length;
		int m = Math.min(row, col);
		int n = Math.max(row, col);
		// indicating sum up in every row or every column
		boolean colIsBig = col > row;
		int res = Integer.MIN_VALUE;
		for (int i = 0; i < m; i++) {
			int[] array = new int[n];
			// sum from row j to row i
			for (int j = i; j >= 0; j--) {
				int val = 0;
				TreeSet<Integer> set = new TreeSet<Integer>();
				set.add(0);
				// traverse every column/row and sum up
				for (int k = 0; k < n; k++) {
					array[k] = array[k] + (colIsBig ? matrix[j][k] : matrix[k][j]);
					val = val + array[k];
					// use TreeMap to binary search previous sum to get possible result
					// 返回最小的元素在这一组大于或等于给定的元素，如果没有则返回 null
					Integer subres = set.ceiling(val - target);
					if (null != subres) {
						res = Math.max(res, val - subres);
					}
					set.add(val);
				}
			}
		}
		return res;
	}

	/**
	 * 讨论区中解法二
	 * 
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int maxSumSubmatrix1(int[][] matrix, int k) {
		int m = matrix.length, n = matrix[0].length, ans = Integer.MIN_VALUE;
		long[] sum = new long[m + 1]; // stores sum of rect[0..p][i..j]
		for (int i = 0; i < n; ++i) {
			long[] sumInRow = new long[m];
			for (int j = i; j < n; ++j) { // for each rect[*][i..j]
				for (int p = 0; p < m; ++p) {
					sumInRow[p] += matrix[p][j];
					sum[p + 1] = sum[p] + sumInRow[p];
				}
				ans = Math.max(ans, mergeSort(sum, 0, m + 1, k));
				if (ans == k)
					return k;
			}
		}
		return ans;
	}

	int mergeSort(long[] sum, int start, int end, int k) {
		if (end == start + 1)
			return Integer.MIN_VALUE; // need at least 2 to proceed
		int mid = start + (end - start) / 2, cnt = 0;
		int ans = mergeSort(sum, start, mid, k);
		if (ans == k)
			return k;
		ans = Math.max(ans, mergeSort(sum, mid, end, k));
		if (ans == k)
			return k;
		long[] cache = new long[end - start];
		for (int i = start, j = mid, p = mid; i < mid; ++i) {
			while (j < end && sum[j] - sum[i] <= k)
				++j;
			if (j - 1 >= mid) {
				ans = Math.max(ans, (int) (sum[j - 1] - sum[i]));
				if (ans == k)
					return k;
			}
			while (p < end && sum[p] < sum[i])
				cache[cnt++] = sum[p++];
			cache[cnt++] = sum[i];
		}
		System.arraycopy(cache, 0, sum, start, cnt);
		return ans;
	}

}
