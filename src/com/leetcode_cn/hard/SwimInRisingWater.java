package com.leetcode_cn.hard;

import java.util.HashSet;
import java.util.Set;

/********************水位上升的泳池中游泳**************/
/**
 * 
 * 在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。
 * 
 * 现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t
 * 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。
 * 
 * 你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？
 * 
 * 示例 1:
 * 
 * 输入: [[0,2],[1,3]]
 * 
 * 输出: 3
 * 
 * 解释:
 * 
 * 时间为0时，你位于坐标方格的位置为 (0, 0)。
 * 
 * 此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。
 * 
 * 等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
 * 
 * 示例2:
 * 
 * 输入:
 * [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * 
 * 输入: 16
 * 
 * 解释:
 * 
 * 0 1 2 3 4
 * 
 * 24 23 22 21 5
 * 
 * 12 13 14 15 16
 * 
 * 11 17 18 19 20
 * 
 * 10 9 8 7 6
 * 
 * 最终的路线用加粗进行了标记。
 * 
 * 我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
 * 
 * 提示:
 * 
 * 2 <= N <= 50.
 * 
 * grid[i][j] 位于区间 [0, ..., N*N - 1] 内。
 * 
 * @author ffj
 *
 */
public class SwimInRisingWater {
	int min_time = Integer.MAX_VALUE;

	/**
	 * DFS
	 * 
	 * @param grid
	 * @return
	 */
	public int swimInWater(int[][] grid) {
		int time = 0;
		int N = grid.length;
		Set<Integer> visited = new HashSet<>(); // 记录已经走过的位置
		while (!visited.contains(N * N - 1)) {
			visited.clear(); // 每一次清空
			dfs(grid, 0, 0, time, visited);
			time++;
		}
		return time - 1;
	}

	int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	private void dfs(int[][] grid, int i, int j, int time, Set<Integer> visited) {
		if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] > time
				|| visited.contains(i * grid.length + j))
			return;
		visited.add(i * grid.length + j); // 保证唯一性
		for (int[] dir : dirs) { // 周围四个点
			dfs(grid, i + dir[0], j + dir[1], time, visited);
		}
	}

	class UF {
		int[] id;

		public UF(int N) {
			id = new int[N];
			for (int i = 0; i < N; i++) {
				id[i] = i;
			}
		}

		public int root(int i) {
			while (i != id[i]) {
				id[i] = id[id[i]];
				i = id[i];
			}
			return i;
		}

		public boolean isConnected(int p, int q) {
			return root(p) == root(q);
		}

		public void union(int p, int q) {
			if (isConnected(p, q))
				return;
			id[root(p)] = root(q);
		}
	}

	/**
	 * Union Find
	 * 
	 * @param grid
	 * @return
	 */
	public int swimInWater1(int[][] grid) {
		int N = grid.length;
		UF uf = new UF(N * N);
		int time = 0;
		while (!uf.isConnected(0, N * N - 1)) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (grid[i][j] > time)
						continue;
					if (i < N - 1 && grid[i + 1][j] <= time)
						uf.union(i * N + j, i * N + j + N);
					if (j < N - 1 && grid[i][j + 1] <= time)
						uf.union(i * N + j, i * N + j + 1);
				}
			}
			time++;
		}
		return time - 1;
	}
}
