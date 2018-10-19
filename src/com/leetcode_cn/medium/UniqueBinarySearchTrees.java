package com.leetcode_cn.medium;

/*******************不同的二叉搜索树*************/
/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * 
 * 示例:
 * 
 * 输入: 3 输出: 5
 * 
 * 解释: 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * 
 * @author ffj
 *
 */
public class UniqueBinarySearchTrees {

	/**
	 * DP
	 * 
	 * @param n
	 * @return
	 */
	public int numTrees(int n) {
		int[] G = new int[n + 1];
		G[0] = 1;
		G[1] = 1;

		for (int i = 2; i <= n; ++i) {
			for (int j = 1; j <= i; ++j) {
				G[i] += G[j - 1] * G[i - j];
			}
		}
		return G[n];
	}

	/**
	 * 公式推理 Catalan number
	 * 
	 * @param n
	 * @return
	 */
	public int numTrees1(int n) {
		// Note: we should use long here instead of int, otherwise overflow
		long C = 1;
		for (int i = 0; i < n; ++i) {
			C = C * 2 * (2 * i + 1) / (i + 2);
		}
		return (int) C;
	}

	public static void main(String[] args) {
		System.out.println(new UniqueBinarySearchTrees().numTrees(4));
	}
}
