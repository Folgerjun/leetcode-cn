package com.leetcode_cn.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/****************分割数组为连续子序列***************/
/**
 * 
 * 输入一个按升序排序的整数数组（可能包含重复数字），你需要将它们分割成几个子序列，其中每个子序列至少包含三个连续整数。返回你是否能做出这样的分割？
 * 
 * 示例 1：
 * 
 * 输入: [1,2,3,3,4,5] 输出: True
 * 
 * 解释:
 * 
 * 你可以分割出这样两个连续子序列 :
 * 
 * 1, 2, 3
 * 
 * 3, 4, 5
 * 
 * 示例 2：
 * 
 * 输入: [1,2,3,3,4,4,5,5] 输出: True
 * 
 * 解释:
 * 
 * 你可以分割出这样两个连续子序列 :
 * 
 * 1, 2, 3, 4, 5
 * 
 * 3, 4, 5
 * 
 * 示例 3：
 * 
 * 输入: [1,2,3,4,4,5] 输出: False
 * 
 * 提示：
 * 
 * 输入的数组长度范围为 [1, 10000]
 * 
 * @author ffj
 *
 */
public class SplitArrayIntoConsecutiveSubsequences {

	/**
	 * 官网解法一
	 * 
	 * @param nums
	 * @return
	 */
	public boolean isPossible(int[] nums) {
		Integer prev = null;
		int prevCount = 0;
		Queue<Integer> starts = new LinkedList<Integer>();
		int anchor = 0;
		for (int i = 0; i < nums.length; ++i) {
			int t = nums[i];
			if (i == nums.length - 1 || nums[i + 1] != t) {
				int count = i - anchor + 1;
				if (prev != null && t - prev != 1) {
					while (prevCount-- > 0)
						if (prev < starts.poll() + 2)
							return false;
					prev = null;
				}

				if (prev == null || t - prev == 1) {
					while (prevCount > count) {
						prevCount--;
						if (t - 1 < starts.poll() + 2)
							return false;
					}
					while (prevCount++ < count)
						starts.add(t);
				}
				prev = t;
				prevCount = count;
				anchor = i + 1;
			}
		}

		while (prevCount-- > 0)
			if (nums[nums.length - 1] < starts.poll() + 2)
				return false;
		return true;
	}

	/**
	 * 官网解法二 贪婪
	 * 
	 * 遍历nums，判断一个元素是否能够插入一个已经构建好的序列的末端，或者是否能作为新序列的起点，如果两者都不可以，返回false。
	 * 
	 * @param nums
	 * @return
	 */
	public boolean isPossible1(int[] nums) {
		Counter count = new Counter();
		Counter tails = new Counter();
		for (int x : nums)
			count.add(x, 1);

		for (int x : nums) {
			if (count.get(x) == 0) {
				continue;
			} else if (tails.get(x) > 0) {
				tails.add(x, -1);
				tails.add(x + 1, 1);
			} else if (count.get(x + 1) > 0 && count.get(x + 2) > 0) {
				count.add(x + 1, -1);
				count.add(x + 2, -1);
				tails.add(x + 3, 1);
			} else {
				return false;
			}
			count.add(x, -1);
		}
		return true;
	}
}

class Counter extends HashMap<Integer, Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3539403566703530463L;

	public int get(int k) {
		return containsKey(k) ? super.get(k) : 0;
	}

	public void add(int k, int v) {
		put(k, get(k) + v);
	}
}
