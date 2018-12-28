package com.leetcode_cn.hard;

/**************寻找最近的回文数************/
/**
 * 给定一个整数 n ，你需要找到与它最近的回文数（不包括自身）。
 * 
 * “最近的”定义为两个整数差的绝对值最小。
 * 
 * 示例 1:
 * 
 * 输入: "123" 输出: "121"
 * 
 * 注意:
 * 
 * n 是由字符串表示的正整数，其长度不超过18。 如果有多个结果，返回最小的那个。
 * 
 * @author ffj
 *
 */
public class FindTheClosestPalindrome {

	public String nearestPalindromic(String n) {

		char[] arr = n.toCharArray();
		for (int i = 0, j = arr.length - 1; i < j; i++, j--)
			arr[j] = arr[i];

		// 最接近的三个回文数
		String curP = String.valueOf(arr);
		String preP = nearestPalindrom(curP, false);
		String nextP = nearestPalindrom(curP, true);

		long num = Long.valueOf(n);
		long cur = Long.valueOf(curP);
		long pre = Long.valueOf(preP);
		long next = Long.valueOf(nextP);

		// 与三个回文数的差值
		long d1 = Math.abs(num - pre);
		long d2 = Math.abs(num - cur);
		long d3 = Math.abs(num - next);

		if (num == cur) { // 本身是回文数，因题目要求不包括自身
			return d1 <= d3 ? preP : nextP;
		} else if (num > cur) {
			return d2 <= d3 ? curP : nextP;
		} else {
			return d1 <= d2 ? preP : curP;
		}
	}

	/**
	 * 返回最接近的回文数
	 * 
	 * @param curP
	 *            一个回文数
	 * @param dir
	 *            false：比该数小 | true：比该数大
	 * @return
	 */
	private String nearestPalindrom(String curP, boolean dir) {
		int k = curP.length() >> 1, p = curP.length() - k;
		// 取一半
		int l = Integer.valueOf(curP.substring(0, p));
		// 加一或减一
		l += (dir ? 1 : -1);

		if (l == 0)
			return k == 0 ? "0" : "9";

		StringBuilder left = new StringBuilder(String.valueOf(l));
		StringBuilder right = new StringBuilder(left).reverse();
		if (k > left.length())
			right.append("9");

		return left.append(right.substring(right.length() - k)).toString();
	}

	public static void main(String[] args) {
		String str = new FindTheClosestPalindrome().nearestPalindromic("999");
		System.out.println("str :" + str);
	}

}
