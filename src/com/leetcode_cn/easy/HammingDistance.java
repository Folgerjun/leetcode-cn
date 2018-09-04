package com.leetcode_cn.easy;

/*****************汉明距离***************/
/**
 * 
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * 
 * 注意： 0 ≤ x, y < 231.
 * 
 * 示例:
 * 
 * 输入: x = 1, y = 4
 * 
 * 输出: 2
 * 
 * 解释:
 * 
 * 1 (0 0 0 1)
 * 
 * 4 (0 1 0 0)
 * 
 * -----↑---↑
 * 
 * 上面的箭头指出了对应二进制位不同的位置。
 * 
 * @author ffj
 *
 */
public class HammingDistance {

	public int hammingDistance(int x, int y) {
		int total = 0;
		for (int i = 0; i < 32; i++) { // 整数最多32位
			// 记录每一位上的1的个数和
			int count = 0;
			count += (x >> i) & 1;
			count += (y >> i) & 1;
			// 每一位的总汉明距离 = 1的个数 * 0的个数
			total += count * (2 - count);
		}
		return total;
	}

	/**
	 * 异或操作求1个数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int hammingDistance1(int x, int y) {
		int num = x ^ y, total = 0;
		while (num != 0) {
			num &= (num - 1); // 每次&操作便会消除一位1
			total++;
		}
		return total;
	}

}
