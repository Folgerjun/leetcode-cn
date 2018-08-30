package com.leetcode_cn.hard;

/****************分割回文串 II***************/
/**
 * 
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 
 * 返回符合要求的最少分割次数。
 * 
 * 示例:
 * 
 * 输入: "aab" 输出: 1
 * 
 * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * 
 * @author ffj
 *
 */
public class PalindromePartitioningII {

	public static void main(String[] args) {
		System.out.println(new PalindromePartitioningII().minCut("ababa"));
	}

	/**
	 * DP 动态规划
	 * 
	 * @param s
	 * @return
	 */
	public int minCut(String s) {

		char[] c = s.toCharArray();
		int n = c.length;
		int[] cut = new int[n];
		boolean[][] pal = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = 0; j <= i; j++) {
				if (c[j] == c[i] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
					pal[j][i] = true;
					min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
				}
			}
			cut[i] = min;
		}
		return cut[n - 1];

	}

}
