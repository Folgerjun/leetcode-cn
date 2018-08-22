package com.leetcode_cn.medium;

/**********************第K个语法符号**************/
/**
 * 在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
 * 
 * 给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始）
 * 
 * 
 * 例子:
 * 
 * 输入: N = 1, K = 1 输出: 0
 * 
 * 输入: N = 2, K = 1 输出: 0
 * 
 * 输入: N = 2, K = 2 输出: 1
 * 
 * 输入: N = 4, K = 5 输出: 1
 * 
 * 解释:
 * 
 * 第一行: 0
 * 
 * 第二行: 01
 * 
 * 第三行: 0110
 * 
 * 第四行: 01101001
 * 
 * 注意：
 * 
 * N 的范围 [1, 30]. K 的范围 [1, 2^(N-1)].
 * 
 * @author ffj
 *
 */
public class KThSymbolInGrammar {

	int time = 0;

	public int kthGrammar(int N, int K) {

		if (N == 1)
			return 0;
		int len = (int) Math.pow(2, N - 1);
		if (K > len / 2) { // 统计调换次数
			K -= len / 2;
			time++;
		}
		kthGrammar(N - 1, K);

		return time % 2; // 调换次数偶数就是0 奇数就是1
	}

	/**
	 * 一行代码
	 * 
	 * @param N
	 * @param K
	 * @return
	 */
	public int kthGrammar1(int N, int K) {
		return Integer.bitCount(K - 1) & 1;
	}
}
