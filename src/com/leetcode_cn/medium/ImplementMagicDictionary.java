package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*********************实现一个魔法字典**************/
/**
 * 实现一个带有buildDict, 以及 search方法的魔法字典。
 * 
 * 对于buildDict方法，你将被给定一串不重复的单词来构建一个字典。
 * 
 * 对于search方法，你将被给定一个单词，并且判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
 * 
 * 示例 1:
 * 
 * Input: buildDict(["hello", "leetcode"]), Output: Null
 * 
 * Input: search("hello"), Output: False
 * 
 * Input: search("hhllo"), Output: True
 * 
 * Input: search("hell"), Output: False
 * 
 * Input: search("leetcoded"), Output: False
 * 
 * 注意:
 * 
 * 你可以假设所有输入都是小写字母 a-z。 为了便于竞赛，测试所用的数据量很小。你可以在竞赛结束后，考虑更高效的算法。
 * 请记住重置MagicDictionary类中声明的类变量，因为静态/类变量会在多个测试用例中保留。 请参阅这里了解更多详情。
 * 
 * @author ffj
 *
 */
public class ImplementMagicDictionary {

	Map<Integer, ArrayList<String>> map;

	/** Initialize your data structure here. */
	public ImplementMagicDictionary() {
		map = new HashMap<>();
	}

	/** Build a dictionary through a list of words */
	public void buildDict(String[] dict) {
		for (String s : dict) {
			// computeIfAbsent 返回value值 key如果没有则塞入 value为null则替换
			map.computeIfAbsent(s.length(), x -> new ArrayList<>()).add(s);
		}
	}

	/**
	 * Returns if there is any word in the trie that equals to the given word after
	 * modifying exactly one character
	 */
	public boolean search(String word) {
		// 长度一样的没有 直接返回false
		if (!map.containsKey(word.length()))
			return false;
		for (String s : map.get(word.length())) {
			int num = 0;
			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) != s.charAt(i)) { // 发现不同加一
					if (++num > 1) // 超过1直接返回
						break;
				}
			}
			if (num == 1) // 满足条件
				return true;
		}
		return false;
	}
}
