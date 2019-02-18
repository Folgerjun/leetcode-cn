package com.leetcode_cn.easy;

import java.util.Arrays;

/************最后一个单词的长度**********/
/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 * 
 * 如果不存在最后一个单词，请返回 0 。
 * 
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 * 
 * 示例:
 * 
 * 输入: "Hello World" 输出: 5
 * 
 * @author ffj
 *
 */
public class LengthOfLastWord {

	public static void main(String[] args) {
		String s = "Hello World";
		new LengthOfLastWord().lengthOfLastWord(s);
	}

	public int lengthOfLastWord(String s) {

		if (s == null || s.length() == 0)
			return 0;

		s = s.trim(); // 去空格

		String[] strArr = s.split(" ");

		int len = strArr[strArr.length - 1].length();

		System.out.println(Arrays.toString(strArr));
		System.out.println(strArr[strArr.length - 1].length());
		return len;

	}

}
