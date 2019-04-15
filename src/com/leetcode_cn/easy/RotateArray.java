package com.leetcode_cn.easy;

/**************旋转数组***********/
/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 
 * 示例 1:
 * 
 * 输入: [1,2,3,4,5,6,7] 和 k = 3 输出: [5,6,7,1,2,3,4]
 * 
 * 解释: 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 
 * 示例 2:
 * 
 * 输入: [-1,-100,3,99] 和 k = 2
 * 
 * 输出: [3,99,-1,-100]
 * 
 * 解释: 向右旋转 1 步: [99,-1,-100,3]
 * 
 * 向右旋转 2 步: [3,99,-1,-100]
 * 
 * 说明:
 * 
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 
 * 要求使用空间复杂度为 O(1) 的原地算法。
 * 
 * @author ffj
 *
 */
public class RotateArray {

	/**
	 * 循环赋值（参考 Collections源码 rotate1 方法）
	 * 
	 * @param nums
	 * @param k
	 */
	public void rotate(int[] nums, int k) {
		int len = nums.length;
		int offset = k % len;
		if (offset < 0)
			offset += len;
		if (offset == 0)
			return;
		for (int i = 0, nMoved = 0; nMoved != len; i++) {
			int value = nums[i];
			int index = i;
			do {
				// 下标渐移
				index += offset;
				if (index >= len)
					index -= len;
				// 值交换
				int temp = nums[index];
				nums[index] = value;
				value = temp;
				// 记录改变的值数量
				nMoved++;
			} while (index != i);
		}
	}
}
