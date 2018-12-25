package com.leetcode_cn.hard;

import java.util.HashMap;
import java.util.Map;

/*************直线上最多的点数***********/
/**
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 * 
 * 示例 1:
 * 
 * 输入: [[1,1],[2,2],[3,3]]
 * 
 * 输出: 3
 * 
 * 解释:
 * 
 * ^
 * 
 * |
 * 
 * |--------o
 * 
 * |-----o
 * 
 * |--o
 * 
 * +------------->
 * 
 * 0 1 2 3 4
 * 
 * 示例 2:
 * 
 * 输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 
 * 输出: 4
 * 
 * 解释:
 * 
 * ^
 * 
 * |
 * 
 * |--o
 * 
 * |-----o--------o
 * 
 * |--------o
 * 
 * |--o--------o
 * 
 * +------------------->
 * 
 * 0 1 2 3 4 5 6
 * 
 * @author ffj
 *
 */
public class MaxPointsOnALine {

	class Point {
		int x;
		int y;

		Point() {
			x = 0;
			y = 0;
		}

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	public static void main(String[] args) {
		int result = new MaxPointsOnALine().generateGCD(6, 8);
		System.out.println("result:" + result);
	}

	/**
	 * 计算两个数的最大公约数
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private int generateGCD(int a, int b) {

		if (b == 0)
			return a;
		else
			return generateGCD(b, a % b);

	}

	/**
	 * 直线上最多的点数 通过两两连线来计算
	 * 
	 * @param points
	 * @return
	 */
	public int maxPoints(Point[] points) {
		if (points == null)
			return 0;
		if (points.length <= 2)
			return points.length;

		Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
		int result = 0;
		for (int i = 0; i < points.length; i++) { // 以该点作为参照点
			map.clear();
			int overlap = 0, max = 0;
			for (int j = i + 1; j < points.length; j++) {
				int x = points[j].x - points[i].x;
				int y = points[j].y - points[i].y;
				if (x == 0 && y == 0) { // 相同的点
					overlap++;
					continue;
				}
				int gcd = generateGCD(x, y);
				if (gcd != 0) {
					x /= gcd;
					y /= gcd;
				}
				if (map.containsKey(x)) {
					if (map.get(x).containsKey(y)) {
						map.get(x).put(y, map.get(x).get(y) + 1);
					} else {
						map.get(x).put(y, 1);
					}
				} else {
					Map<Integer, Integer> m = new HashMap<Integer, Integer>();
					m.put(y, 1);
					map.put(x, m);
				}
				// 该点在其中的直线中最多点
				max = Math.max(max, map.get(x).get(y));
			}
			// 相同斜率的点加上相同点
			result = Math.max(result, max + overlap + 1);
		}
		return result;
	}

}
