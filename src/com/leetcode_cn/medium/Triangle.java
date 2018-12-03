package com.leetcode_cn.medium;

import java.util.List;
import java.util.TreeSet;

/**********************三角形最小路径和*************/
/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 
 * 例如，给定三角形：
 * 
 * [ [2], [3,4], [6,5,7], [4,1,8,3] ]
 * 
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 
 * 说明：
 * 
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 * 
 * @author ffj
 *
 */
public class Triangle {

	int size = 0;
	TreeSet<Integer> ts = new TreeSet<>();
	List<List<Integer>> list;

	/**
	 * 超时
	 * 
	 * @param triangle
	 * @return
	 */
	public int minimumTotal(List<List<Integer>> triangle) {
		list = triangle;
		size = triangle.size();
		if (size == 0)
			return 0;

		helper(0, 0, 0);
		return ts.first();
	}

	/**
	 * 
	 * @param row
	 *            行数
	 * @param index
	 *            在该行中下标
	 * @param sum_length
	 *            路径之和
	 */
	private void helper(int row, int index, int sum_length) {
		if (row >= size) {
			ts.add(sum_length);
			return;
		}
		sum_length += list.get(row).get(index);
		helper(row + 1, index, sum_length);
		helper(row + 1, index + 1, sum_length);
	}

	/**
	 * DP
	 * 
	 * @param triangle
	 * @return
	 */
	public int minimumTotal1(List<List<Integer>> triangle) {

		// 从倒数第二行开始往上走
		for (int i = triangle.size() - 2; i >= 0; i--) {
			// 从每行的起始下标开始直到 i
			for (int j = 0; j <= i; j++) {
				// i 行 j 下标的值
				int self = triangle.get(i).get(j);
				// 将 i 行 j 下标的值赋为 ： i 行 j 下标的值 与 i+1 行 j 下标和 j+1 下标值之和的较小值
				triangle.get(i).set(j,
						Math.min(triangle.get(i + 1).get(j) + self, triangle.get(i + 1).get(j + 1) + self));
			}
		}
		// 层层往上 顶层值便是路径最小值
		return triangle.get(0).get(0);
	}

}
