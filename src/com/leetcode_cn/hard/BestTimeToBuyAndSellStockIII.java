package com.leetcode_cn.hard;

/******************买卖股票的最佳时机 III***********/
/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 
 * 示例 1:
 * 
 * 输入: [3,3,5,0,0,3,1,4] 输出: 6
 * 
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。 随后，在第
 * 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 
 * 示例 2:
 * 
 * 输入: [1,2,3,4,5] 输出: 4
 * 
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 
 * 示例 3:
 * 
 * 输入: [7,6,4,3,1] 输出: 0 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 * 
 * @author ffj
 *
 */
public class BestTimeToBuyAndSellStockIII {

	public static void main(String[] args) {
		int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };
		int result = new BestTimeToBuyAndSellStockIII().maxProfit(prices);
		System.out.println("total:" + result);
	}

	/**
	 * 官网讨论中解法
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {

		int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
		int release1 = 0, release2 = 0;
		for (int i : prices) { // Assume we only have 0 money at first
			release2 = Math.max(release2, hold2 + i); // The maximum if we've just sold 2nd stock so far.
			hold2 = Math.max(hold2, release1 - i); // The maximum if we've just buy 2nd stock so far.
			release1 = Math.max(release1, hold1 + i); // The maximum if we've just sold 1nd stock so far.
			hold1 = Math.max(hold1, -i); // The maximum if we've just buy 1st stock so far.
		}
		return release2; /// Since release1 is initiated as 0, so release2 will always higher than
							/// release1.
	}

	/**
	 * DP
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit1(int[] prices) {
		if (prices == null || prices.length == 0)
			return 0;
		int lenght = prices.length;

		int[] leftProfit = new int[lenght];
		int leftMaxProfit = 0;
		int leftMin = prices[0];
		for (int i = 0; i < lenght; i++) {
			if (prices[i] < leftMin)
				leftMin = prices[i];
			if (prices[i] - leftMin > leftMaxProfit)
				leftMaxProfit = prices[i] - leftMin;
			leftProfit[i] = leftMaxProfit;
		}

		int maxProfit = 0;
		int rightMaxProfit = 0;
		int rightMax = prices[lenght - 1];
		for (int i = lenght - 1; i >= 0; i--) {
			if (prices[i] > rightMax)
				rightMax = prices[i];
			if (rightMax - prices[i] > rightMaxProfit)
				rightMaxProfit = rightMax - prices[i];
			int currentProfit = rightMaxProfit + (i > 0 ? leftProfit[i - 1] : 0);
			if (currentProfit > maxProfit) {
				maxProfit = currentProfit;
			}
		}

		return maxProfit;
	}

}
