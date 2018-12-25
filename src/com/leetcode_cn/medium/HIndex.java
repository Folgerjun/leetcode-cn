package com.leetcode_cn.medium;

/*********H指数********/
/**
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
 * 
 * h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）至多有 h
 * 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）”
 * 
 * 示例:
 * 
 * 输入: citations = [3,0,6,1,5] 输出: 3
 * 
 * 解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。 由于研究者有 3 篇论文每篇至少被引用了 3
 * 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
 * 
 * 说明: 如果 h 有多种可能的值，h 指数是其中最大的那个。
 * 
 * @author ffj
 *
 */
public class HIndex {

	public static void main(String[] args) {
		int[] citations = { 3, 0, 6, 1, 5 };
		int result = new HIndex().hIndex(citations);
		System.out.println("result :" + result);
	}

	public int hIndex(int[] citations) {
		// [3,0,6,1,5]
		int length = citations.length;
		if (length == 0) { // 数组长度为 0 自然为 0
			return 0;
		}
		// 与下标对应 引用次数和论文数量
		int[] array2 = new int[length + 1];
		for (int i = 0; i < length; i++) {
			if (citations[i] > length) {
				// 大于长度的值肯定在其中 放置最后
				array2[length] += 1;
			} else {
				array2[citations[i]] += 1;
			}
		}
		int t = 0;
		for (int i = length; i >= 0; i--) {
			t = t + array2[i];
			if (t >= i) { // 满足条件 返回长度
				return i;
			}
		}
		return 0;
	}

}
