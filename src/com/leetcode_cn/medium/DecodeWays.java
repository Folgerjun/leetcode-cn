package com.leetcode_cn.medium;

/**********解码方法*******/
/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * 
 * 'A' -> 1
 * 
 * 'B' -> 2 ...
 * 
 * 'Z' -> 26
 * 
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * 
 * 示例 1:
 * 
 * 输入: "12" 输出: 2
 * 
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 
 * 示例 2:
 * 
 * 输入: "226" 输出: 3
 * 
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * 
 * @author ffj
 *
 */
public class DecodeWays {

	public int numDecodings(String s) {
		int n = s.length();
		if (n == 0)
			return 0;

		int[] memo = new int[n + 1];
		memo[n] = 1;
		memo[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;
		// 必须遍历完
		for (int i = n - 2; i >= 0; i--)
			if (s.charAt(i) != '0')
				memo[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? memo[i + 1] + memo[i + 2] : memo[i + 1];

		return memo[0];

	}

	/**
	 * 条件版的 dp[i] = dp[i - 1] + dp[i - 2]
	 * 
	 * @param str
	 * @return
	 */
	public int numDecodings1(String str) {
		int len = str.length();
		// 0 返回
		if (len == 0 || (len == 1 && str.charAt(0) == '0'))
			return 0;
		int[] dp = new int[2];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 0; i < len; i++) {
			int temp = 0;
			if (str.charAt(i) != '0')
				temp += dp[1];
			// 满足可拆分条件的
			if (i > 0 && (str.charAt(i - 1) == '1' || (str.charAt(i - 1) == '2' && str.charAt(i) <= '6')))
				temp += dp[0];
			dp[0] = dp[1];
			dp[1] = temp;
		}
		return dp[1];
	}

}
