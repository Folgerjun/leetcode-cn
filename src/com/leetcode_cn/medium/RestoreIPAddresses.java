package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*****************复原IP地址************/
/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 
 * 示例:
 * 
 * 输入: "25525511135"
 * 
 * 输出: ["255.255.11.135", "255.255.111.35"]
 * 
 * @author ffj
 *
 */
public class RestoreIPAddresses {

	// 数字长度
	int n;
	// 数字
	String s;
	// 存储三段数字
	LinkedList<String> segments = new LinkedList<String>();
	// 存储结果
	ArrayList<String> output = new ArrayList<String>();

	public List<String> restoreIpAddresses(String s) {
		n = s.length();
		this.s = s;
		backtrack(-1, 3);
		return output;
	}

	/**
	 * 校验数字是否合理
	 * 
	 * @param segment
	 * @return
	 */
	public boolean valid(String segment) {
		/*
		 * Check if the current segment is valid : 1. less or equal to 255 2. the first
		 * character could be '0' only if the segment is equal to '0'
		 */
		int m = segment.length();
		if (m > 3)
			return false;
		return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (m == 1);
	}

	/**
	 * 分为三段
	 * 
	 * @param curr_pos
	 */
	public void update_output(int curr_pos) {
		/*
		 * Append the current list of segments to the list of solutions
		 */
		String segment = s.substring(curr_pos + 1, n);
		if (valid(segment)) {
			segments.add(segment);
			// 三段拼接
			output.add(String.join(".", segments));
			// 回溯
			segments.removeLast();
		}
	}

	/**
	 * 回溯法 一段一段截
	 * 
	 * @param prev_pos
	 *            截取起始点
	 * @param dots
	 *            剩余段数
	 */
	public void backtrack(int prev_pos, int dots) {
		/*
		 * prev_pos : the position of the previously placed dot dots : number of dots to
		 * place
		 */
		// The current dot curr_pos could be placed
		// in a range from prev_pos + 1 to prev_pos + 4.
		// The dot couldn't be placed
		// after the last character in the string.
		int max_pos = Math.min(n - 1, prev_pos + 4);
		for (int curr_pos = prev_pos + 1; curr_pos < max_pos; curr_pos++) {
			String segment = s.substring(prev_pos + 1, curr_pos + 1);
			if (valid(segment)) {
				segments.add(segment); // place dot
				if (dots - 1 == 0) // if all 3 dots are placed
					update_output(curr_pos); // add the solution to output
				else
					backtrack(curr_pos, dots - 1); // continue to place dots
				segments.removeLast(); // remove the last placed dot
			}
		}
	}

	/**
	 * 讨论中解法
	 * 
	 * 3-loop divides the string s into 4 substring: s1, s2, s3, s4. Check if each
	 * substring is valid. In isValid, strings whose length greater than 3 or equals
	 * to 0 is not valid; or if the string's length is longer than 1 and the first
	 * letter is '0' then it's invalid; or the string whose integer representation
	 * greater than 255 is invalid.
	 * 
	 * @param s
	 * @return
	 */
	public List<String> restoreIpAddresses1(String s) {
		List<String> res = new ArrayList<String>();
		int len = s.length();
		for (int i = 1; i < 4 && i < len - 2; i++) {
			for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
				for (int k = j + 1; k < j + 4 && k < len; k++) {
					String s1 = s.substring(0, i), s2 = s.substring(i, j), s3 = s.substring(j, k),
							s4 = s.substring(k, len);
					if (isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)) {
						res.add(s1 + "." + s2 + "." + s3 + "." + s4);
					}
				}
			}
		}
		return res;
	}

	public boolean isValid(String s) {
		if (s.length() > 3 || s.length() == 0 || (s.charAt(0) == '0' && s.length() > 1) || Integer.parseInt(s) > 255)
			return false;
		return true;
	}
}
