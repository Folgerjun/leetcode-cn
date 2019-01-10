package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**************字母异位词分组**********/
/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 
 * 示例:
 * 
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 
 * 输出: [ ["ate","eat","tea"], ["nat","tan"], ["bat"] ]
 * 
 * 说明：
 * 
 * 所有输入均为小写字母。
 * 
 * 不考虑答案输出的顺序。
 * 
 * @author ffj
 *
 */
public class GroupAnagrams {

	public List<List<String>> groupAnagrams(String[] strs) {
		// key:排序后的str value:str
		Map<String, List<String>> map = new HashMap<>();
		Arrays.asList(strs).forEach(str -> {
			char[] chArr = str.toCharArray();
			// 按字母排序
			Arrays.sort(chArr);
			String sortStrKey = String.valueOf(chArr);
			map.computeIfAbsent(sortStrKey, v -> new ArrayList<>()).add(str);
		});
		return new ArrayList<>(map.values());
	}

}
