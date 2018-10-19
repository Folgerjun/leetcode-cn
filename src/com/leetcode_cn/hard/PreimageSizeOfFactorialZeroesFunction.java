package com.leetcode_cn.hard;

/*********************阶乘函数后K个零**************/
/**
 * f(x) 是 x! 末尾是0的数量。（回想一下 x! = 1 * 2 * 3 * ... * x，且0! = 1）
 * 
 * 例如， f(3) = 0 ，因为3! = 6的末尾没有0；而 f(11) = 2 ，因为11!= 39916800末端有2个0。给定
 * K，找出多少个非负整数x ，有 f(x) = K 的性质。
 * 
 * 示例 1:
 * 
 * 输入:K = 0 输出:5
 * 
 * 解释: 0!, 1!, 2!, 3!, and 4! 均符合 K = 0 的条件。
 * 
 * 示例 2:
 * 
 * 输入:K = 5 输出:0
 * 
 * 解释:没有匹配到这样的 x!，符合K = 5 的条件。
 * 
 * 注意：
 * 
 * K是范围在 [0, 10^9] 的整数。
 * 
 * @author ffj
 *
 */
public class PreimageSizeOfFactorialZeroesFunction {

	/**
	 * https://www.cnblogs.com/hutonm/p/5624996.html
	 * 
	 * 因子“5”的个数相关
	 * 
	 * @param K
	 * @return
	 */
	public int preimageSizeFZF(int K) {
		// 因为K*5可能会超出int的范围，所以强转为long类型
		return binarySearch((long) K / 5, (long) K * 5, (long) K) ? 5 : 0;
	}

	private boolean binarySearch(long l, long r, long K) {
		while (l <= r) { // 因为l==r的时候也需要搜索
			long mid = l + (r - l) / 2; // （l + r) / 2 的方式(l + r) 两个long类型相加可能会超过long类型的范围
			long numOfZeros = getNumberofZeros(mid);
			if (numOfZeros == K)
				return true;
			else if (numOfZeros > K)
				r = mid - 1;
			else
				l = mid + 1;
		}
		return false;
	}

	/**
	 * 获取该数阶层末尾“0”的个数
	 * 
	 * 令f(x)表示正整数x末尾所含有的“0”的个数，则有：
	 * 
	 * 当0 < n < 5时，f(n!) = 0;
	 * 
	 * 当n >= 5时，f(n!) = k + f(k!), 其中 k = n / 5（取整）。
	 * 
	 * @param num
	 * @return
	 */
	private long getNumberofZeros(long num) {
		if (num < 5)
			return 0;
		return (num / 5 + getNumberofZeros(num / 5));
	}

	public static void main(String[] args) {
		int K = 5;
		System.out.println(new PreimageSizeOfFactorialZeroesFunction().preimageSizeFZF(K));
	}

}
