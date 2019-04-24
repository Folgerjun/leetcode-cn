package com.leetcode_cn.medium;

import java.util.HashSet;
import java.util.Set;

/********矩阵置零*********/
/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 * 
 * 示例 1:
 * 
 * 输入: [ [1,1,1], [1,0,1], [1,1,1] ]
 * 
 * 输出: [ [1,0,1], [0,0,0], [1,0,1] ]
 * 
 * 示例 2:
 * 
 * 输入: [ [0,1,2,0], [3,4,5,2], [1,3,1,5] ]
 * 
 * 输出: [ [0,0,0,0], [0,4,5,0], [0,3,1,0] ]
 * 
 * 进阶:
 * 
 * 一个直接的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 
 * 你能想出一个常数空间的解决方案吗？
 * 
 * @author ffj
 *
 */
public class SetMatrixZeroes {

	/**
	 * 官网解法 https://leetcode.com/problems/set-matrix-zeroes/solution/
	 * 
	 * @param matrix
	 */
	public void setZeroes2(int[][] matrix) {
		Boolean isCol = false;
		int R = matrix.length;
		int C = matrix[0].length;

		for (int i = 0; i < R; i++) {

			// Since first cell for both first row and first column is the same i.e.
			// matrix[0][0]
			// We can use an additional variable for either the first row/column.
			// For this solution we are using an additional variable for the first column
			// and using matrix[0][0] for the first row.
			if (matrix[i][0] == 0) { // 第一列中有 0
				isCol = true;
			}

			for (int j = 1; j < C; j++) {
				// If an element is zero, we set the first element of the corresponding row and
				// column to 0
				if (matrix[i][j] == 0) {
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
		}

		// Iterate over the array once again and using the first row and first column,
		// update the elements.
		for (int i = 1; i < R; i++) {
			for (int j = 1; j < C; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}

		// See if the first row needs to be set to zero as well
		if (matrix[0][0] == 0) {
			for (int j = 0; j < C; j++) {
				matrix[0][j] = 0;
			}
		}

		// See if the first column needs to be set to zero as well
		if (isCol) {
			for (int i = 0; i < R; i++) {
				matrix[i][0] = 0;
			}
		}

	}

	/**
	 * 用 set 代替数组
	 * 
	 * @param matrix
	 */
	public void setZeroes1(int[][] matrix) {
		if (matrix.length == 0)
			return;
		int rows = matrix.length, cols = matrix[0].length;
		Set<Integer> zeroRows = new HashSet<>();
		Set<Integer> zeroCols = new HashSet<>();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (matrix[row][col] == 0) {
					zeroRows.add(row);
					zeroCols.add(col);
				}
			}
		}
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (zeroRows.contains(row) || zeroCols.contains(col)) {
					matrix[row][col] = 0;
				}
			}
		}
	}

	/**
	 * 多次循环 复杂度太高
	 * 
	 * @param matrix
	 */
	public void setZeroes(int[][] matrix) {
		if (matrix.length == 0)
			return;
		int rows = matrix.length, cols = matrix[0].length;
		// 记录 0 的行与列
		int[] zeroRows = new int[rows];
		int[] zeroCols = new int[cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (matrix[row][col] == 0) {
					zeroRows[row] = 1;
					zeroCols[col] = 1;
				}
			}
		}
		for (int row = 0; row < rows; row++) {
			if (zeroRows[row] == 1) {
				// 整行置 0
				for (int col = 0; col < cols; col++)
					matrix[row][col] = 0;
			}
		}
		for (int col = 0; col < cols; col++) {
			if (zeroCols[col] == 1) {
				// 整列置 0
				for (int row = 0; row < rows; row++)
					matrix[row][col] = 0;
			}
		}
	}
}
