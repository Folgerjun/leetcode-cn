package com.leetcode_cn.easy;

/***************4的幂**********/
/**
 * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
 * 
 * 示例 1:
 * 
 * 输入: 16 输出: true
 * 
 * 示例 2:
 * 
 * 输入: 5 输出: false
 * 
 * 进阶：
 * 
 * 你能不使用循环或者递归来完成本题吗？
 * 
 * @author ffj
 *
 */
public class PowerOfFour {

	public boolean isPowerOfFour(int num) {

		// 首先得是2的幂：((num-1) & num) == 0, 然后又因为4的幂 二进制表示都是(这里只举八位，int是32位，0101
		// 0101)，如果是4的幂，应该坐落在bit为1的位上。
		return (((num - 1) & num) == 0) && ((num & (0x55555555)) != 0);

	}

}
