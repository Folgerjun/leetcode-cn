package com.leetcode_cn.easy;

import java.util.Arrays;
import java.util.List;

/****************杨辉三角 II**************/
/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * 
 * 
 * 
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 
 * 示例:
 * 
 * 输入: 3
 * 
 * 输出: [1,3,3,1]
 * 
 * @author ffj
 *
 */
public class PascalsTriangleII {

	public static void main(String[] args) {
		System.out.println(new PascalsTriangleII().getRow(3));
	}

	// i=0: [1]
	// i=1: [1,1]
	// i=2: [1,1,1] -> [1,2,1]
	// i=3: [1,2,1,1] -> [1,2,3,1] -> [1,3,3,1]
	// i=4: [1,3,3,1,1] -> [1,3,3,4,1] -> [1,3,6,4,1] -> [1,4,6,4,1]
	public List<Integer> getRow(int rowIndex) {
		Integer[] row = new Integer[rowIndex + 1];
		Arrays.fill(row, 1);
		for (int i = 0; i <= rowIndex; i++) {
			for (int j = i - 1; j >= 1; j--) {
				row[j] += row[j - 1];
			}
		}
		return Arrays.asList(row);
	}

}
