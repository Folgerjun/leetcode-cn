package com.leetcode_cn.easy;

/*******************岛屿的周长**************/
/**
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0
 * 表示水域。网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。岛屿中没有“湖”（“湖”
 * 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * 
 * 示例 :
 * 
 * [[0,1,0,0], [1,1,1,0], [0,1,0,0], [1,1,0,0]]
 * 
 * 答案: 16
 * 
 * 解释: 它的周长是下面图片中的 16 个黄色的边：
 * 
 * @author ffj
 *
 */
public class IslandPerimeter {

	public int islandPerimeter(int[][] grid) {

		int rows = grid.length, cols = grid[0].length, sum = 0;

		// 表示周围四个点
		int[][] arr = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (grid[row][col] == 1) {
					// 陆地
					int num = 4;
					for (int[] ar : arr) {
						int R = row + ar[0], C = col + ar[1];
						// 四周有一块相邻陆地就减一条边
						if (R < rows && R >= 0 && C < cols && C >= 0 && grid[R][C] == 1)
							num--;
					}
					sum += num;
				}
			}
		}
		return sum;

	}

	/**
	 * 讨论中解法
	 * 
	 * @param grid
	 * @return
	 */
	public int islandPerimeter1(int[][] grid) {
		int islands = 0, neighbours = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					islands++; // count islands
					if (i < grid.length - 1 && grid[i + 1][j] == 1)
						neighbours++; // count down neighbours
					if (j < grid[i].length - 1 && grid[i][j + 1] == 1)
						neighbours++; // count right neighbours
				}
			}
		}

		return islands * 4 - neighbours * 2;
	}

}
