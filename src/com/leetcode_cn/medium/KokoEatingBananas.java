package com.leetcode_cn.medium;

import java.util.Arrays;

/*****************爱吃香蕉的珂珂******************/
/**
 * 
 * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 * 
 * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K
 * 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * 
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入: piles = [3,6,7,11], H = 8
 * 
 * 输出: 4
 * 
 * 示例 2：
 * 
 * 输入: piles = [30,11,23,4,20], H = 5
 * 
 * 输出: 30
 * 
 * 示例 3：
 * 
 * 输入: piles = [30,11,23,4,20], H = 6
 * 
 * 输出: 23
 * 
 * 
 * 提示：
 * 
 * 1 <= piles.length <= 10^4
 * 
 * piles.length <= H <= 10^9
 * 
 * 1 <= piles[i] <= 10^9
 * 
 * @author ffj
 *
 */
public class KokoEatingBananas {

	/**
	 * 超时
	 * 
	 * @param piles
	 * @param H
	 * @return
	 */
	public int minEatingSpeed(int[] piles, int H) {

		Arrays.sort(piles);

		for (int K = 1; K <= piles[piles.length - 1]; K++) {
			int h = 0;
			for (int pile : piles) {
				h += (pile - 1) / K + 1;
			}
			if (h == H)
				return K;
		}
		return 0;
	}

	/**
	 * 改进二分法
	 * 
	 * @param piles
	 * @param H
	 * @return
	 */
	public int minEatingSpeed1(int[] piles, int H) {

		Arrays.sort(piles);

		int lo = 1, hi = piles[piles.length - 1];

		while (lo < hi) {
			int mid = (lo + hi) / 2;
			int h = 0;
			for (int pile : piles) {
				h += (pile - 1) / mid + 1;
			}
			if (h <= H)
				hi = mid;
			else
				lo = mid + 1;
		}
		return lo;
	}

	public static void main(String[] args) {
		int[] piles = { 30, 11, 23, 4, 20 };
		int H = 6;
		System.out.println(new KokoEatingBananas().minEatingSpeed1(piles, H));
	}

}
