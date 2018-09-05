package com.leetcode_cn.medium;

import java.util.Arrays;
import java.util.List;

/******************字母移位***************/
/**
 * 有一个由小写字母组成的字符串 S，和一个整数数组 shifts。
 * 
 * 我们将字母表中的下一个字母称为原字母的 移位（由于字母表是环绕的， 'z' 将会变成 'a'）。
 * 
 * 例如·，shift('a') = 'b'， shift('t') = 'u',， 以及 shift('z') = 'a'。
 * 
 * 对于每个 shifts[i] = x ， 我们会将 S 中的前 i+1 个字母移位 x 次。
 * 
 * 返回将所有这些移位都应用到 S 后最终得到的字符串。
 * 
 * 示例：
 * 
 * 输入：S = "abc", shifts = [3,5,9]
 * 
 * 输出："rpl"
 * 
 * 解释： 我们以 "abc" 开始。
 * 
 * 将 S 中的第 1 个字母移位 3 次后，我们得到 "dbc"。
 * 
 * 再将 S 中的前 2 个字母移位 5 次后，我们得到 "igc"。
 * 
 * 最后将 S 中的这 3 个字母移位 9 次后，我们得到答案 "rpl"。
 * 
 * 提示：
 * 
 * 1 <= S.length = shifts.length <= 20000
 * 
 * 0 <= shifts[i] <= 10 ^ 9
 * 
 * @author ffj
 *
 */
public class ShiftingLetters {

	public static void main(String[] args) {

		System.out.println(Math.floorMod(-9, 6));

		// String S = "a";
		// int[] shifts = { 26 };
		//
		// System.out.println(new ShiftingLetters().shiftingLetters(S, shifts));
	}

	/**
	 * 数字太大会超出上限
	 * 
	 * @param S
	 * @param shifts
	 * @return
	 */
	public String shiftingLetters(String S, int[] shifts) {

		if (shifts.length == 0)
			return S;

		Character[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
				'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		List<Character> list = Arrays.asList(letters);

		int sum = 0;
		for (int i : shifts)
			sum += i;

		char[] charArr = S.toCharArray();
		for (int i = 0; i < S.length(); i++) {
			char ch = charArr[i];
			char c = list.get((list.indexOf(ch) + sum) % 26);
			charArr[i] = c;
			sum -= shifts[i];
			if (sum == 0)
				break;
		}

		return String.valueOf(charArr);

	}

	/**
	 * 官网解答
	 * 
	 * @param S
	 * @param shifts
	 * @return
	 */
	public String shiftingLetters1(String S, int[] shifts) {
		StringBuilder ans = new StringBuilder();
		int X = 0;
		for (int shift : shifts)
			X = (X + shift) % 26;

		for (int i = 0; i < S.length(); ++i) {
			int index = S.charAt(i) - 'a';
			ans.append((char) ((index + X) % 26 + 97));
			X = Math.floorMod(X - shifts[i], 26); // 返回int参数的floor模数。
		}

		return ans.toString();
	}
}
