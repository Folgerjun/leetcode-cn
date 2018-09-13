package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.List;

/******************汇总区间************/
/**
 * 给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。
 * 
 * 示例 1:
 * 
 * 输入: [0,1,2,4,5,7]
 * 
 * 输出: ["0->2","4->5","7"]
 * 
 * 解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
 * 
 * 示例 2:
 * 
 * 输入: [0,2,3,4,6,8,9]
 * 
 * 输出: ["0","2->4","6","8->9"]
 * 
 * 解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。
 * 
 * @author ffj
 *
 */
public class SummaryRanges {

	/**
	 * 循环分区 开始点和结束点
	 * 
	 * @param nums
	 * @return
	 */
	public List<String> summaryRanges(int[] nums) {

		List<String> list = new ArrayList<>();
		int index = 0;
		if (nums.length > 0) {
			int len = nums.length, start = 0, end = 0;
			while (index < len) {
				start = nums[index];
				while (index < len - 1 && nums[index] + 1 == nums[index + 1])
					index++;
				end = nums[index];
				if (start != end) {
					list.add(start + "->" + end);
				} else {
					list.add(start + "");
				}
				index++;
			}
		}
		return list;
	}

}
