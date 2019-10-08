package com.leetcode_cn.medium;

/*************螺旋矩阵 II***********/
/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * 
 * 示例:
 * 
 * 输入: 3 输出: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ] ]
 * 
 * @author ffj
 *
 */
public class SpiralMatrixII {

	public static void main(String[] args) {
		int n = 3;
		System.out.println(new SpiralMatrixII().generateMatrix(n));
	}

	public int[][] generateMatrix(int n) {
		int[][] result = new int[n][n];
		int up = 0, down = n - 1, left = 0, right = n - 1, num = 1;
		while (up <= down || right >= left) {
			if (left > right) // 判断是否越界
				break;
			// left -> right
			for (int i = left; i <= right; i++)
				result[up][i] = num++;
			right--;
			if ((up + 1) > down)
				break;
			// up -> down
			for (int i = up + 1; i <= down; i++)
				result[i][right + 1] = num++;
			down--;
			if (left > right) // 判断是否越界
				break;
			// right -> left
			for (int i = right; i >= left; i--)
				result[down + 1][i] = num++;
			left++;
			if ((up + 1) > down)
				break;
			// down -> up
			for (int i = down; i >= up + 1; i--)
				result[i][left - 1] = num++;
			up++;
		}
		return result;
	}

}
