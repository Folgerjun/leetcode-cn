package com.leetcode_cn.medium;

/************************盛最多水的容器*******************/
/**
 * 
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
 * ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 
 * 
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 
 * 
 * 示例:
 * 
 * 输入: [1,8,6,2,5,4,8,3,7] 输出: 49
 * 
 * @author ffj
 *
 */
public class ContainerWithMostWater {

	public int maxArea(int[] height) {
		int length = height.length;
		int maxArea = 0;
		for (int i = 0; i < length - 1; i++) {
			for (int j = i + 1; j < length; j++) {
				maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
			}
		}
		return maxArea;
	}

	/**
	 * 官网解法 双指针法
	 * 
	 * @param height
	 * @return
	 */
	public int maxArea1(int[] height) {
		int maxarea = 0, l = 0, r = height.length - 1;
		while (l < r) {
			maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
			// 移动较为矮的一方 用高度弥补长度
			if (height[l] < height[r])
				l++;
			else
				r--;
		}
		return maxarea;
	}
}
