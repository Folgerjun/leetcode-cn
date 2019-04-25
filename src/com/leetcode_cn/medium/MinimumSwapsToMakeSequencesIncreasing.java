package com.leetcode_cn.medium;

/********使序列递增的最小交换次数*********/
/**
 * 我们有两个长度相等且不为空的整型数组 A 和 B 。
 * 
 * 我们可以交换 A[i] 和 B[i] 的元素。注意这两个元素在各自的序列中应该处于相同的位置。
 * 
 * 在交换过一些元素之后，数组 A 和 B 都应该是严格递增的（数组严格递增的条件仅为A[0] < A[1] < A[2] < ... <
 * A[A.length - 1]）。
 * 
 * 给定数组 A 和 B ，请返回使得两个数组均保持严格递增状态的最小交换次数。假设给定的输入总是有效的。
 * 
 * 示例: 输入: A = [1,3,5,4], B = [1,2,3,7] 输出: 1
 * 
 * 解释:
 * 
 * 交换 A[3] 和 B[3] 后，两个数组如下:
 * 
 * A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
 * 
 * 两个数组均为严格递增的。
 * 
 * 注意:
 * 
 * A, B 两个数组的长度总是相等的，且长度的范围为 [1, 1000]。
 * 
 * A[i], B[i] 均为 [0, 2000]区间内的整数。
 * 
 * @author ffj
 *
 */
public class MinimumSwapsToMakeSequencesIncreasing {

	public int minSwap(int[] A, int[] B) {
		int len = A.length;
		// 第i位 转 的累加次数
		int[] swap = new int[len];
		// 第i位 不转 的累加次数
		int[] not_swap = new int[len];
		swap[0] = 1;
		for (int i = 1; i < len; i++) {
			if (A[i - 1] >= B[i] || B[i - 1] >= A[i]) {
				swap[i] = swap[i - 1] + 1; // 这个换 必然冲突 使得之前换的次数加上这次
				not_swap[i] = not_swap[i - 1]; // 这个不换 不影响
			} else if (A[i - 1] >= A[i] || B[i - 1] >= B[i]) {
				swap[i] = not_swap[i - 1] + 1; // 这个换 之前不换 加上 这次换
				not_swap[i] = swap[i - 1]; // 这个不换 之前的换
			} else {
				// 取较小值
				int temp = Math.min(swap[i - 1], not_swap[i - 1]);
				swap[i] = temp + 1; // 换 再加个1
				not_swap[i] = temp; // 不换 取较小值
			}
		} // 返回较小值
		return Math.min(not_swap[len - 1], swap[len - 1]);
	}

}
