package com.leetcode_cn.medium;

/*******************翻转字符串里的单词**************/
/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 
 * 示例:
 * 
 * 输入: "the sky is blue", 输出: "blue is sky the".
 * 
 * 说明:
 * 
 * 无空格字符构成一个单词。
 * 
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。 进阶: 请选用C语言的用户尝试使用 O(1) 空间复杂度的原地解法。
 * 
 * @author ffj
 *
 */
public class ReverseWordsInAString {

	public static void main(String[] args) {
		String s = new ReverseWordsInAString().reverseWords("   a   b  c d   e  ");
		System.out.println(s);
	}

	public String reverseWords(String s) {

		s = s.trim();
		String[] strArr = s.split(" ");
		StringBuilder sb = new StringBuilder();
		// 倒着输出
		for (int i = strArr.length - 1; i >= 0; i--) {
			// 前后去空比较
			String ss = strArr[i].trim();
			if (!"".equals(ss)) {
				sb.append(strArr[i]).append(" ");
			}
		}
		return sb.toString().trim(); // 去除最后一个空格
	}

	/**
	 * 没有StringBuilder
	 * 
	 * @param s
	 * @return
	 */
	public String reverseWords1(String s) {
		if (s == null)
			return null;

		char[] a = s.toCharArray();
		int n = a.length;

		// step 1. reverse the whole string
		reverse(a, 0, n - 1);
		// step 2. reverse each word
		reverseWords(a, n);
		// step 3. clean up spaces
		return cleanSpaces(a, n);
	}

	void reverseWords(char[] a, int n) {
		int i = 0, j = 0;

		while (i < n) {
			while (i < j || i < n && a[i] == ' ')
				i++; // skip spaces
			while (j < i || j < n && a[j] != ' ')
				j++; // skip non spaces
			reverse(a, i, j - 1); // reverse the word
		}
	}

	// trim leading, trailing and multiple spaces
	String cleanSpaces(char[] a, int n) {
		int i = 0, j = 0;

		while (j < n) {
			while (j < n && a[j] == ' ')
				j++; // skip spaces
			while (j < n && a[j] != ' ')
				a[i++] = a[j++]; // keep non spaces
			while (j < n && a[j] == ' ')
				j++; // skip spaces
			if (j < n)
				a[i++] = ' '; // keep only one space
		}

		return new String(a).substring(0, i);
	}

	// reverse a[] from a[i] to a[j]
	private void reverse(char[] a, int i, int j) {
		while (i < j) {
			char t = a[i];
			a[i++] = a[j];
			a[j--] = t;
		}
	}

}
