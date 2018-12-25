package com.leetcode_cn.medium;

/***********最小路径和*************/
/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 
 * 说明：每次只能向下或者向右移动一步。
 * 
 * 示例:
 * 
 * 输入: [ [1,3,1], [1,5,1], [4,2,1] ]
 * 
 * 输出: 7
 * 
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * 
 * @author ffj
 *
 */
public class MinimumPathSum {
	int min_val = Integer.MAX_VALUE;

	public int minPathSum(int[][] grid) {

		bfs(0, 0, grid, 0);
		return min_val;
	}

	/**
	 * 超时
	 * 
	 * @param row
	 * @param col
	 * @param grid
	 * @param val
	 */
	private void bfs(int row, int col, int[][] grid, int val) {
		if (row == grid.length || col == grid[0].length)
			return;
		if (row == grid.length - 1 && col == grid[0].length - 1) {
			min_val = Math.min(min_val, val + grid[row][col]);
			return;
		}
		bfs(row + 1, col, grid, val + grid[row][col]);
		bfs(row, col + 1, grid, val + grid[row][col]);
	}

	/**
	 * DP
	 * 
	 * @param grid
	 * @return
	 */
	public int minPathSumDP(int[][] grid) {
		int rows = grid.length, cols = grid[0].length;
		// 遍历所有数字
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (row == 0 && col == 0) // grid[0][0] = grid[0][0]
					grid[row][col] = grid[row][col];
				else if (row == 0 && col != 0) // 第一行上的数字只能是左边的数字选过来
					grid[row][col] = grid[row][col - 1] + grid[row][col];
				else if (row != 0 && col == 0) // 第一列上的数字只能是上边的数字选过来
					grid[row][col] = grid[row - 1][col] + grid[row][col];
				else // 其余的数字可能是上边或者左边选过来 选较小值来与该数相加
					grid[row][col] = Math.min(grid[row - 1][col], grid[row][col - 1]) + grid[row][col];

			}
		}
		return grid[rows - 1][cols - 1];
	}

}
