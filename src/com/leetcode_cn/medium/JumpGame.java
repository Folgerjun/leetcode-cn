package com.leetcode_cn.medium;

/*************跳跃游戏***********/
/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 
 * 判断你是否能够到达最后一个位置。
 * 
 * 示例 1:
 * 
 * 输入: [2,3,1,1,4] 输出: true
 * 
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 * 
 * 示例 2:
 * 
 * 输入: [3,2,1,0,4] 输出: false
 * 
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 * 
 * @author ffj
 *
 */
public class JumpGame {

	public static void main(String[] args) {
		int[] nums = { 5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0 };
		System.out.println(new JumpGame().canJump(nums));
	}

	public boolean canJump(int[] nums) {

		if (nums == null || nums.length == 0)
			return true;
		int curMax = nums[0]; // 能到达的最远下标点
		for (int i = 1; i < nums.length; i++) {
			if (curMax < i)
				return false; // mean we are not able to reach position i
			curMax = Math.max(curMax, i + nums[i]);
		}
		return true;
	}

	/**
	 * 双指针法
	 * 
	 * @param nums
	 * @return
	 */
	public boolean canJump1(int[] nums) {
		if (nums == null || nums.length == 0)
			return true;

		return helper(0, nums[0], nums);

	}

	private boolean helper(int start, int end, int[] nums) {
		if (end >= nums.length - 1)
			return true;
		if (end == start)
			return false;
		int father = end;
		for (int i = start; i <= end; i++) {
			father = Math.max(father, nums[i] + i);
		}
		return helper(end, father, nums);
	}

}
