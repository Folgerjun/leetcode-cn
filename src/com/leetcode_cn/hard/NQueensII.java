package com.leetcode_cn.hard;

/*******************N皇后 II************/
/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 * 
 * 示例:
 * 
 * 输入: 4 输出: 2
 * 
 * 解释: 4 皇后问题存在如下两个不同的解法。 [  [".Q..", "...Q", "Q...", "..Q."], // 解法 1  
 * 
 *  ["..Q.", "Q...", "...Q", ".Q.."] ] // 解法 2  
 * 
 * @author ffj
 *
 */
public class NQueensII {

	int num;

	public int totalNQueens(int n) {
		char[][] board = new char[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				board[i][j] = '.';
		dfs(board, 0);
		return num;
	}

	private void dfs(char[][] board, int colIndex) {
		if (colIndex == board.length) {
			num++;
			return;
		}

		for (int i = 0; i < board.length; i++) {
			if (validate(board, i, colIndex)) {
				board[i][colIndex] = 'Q';
				dfs(board, colIndex + 1);
				board[i][colIndex] = '.';
			}
		}
	}

	/**
	 * 验证是否满足条件
	 * 
	 * @param board
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean validate(char[][] board, int x, int y) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < y; j++) {
				if (board[i][j] == 'Q' && (x + j == y + i || x + y == i + j || x == i))
					return false;
			}
		}
		return true;
	}

}
