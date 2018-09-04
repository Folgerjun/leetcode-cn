package com.leetcode_cn.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*****************掉落的方块**************/
/**
 * 在无限长的数轴（即 x 轴）上，我们根据给定的顺序放置对应的正方形方块。
 * 
 * 第 i 个掉落的方块（positions[i] = (left, side_length)）是正方形，其中 left
 * 表示该方块最左边的点位置(positions[i][0])，side_length 表示该方块的边长(positions[i][1])。
 * 
 * 每个方块的底部边缘平行于数轴（即 x 轴），并且从一个比目前所有的落地方块更高的高度掉落而下。在上一个方块结束掉落，并保持静止后，才开始掉落新方块。
 * 
 * 方块的底边具有非常大的粘性，并将保持固定在它们所接触的任何长度表面上（无论是数轴还是其他方块）。邻接掉落的边不会过早地粘合在一起，因为只有底边才具有粘性。
 * 
 * 
 * 
 * 返回一个堆叠高度列表 ans 。每一个堆叠高度 ans[i] 表示在通过 positions[0], positions[1], ...,
 * positions[i] 表示的方块掉落结束后，目前所有已经落稳的方块堆叠的最高高度。
 * 
 * 
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: [[1, 2], [2, 3], [6, 1]]
 * 
 * 输出: [2, 5, 5]
 * 
 * 解释:
 * 
 * 第一个方块 positions[0] = [1, 2] 掉落：
 * 
 * _aa
 * 
 * _aa
 * 
 * -------
 * 
 * 方块最大高度为 2 。
 * 
 * 第二个方块 positions[1] = [2, 3] 掉落：
 * 
 * __aaa
 * 
 * __aaa
 * 
 * __aaa
 * 
 * _aa__
 * 
 * _aa__
 * 
 * --------------
 * 
 * 方块最大高度为5。
 * 
 * 大的方块保持在较小的方块的顶部，不论它的重心在哪里，因为方块的底部边缘有非常大的粘性。
 * 
 * 第三个方块 positions[1] = [6, 1] 掉落：
 * 
 * __aaa
 * 
 * __aaa
 * 
 * __aaa
 * 
 * _aa
 * 
 * _aa___a
 * 
 * --------------
 * 
 * 方块最大高度为5。
 * 
 * 因此，我们返回结果[2, 5, 5]。
 * 
 * 
 * 示例 2:
 * 
 * 输入: [[100, 100], [200, 100]]
 * 
 * 输出: [100, 100]
 * 
 * 解释: 相邻的方块不会过早地卡住，只有它们的底部边缘才能粘在表面上。
 * 
 * 
 * 注意:
 * 
 * 1 <= positions.length <= 1000.
 * 
 * 1 <= positions[i][0] <= 10^8.
 * 
 * 1 <= positions[i][1] <= 10^6.
 * 
 * @author ffj
 *
 */
public class FallingSquares {

	/**
	 * 官网解法一 离线传播
	 * 
	 * @param positions
	 * @return
	 */
	public List<Integer> fallingSquares(int[][] positions) {

		int[] qans = new int[positions.length];
		for (int i = 0; i < positions.length; i++) {
			int left = positions[i][0];
			int size = positions[i][1];
			int right = left + size;
			qans[i] += size;

			for (int j = i + 1; j < positions.length; j++) {
				int left2 = positions[j][0];
				int size2 = positions[j][1];
				int right2 = left2 + size2;
				if (left2 < right && left < right2) { // intersect
					qans[j] = Math.max(qans[j], qans[i]);
				}
			}
		}

		List<Integer> ans = new ArrayList<Integer>();
		int cur = -1;
		for (int x : qans) {
			cur = Math.max(cur, x);
			ans.add(cur);
		}
		return ans;

	}

	/******************************************************/

	int[] heights;

	public int query(int L, int R) {
		int ans = 0;
		for (int i = L; i <= R; i++) {
			ans = Math.max(ans, heights[i]);
		}
		return ans;
	}

	public void update(int L, int R, int h) {
		for (int i = L; i <= R; i++) {
			heights[i] = Math.max(heights[i], h);
		}
	}

	/**
	 * 官网解法二 压缩坐标抽
	 * 
	 * @param positions
	 * @return
	 */
	public List<Integer> fallingSquares1(int[][] positions) {
		// Coordinate Compression
		Set<Integer> coords = new HashSet<Integer>();
		for (int[] pos : positions) {
			coords.add(pos[0]);
			coords.add(pos[0] + pos[1] - 1);
		}
		List<Integer> sortedCoords = new ArrayList<Integer>(coords);
		Collections.sort(sortedCoords);

		Map<Integer, Integer> index = new HashMap<Integer, Integer>();
		int t = 0;
		for (int coord : sortedCoords)
			index.put(coord, t++);

		heights = new int[t];
		int best = 0;
		List<Integer> ans = new ArrayList<Integer>();

		for (int[] pos : positions) {
			int L = index.get(pos[0]);
			int R = index.get(pos[0] + pos[1] - 1);
			int h = query(L, R) + pos[1];
			update(L, R, h);
			best = Math.max(best, h);
			ans.add(best);
		}
		return ans;
	}

	/******************************************************/
	/**
	 * 更多解法详情：https://leetcode.com/problems/falling-squares/solution/
	 */
	/******************************************************/
}
