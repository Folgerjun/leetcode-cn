package com.leetcode_cn.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/******************两句话中的不常见单词**************/
/**
 * 给定两个句子 A 和 B 。 （句子是一串由空格分隔的单词。每个单词仅由小写字母组成。）
 * 
 * 如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现，那么这个单词就是不常见的。
 * 
 * 返回所有不常用单词的列表。
 * 
 * 您可以按任何顺序返回列表。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：A = "this apple is sweet", B = "this apple is sour" 输出：["sweet","sour"]
 * 
 * 示例 2：
 * 
 * 输入：A = "apple apple", B = "banana" 输出：["banana"]
 * 
 * 提示：
 * 
 * 0 <= A.length <= 200
 * 
 * 0 <= B.length <= 200
 * 
 * A 和 B 都只包含空格和小写字母。
 * 
 * @author ffj
 *
 */
public class UncommonWordsFromTwoSentences {

	public static void main(String[] args) {
		String A = "this apple is sweet", B = "this apple is sour";
		String[] arrStr = new UncommonWordsFromTwoSentences().uncommonFromSentences(A, B);
		System.out.println(Arrays.toString(arrStr));
	}

	public String[] uncommonFromSentences(String A, String B) {

		Map<String, Integer> map = new TreeMap<>();
		// A
		for (String str : A.split(" "))
			map.put(str, map.getOrDefault(str, 0) + 1);
		// B
		for (String str : B.split(" "))
			map.put(str, map.getOrDefault(str, 0) + 1);
		// 这里将map.entrySet()转换成list
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		// 然后通过比较器来实现排序
		Collections.sort(list, (o1, o2) -> {
			// 升序排序
			return o1.getValue().compareTo(o2.getValue());
		});
		// 遍历只出现一次的字符串
		List<String> listS = new ArrayList<>();
		list.forEach(o -> {
			if (o.getValue() == 1)
				listS.add(o.getKey());
			return;
		});
		return listS.toArray(new String[listS.size()]);
	}

	/**
	 * 拼接两字符串 遍历找出只出现一次的字符串
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public String[] uncommonFromSentences1(String A, String B) {
		String newStr = A + " " + B;
		Map<String, Integer> map = new HashMap<>();
		for (String str : newStr.split(" "))
			map.put(str, map.getOrDefault(str, 0) + 1);
		List<String> list = new LinkedList<>();
		map.forEach((k, v) -> {
			if (v == 1)
				list.add(k);
		});
		return list.toArray(new String[list.size()]);
	}
}
