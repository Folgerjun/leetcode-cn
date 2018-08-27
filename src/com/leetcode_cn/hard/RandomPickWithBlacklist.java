package com.leetcode_cn.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*********************黑名单中的随机数******************/
/**
 * 给定一个包含 [0，n ) 中独特的整数的黑名单 B，写一个函数从 [ 0，n ) 中返回一个不在 B 中的随机整数。
 * 
 * 对它进行优化使其尽量少调用系统方法 Math.random() 。
 * 
 * 提示:
 * 
 * 1 <= N <= 1000000000 0 <= B.length < min(100000, N) [0, N) 不包含 N，详细参见
 * interval notation 。
 * 
 * 示例 1:
 * 
 * 输入: ["Solution","pick","pick","pick"] [[1,[]],[],[],[]] 输出: [null,0,0,0]
 * 
 * 示例 2:
 * 
 * 输入: ["Solution","pick","pick","pick"] [[2,[]],[],[],[]] 输出: [null,1,1,1]
 * 
 * 示例 3:
 * 
 * 输入: ["Solution","pick","pick","pick"] [[3,[1]],[],[],[]] Output: [null,0,0,2]
 * 
 * 示例 4:
 * 
 * 输入: ["Solution","pick","pick","pick"] [[4,[2]],[],[],[]] 输出: [null,1,3,1]
 * 
 * 输入语法说明：
 * 
 * 输入是两个列表：调用成员函数名和调用的参数。Solution的构造函数有两个参数，N 和黑名单 B。pick
 * 没有参数，输入参数是一个列表，即使参数为空，也会输入一个 [] 空列表。
 * 
 * @author ffj
 *
 */
public class RandomPickWithBlacklist {
	int M;
	Random r = new Random();
	Map<Integer, Integer> map = new HashMap<>(); // 定义一个map来存放黑名单中的值

	/**
	 * 图解：https://s3-lc-upload.s3.amazonaws.com/users/cafebaby/image_1530657902.png
	 * 
	 * @param N
	 * @param blacklist
	 */
	public RandomPickWithBlacklist(int N, int[] blacklist) {

		for (int i : blacklist) {
			map.put(i, -1);
		}
		M = N - map.size();

		for (int i : blacklist) {
			if (i < M) { // 界限值以下有黑名单中的值
				while (map.containsKey(N - 1)) // 将黑名单中的value值替换为白名单中的值
					N--;
				map.put(i, --N);

			}
		}
	}

	public int pick() {
		int p = r.nextInt(M); // 产生一个随机数
		if (map.containsKey(p)) // 若是这个随机数在黑名单中 返回其不在黑名单中的值
			return map.get(p);
		return p;

	}
}
