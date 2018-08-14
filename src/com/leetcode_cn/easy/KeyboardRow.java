package com.leetcode_cn.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**********************键盘行**************/
/**
 * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。
 * 
 * 示例1:
 * 
 * 输入: ["Hello", "Alaska", "Dad", "Peace"] 输出: ["Alaska", "Dad"]
 * 
 * 注意: 你可以重复使用键盘上同一字符。 你可以假设输入的字符串将只包含字母。
 * 
 * @author ffj
 *
 */
public class KeyboardRow {

	public String[] findWords(String[] words) {

		if (words == null || words.length == 0)
			return new String[0];

		List<Character> list1 = Arrays.asList('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P');
		List<Character> list2 = Arrays.asList('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L');
		// List<Character> list3 = Arrays.asList('Z', 'X', 'C', 'V', 'B', 'N', 'M');

		List<String> ls = new ArrayList<>();

		for (int i = 0; i < words.length; i++) {
			String s = words[i];
			int sum1 = 0, sum2 = 0, sum3 = 0;
			for (int j = 0; j < s.length(); j++) {
				char c = Character.toUpperCase(s.charAt(j)); // 转大写字母统一比较
				if (list1.contains(c))
					sum1 = 1;
				else if (list2.contains(c))
					sum2 = 1;
				else
					sum3 = 1;

				if (sum1 + sum2 + sum3 != 1) // 不在同一行直接退出该循环
					break;

				if (j == s.length() - 1)
					ls.add(s);
			}
		}
		return ls.toArray(new String[ls.size()]);
	}
}
