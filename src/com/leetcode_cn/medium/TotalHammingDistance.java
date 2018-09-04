package com.leetcode_cn.medium;

/***************汉明距离总和**************/
/**
 * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 * 
 * 计算一个数组中，任意两个数之间汉明距离的总和。
 * 
 * 示例:
 * 
 * 输入: 4, 14, 2
 * 
 * 输出: 6
 * 
 * 解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
 * 
 * 所以答案为： HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14,
 * 2) = 2 + 2 + 2 = 6.
 * 
 * 注意:
 * 
 * 数组中元素的范围为从 0到 10^9。
 * 
 * 数组的长度不超过 10^4。
 * 
 * @author ffj
 *
 */
public class TotalHammingDistance {

	public int totalHammingDistance(int[] nums) {
		if (nums.length < 2)
			return 0;
		int sum = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				sum += HammingDistance(nums[i], nums[j]);
			}
		}
		return sum;
	}

	/**
	 * 返回两者对应二进制数对应位不同个数
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	private int HammingDistance(int num1, int num2) {
		// 异或 不同置1相同置0
		int num = num1 ^ num2;
		int count = 0;
		while (num != 0) {
			num &= (num - 1);
			count++;
		}
		return count;
	}

	/**
	 * 讨论中解法
	 * 
	 * @param nums
	 * @return
	 */
	public int totalHammingDistance1(int[] nums) {
		int total = 0, n = nums.length;
		for (int j = 0; j < 32; j++) { // 最多32位
			int bitCount = 0;
			for (int i = 0; i < n; i++) // 计算出每位1的个数
				bitCount += (nums[i] >> j) & 1;
			total += bitCount * (n - bitCount); // 每位两者之间差异个数就是 1的位数 * 0 的位数
		}
		return total;
	}

	public static void main(String[] args) {
		// int i = 4 ^ 14;
		// System.out.println(i);
		// System.out.println(new TotalHammingDistance().HammingDistance(4, 14));
		int[] nums = { 4, 14, 2 };
		System.out.println(new TotalHammingDistance().totalHammingDistance1(nums));
	}

}
