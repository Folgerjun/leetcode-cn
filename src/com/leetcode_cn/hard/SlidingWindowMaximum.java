package com.leetcode_cn.hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*******************滑动窗口最大值*****************/
/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
 * 
 * 返回滑动窗口最大值。
 * 
 * 示例:
 * 
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3 输出: [3,3,5,5,6,7]
 * 
 * 解释:
 * 
 * ---滑动窗口的位置------最大值
 * 
 * [1 3 -1] -3 5 3 6 7 3
 * 
 * 1 [3 -1 -3] 5 3 6 7 3
 * 
 * 1 3 [-1 -3 5] 3 6 7 5
 * 
 * 1 3 -1 [-3 5 3] 6 7 5
 * 
 * 1 3 -1 -3 [5 3 6] 7 6
 * 
 * 1 3 -1 -3 5 [3 6 7] 7
 * 
 * 注意：
 * 
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 输入数组的大小，且输入数组不为空。
 * 
 * 进阶：
 * 
 * 你能在线性时间复杂度内解决此题吗？
 * 
 * @author ffj
 *
 */
public class SlidingWindowMaximum {

	public static void main(String[] args) {
		int[] nums = { 1, 3 };
		int k = 2;
		System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
	}

	public static int[] maxSlidingWindow(int[] nums, int k) {

		if (nums == null || k <= 0) {
			return new int[0];
		}
		int n = nums.length;
		int[] r = new int[n - k + 1];
		int ri = 0;
		// store index
		Deque<Integer> q = new ArrayDeque<>(); // 双端队列
		for (int i = 0; i < n; i++) {
			// remove numbers out of range k
			while (!q.isEmpty() && q.peek() < i - k + 1) { // 超出个数的从头开始删
				q.poll();
			}
			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) { // 比当前值小的都不要
				q.pollLast();
			}
			// q contains index... r contains content
			q.offer(i);
			if (i >= k - 1) { // 当满足个数时才添加数据
				r[ri++] = nums[q.peek()];
			}
		}
		return r;
	}

}
