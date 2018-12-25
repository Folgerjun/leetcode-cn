package com.leetcode_cn.hard;

import java.util.Stack;

/*************接雨水***********/
/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 
 * 
 * 
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢
 * Marcos 贡献此图。
 * 
 * 示例:
 * 
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1] 输出: 6
 * 
 * @author ffj
 *
 */
public class TrappingRainWater {

	public static void main(String[] args) {
		int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		int result = new TrappingRainWater().trap(height);
		System.out.println("result :" + result);
	}

	public int trap(int[] height) {

		// 无法积水
		if (height == null || height.length < 2)
			return 0;

		Stack<Integer> stack = new Stack<>();
		int water = 0, i = 0;
		while (i < height.length) {
			// 栈为空或是下个值小于当前值 推入栈中
			if (stack.isEmpty() || height[i] <= height[stack.peek()]) {
				stack.push(i++);
			} else {
				int pre = stack.pop();
				if (!stack.isEmpty()) { // 计算此区域中积水的面积
					// find the smaller height between the two sides
					int minHeight = Math.min(height[stack.peek()], height[i]);
					// calculate the area 作累加
					water += (minHeight - height[pre]) * (i - stack.peek() - 1);
				}
			}
		}
		return water;
	}

}
