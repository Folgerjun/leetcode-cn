package com.leetcode_cn.easy;

/************最大三角形面积*************/
/**
 * 
 * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
 * 
 * 示例:
 * 
 * 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]] 输出: 2
 * 
 * 解释: 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
 * 
 * 注意:
 * 
 * 3 <= points.length <= 50.
 * 
 * 不存在重复的点。
 * 
 * -50 <= points[i][j] <= 50.
 * 
 * 结果误差值在 10^-6 以内都认为是正确答案。
 * 
 * @author ffj
 *
 */
public class LargestTriangleArea {

	public double largestTriangleArea(int[][] points) {
		int N = points.length;
		double ans = 0;
		for (int i = 0; i < N; ++i)
			for (int j = i + 1; j < N; ++j)
				for (int k = j + 1; k < N; ++k)
					// 循环取三个点 取最大值
					ans = Math.max(ans, area(points[i], points[j], points[k]));
		return ans;
	}

	/**
	 * 根据三点计算面积
	 * 
	 * @param P
	 * @param Q
	 * @param R
	 * @return
	 */
	public double area(int[] P, int[] Q, int[] R) {
		return 0.5 * Math.abs(P[0] * Q[1] + Q[0] * R[1] + R[0] * P[1] - P[1] * Q[0] - Q[1] * R[0] - R[1] * P[0]);
	}

}
