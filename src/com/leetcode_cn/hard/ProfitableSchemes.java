package com.leetcode_cn.hard;

/***********************盈利计划*******************/
/**
 * 
 * 帮派里有 G 名成员，他们可能犯下各种各样的罪行。
 * 
 * 第 i 种犯罪会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。
 * 
 * 让我们把这些犯罪的任何子集称为盈利计划，该计划至少产生 P 的利润。
 * 
 * 有多少种方案可以选择？因为答案很大，所以返回它模 10^9 + 7 的值。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：G = 5, P = 3, group = [2,2], profit = [2,3] 输出：2
 * 
 * 解释： 至少产生 3 的利润，该帮派可以犯下罪 0 和罪 1 ，或仅犯下罪 1 。 总的来说，有两种方案。
 * 
 * 示例 2:
 * 
 * 输入：G = 10, P = 5, group = [2,3,5], profit = [6,7,8] 输出：7
 * 
 * 解释： 至少产生 5 的利润，只要他们犯其中一种罪就行，所以该帮派可以犯下任何罪行 。 有 7
 * 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
 * 
 * 
 * 提示：
 * 
 * 1 <= G <= 100
 * 
 * 0 <= P <= 100
 * 
 * 1 <= group[i] <= 100
 * 
 * 0 <= profit[i] <= 100
 * 
 * 1 <= group.length = profit.length <= 100
 * 
 * @author ffj
 *
 */
public class ProfitableSchemes {

	public static void main(String[] args) {
		int G = 5, P = 3;
		int[] group = new int[] { 2, 2 }, profit = new int[] { 2, 3 };
		System.out.println(new ProfitableSchemes().profitableSchemes(G, P, group, profit));
	}

	public int profitableSchemes(int G, int P, int[] group, int[] profit) {
		int MOD = 1_000_000_007;
		int N = group.length;
		long[][][] dp = new long[2][P + 1][G + 1];
		dp[0][0][0] = 1;

		for (int i = 0; i < N; ++i) {
			int p0 = profit[i]; // the current crime profit
			int g0 = group[i]; // the current crime group size

			long[][] cur = dp[i % 2];
			long[][] cur2 = dp[(i + 1) % 2];

			// Deep copy cur into cur2
			for (int jp = 0; jp <= P; ++jp)
				for (int jg = 0; jg <= G; ++jg)
					cur2[jp][jg] = cur[jp][jg];

			for (int p1 = 0; p1 <= P; ++p1) { // p1 : the current profit
				// p2 : the new profit after committing this crime
				int p2 = Math.min(p1 + p0, P);
				for (int g1 = 0; g1 <= G - g0; ++g1) { // g1 : the current group size
					// g2 : the new group size after committing this crime
					int g2 = g1 + g0;
					cur2[p2][g2] += cur[p1][g1];
					cur2[p2][g2] %= MOD;
				}
			}
		}

		// Sum all schemes with profit P and group size 0 <= g <= G.
		long ans = 0;
		for (long x : dp[N % 2][P])
			ans += x;

		return (int) ans;
	}

	/**
	 * 讨论中解法
	 * 
	 * @param G
	 * @param P
	 * @param group
	 * @param profit
	 * @return
	 */
	public int profitableSchemes1(int G, int P, int[] group, int[] profit) {
		int[][] dp = new int[P + 1][G + 1];
		dp[0][0] = 1;
		int res = 0, mod = (int) 1e9 + 7;
		for (int k = 0; k < group.length; k++) {
			int g = group[k], p = profit[k];
			for (int i = P; i >= 0; i--)
				for (int j = G - g; j >= 0; j--)
					dp[Math.min(i + p, P)][j + g] = (dp[Math.min(i + p, P)][j + g] + dp[i][j]) % mod;
		}
		for (int x : dp[P])
			res = (res + x) % mod;
		return res;
	}

}
