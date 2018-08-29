package com.leetcode_cn.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**************************公交路线******************/
/**
 * 我们有一系列公交路线。每一条路线 routes[i] 上都有一辆公交车在上面循环行驶。例如，有一条路线 routes[0] = [1, 5,
 * 7]，表示第一辆 (下标为0) 公交车会一直按照 1->5->7->1->5->7->1->... 的车站路线行驶。
 * 
 * 假设我们从 S 车站开始（初始时不在公交车上），要去往 T 站。 期间仅可乘坐公交车，求出最少乘坐的公交车数量。返回 -1 表示不可能到达终点车站。
 * 
 * 示例:
 * 
 * 输入: routes = [[1, 2, 7], [3, 6, 7]] S = 1 T = 6
 * 
 * 输出: 2
 * 
 * 解释: 最优策略是先乘坐第一辆公交车到达车站 7, 然后换乘第二辆公交车到车站 6。
 * 
 * 说明:
 * 
 * 1 <= routes.length <= 500.
 * 
 * 1 <= routes[i].length <= 500.
 * 
 * 0 <= routes[i][j] < 10 ^ 6.
 * 
 * @author ffj
 *
 */
public class BusRoutes {

	public static void main(String[] args) {
		int[][] routes = { { 1, 2, 7 }, { 3, 6, 7 } };
		int S = 1, T = 6;
		System.out.println(new BusRoutes().numBusesToDestination(routes, S, T));
	}

	public int numBusesToDestination(int[][] routes, int S, int T) {
		// 相同无需转乘
		if (S == T)
			return 0;
		// key : 站牌号, value : 公交序号
		Map<Integer, Set<Integer>> map = new HashMap<>();
		int len = routes.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < routes[i].length; j++) {
				map.putIfAbsent(routes[i][j], new HashSet<>());
				map.get(routes[i][j]).add(i);
			}
		}

		Queue<Integer> que1 = new LinkedList<>();
		Queue<Integer> que2 = new LinkedList<>();
		// 存放已经访问过的站
		Set<Integer> visited = new HashSet<>();
		int steps = 1;
		helper(routes, S, map, que1, visited);
		while (!que1.isEmpty()) {
			int stop = que1.poll();
			if (stop == T)
				return steps;

			helper(routes, stop, map, que2, visited);

			if (que1.isEmpty()) { // que1队列中全部搜索完毕后 搜索que2队列中的元素
				que1 = que2;
				que2 = new LinkedList<>();
				steps++; // 换乘数加一
			}
		}
		return -1;
	}

	/**
	 * BFS 广度优先搜索 车站相关联
	 * 
	 * @param routes
	 * @param stop
	 * @param map
	 * @param que
	 * @param visited
	 */
	private void helper(int[][] routes, int stop, Map<Integer, Set<Integer>> map, Queue<Integer> que,
			Set<Integer> visited) {

		for (int route : map.get(stop)) {
			for (int s : routes[route]) {
				if (!visited.contains(s)) {
					que.offer(s);
					visited.add(s);
				}
			}
		}

	}

}
