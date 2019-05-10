package com.leetcode_cn.medium;

/*************颜色分类***********/
/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 
 * 注意: 不能使用代码库中的排序函数来解决这道题。
 * 
 * 示例:
 * 
 * 输入: [2,0,2,1,1,0]
 * 
 * 输出: [0,0,1,1,2,2]
 * 
 * 进阶：
 * 
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * 
 * @author ffj
 *
 */
public class SortColors {

	public static void main(String[] args) {
		int[] nums = { 2, 0, 2, 1, 1, 0 };
		new SortColors().sortColors(nums);
	}

	/**
	 * 两遍循环
	 * 
	 * @param nums
	 */
	public void sortColors(int[] nums) {
		// 统计出现个数
		int[] numbers = new int[3];
		for (int num : nums)
			numbers[num] += 1;
		int index = 0;
		for (int i = 0; i < numbers.length; i++) {
			while (numbers[i] != 0) {
				nums[index++] = i;
				numbers[i] -= 1;
			}
		}
	}

	/**
	 * 三路快排 0放最前/2放最后/1不变
	 * 
	 * @param nums
	 */
	public void sortColors1(int[] nums) {

		int len = nums.length;
		if (len < 1)
			return;
		// left：0放置的下标/right：1放置的下标/index：循环进行中的下标
		int left = 0, index = 0, right = len - 1;
		while (index <= right) {
			// 1 不变继续遍历
			if (index < left || nums[index] == 1)
				index++;
			else if (nums[index] == 0)
				swap(nums, left++, index);
			else if (nums[index] == 2)
				swap(nums, right--, index);
		}
	}

	/**
	 * 两两值交换
	 * 
	 * @param nums
	 * @param i
	 * @param j
	 */
	private void swap(int[] nums, int i, int j) {
		if (i == j)
			return;
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

}
