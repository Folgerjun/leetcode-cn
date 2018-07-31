package com.leetcode_cn.medium;

/********************整数替换*********************/
/**
 * 
 * 给定一个正整数 n，你可以做如下操作：
 * 
 * 1. 如果 n 是偶数，则用 n / 2替换 n。
 * 
 * 2. 如果 n 是奇数，则可以用 n + 1或n - 1替换 n。
 * 
 * n 变为 1 所需的最小替换次数是多少？
 * 
 * 示例 1:
 * 
 * 输入: 8
 * 
 * 输出: 3
 * 
 * 解释: 8 -> 4 -> 2 -> 1
 * 
 * 示例 2:
 * 
 * 输入: 7
 * 
 * 输出: 4
 * 
 * 解释: 7 -> 8 -> 4 -> 2 -> 1 或 7 -> 6 -> 3 -> 2 -> 1
 * 
 * @author ffj
 *
 */
public class IntegerReplacement {
	public static void main(String[] args) {
		int n = 65535;
		int step = integerReplacement(n);
		System.out.println(step);
	}

	public static int integerReplacement(int n) {
		if (n == 1)
			return 0;
		int step = 0;
		while (n != 1) {
			if ((n & 1) == 0) {
				// 偶数
				n >>>= 1; // 右移一位即取一半
			} else if (n == 3 || ((n >>> 1) & 1) == 0) { // 特殊的3 以及当n右移一位为偶数时 减一操作
				n--;
			} else { // 加一操作
				n++;
			}
			step++;
		}
		return step;
	}

}
