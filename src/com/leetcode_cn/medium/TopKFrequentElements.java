package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*************************前K个高频元素****************/
/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 
 * 示例 1:
 * 
 * 输入: nums = [1,1,1,2,2,3], k = 2 输出: [1,2]
 * 
 * 示例 2:
 * 
 * 输入: nums = [1], k = 1 输出: [1]
 * 
 * 说明：
 * 
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 
 * @author ffj
 *
 */
public class TopKFrequentElements {

	public static void main(String[] args) {
		int[] nums = { 1 };
		int k = 1;
		System.out.println(topKFrequent(nums, k));
	}

	public static List<Integer> topKFrequent(int[] nums, int k) {
		List<Integer> list = new ArrayList<>();
		if (nums.length == 1) {
			list.add(nums[0]);
			return list;
		}
		Map<Integer, List<Integer>> map = new HashMap<>(); // key：出现次数 value：相同次数的数值集合
		Set<Integer> set = new TreeSet<>(new MyComparator()); // 出现次数
		Arrays.sort(nums);
		int cnt = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i - 1]) {
				map.computeIfAbsent(cnt, x -> new ArrayList<>()).add(nums[i - 1]);
				set.add(cnt);
				cnt = 1;

			} else
				cnt++;

			if (i == nums.length - 1) { // 考虑最后一个元素情况下
				map.computeIfAbsent(cnt, x -> new ArrayList<>()).add(nums[i]);
				set.add(cnt);
			}
		}
		System.out.println("set :" + set);
		System.out.println("map :" + map);
		for (int num : set) {
			List<Integer> numList = map.get(num);
			for (int i : numList) {
				list.add(i);
				if (--k == 0)
					return list;
			}
		}
		return list;
	}
}

class MyComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer o1, Integer o2) {
		return o2.compareTo(o1);// 降序排列
	}

	/**
	 * 讨论中解法
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public List<Integer> topKFrequent(int[] nums, int k) {

		@SuppressWarnings("unchecked")
		List<Integer>[] bucket = new List[nums.length + 1];
		Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

		for (int n : nums) {
			frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
		}

		for (int key : frequencyMap.keySet()) {
			int frequency = frequencyMap.get(key);
			if (bucket[frequency] == null) {
				bucket[frequency] = new ArrayList<>();
			}
			bucket[frequency].add(key);
		}

		List<Integer> res = new ArrayList<>();

		for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
			if (bucket[pos] != null) {
				res.addAll(bucket[pos]);
			}
		}
		return res;
	}

}