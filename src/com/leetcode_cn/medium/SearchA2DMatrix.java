package com.leetcode_cn.medium;

/********搜索二维矩阵**********/
/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 
 * 每行中的整数从左到右按升序排列。
 * 
 * 每行的第一个整数大于前一行的最后一个整数。
 * 
 * 示例 1:
 * 
 * 输入:
 * 
 * matrix = [ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ]
 * 
 * target = 3
 * 
 * 输出: true
 * 
 * 示例 2:
 * 
 * 输入:
 * 
 * matrix = [ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ]
 * 
 * target = 13
 * 
 * 输出: false
 * 
 * @author ffj
 *
 */
public class SearchA2DMatrix {

	public boolean searchMatrix(int[][] matrix, int target) {

		if (matrix == null || matrix.length == 0)
			return false;
		int rows = matrix.length, cols = matrix[0].length;
		// 从右上角往左下角找
		int row = 0, col = cols - 1;
		while (row < rows && col >= 0) {
			if (target > matrix[row][col])
				row++;
			else if (target < matrix[row][col])
				col--;
			else // 找到该数
				return true;
		}
		return false;
	}
}
