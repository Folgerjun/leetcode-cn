package com.leetcode_cn.medium;

/*********被围绕的区域*********/
/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 
 * 示例:
 * 
 * X X X X
 * 
 * X O O X
 * 
 * X X O X
 * 
 * X O X X
 * 
 * 运行你的函数后，矩阵变为：
 * 
 * X X X X
 * 
 * X X X X
 * 
 * X X X X
 * 
 * X O X X
 * 
 * 解释:
 * 
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O'
 * 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 
 * @author ffj
 *
 */
public class SurroundedRegions {

	int rows, cols;

	/**
	 * 反向思维 不去考虑围住的区域 反过来考虑 不被围住的区域
	 * 
	 * @param board
	 */
	public void solve(char[][] board) {

		if (board == null || board.length == 0)
			return;
		rows = board.length;
		cols = board[0].length;
		// 遍历边界点
		for (int row = 0; row < rows; row++) {
			helper(board, row, 0);
			helper(board, row, cols - 1);
		}
		for (int col = 0; col < cols; col++) {
			helper(board, 0, col);
			helper(board, rows - 1, col);
		}

		// 将 'O' 置为 'X', '-' 置为 'O'
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				char value = board[row][col];
				if (value == 'O')
					board[row][col] = 'X';
				else if (value == '-')
					board[row][col] = 'O';
			}
		}
	}

	private void helper(char[][] board, int row, int col) {
		if (row < 0 || col < 0 || row >= rows || col >= cols || board[row][col] != 'O')
			return;
		// 将边界 O 相邻的都置为 - 那剩下的就是都是包围的了
		board[row][col] = '-';
		// 周围继续深度遍历
		helper(board, row, col + 1);
		helper(board, row, col - 1);
		helper(board, row + 1, col);
		helper(board, row - 1, col);
	}

}
