package com.leetcode_cn.medium;

import java.util.Arrays;

/********************最长数对链************/
/**
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 * 
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 * 
 * 给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 * 
 * 示例 :
 * 
 * 输入: [[1,2], [2,3], [3,4]] 输出: 2
 * 
 * 解释: 最长的数对链是 [1,2] -> [3,4]
 * 
 * 注意：
 * 
 * 给出数对的个数在 [1, 1000] 范围内。
 * 
 * @author ffj
 *
 */
public class MaximumLengthOfPairChain {

	/**
	 * DP
	 * 
	 * @param pairs
	 * @return
	 */
	public int findLongestChain(int[][] pairs) {

		// 排序 第一个元素升序
		Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
		int N = pairs.length;
		int[] dp = new int[N];
		Arrays.fill(dp, 1);

		for (int j = 1; j < N; ++j) {
			for (int i = 0; i < j; ++i) {
				if (pairs[i][1] < pairs[j][0])
					dp[j] = Math.max(dp[j], dp[i] + 1);
			}
		}
		int ans = 0;
		for (int x : dp)
			if (x > ans)
				ans = x;
		return ans;

	}

	/**
	 * 先满足第二个元素的条件 再满足 b < c
	 * 
	 * @param pairs
	 * @return
	 */
	public int findLongestChain1(int[][] pairs) {
		// 第二个元素升序
		Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
		int cur = Integer.MIN_VALUE, ans = 0;
		for (int[] pair : pairs)
			if (cur < pair[0]) {
				cur = pair[1];
				ans++;
			}
		return ans;
	}

}
