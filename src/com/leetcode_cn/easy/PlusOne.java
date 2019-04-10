package com.leetcode_cn.easy;

import java.util.Arrays;

/***************加一**********/
/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * 
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * 
 * 示例 1:
 * 
 * 输入: [1,2,3] 输出: [1,2,4]
 * 
 * 解释: 输入数组表示数字 123。
 * 
 * 示例 2:
 * 
 * 输入: [4,3,2,1] 输出: [4,3,2,2]
 * 
 * 解释: 输入数组表示数字 4321。
 * 
 * @author ffj
 *
 */
public class PlusOne {

	public static void main(String[] args) {
		int[] result = new PlusOne().plusOne(new int[] { 9, 9 });
		System.out.println(Arrays.toString(result));
	}

	public int[] plusOne(int[] digits) {
		int length = digits.length;
		digits[length - 1] = digits[length - 1] + 1;
		if (digits[length - 1] > 9) {// 9 -> 10
			digits[length - 1] = 0;
			digits = plushelper(length - 2, digits);
		}
		return digits;
	}

	/**
	 * 进位
	 * 
	 * @param index
	 * @param digits
	 * @return
	 */
	private int[] plushelper(int index, int[] digits) {
		if (index < 0) {
			int[] newArr = new int[digits.length + 1];
			System.arraycopy(digits, 0, newArr, 1, digits.length);
			newArr[0] = 1;
			return newArr;
		} else {
			if (digits[index] == 9) {
				digits[index] = 0;
				digits = plushelper(index - 1, digits);
			} else
				digits[index] += 1;
		}
		return digits;
	}
}
