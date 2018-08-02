package com.leetcode_cn.medium;

import java.util.Arrays;

/*******************划分为k个相等的子集*****************/
/**
 * 给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * 
 * 示例 1：
 * 
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4 输出： True
 * 
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 * 
 * 
 * 注意:
 * 
 * 1 <= k <= len(nums) <= 16
 * 
 * 0 < nums[i] < 10000
 * 
 * @author ffj
 *
 */
public class PartitionToKEqualSumSubsets {

	public static void main(String[] args) {
		int[] nums = { 4, 15, 1, 1, 1, 1, 3, 11, 1, 10 };
		int k = 3;
		boolean value = canPartitionKSubsets(nums, k);
		System.out.println(value);
	}

	public static boolean search(int[] groups, int row, int[] nums, int target) {
		if (row < 0)
			return true;
		int v = nums[row--];
		for (int i = 0; i < groups.length; i++) {
			if (groups[i] + v <= target) { // 累加
				groups[i] += v;
				if (search(groups, row, nums, target))
					return true;
				groups[i] -= v;
			}
			if (groups[i] == 0)
				break;
		}
		return false;
	}

	public static boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = Arrays.stream(nums).sum(); // 求和
		if (sum % k > 0) // 不能整除直接返回false
			return false;
		int target = sum / k; // 每组的值

		Arrays.sort(nums); // 数组排序
		int row = nums.length - 1; // 最后一个数的下标
		if (nums[row] > target) // 最大数比平均数还大 直接返回false
			return false;
		while (row >= 0 && nums[row] == target) { // 若是存在只可能是在最后
			row--; // 数组个数减一 对应相应最后一个数下标
			k--; // 组数减一
		}
		return search(new int[k], row, nums, target);
	}

	/***********************************************************************/

	enum Result {
		TRUE, FALSE
	}

	boolean search1(int used, int todo, Result[] memo, int[] nums, int target) {
		if (memo[used] == null) {
			memo[used] = Result.FALSE;
			int targ = (todo - 1) % target + 1;
			for (int i = 0; i < nums.length; i++) {
				if ((((used >> i) & 1) == 0) && nums[i] <= targ) {
					if (search1(used | (1 << i), todo - nums[i], memo, nums, target)) {
						memo[used] = Result.TRUE;
						break;
					}
				}
			}
		}
		return memo[used] == Result.TRUE;
	}

	/**
	 * 官网解法
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public boolean canPartitionKSubsets1(int[] nums, int k) {
		int sum = Arrays.stream(nums).sum();
		if (sum % k > 0)
			return false;

		Result[] memo = new Result[1 << nums.length];
		memo[(1 << nums.length) - 1] = Result.TRUE;
		return search1(0, sum, memo, nums, sum / k);
	}

	/****************************************************************************/

	/**
	 * 官网解法
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public boolean canPartitionKSubsets2(int[] nums, int k) {
		int N = nums.length;
		Arrays.sort(nums);
		int sum = Arrays.stream(nums).sum();
		int target = sum / k;
		if (sum % k > 0 || nums[N - 1] > target)
			return false;

		boolean[] dp = new boolean[1 << N];
		dp[0] = true;
		int[] total = new int[1 << N];

		for (int state = 0; state < (1 << N); state++) {
			if (!dp[state])
				continue;
			for (int i = 0; i < N; i++) {
				int future = state | (1 << i);
				if (state != future && !dp[future]) {
					if (nums[i] <= target - (total[state] % target)) {
						dp[future] = true;
						total[future] = total[state] + nums[i];
					} else {
						break;
					}
				}
			}
		}
		return dp[(1 << N) - 1];
	}
}
