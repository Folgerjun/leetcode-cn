package com.leetcode_cn.medium;

import java.util.TreeMap;

/********************一手顺子****************/
/**
 * 爱丽丝有一手（hand）由整数数组给定的牌。
 * 
 * 现在她想把牌重新排列成组，使得每个组的大小都是 W，且由 W 张连续的牌组成。
 * 
 * 如果她可以完成分组就返回 true，否则返回 false。
 * 
 * 示例 1：
 * 
 * 输入：hand = [1,2,3,6,2,3,4,7,8], W = 3 输出：true
 * 
 * 解释：爱丽丝的手牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 * 
 * 示例 2：
 * 
 * 输入：hand = [1,2,3,4,5], W = 4 输出：false
 * 
 * 解释：爱丽丝的手牌无法被重新排列成几个大小为 4 的组。
 * 
 * 提示：
 * 
 * 1 <= hand.length <= 10000
 * 
 * 0 <= hand[i] <= 10^9
 * 
 * 1 <= W <= hand.length
 * 
 * @author ffj
 *
 */
public class HandOfStraights {

	public boolean isNStraightHand(int[] hand, int W) {

		if (hand.length % W != 0)
			return false;
		// 先将元素塞入 treemap 中有序排列
		TreeMap<Integer, Integer> count = new TreeMap<>();
		for (int i : hand) {
			if (!count.containsKey(i))
				count.put(i, 1);
			else
				count.replace(i, count.get(i) + 1);
		}

		while (count.size() > 0) {
			int first = count.firstKey();
			// 每次删除一组数
			for (int card = first; card < first + W; ++card) {
				if (!count.containsKey(card)) // 没有该数直接返回 false
					return false;
				int num = count.get(card);
				// 减少 treemap 中相应 key 的 value 值
				if (num == 1)
					count.remove(card);
				else
					count.replace(card, num - 1);
			}
		}
		// 直到 treemap 中全都删除
		return true;
	}

}
