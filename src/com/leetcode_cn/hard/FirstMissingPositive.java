package com.leetcode_cn.hard;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/******************缺失的第一个正数**************/
/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * 
 * 示例 1:
 * 
 * 输入: [1,2,0] 输出: 3
 * 
 * 示例 2:
 * 
 * 输入: [3,4,-1,1] 输出: 2
 * 
 * 示例 3:
 * 
 * 输入: [7,8,9,11,12] 输出: 1
 * 
 * 说明:
 * 
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 * 
 * @author ffj
 *
 */
public class FirstMissingPositive {

	public static void main(String[] args) {
		int[] nums = { 7, 8, 9, 11, 12 };
		int result = new FirstMissingPositive().firstMissingPositive(nums);
		System.out.println("缺失：" + result);
	}

	public int firstMissingPositive(int[] nums) {

		// 设置初始值容量为 10
		String[] collectNums = new String[10];
		for (int num : nums) {
			if (num > 0) {
				if (num >= collectNums.length) {
					// 复制进行扩容
					String[] tempStrArr = new String[num + 1];
					System.arraycopy(collectNums, 0, tempStrArr, 0, collectNums.length);
					collectNums = tempStrArr;
				}
				// 是正数 value 置 1
				collectNums[num] = "1";
			}
		}
		List<String> collectList = Stream.of(collectNums).collect(Collectors.toList());
		// 删除第一个下标元素
		collectList.remove(0);
		int result = collectList.indexOf(null) + 1;
		return result == 0 ? collectList.size() + 1 : result;

	}

	/**
	 * 官网讨论区中解法：
	 * 
	 * 1.先标识哪些是有效数字哪些是无效数字
	 * 
	 * 2.在该数组中操作标识已有的数字
	 * 
	 * 3.找到未标识的数字 返回对应值
	 * 
	 * @param nums
	 * @return
	 */
	public int firstMissingPositive1(int[] nums) {
		int n = nums.length;

		// 1. mark numbers (num < 0) and (num > n) with a special marker number (n+1)
		// (we can ignore those because if all number are > n then we'll simply return
		// 1)
		for (int i = 0; i < n; i++) { // 将其中小于等于 0 的和大于该数组长度的值都设置为该数组长度加 1，为了下一步方便操作剔除无效值
			if (nums[i] <= 0 || nums[i] > n) {
				nums[i] = n + 1;
			}
		}
		// note: all number in the array are now positive, and on the range 1..n+1

		// 2. mark each cell appearing in the array, by converting the index for that
		// number to negative
		for (int i = 0; i < n; i++) {
			int num = Math.abs(nums[i]);
			if (num > n) { // 该步即剔除无效值
				continue;
			}
			// 说明该数字必在该数组长度中，转换为下标
			num--; // -1 for zero index based array (so the number 1 will be at pos 0)
			if (nums[num] > 0) { // prevents double negative operations
				// 将该下标的值标识 表示该数已存在
				nums[num] = -1 * nums[num];
			}
		}

		// 3. find the first cell which isn't negative (doesn't appear in the array)
		for (int i = 0; i < n; i++) { // 找到未标识的数字 返回对应值
			if (nums[i] >= 0) {
				return i + 1;
			}
		}

		// 4. no positive numbers were found, which means the array contains all numbers
		// 1..n
		// 期间数字都存在就返回最后一个值
		return n + 1;
	}

}
