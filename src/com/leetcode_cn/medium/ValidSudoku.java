package com.leetcode_cn.medium;

import java.util.HashSet;
import java.util.Set;

/*********************有效的数独******************/
/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * 
 * 数字 1-9 在每一行只能出现一次。
 * 
 * 数字 1-9 在每一列只能出现一次。
 * 
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * 
 * 说明:
 * 
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 
 * 给定数独永远是 9x9 形式的。
 * 
 * @author ffj
 *
 */
public class ValidSudoku {

	// 满足 3*3 方格
	private int[][] indexArr = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 }, { 2, 1 },
			{ 2, 2 } };

	public static void main(String[] args) {
	}

	public boolean isValidSudoku(char[][] board) {

		// 循环整行和整列
		for (int i = 0; i < 9; i++) {
			Set<Character> hasVisited = new HashSet<>();
			Set<Character> hasVisited1 = new HashSet<>();
			for (int j = 0; j < 9; j++) {
				char ch = board[i][j];
				char ch1 = board[j][i];

				if ((ch != '.' && !hasVisited.add(ch)) || (ch1 != '.' && !hasVisited1.add(ch1)))
					return false;
			}
		}

		// 循环 3 * 3 方格
		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				Set<Character> hasVisited = new HashSet<>();
				for (int[] arr : indexArr) {
					char ch = board[i + arr[0]][j + arr[1]];
					if (ch != '.' && !hasVisited.add(ch))
						return false;
				}
			}
		}
		return true;
	}
}
