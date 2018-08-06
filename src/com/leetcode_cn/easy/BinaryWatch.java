package com.leetcode_cn.easy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/*********************二进制手表********************/
/**
 * 
 * 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
 * 
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * 
 * 
 * 
 * 例如，上面的二进制手表读取 “3:25”。
 * 
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 * 
 * 案例:
 * 
 * 输入: n = 1 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04",
 * "0:08", "0:16", "0:32"]
 * 
 * 
 * 注意事项:
 * 
 * 输出的顺序没有要求。 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。 分钟必须由两位数组成，可能会以零开头，比如 “10:2”
 * 是无效的，应为 “10:02”。
 * 
 * @author ffj
 *
 */
public class BinaryWatch {

	public static void main(String[] args) {

		String i = new BigInteger(String.valueOf((7 << 6) + 44)).toString(2);
		// int i = Integer.parseInt(String.valueOf((7 << 6) + 44), 2);
		System.out.println(i);
	}

	/**
	 * 返回二进制中1的个数
	 * 
	 * @param n
	 * @return
	 */
	public static int NumberOf1(int n) {
		int count = 0;
		while (n != 0) {
			count++;
			n = n & (n - 1); // 妙不可言 如：1100 & 1011 = 1000
		}
		return count;
	}

	public static List<String> readBinaryWatch(int num) {

		List<String> result = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 60; j++) {
				if (NumberOf1((i << 6) + j) == num) { // 神乎其技
					String value = String.valueOf(i) + ":" + ((j < 10) ? ("0" + String.valueOf(j)) : String.valueOf(j));
					result.add(value);
				}
			}
		}
		return result;
	}
}
