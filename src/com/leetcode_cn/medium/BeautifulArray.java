package com.leetcode_cn.medium;

import java.util.Arrays;

/***************漂亮数组*************/
/**
 * 对于某些固定的 N，如果数组 A 是整数 1, 2, ..., N 组成的排列，使得：
 * 
 * 对于每个 i < j，都不存在 k 满足 i < k < j 使得 A[k] * 2 = A[i] + A[j]。
 * 
 * 那么数组 A 是漂亮数组。
 * 
 * 给定 N，返回任意漂亮数组 A（保证存在一个）。
 * 
 * 示例 1：
 * 
 * 输入：4
 * 
 * 输出：[2,1,4,3]
 * 
 * 示例 2：
 * 
 * 输入：5
 * 
 * 输出：[3,1,2,5,4]  
 * 
 * 提示：
 * 
 * 1 <= N <= 1000
 * 
 * @author ffj
 *
 */
public class BeautifulArray {

	public static void main(String[] args) {
		int N = 5;
		int[] result = new BeautifulArray().beautifulArray(N);
		System.out.println(Arrays.toString(result));
	}

	/**
	 * 漂亮数组有以下的性质:
	 * 
	 * （1）A是一个漂亮数组，如果对A中所有元素添加一个常数，那么Ａ还是一个漂亮数组。
	 * 
	 * （2）A是一个漂亮数组，如果对A中所有元素乘以一个常数，那么A还是一个漂亮数组。
	 * 
	 * （3）A是一个漂亮数组，如果删除一些A中所有元素，那么A还是一个漂亮数组。
	 * 
	 * （4) A是一个奇数构成的漂亮数组，B是一个偶数构成的漂亮数组，那么A+B也是一个漂亮数组
	 * 比如:{1,5,3,7}+{2,6,4,8}={1,5,3,7,2,6,4,8}也是一个漂亮数组。
	 * 
	 * 所以我们假设一个{1-m}的数组是漂亮数组，可以通过下面的方式构造漂亮数组{1-2m}:
	 * 
	 * 1.对{1-m}中所有的数乘以2-1，构成一个奇数漂亮数组A。如{1,3,2,4},可以得到{1,5,3,7}
	 * 2.对{1-m}中所有的数乘以2,构成一个偶数漂亮数组B,如{1,3,2,4}, 可以得到{2,6,4,8}
	 * 3.A+B构成了{1-2m}的漂亮数组。{1,5,3,7}+{2,6,4,8}={1,5,3,7,2,6,4,8}
	 * 
	 * 4.从中删除不要的数字即可。
	 * 
	 * 
	 * @param N
	 * @return
	 */
	public int[] beautifulArray(int N) {
		int[] nums = new int[N];
		nums[0] = 1;
		if (N == 1)
			return nums;
		int m = N - 1;
		int k = 1;
		while (m != 1) {
			m >>= 1;
			k <<= 1;
		}
		int i = 1, t = 1, j;
		while (i < N) {
			for (j = 0; j < t; j++) {
				if (nums[j] + k <= N) {
					nums[i] = nums[j] + k;
					i++;
				}
			}
			t = i;
			k >>= 1;
		}
		return nums;
	}

}
