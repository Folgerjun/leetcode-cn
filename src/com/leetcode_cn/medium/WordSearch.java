package com.leetcode_cn.medium;

/****************************单词搜索******************/
/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * 
 * 示例:
 * 
 * board = [ ['A','B','C','E'], ['S','F','C','S'], ['A','D','E','E'] ]
 * 
 * 给定 word = "ABCCED", 返回 true.
 * 
 * 给定 word = "SEE", 返回 true.
 * 
 * 给定 word = "ABCB", 返回 false.
 * 
 * @author ffj
 *
 */
public class WordSearch {

	/**
	 * DFS
	 * 
	 * @param board
	 * @param word
	 * @return
	 */
	public boolean exist(char[][] board, String word) {
		char[] w = word.toCharArray();
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if (exist(board, y, x, w, 0))
					return true;
			}
		}
		return false;
	}

	/**
	 * 递归
	 * 
	 * @param board
	 * @param y
	 * @param x
	 * @param word
	 * @param i
	 * @return
	 */
	private boolean exist(char[][] board, int y, int x, char[] word, int i) {
		if (i == word.length)
			return true;
		if (y < 0 || x < 0 || y == board.length || x == board[y].length)
			return false;
		if (board[y][x] != word[i])
			return false;
		board[y][x] ^= 256;
		boolean exist = exist(board, y, x + 1, word, i + 1) || exist(board, y, x - 1, word, i + 1)
				|| exist(board, y + 1, x, word, i + 1) || exist(board, y - 1, x, word, i + 1);
		board[y][x] ^= 256;
		return exist;
	}
}
