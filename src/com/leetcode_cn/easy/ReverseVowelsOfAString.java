package com.leetcode_cn.easy;

/*********反转字符串中的元音字母********/
/**
 * 
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * 
 * 示例 1:
 * 
 * 输入: "hello" 输出: "holle"
 * 
 * 示例 2:
 * 
 * 输入: "leetcode" 输出: "leotcede"
 * 
 * 说明: 元音字母不包含字母"y"。
 * 
 * @author ffj
 *
 */
public class ReverseVowelsOfAString {

	public String reverseVowels(String s) {

		if (s == null || s.length() == 0)
			return s;
		String vowels = "aeiouAEIOU";
		char[] chars = s.toCharArray();
		int start = 0, end = s.length() - 1;
		while (start < end) {
			// 首尾指针找出元音字母然后互换
			while (start < end && !vowels.contains(chars[start] + ""))
				start++;
			while (start < end && !vowels.contains(chars[end] + ""))
				end--;
			// 互换
			char temp = chars[start];
			chars[start] = chars[end];
			chars[end] = temp;
			start++;
			end--;
		}
		return new String(chars);
	}
}
