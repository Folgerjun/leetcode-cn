package com.leetcode_cn.easy;

/*************计数质数*********/
/**
 * 统计所有小于非负整数 n 的质数的数量。
 * 
 * 示例:
 * 
 * 输入: 10 输出: 4
 * 
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 
 * @author ffj
 *
 */
public class CountPrimes {

	public static void main(String[] args) {

	}

	public int countPrimes(int n) {
		if (n < 2)
			return 0;
		int[] ans = new int[n];
		ans[0] = 1;
		ans[1] = 1;
		// 0 1 不是质数
		for (int i = 2; i <= Math.sqrt(n); ++i) {
			// 将非质数置 1
			if (ans[i] == 0)
				for (int j = i * i; j < n; j += i)
					ans[j] = 1;
		}
		int result = 0;
		// 统计质数个数
		for (int num : ans) {
			if (num == 0)
				result++;
		}
		return result;
	}
}
