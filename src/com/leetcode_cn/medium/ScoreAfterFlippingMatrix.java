package com.leetcode_cn.medium;

/**********************翻转矩阵后的得分******************/
/**
 * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 * 
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 * 
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 * 
 * 返回尽可能高的分数。
 * 
 * 
 * 
 * 示例：
 * 
 * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * 
 * 输出：39
 * 
 * 解释：
 * 
 * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]] 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 =
 * 39
 * 
 * 
 * 提示：
 * 
 * 1 <= A.length <= 20
 * 
 * 1 <= A[0].length <= 20
 * 
 * A[i][j] 是 0 或 1
 * 
 * @author ffj
 *
 */
public class ScoreAfterFlippingMatrix {

	public int matrixScore(int[][] A) {
		int rows = A.length, cols = A[0].length;
		// 先将每一行第一个置为1
		for (int i = 0; i < rows; i++) {
			if (A[i][0] == 0) {
				for (int j = 0; j < cols; j++) {
					if (A[i][j] == 0)
						A[i][j] = 1;
					else
						A[i][j] = 0;
				}
			}
		}

		// 然后剩下比较每一列 若是0的个数大于1的个数 就反转列
		for (int col = 1; col < cols; col++) {
			int count0 = 0, count1 = 0;
			for (int row = 0; row < rows; row++) {
				if (A[row][col] == 0)
					count0++;
				else
					count1++;
			}
			if (count0 > count1) {
				// 列交换
				for (int row = 0; row < rows; row++) {
					if (A[row][col] == 0)
						A[row][col] = 1;
					else
						A[row][col] = 0;
				}
			}
		}
		// 最后求和
		int sum = 0;
		for (int row = 0; row < rows; row++) {
			int rowSum = 0;
			for (int col = 0; col < cols; col++) {
				rowSum = rowSum << 1 + A[row][col];
			}
			sum += rowSum;
		}
		return sum;
	}

	/**
	 * 官网解法 蛮力
	 * 
	 * @param A
	 * @return
	 */
	public int matrixScore1(int[][] A) {
		int R = A.length, C = A[0].length;
		int[] colsums = new int[C];
		for (int r = 0; r < R; ++r)
			for (int c = 0; c < C; ++c)
				colsums[c] += A[r][c];

		int ans = 0;
		for (int state = 0; state < (1 << R); ++state) {
			// Toggle the rows so that after, 'state' represents
			// the toggled rows.
			if (state > 0) {
				int trans = state ^ (state - 1);
				for (int r = 0; r < R; ++r) {
					if (((trans >> r) & 1) > 0) {
						for (int c = 0; c < C; ++c) {
							colsums[c] += A[r][c] == 1 ? -1 : 1;
							A[r][c] ^= 1;
						}
					}
				}
			}

			// Calculate the score with the rows toggled by 'state'
			int score = 0;
			for (int c = 0; c < C; ++c)
				score += Math.max(colsums[c], R - colsums[c]) * (1 << (C - 1 - c));
			ans = Math.max(ans, score);
		}

		return ans;
	}

	/**
	 * 官网解法 贪心
	 * 
	 * @param A
	 * @return
	 */
	public int matrixScore2(int[][] A) {
		int R = A.length, C = A[0].length;
		int ans = 0;
		for (int c = 0; c < C; ++c) {
			int col = 0;
			for (int r = 0; r < R; ++r)
				col += A[r][c] ^ A[r][0];
			ans += Math.max(col, R - col) * (1 << (C - 1 - c));
		}
		return ans;
	}

}
