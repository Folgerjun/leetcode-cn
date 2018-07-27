package com.leetcode_cn.medium;

/*************** 最大正方形 *****************************/
/**
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * 
 * 示例:
 * 
 * 输入:
 * 
 * 1 0 1 0 0
 * 
 * 1 0 1 1 1
 * 
 * 1 1 1 1 1
 * 
 * 1 0 0 1 0
 * 
 * 输出: 4
 * 
 * @author ffj
 *
 */
public class MaximalSquare {

	public static void main(String[] args) {
		System.out.println('1' - '0');
	}

	/**
	 * 思路：
	 * 当我们判断以某个点为正方形右下角时最大的正方形时，那它的上方，左方和左上方三个点也一定是某个正方形的右下角，否则该点为右下角的正方形最大就是它自己了。
	 * 这是定性的判断，那具体的最大正方形边长呢？我们知道，该点为右下角的正方形的最大边长，最多比它的上方，左方和左上方为右下角的正方形的边长多1，
	 * 最好的情况是是它的上方，左方和左上方为右下角的正方形的大小都一样的，这样加上该点就可以构成一个更大的正方形。但如果它的上方，左方和左上方为右下角的正方形的大小不一样，
	 * 合起来就会缺了某个角落，这时候只能取那三个正方形中最小的正方形的边长加1了。
	 * 
	 * @param matrix
	 * @return
	 */
	public int maximalSquare(char[][] matrix) {
		if (matrix.length == 0)
			return 0;
		int m = matrix.length, n = matrix[0].length;
		int max = 0;
		int[][] dp = new int[m][n]; // 重组一个二维数组
		// 第一列赋值
		for (int i = 0; i < m; i++) {
			dp[i][0] = matrix[i][0] - '0';
			max = Math.max(max, dp[i][0]);
		}
		// 第一行赋值
		for (int i = 0; i < n; i++) {
			dp[0][i] = matrix[0][i] - '0';
			max = Math.max(max, dp[0][i]);
		}
		// 递推 小正方 -> 大正方 （动手便很清晰了）
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = matrix[i][j] == '1' ? Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1
						: 0;
				max = Math.max(max, dp[i][j]);
			}
		}
		return max * max;
	}

	/**
	 * 官网解法一 暴力解法
	 * 
	 * @param matrix
	 * @return
	 */
	public int maximalSquare1(char[][] matrix) {
		int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
		int maxsqlen = 0;
		for (int i = 0; i < rows; i++) { // 行
			for (int j = 0; j < cols; j++) { // 列
				if (matrix[i][j] == '1') {
					int sqlen = 1;
					boolean flag = true;
					while (sqlen + i < rows && sqlen + j < cols && flag) {
						for (int k = j; k <= sqlen + j; k++) {
							if (matrix[i + sqlen][k] == '0') {
								flag = false;
								break;
							}
						}
						for (int k = i; k <= sqlen + i; k++) {
							if (matrix[k][j + sqlen] == '0') {
								flag = false;
								break;
							}
						}
						if (flag)
							sqlen++;
					}
					if (maxsqlen < sqlen) {
						maxsqlen = sqlen;
					}
				}
			}
		}
		return maxsqlen * maxsqlen;
	}

	/**
	 * 官网解法二 DP思想
	 * 
	 * @param matrix
	 * @return
	 */
	public int maximalSquare2(char[][] matrix) {
		int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
		int[][] dp = new int[rows + 1][cols + 1];
		int maxsqlen = 0;
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= cols; j++) {
				if (matrix[i - 1][j - 1] == '1') {
					dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
					maxsqlen = Math.max(maxsqlen, dp[i][j]);
				}
			}
		}
		return maxsqlen * maxsqlen;
	}

	/**
	 * 官网解法三 DP思想
	 * 
	 * @param matrix
	 * @return
	 */
	public int maximalSquare3(char[][] matrix) {
		int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
		int[] dp = new int[cols + 1];
		int maxsqlen = 0, prev = 0;
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= cols; j++) {
				int temp = dp[j];
				if (matrix[i - 1][j - 1] == '1') {
					dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
					maxsqlen = Math.max(maxsqlen, dp[j]);
				} else {
					dp[j] = 0;
				}
				prev = temp;
			}
		}
		return maxsqlen * maxsqlen;
	}

}
