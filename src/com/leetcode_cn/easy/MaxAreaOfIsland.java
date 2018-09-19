package com.leetcode_cn.easy;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**************岛屿的最大面积**********/
/**
 * 
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地)
 * 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 * 
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 * 
 * 示例 1:
 * 
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0], [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0], [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0], [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0], [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 * 
 * 示例 2:
 * 
 * [[0,0,0,0,0,0,0,0]]
 * 
 * 对于上面这个给定的矩阵, 返回 0。
 * 
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 * 
 * @author ffj
 *
 */
public class MaxAreaOfIsland {

	public static void main(String[] args) {
		int[][] grid = { { 0, 0, 0, 0, 0, 1, 1, 0 }, { 0, 0, 0, 0, 1, 1, 1, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0 } };
		System.out.println(new MaxAreaOfIsland().maxAreaOfIsland(grid));
	}

	public int maxAreaOfIsland(int[][] grid) {

		Set<Integer> visited = new HashSet<>();
		int rows = grid.length, cols = grid[0].length, max_area = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int area = dfs(i, j, grid, visited);
				max_area = Math.max(max_area, area);
				// System.out.println(visited);
			}
		}
		return max_area;

	}

	/**
	 * DFS 返回每个点值为 1 的个数
	 * 
	 * @param i
	 * @param j
	 * @param grid
	 * @param visited
	 * @return
	 */
	private int dfs(int i, int j, int[][] grid, Set<Integer> visited) {
		// 越界或者已经访问过
		if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || !visited.add(i * 10 + j)
				|| grid[i][j] == 0)
			return 0;
		return (1 + dfs(i + 1, j, grid, visited) + dfs(i - 1, j, grid, visited) + dfs(i, j + 1, grid, visited)
				+ dfs(i, j - 1, grid, visited));

	}

	/**
	 * Stack
	 * 
	 * @param grid
	 * @return
	 */
	public int maxAreaOfIsland1(int[][] grid) {
		boolean[][] seen = new boolean[grid.length][grid[0].length];
		int[] dr = new int[] { 1, -1, 0, 0 };
		int[] dc = new int[] { 0, 0, 1, -1 };

		int ans = 0;
		for (int r0 = 0; r0 < grid.length; r0++) {
			for (int c0 = 0; c0 < grid[0].length; c0++) {
				if (grid[r0][c0] == 1 && !seen[r0][c0]) {
					int shape = 0;
					Stack<int[]> stack = new Stack<int[]>();
					stack.push(new int[] { r0, c0 });
					seen[r0][c0] = true;
					while (!stack.empty()) {
						int[] node = stack.pop();
						int r = node[0], c = node[1];
						shape++;
						for (int k = 0; k < 4; k++) {
							int nr = r + dr[k];
							int nc = c + dc[k];
							if (0 <= nr && nr < grid.length && 0 <= nc && nc < grid[0].length && grid[nr][nc] == 1
									&& !seen[nr][nc]) {
								stack.push(new int[] { nr, nc });
								seen[nr][nc] = true;
							}
						}
					}
					ans = Math.max(ans, shape);
				}
			}
		}
		return ans;
	}
}
