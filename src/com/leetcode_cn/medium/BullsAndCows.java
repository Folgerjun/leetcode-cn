package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.List;

/********************猜数字游戏*******************/
/**
 * 你正在和你的朋友玩 猜数字（Bulls and
 * Cows）游戏：你写下一个数字让你的朋友猜。每次他猜测后，你给他一个提示，告诉他有多少位数字和确切位置都猜对了（称为“Bulls”,
 * 公牛），有多少位数字猜对了但是位置不对（称为“Cows”, 奶牛）。你的朋友将会根据提示继续猜，直到猜出秘密数字。
 * 
 * 请写出一个根据秘密数字和朋友的猜测数返回提示的函数，用 A 表示公牛，用 B 表示奶牛。
 * 
 * 请注意秘密数字和朋友的猜测数都可能含有重复数字。
 * 
 * 示例 1:
 * 
 * 输入: secret = "1807", guess = "7810" 输出: "1A3B"
 * 
 * 解释: 1 公牛和 3 奶牛。公牛是 8，奶牛是 0, 1 和 7。
 * 
 * 示例 2:
 * 
 * 输入: secret = "1123", guess = "0111" 输出: "1A1B"
 * 
 * 解释: 朋友猜测数中的第一个 1 是公牛，第二个或第三个 1 可被视为奶牛。
 * 
 * 说明: 你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。
 * 
 * 
 * @author ffj
 *
 */
public class BullsAndCows {

	public static void main(String[] args) {
		String secret = "1123";
		String guess = "0111";
		System.out.println(getHint(secret, guess));
	}

	public static String getHint(String secret, String guess) {

		char[] sec = secret.toCharArray();
		char[] gue = guess.toCharArray();
		int length = sec.length, cntA = 0, cntB = 0;
		List<Character> listA = new ArrayList<>();
		List<Character> listB = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			if (sec[i] == gue[i]) // 公牛
				cntA++;
			else {
				listA.add(sec[i]);
				listB.add(gue[i]);
			}
		}
		for (Character ch : listB) {
			int index = listA.indexOf(ch);
			if (index != -1) {
				// 奶牛
				listA.remove(index);
				cntB++;
			}
		}
		return cntA + "A" + cntB + "B";

	}

	/**
	 * 讨论中解法
	 * 
	 * @param secret
	 * @param guess
	 * @return
	 */
	public String getHint1(String secret, String guess) {
		int bulls = 0;
		int cows = 0;
		int[] numbers = new int[10];
		for (int i = 0; i < secret.length(); i++) {
			if (secret.charAt(i) == guess.charAt(i)) // 公牛
				bulls++;
			else {
				if (numbers[secret.charAt(i) - '0']++ < 0) // 供不应求
					cows++;
				if (numbers[guess.charAt(i) - '0']-- > 0) // 供大于求
					cows++;
			}
		}
		return bulls + "A" + cows + "B";
	}
}
