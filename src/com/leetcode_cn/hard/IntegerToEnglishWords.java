package com.leetcode_cn.hard;

/****************** 整数转换英文表示 *********************************/
/**
 * 将非负整数转换为其对应的英文表示。可以保证给定输入小于 2^31 - 1 。
 * 
 * 示例 1:
 * 
 * 输入: 123 输出: "One Hundred Twenty Three"
 * 
 * 示例 2:
 * 
 * 输入: 12345 输出: "Twelve Thousand Three Hundred Forty Five"
 * 
 * 示例 3:
 * 
 * 输入: 1234567 输出: "One Million Two Hundred Thirty Four Thousand Five Hundred
 * Sixty Seven"
 * 
 * 示例 4:
 * 
 * 输入: 1234567891 输出: "One Billion Two Hundred Thirty Four Million Five Hundred
 * Sixty Seven Thousand Eight Hundred Ninety One"
 * 
 * @author ffj
 *
 */
public class IntegerToEnglishWords {

	private static String[] numOne = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
	private static String[] numTwo = { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
			"Seventeen", "Eighteen", "Nineteen" };
	private static String[] numThree = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
			"Ninety" };

	public static void main(String[] args) {

		String result = numberToWords(1234567891);
		System.out.println(result);
	}

	public static String numberToWords(int num) {
		// 输入0 直接返回
		if (num == 0)
			return "Zero";
		String result = "";
		if (num > 999999999) {
			result += numberToWordsWithThreeDIgit(num / 1000000000) + " Billion";
			num %= 1000000000;
		}
		if (num > 999999) {
			result += numberToWordsWithThreeDIgit(num / 1000000) + " Million";
			num %= 1000000;
		}
		if (num > 999) {
			result += numberToWordsWithThreeDIgit(num / 1000) + " Thousand";
			num %= 1000;
		}
		if (num > 0) {
			result += numberToWordsWithThreeDIgit(num);
		}
		return result.trim(); // 去掉首尾空格输出

	}

	/**
	 * 拼接个十百数字
	 * 
	 * @param num
	 * @return
	 */
	public static String numberToWordsWithThreeDIgit(int num) {
		String result = "";
		if (num > 99) {
			result += " " + numOne[num / 100] + " Hundred";
		}
		num %= 100;
		if (num > 19) {
			result += " " + numThree[num / 10];
			num %= 10;
		} else if (num > 9) {
			result += " " + numTwo[num - 10];
			num = 0;
		}
		if (num > 0) {
			result += " " + numOne[num];
		}
		return result;
	}

}
