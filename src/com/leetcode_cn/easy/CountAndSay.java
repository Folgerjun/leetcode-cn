package com.leetcode_cn.easy;

/*******************报数*****************/
/**
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 * 
 * 1. 1
 * 
 * 2. 11
 * 
 * 3. 21
 * 
 * 4. 1211
 * 
 * 5. 111221
 * 
 * 1 被读作 "one 1" ("一个一") , 即 11。
 * 
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 
 * 21 被读作 "one 2", "one 1" （"一个二" , "一个一") , 即 1211。
 * 
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 * 
 * 注意：整数顺序将表示为一个字符串。
 * 
 * 示例 1:
 * 
 * 输入: 1 输出: "1"
 * 
 * 示例 2:
 * 
 * 输入: 4 输出: "1211"
 * 
 * @author ffj
 *
 */
public class CountAndSay {

	String s = "1"; // 初始值
	int sum = 1; // 相同的个数

	public String countAndSay(int n) {

		if (n == 1)
			return s;
		StringBuilder sb = new StringBuilder();
		if (s.length() == 1) {
			sb.append("11");
		} else {
			for (int i = 0; i < s.length() - 1; i++) {
				if (s.charAt(i) == s.charAt(i + 1)) {
					// 两者相等
					sum++;
					if (i == s.length() - 2) {
						// 倒数第二个数的情况 结束尾数相等
						sb.append(sum + "" + s.charAt(i));
						sum = 1;
					}
				} else {
					// 两者不相等
					sb.append(sum + "" + s.charAt(i));
					sum = 1;
					if (i == s.length() - 2) {
						// 倒数第二个数的情况 最后一个数数量就是 1
						sb.append(sum + "" + s.charAt(i + 1));
						sum = 1;
					}
				}

			}
		}
		// 赋值 接着往下走
		s = sb.toString();

		return countAndSay(n - 1);

	}

	public static void main(String[] args) {
		int n = 5;
		System.out.println(new CountAndSay().countAndSay(n));
	}

}
