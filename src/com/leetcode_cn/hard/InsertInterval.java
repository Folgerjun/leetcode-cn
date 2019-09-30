package com.leetcode_cn.hard;

import java.util.Arrays;

/******************插入区间***********/
/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 
 * 示例 1:
 * 
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 
 * 输出: [[1,5],[6,9]]
 * 
 * 示例 2:
 * 
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 
 * 输出: [[1,2],[3,10],[12,16]]
 * 
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 
 * @author ffj
 *
 */
public class InsertInterval {
	public static void main(String[] args) {
		int[][] intervals = { { 1, 3 }, { 6, 9 } };
		int[] newInterval = { 2, 5 };
		int[][] result = new InsertInterval().insert(intervals, newInterval);
		System.out.println(result);
	}

	public int[][] insert(int[][] intervals, int[] newInterval) {

		int[][] retArr;

		int newStart = newInterval[0];
		int newEnd = newInterval[1];
		int si = -1, ei = -1;
		boolean sFind = false, eFind = false;
		for (int i = 0; i < intervals.length; i++) {
			int[] item = intervals[i];
			int itemStart = item[0];
			int itemEnd = item[1];
			if (si == -1) {
				if (newStart <= itemEnd) {
					si = i;
					if (newStart >= itemStart) {
						sFind = true;
					}
				}
			}
			if (ei == -1) {
				if (newEnd <= itemEnd) {
					ei = i;
					if (newEnd >= itemStart) {
						eFind = true;
					}
				}
			}
			if (si != -1 && ei != -1) {
				break;
			}
		}
		// 插入or合并区间
		if (si == -1 && ei == -1) {
			// 插入最后
			retArr = Arrays.copyOf(intervals, intervals.length + 1);
			retArr[intervals.length] = new int[] { newStart, newEnd };
		} else if (si == ei) {
			int[] temp = new int[2];
			if (!sFind) {
				temp[0] = newStart;
			} else {
				temp[0] = intervals[si][0];
			}
			if (!eFind) {
				temp[1] = newEnd;
			} else {
				temp[1] = intervals[ei][1];
			}
			if (!sFind && !eFind) {
				// 新插入一个
				retArr = new int[intervals.length + 1][];
				int insetI = 0;
				for (int i = 0; i < intervals.length; i++) {
					if (i == si) {
						retArr[insetI] = temp;
						insetI++;
					}
					retArr[insetI] = intervals[i];
					insetI++;
				}
			} else {
				intervals[si] = temp;
				retArr = Arrays.copyOf(intervals, intervals.length);
			}
		} else {
			// 合并区间
			int skipStartI, skipEndI;
			int[] temp = new int[2];
			if (sFind) {
				temp[0] = intervals[si][0];
				skipStartI = si;
			} else {
				temp[0] = newStart;
				skipStartI = si;
			}
			if (eFind) {
				temp[1] = intervals[ei][1];
				skipEndI = ei;
			} else {
				temp[1] = newEnd;
				if (ei == -1) {
					skipEndI = intervals.length - 1;
				} else {
					skipEndI = ei - 1;
				}
			}
			int skip = skipEndI - skipStartI;
			retArr = new int[intervals.length - skip][];
			for (int i = 0; i < intervals.length; i++) {
				if (i < skipStartI) {
					retArr[i] = intervals[i];
				} else if (i >= skipStartI && i <= skipEndI) {
					if (i == skipStartI) {
						retArr[skipStartI] = temp;
					}
				} else {
					retArr[i - skip] = intervals[i];
				}
			}
		}
		return retArr;
	}

}
