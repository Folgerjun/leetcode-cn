package com.leetcode_cn.medium;

/*******************分汤*******************/
/**
 * 有 A 和 B 两种类型的汤。一开始每种类型的汤有 N 毫升。有四种分配操作：
 * 
 * 提供 100ml 的汤A 和 0ml 的汤B。
 * 
 * 提供 75ml 的汤A 和 25ml 的汤B。
 * 
 * 提供 50ml 的汤A 和 50ml 的汤B。
 * 
 * 提供 25ml 的汤A 和 75ml 的汤B。
 * 
 * 当我们把汤分配给某人之后，汤就没有了。每个回合，我们将从四种概率同为0.25的操作中进行分配选择。如果汤的剩余量不足以完成某次操作，我们将尽可能分配。当两种类型的汤都分配完时，停止操作。
 * 
 * 注意不存在先分配100 ml汤B的操作。
 * 
 * 需要返回的值： 汤A先分配完的概率 + 汤A和汤B同时分配完的概率 / 2。
 * 
 * 示例:
 * 
 * 输入: N = 50
 * 
 * 输出: 0.625
 * 
 * 解释:
 * 
 * 如果我们选择前两个操作，A将首先变为空。对于第三个操作，A和B会同时变为空。对于第四个操作，B将首先变为空。
 * 所以A变为空的总概率加上A和B同时变为空的概率的一半是 0.25 *(1 + 1 + 0.5 + 0)= 0.625。
 * 
 * 注释:
 * 
 * 0 <= N <= 10^9。
 * 
 * 返回值在 10^-6 的范围将被认为是正确的。
 * 
 * @author ffj
 *
 */
public class SoupServings {

	/**
	 * DP
	 * 
	 * @param N
	 * @return
	 */
	public double soupServings(int N) {
		N = N / 25 + (N % 25 > 0 ? 1 : 0);
		if (N >= 500)
			return 1.0;

		double[][] memo = new double[N + 1][N + 1];
		for (int s = 0; s <= 2 * N; ++s) {
			for (int i = 0; i <= N; ++i) {
				int j = s - i;
				if (j < 0 || j > N)
					continue;
				double ans = 0.0;
				if (i == 0)
					ans = 1.0;
				if (i == 0 && j == 0)
					ans = 0.5;
				if (i > 0 && j > 0) {
					ans = 0.25 * (memo[M(i - 4)][j] + memo[M(i - 3)][M(j - 1)] + memo[M(i - 2)][M(j - 2)]
							+ memo[M(i - 1)][M(j - 3)]);
				}
				memo[i][j] = ans;
			}
		}
		return memo[N][N];
	}

	public int M(int x) {
		return Math.max(0, x);
	}

	/**
	 * 扩展 斐波那契数列 1 1 2 3 5 8 13 21 24 ...
	 * 
	 * @param n
	 * @return
	 */
	private int Fibonacci_sequence(int n) {
		// 第一个和第二个为1
		if (n == 1 || n == 2)
			return 1;
		int sum = 0;
		sum = Fibonacci_sequence(n - 1) + Fibonacci_sequence(n - 2);
		return sum;
	}

	public static void main(String[] args) {

		System.out.println(new SoupServings().Fibonacci_sequence(8));
	}
}
