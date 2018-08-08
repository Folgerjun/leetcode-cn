package com.leetcode_cn.medium;

/************************矩形面积******************/
/**
 * 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。
 * 
 * 每个矩形由其左下顶点和右上顶点坐标表示，如图所示。
 * 
 * Rectangle Area
 * 
 * 示例:
 * 
 * 输入: -3, 0, 3, 4, 0, -1, 9, 2 输出: 45
 * 
 * 说明: 假设矩形面积不会超出 int 的范围。
 * 
 * @author ffj
 *
 */
public class RectangleArea {

	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {

		int sum = (C - A) * (D - B) + (H - F) * (G - E); // 两个矩形面积之和

		if (E >= C || H <= B || G <= A || F >= D)
			return sum; // 两个矩形没有相交

		return sum - ((Math.min(G, C) - Math.max(A, E)) * (Math.min(D, H) - Math.max(B, F))); // 减去相交的面积
	}

}
