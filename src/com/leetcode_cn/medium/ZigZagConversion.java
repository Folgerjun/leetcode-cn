package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.List;

/*********************Z字形变换******************/
/**
 * 
 * 将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：
 * 
 * P --A --H --N
 * 
 * A P L S I I G
 * 
 * Y --I --R
 * 
 * 之后从左往右，逐行读取字符："PAHNAPLSIIGYIR"
 * 
 * 实现一个将字符串进行指定行数变换的函数:
 * 
 * string convert(string s, int numRows);
 * 
 * 示例 1:
 * 
 * 输入: s = "PAYPALISHIRING", numRows = 3
 * 
 * 输出: "PAHNAPLSIIGYIR"
 * 
 * 示例 2:
 * 
 * 输入: s = "PAYPALISHIRING", numRows = 4
 * 
 * 输出: "PINALSIGYAHRPI"
 * 
 * 解释:
 * 
 * P ----I ----N
 * 
 * A --L S --I G
 * 
 * Y A --H R
 * 
 * P ----I
 * 
 * @author ffj
 *
 */
public class ZigZagConversion {

	public static void main(String[] args) {
		String s = "ABC";
		int numRows = 1;
		String value = convert(s, numRows);
		System.out.println(value);
	}

	public static String convert(String s, int numRows) {

		if (s == null || s.length() == 0)
			return "";
		char[][] sChar = new char[numRows][s.length()];
		int row = 0, column = 0;
		boolean flag = false;
		for (int i = 0; i < s.length(); i++) {
			char chars = s.charAt(i);
			if (!flag) {
				sChar[row++][column] = chars;
				if (row == numRows) {
					flag = true;
					row--;
				}
			} else {
				sChar[row == 0 ? row : --row][++column] = chars;
				if (row == 0) {
					if (numRows != 1) { // 只有一行时只能增加列值 横向赋值
						row++;
					} else {
						column++;
					}
					flag = false;
				}
			}

		}
		StringBuilder value = new StringBuilder();
		for (int i = 0; i < sChar.length; i++) {
			for (int j = 0; j < sChar[i].length; j++) {
				char c = sChar[i][j]; // char数组中若未赋值 Unicode码为0
				// System.out.println("c :" + c + " ,c == null" + (c == 0));
				if (c != 0) {
					value.append(sChar[i][j]);
				}
			}
		}
		return value.toString();
	}

	/**
	 * 社区解答
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convert1(String s, int numRows) {
		if (numRows <= 1)
			return s;
		StringBuilder[] sb = new StringBuilder[numRows];
		for (int i = 0; i < sb.length; i++) {
			sb[i] = new StringBuilder(""); // init every sb element **important step!!!!
		}
		int incre = 1;
		int index = 0;
		for (int i = 0; i < s.length(); i++) {
			sb[index].append(s.charAt(i));
			if (index == 0) {
				incre = 1;
			}
			if (index == numRows - 1) {
				incre = -1;
			}
			index += incre;
		}
		String re = "";
		for (int i = 0; i < sb.length; i++) {
			re += sb[i];
		}
		return re.toString();
	}

	/**
	 * 官网解法一 按行排序
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convert2(String s, int numRows) {

		if (numRows == 1)
			return s;

		List<StringBuilder> rows = new ArrayList<>();
		for (int i = 0; i < Math.min(numRows, s.length()); i++)
			rows.add(new StringBuilder());

		int curRow = 0;
		boolean goingDown = false;

		for (char c : s.toCharArray()) {
			rows.get(curRow).append(c);
			if (curRow == 0 || curRow == numRows - 1)
				goingDown = !goingDown;
			curRow += goingDown ? 1 : -1;
		}

		StringBuilder ret = new StringBuilder();
		for (StringBuilder row : rows)
			ret.append(row);
		return ret.toString();
	}

	/**
	 * 官网解法二 按行访问
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convert3(String s, int numRows) {

		if (numRows == 1)
			return s;

		StringBuilder ret = new StringBuilder();
		int n = s.length();
		int cycleLen = 2 * numRows - 2;

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j + i < n; j += cycleLen) {
				ret.append(s.charAt(j + i));
				if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
					ret.append(s.charAt(j + cycleLen - i));
			}
		}
		return ret.toString();
	}
}
