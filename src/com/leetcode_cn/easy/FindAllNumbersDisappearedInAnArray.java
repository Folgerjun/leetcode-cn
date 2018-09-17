package com.leetcode_cn.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***************找到所有数组中消失的数字***************/
/**
 * 给定一个范围在 1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * 
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * 
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * 
 * 示例:
 * 
 * 输入: [4,3,2,7,8,2,3,1]
 * 
 * 输出: [5,6]
 * 
 * @author ffj
 *
 */
public class FindAllNumbersDisappearedInAnArray {

	public static void main(String[] args) {
		int[] nums = { 4, 3, 2, 7, 8, 2, 3, 1 };
		System.out.println(new FindAllNumbersDisappearedInAnArray().findDisappearedNumbers1(nums));
	}

	/**
	 * 超时
	 * 
	 * @param nums
	 * @return
	 */
	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> result = new ArrayList<>();
		if (nums.length == 0)
			return result;
		int len = nums.length;
		// int[] 转 List<Integer>
		List<Integer> numlist = Arrays.stream(nums).boxed().collect(Collectors.toList());
		for (int i = 1; i <= len; i++) {
			if (!numlist.contains(i))
				result.add(i);
		}
		return result;
	}

	/**
	 * 一一对应
	 * 
	 * @param nums
	 * @return
	 */
	public List<Integer> findDisappearedNumbers1(int[] nums) {
		List<Integer> ret = new ArrayList<Integer>();

		// 将对应的值的下标 标记
		for (int i = 0; i < nums.length; i++) {
			int val = Math.abs(nums[i]) - 1;
			if (nums[val] > 0) {
				nums[val] = -nums[val];
			}
		}

		// 没有标记的就说明该值没有
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				ret.add(i + 1);
			}
		}
		return ret;
	}
}
