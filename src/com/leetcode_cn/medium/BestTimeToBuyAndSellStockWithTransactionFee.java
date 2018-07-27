package com.leetcode_cn.medium;

/*************买卖股票的最佳时机含手续费********https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solution/*******************/
/**
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * 
 * 你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 
 * 返回获得利润的最大值。
 * 
 * 示例 1:
 * 
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 
 * 输出: 8
 * 
 * 解释: 能够达到的最大利润: 在此处买入 prices[0] = 1
 * 
 * 在此处卖出 prices[3] = 8
 * 
 * 在此处买入 prices[4] = 4
 * 
 * 在此处卖出 prices[5] = 9
 * 
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * 
 * 注意:
 * 
 * 0 < prices.length <= 50000 / 0 < prices[i] < 50000 / 0 <= fee < 50000.
 * 
 * @author ffj
 *
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

	public static void main(String[] args) {
		int[] prices = { 1, 3, 2, 8, 4, 9 };
		int fee = 2;
		int result = maxProfit(prices, fee);
		System.out.println(result);

	}

	public static int maxProfit(int[] prices, int fee) {

		if (prices == null || prices.length == 0) {
			return 0;
		}
		// cash: 手头的现金，即总的赚的金额，同时也是未持股时的现金额
		// hold: 手中有持股时的现金，即总金额减去手中股票的买入价
		int cash = 0, hold = -prices[0];
		for (int i = 1; i < prices.length; i++) {
			cash = Math.max(cash, hold + prices[i] - fee); // 若卖出比原本买股票之前赚 就卖
			hold = Math.max(hold, cash - prices[i]); // 买入后剩余的钱比之前持股时钱还要多 就买
		}
		return cash;
	}

}
