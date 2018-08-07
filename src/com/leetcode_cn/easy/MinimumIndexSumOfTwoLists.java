package com.leetcode_cn.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/************************两个列表的最小索引总和******************/
/**
 * 假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * 
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。
 * 
 * 示例 1:
 * 
 * 输入: ["Shogun", "Tapioca Express", "Burger King", "KFC"] ["Piatti", "The Grill
 * at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"] 输出: ["Shogun"]
 * 
 * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
 * 
 * 示例 2:
 * 
 * 输入: ["Shogun", "Tapioca Express", "Burger King", "KFC"] ["KFC", "Shogun",
 * "Burger King"] 输出: ["Shogun"]
 * 
 * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
 * 
 * 提示:
 * 
 * 两个列表的长度范围都在 [1, 1000]内。
 * 
 * 两个列表中的字符串的长度将在[1，30]的范围内。
 * 
 * 下标从0开始，到列表的长度减1。
 * 
 * 两个列表都没有重复的元素。
 * 
 * @author ffj
 *
 */
public class MinimumIndexSumOfTwoLists {

	public String[] findRestaurant(String[] list1, String[] list2) {

		int index = 0; // 从0开始计数
		boolean flag = false;
		List<String> result = new ArrayList<>();

		while (!flag && index < list1.length + list2.length - 1) {
			for (int i = 0; i <= index; i++) {
				int j = index - i;
				if (i < list1.length && j < list2.length && list1[i].equals(list2[j])) { // 只要满足条件了就带值返回 即最小索引
					result.add(list1[i]);
					flag = true;
				}
			}

			index++;
		}

		return result.toArray(new String[result.size()]);
	}

	/**
	 * 官网解法 hashmap
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public String[] findRestaurant1(String[] list1, String[] list2) {
		HashMap<Integer, List<String>> map = new HashMap<>();
		for (int i = 0; i < list1.length; i++) {
			for (int j = 0; j < list2.length; j++) {
				if (list1[i].equals(list2[j])) { // 找到相等的就放进map中
					if (!map.containsKey(i + j))
						map.put(i + j, new ArrayList<String>());
					map.get(i + j).add(list1[i]);
				}
			}
		}
		int min_index_sum = Integer.MAX_VALUE;
		for (int key : map.keySet()) // 遍历map key 找出最小值
			min_index_sum = Math.min(min_index_sum, key);
		String[] res = new String[map.get(min_index_sum).size()];
		return map.get(min_index_sum).toArray(res); // 返回其value值
	}

	/**
	 * 官网解法 hashmap 与上类似
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public String[] findRestaurant2(String[] list1, String[] list2) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < list1.length; i++)
			map.put(list1[i], i);
		List<String> res = new ArrayList<>();
		int min_sum = Integer.MAX_VALUE, sum;
		for (int j = 0; j < list2.length && j <= min_sum; j++) {
			if (map.containsKey(list2[j])) {
				sum = j + map.get(list2[j]);
				if (sum < min_sum) { // 发现更小的数就将之前的值清空
					res.clear();
					res.add(list2[j]); // 放入新的值
					min_sum = sum;
				} else if (sum == min_sum)
					res.add(list2[j]); // 相同索引值就加进去
			}
		}
		return res.toArray(new String[res.size()]);
	}
}
