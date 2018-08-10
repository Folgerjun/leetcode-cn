package com.leetcode_cn.easy;

/******************罗马数字转整数******************/
/**
 * 罗马数字包含以下七种字符：I， V， X， L，C，D 和 M。
 * 
 * 字符 数值
 * 
 * I 1
 * 
 * V 5
 * 
 * X 10
 * 
 * L 50
 * 
 * C 100
 * 
 * D 500
 * 
 * M 1000
 * 
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V +
 * II 。
 * 
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
 * 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * 
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * 
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 * 
 * 示例 1:
 * 
 * 输入: "III" 输出: 3
 * 
 * 示例 2:
 * 
 * 输入: "IV" 输出: 4
 * 
 * 示例 3:
 * 
 * 输入: "IX" 输出: 9
 * 
 * 示例 4:
 * 
 * 输入: "LVIII" 输出: 58
 * 
 * 解释: L = 50, V = 5, III = 3.
 * 
 * 示例 5:
 * 
 * 输入: "MCMXCIV" 输出: 1994
 * 
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * 
 * @author ffj
 *
 */
public class RomanToInteger {
	/**
	 * 先判断是否存在上述六种情况 有的话先相减差值 然后再循环对应字母数值相加
	 * 
	 * @param s
	 * @return
	 */
	public int romanToInt(String s) {
		int sum = 0;
		if (s.indexOf("IV") != -1)
			sum -= 2;

		if (s.indexOf("IX") != -1)
			sum -= 2;

		if (s.indexOf("XL") != -1)
			sum -= 20;

		if (s.indexOf("XC") != -1)
			sum -= 20;

		if (s.indexOf("CD") != -1)
			sum -= 200;

		if (s.indexOf("CM") != -1)
			sum -= 200;

		char c[] = s.toCharArray();
		int count = 0;

		for (; count <= s.length() - 1; count++) {
			if (c[count] == 'M')
				sum += 1000;
			if (c[count] == 'D')
				sum += 500;
			if (c[count] == 'C')
				sum += 100;
			if (c[count] == 'L')
				sum += 50;
			if (c[count] == 'X')
				sum += 10;
			if (c[count] == 'V')
				sum += 5;
			if (c[count] == 'I')
				sum += 1;

		}

		return sum;
	}

	/**
	 * 先将每个字母对应数值记录 再通过判断数值大小 正常情况数字小的在右边 在左边的情况下就减
	 * 
	 * @param s
	 * @return
	 */
	public int romanToInt1(String s) {
		int nums[] = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'M':
				nums[i] = 1000;
				break;
			case 'D':
				nums[i] = 500;
				break;
			case 'C':
				nums[i] = 100;
				break;
			case 'L':
				nums[i] = 50;
				break;
			case 'X':
				nums[i] = 10;
				break;
			case 'V':
				nums[i] = 5;
				break;
			case 'I':
				nums[i] = 1;
				break;
			}
		}
		int sum = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] < nums[i + 1])
				sum -= nums[i];
			else
				sum += nums[i];
		}
		return sum + nums[nums.length - 1];
	}
}
