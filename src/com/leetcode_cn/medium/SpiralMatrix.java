package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.List;

/**************螺旋矩阵************/
/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 
 * 示例 1:
 * 
 * 输入: [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ]
 * 
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 
 * 示例 2:
 * 
 * 输入: [ [1, 2, 3, 4], [5, 6, 7, 8], [9,10,11,12] ]
 * 
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 * @author ffj
 *
 */
public class SpiralMatrix {

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		System.out.println(new SpiralMatrix().spiralOrder(matrix));
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> list = new ArrayList<>();
		if (matrix == null || matrix.length == 0)
			return list;
		int rows = matrix.length, cols = matrix[0].length;
		int up = 0, down = rows - 1, left = 0, right = cols - 1;

		while (up <= down || right >= left) {
			if (left > right) // 判断是否越界
				break;
			// left -> right
			for (int i = left; i <= right; i++)
				list.add(matrix[up][i]);
			right--;
			if ((up + 1) > down)
				break;
			// up -> down
			for (int i = up + 1; i <= down; i++)
				list.add(matrix[i][right + 1]);
			down--;
			if (left > right)
				break;
			// right -> left
			for (int i = right; i >= left; i--)
				list.add(matrix[down + 1][i]);
			left++;
			if ((up + 1) > down)
				break;
			// down -> up
			for (int i = down; i >= up + 1; i--)
				list.add(matrix[i][left - 1]);
			up++;
		}

		return list;
	}

}
