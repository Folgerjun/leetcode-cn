package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.List;

/***********组合*********/
/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 
 * 示例:
 * 
 * 输入: n = 4, k = 2
 * 
 * 输出: [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
 * 
 * @author ffj
 *
 */
public class Combinations {

	/**
	 * 回溯法
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> combs = new ArrayList<>();
		combine(combs, new ArrayList<>(), 1, n, k);
		return combs;
	}

	private static void combine(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
		if (k == 0) {
			// 注意要一个新对象
			combs.add(new ArrayList<>(comb));
			return;
		}
		for (int i = start; i <= n; i++) {
			comb.add(i);
			combine(combs, comb, i + 1, n, k - 1);
			// 回溯
			comb.remove(comb.size() - 1);
		}
	}

}
