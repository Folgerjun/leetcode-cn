package com.leetcode_cn.easy;

import java.util.HashSet;
import java.util.Set;

/****************快乐数***************/
/**
 * 编写一个算法来判断一个数是不是“快乐数”。
 * 
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到
 * 1。如果可以变为 1，那么这个数就是快乐数。
 * 
 * 示例:
 * 
 * 输入: 19
 * 
 * 输出: true
 * 
 * 解释:
 * 
 * 1^2 + 9^2 = 82
 * 
 * 8^2 + 2^2 = 68
 * 
 * 6^2 + 8^2 = 100
 * 
 * 1^2 + 0^2 + 0^2 = 1
 * 
 * @author ffj
 *
 */
public class HappyNumber {

	public static void main(String[] args) {
		int n = 19;
		System.out.println(new HappyNumber().isHappy(n));
	}

	// 已经计算过的数字
	Set<Integer> visited = new HashSet<>();

	public boolean isHappy(int n) {

		int value = helper(n);
		if (value != 1 && visited.add(value))
			return isHappy(value);
		if (value != 1 && !visited.add(value)) // 说明进入了无限循环
			return false;
		return true;
	}

	/**
	 * 返回处理后累加的数
	 * 
	 * @param n
	 * @return
	 */
	private int helper(int n) {
		if (n == 1)
			return n;
		int sum = 0;
		while (n > 0) {
			int i = n % 10;
			sum += i * i;
			n /= 10;
		}
		System.out.println("sum :" + sum);
		return sum;
	}

	/**
	 * 讨论中解法 类似双指针 一快一慢 快的是慢的两倍 所以若是无限循环 终会相等
	 * 
	 * @param n
	 * @return
	 */
	public boolean isHappy1(int n) {
		int x = n;
		int y = n;
		while (x > 1) {
			x = cal(x);
			if (x == 1)
				return true;
			y = cal(cal(y)); // y 是 x 的两倍
			if (y == 1)
				return true;
			if (x == y)
				return false;
		}
		return true;
	}

	public int cal(int n) {
		int x = n;
		int s = 0;
		while (x > 0) {
			s = s + (x % 10) * (x % 10);
			x = x / 10;
		}
		return s;
	}
}
