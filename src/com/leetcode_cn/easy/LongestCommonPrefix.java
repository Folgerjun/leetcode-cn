package com.leetcode_cn.easy;

/*************************最长公共前缀**********************/
/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 
 * 如果不存在公共前缀，返回空字符串 ""。
 * 
 * 示例 1:
 * 
 * 输入: ["flower","flow","flight"] 输出: "fl"
 * 
 * 示例 2:
 * 
 * 输入: ["dog","racecar","car"] 输出: ""
 * 
 * 解释: 输入不存在公共前缀。
 * 
 * 说明:
 * 
 * 所有输入只包含小写字母 a-z 。
 * 
 * @author ffj
 *
 */
public class LongestCommonPrefix {

	public static void main(String[] args) {
		String[] strs = { "dog", "racecar", "car" };
		System.out.println(longestCommonPrefix1(strs));
	}

	public static String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		// 获取其中最小的字符串长度
		int mix_len = Integer.MAX_VALUE;
		for (String s : strs) {
			int len = s.length();
			mix_len = Math.min(mix_len, len);
		}
		StringBuilder sb = new StringBuilder(mix_len);
		for (int i = 0; i < mix_len; i++) {
			char c = 0;
			boolean flag = true;
			for (int j = 0; j < strs.length; j++) {
				char ch = strs[j].charAt(i);
				if (j == 0) // 第一个字符赋值
					c = ch;
				else {
					if (c != ch) { // 比较不相等就退出循环
						flag = false;
						break;
					}
				}
			}
			if (flag)
				sb.append(c);
			else
				break;
		}
		return sb.length() == 0 ? "" : sb.toString();
	}

	/**
	 * 官网解法
	 * 
	 * @param strs
	 * @return
	 */
	public static String longestCommonPrefix1(String[] strs) {
		if (strs.length == 0)
			return "";
		String prefix = strs[0]; // 以第一个字符串为基准
		for (int i = 1; i < strs.length; i++)
			while (strs[i].indexOf(prefix) != 0) { // 依次找到均满足的相同起始字符
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty())
					return "";
			}
		return prefix;
	}

	/**
	 * 官网解法二
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix2(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		for (int i = 0; i < strs[0].length(); i++) { // 还是以第一个字符为基准
			char c = strs[0].charAt(i);
			for (int j = 1; j < strs.length; j++) {
				if (i == strs[j].length() || strs[j].charAt(i) != c) // 长度遍历到第一个字符串或者遍历到不相等的时候
					return strs[0].substring(0, i); // 直接返回相同字符串
			}
		}
		return strs[0];
	}

	/**
	 * 官网解法三
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix3(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		return longestCommonPrefix(strs, 0, strs.length - 1);
	}

	private String longestCommonPrefix(String[] strs, int l, int r) {
		if (l == r) {
			return strs[l];
		} else {
			int mid = (l + r) / 2;
			String lcpLeft = longestCommonPrefix(strs, l, mid);
			String lcpRight = longestCommonPrefix(strs, mid + 1, r);
			return commonPrefix(lcpLeft, lcpRight);
		}
	}

	String commonPrefix(String left, String right) {
		int min = Math.min(left.length(), right.length());
		for (int i = 0; i < min; i++) {
			if (left.charAt(i) != right.charAt(i))
				return left.substring(0, i);
		}
		return left.substring(0, min);
	}

	/**
	 * 官网解法四
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix4(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		int minLen = Integer.MAX_VALUE;
		for (String str : strs)
			minLen = Math.min(minLen, str.length());
		int low = 1;
		int high = minLen;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (isCommonPrefix(strs, middle))
				low = middle + 1;
			else
				high = middle - 1;
		}
		return strs[0].substring(0, (low + high) / 2);
	}

	private boolean isCommonPrefix(String[] strs, int len) {
		String str1 = strs[0].substring(0, len);
		for (int i = 1; i < strs.length; i++)
			if (!strs[i].startsWith(str1))
				return false;
		return true;
	}

}
