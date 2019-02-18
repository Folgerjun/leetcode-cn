package com.leetcode_cn.medium;

/****************最大平均值和的分组*********/
/**
 * 我们将给定的数组 A 分成 K 个相邻的非空子数组 ，我们的分数由每个子数组内的平均值的总和构成。计算我们所能得到的最大分数是多少。
 * 
 * 注意我们必须使用 A 数组中的每一个数进行分组，并且分数不一定需要是整数。
 * 
 * 示例:
 * 
 * 输入:
 * 
 * A = [9,1,2,3,9] K = 3
 * 
 * 输出: 20
 * 
 * 解释: A 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20. 我们也可以把
 * A 分成[9, 1], [2], [3, 9]. 这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
 * 
 * 说明:
 * 
 * 1 <= A.length <= 100.
 * 
 * 1 <= A[i] <= 10000.
 * 
 * 1 <= K <= A.length.
 * 
 * 答案误差在 10^-6 内被视为是正确的。
 * 
 * @author ffj
 *
 */
public class LargestSumOfAverages {

	public double largestSumOfAverages(int[] A, int K) {
		int N = A.length;
		// memo[i][j] 前i个数分成j组结果的最大值
		double[][] memo = new double[N + 1][N + 1];
		double cur = 0;
		for (int i = 0; i < N; ++i) {
			cur += A[i];
			memo[i + 1][1] = cur / (i + 1);
		}
		return search(N, K, A, memo);
	}

	public double search(int n, int k, int[] A, double[][] memo) {
		if (memo[n][k] > 0)
			return memo[n][k];
		if (n < k)
			return 0;
		double cur = 0;
		for (int i = n - 1; i > 0; --i) {
			cur += A[i];
			memo[n][k] = Math.max(memo[n][k], search(i, k - 1, A, memo) + cur / (n - i));
		}
		return memo[n][k];
	}
}
