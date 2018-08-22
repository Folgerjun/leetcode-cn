package com.leetcode_cn.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***********************前缀和后缀搜索********************/
/**
 * 给定多个 words，words[i] 的权重为 i 。
 * 
 * 设计一个类 WordFilter 实现函数WordFilter.f(String prefix, String suffix)。这个函数将返回具有前缀
 * prefix 和后缀suffix 的词的最大权重。如果没有这样的词，返回 -1。
 * 
 * 例子:
 * 
 * 输入: WordFilter(["apple"])
 * 
 * WordFilter.f("a", "e") // 返回 0
 * 
 * WordFilter.f("b", "") // 返回 -1
 * 
 * 注意:
 * 
 * words的长度在[1, 15000]之间。
 * 
 * 对于每个测试用例，最多会有words.length次对WordFilter.f的调用。
 * 
 * words[i]的长度在[1, 10]之间。
 * 
 * prefix, suffix的长度在[0, 10]之前。
 * 
 * words[i]和prefix, suffix只包含小写字母。
 * 
 * @author ffj
 *
 */
public class PrefixAndSuffixSearch {
	/**
	 * Your WordFilter object will be instantiated and called as such: WordFilter
	 * obj = new WordFilter(words); int param_1 = obj.f(prefix,suffix);
	 */
	private String[] words;

	public PrefixAndSuffixSearch(String[] words) {
		this.words = words;
	}

	public int f(String prefix, String suffix) {
		if (words != null && words.length > 0) {
			for (int i = words.length - 1; i >= 0; i--) {
				if (words[i].startsWith(prefix) && words[i].endsWith(suffix)) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * 用一个HashMap存储
	 * 
	 * @author ffj
	 *
	 */
	class WordFilter {
		Map<String, Integer> map = new HashMap<>();

		public WordFilter(String[] words) {
			for (int w = 0; w < words.length; w++) {
				for (int i = 0; i <= 10 && i <= words[w].length(); i++) {
					for (int j = 0; j <= 10 && j <= words[w].length(); j++) {
						map.put(words[w].substring(0, i) + "#" + words[w].substring(words[w].length() - j), w);
					}
				}
			}
		}

		public int f(String prefix, String suffix) {
			return (map.containsKey(prefix + "#" + suffix)) ? map.get(prefix + "#" + suffix) : -1;
		}
	}

	/**
	 * 两个HashMap存储
	 * 
	 * @author ffj
	 *
	 */
	class WordFilter1 {
		HashMap<String, List<Integer>> mapPrefix = new HashMap<>();
		HashMap<String, List<Integer>> mapSuffix = new HashMap<>();

		public WordFilter1(String[] words) {

			for (int w = 0; w < words.length; w++) {
				for (int i = 0; i <= 10 && i <= words[w].length(); i++) {
					String s = words[w].substring(0, i);
					if (!mapPrefix.containsKey(s))
						mapPrefix.put(s, new ArrayList<>());
					mapPrefix.get(s).add(w);
				}
				for (int i = 0; i <= 10 && i <= words[w].length(); i++) {
					String s = words[w].substring(words[w].length() - i);
					if (!mapSuffix.containsKey(s))
						mapSuffix.put(s, new ArrayList<>());
					mapSuffix.get(s).add(w);
				}
			}
		}

		public int f(String prefix, String suffix) {

			if (!mapPrefix.containsKey(prefix) || !mapSuffix.containsKey(suffix))
				return -1;
			List<Integer> p = mapPrefix.get(prefix);
			List<Integer> s = mapSuffix.get(suffix);
			int i = p.size() - 1, j = s.size() - 1;
			while (i >= 0 && j >= 0) {
				if (p.get(i) < s.get(j))
					j--;
				else if (p.get(i) > s.get(j))
					i--;
				else
					return p.get(i);
			}
			return -1;
		}
	}
}