package com.leetcode_cn.medium;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*************************电话号码的字母组合********************/
/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 
 * 示例:
 * 
 * 输入："23" 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * 说明: 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 * 
 * @author ffj
 *
 */
public class LetterCombinationsOfAPhoneNumber {

	public static void main(String[] args) {
		String digits = "23";
		System.out.println(new LetterCombinationsOfAPhoneNumber().letterCombinations(digits));
	}

	int length = 0; // 字符串长度
	String digits; // 输入字符串
	int index = 0; // 初始下标

	/**
	 * 递归
	 * 
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinations(String digits) {

		if (digits == null || digits.length() == 0)
			return new LinkedList<>();
		this.length = digits.length();
		this.digits = digits;
		List<String> list = new CopyOnWriteArrayList<>(); // 解决修改list操作抛异常 并发list 线程安全
		helper(index, list);
		return list;
	}

	public List<String> helper(int index, List<String> list) {
		System.out.println("index :" + index + " length :" + length + " list :" + list);
		if (index == length)
			return list;
		int num = digits.charAt(index) - '0';
		if (num == 2) {
			addStringNum(list, new String[] { "a", "b", "c" });
		} else if (num == 3) {
			addStringNum(list, new String[] { "d", "e", "f" });
		} else if (num == 4) {
			addStringNum(list, new String[] { "g", "h", "i" });
		} else if (num == 5) {
			addStringNum(list, new String[] { "j", "k", "l" });
		} else if (num == 6) {
			addStringNum(list, new String[] { "m", "n", "o" });
		} else if (num == 7) {
			addStringNum(list, new String[] { "p", "q", "r", "s" });
		} else if (num == 8) {
			addStringNum(list, new String[] { "t", "u", "v" });
		} else if (num == 9) {
			addStringNum(list, new String[] { "w", "x", "y", "z" });
		}
		return list;
	}

	public void addStringNum(List<String> list, String[] str) {
		if (list.size() == 0) {
			list.addAll(Arrays.asList(str));
			helper(++index, list);
			return;
		}
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String s = it.next();
			list.remove(s);
			for (int i = 0; i < str.length; i++) {
				list.add(s + str[i]);
			}
		}
		helper(++index, list);
	}

	/**
	 * 讨论中解法 BFS
	 * 
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinations1(String digits) {
		LinkedList<String> ans = new LinkedList<String>();
		if (digits.isEmpty())
			return ans;
		String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		ans.add("");
		while (ans.peek().length() != digits.length()) {
			String remove = ans.remove();
			String map = mapping[digits.charAt(remove.length()) - '0'];
			for (char c : map.toCharArray()) {
				ans.addLast(remove + c);
			}
		}
		return ans;
	}
}
