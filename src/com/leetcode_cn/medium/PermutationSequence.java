package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.List;

/************* 第 k 个排列 ************/
/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * 
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * 
 * "123"， "132"， "213"， "231"， "312"， "321"
 * 
 * 给定 n 和 k，返回第 k 个排列。
 * 
 * 说明：
 * 
 * 给定 n 的范围是 [1, 9]。
 * 
 * 给定 k 的范围是[1, n!]。
 * 
 * 示例 1:
 * 
 * 输入: n = 3, k = 3 输出: "213"
 * 
 * 示例 2:
 * 
 * 输入: n = 4, k = 9 输出: "2314"
 * 
 * @author ffj
 *
 */
public class PermutationSequence {

	public String getPermutation(int n, int k) {

		List<Integer> numbers = new ArrayList<>();
		int[] factorial = new int[n + 1];
		StringBuilder sb = new StringBuilder();

		// 存储一个阶乘值数组
		int sum = 1;
		factorial[0] = 1;
		for (int i = 1; i <= n; i++) {
			sum *= i;
			factorial[i] = sum;
		}
		// factorial[] = {1, 1, 2, 6, 24, ... n!}

		// 存储一个有序数值集合
		for (int i = 1; i <= n; i++)
			numbers.add(i);
		// numbers = {1, 2, 3, ..., n}

		k--;
		int index;
		for (int i = 1; i <= n; i++) {
			index = k / factorial[n - i];
			sb.append(String.valueOf(numbers.get(index)));
			numbers.remove(index);
			// 一位一位计算
			k -= index * factorial[n - i];
		}

		return sb.toString();
	}

}
