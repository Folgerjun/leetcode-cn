package com.leetcode_cn.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*************有序矩阵中第K小的元素**********/
/**
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。 请注意，它是排序后的第k小元素，而不是第k个元素。
 * 
 * 示例:
 * 
 * matrix = [ [ 1, 5, 9], [10, 11, 13], [12, 13, 15] ],
 * 
 * k = 8,
 * 
 * 返回 13。 说明: 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。
 * 
 * 
 * @author ffj
 *
 */
public class KthSmallestElementInASortedMatrix {
	public static void main(String[] args) {

	}

	/**
	 * 数组排序
	 * 
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int kthSmallest(int[][] matrix, int k) {
		int len = matrix.length;
		int[] num = new int[len * len];
		for (int i = 0; i < len; i++)
			for (int j = 0; j < len; j++)
				num[i * len + j] = matrix[i][j];
		// 数组升序排序
		Arrays.sort(num);
		return num[k - 1];
	}

	/**
	 * 堆排序
	 * 
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int kthSmallest1(int[][] matrix, int k) {
		// 大顶堆
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		for (int i = 0; i < matrix.length; i++) {
			// 比最大的还要大 忽略
			if (maxHeap.size() == k && matrix[i][0] > maxHeap.peek()) {
				break;
			}
			for (int j = 0; j < matrix[0].length; j++) {
				if (maxHeap.size() == k && matrix[i][j] > maxHeap.peek()) {
					break;
				}
				maxHeap.offer(matrix[i][j]);
				if (maxHeap.size() > k) { // 只管前 k 个数
					maxHeap.poll();
				}
			}
		}
		return maxHeap.peek();
	}
}
