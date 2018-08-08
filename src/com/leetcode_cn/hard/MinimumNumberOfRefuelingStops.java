package com.leetcode_cn.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**************************最低加油次数****************/
/**
 * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
 * 
 * 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1]
 * 升汽油。
 * 
 * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
 * 
 * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
 * 
 * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
 * 
 * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
 * 
 * 示例 1：
 * 
 * 输入：target = 1, startFuel = 1, stations = [] 输出：0
 * 
 * 解释：我们可以在不加油的情况下到达目的地。
 * 
 * 示例 2：
 * 
 * 输入：target = 100, startFuel = 1, stations = [[10,100]] 输出：-1
 * 
 * 解释：我们无法抵达目的地，甚至无法到达第一个加油站。
 * 
 * 示例 3：
 * 
 * 输入：target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * 输出：2
 * 
 * 解释： 我们出发时有 10 升燃料。 我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。 然后，我们从 10
 * 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料）， 并将汽油从 10 升加到 50 升。然后我们开车抵达目的地。
 * 我们沿途在1两个加油站停靠，所以返回 2 。
 * 
 * 提示：
 * 
 * 1 <= target, startFuel, stations[i][1] <= 10^9
 * 
 * 0 <= stations.length <= 500
 * 
 * 0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] <
 * target
 * 
 * @author ffj
 *
 */
public class MinimumNumberOfRefuelingStops {

	public static void main(String[] args) {
		int target = 1000;
		int startFuel = 83;
		int[][] stations = { { 22, 17 }, { 100, 23 }, { 121, 71 }, { 141, 77 }, { 236, 4 }, { 438, 78 }, { 500, 76 },
				{ 642, 66 }, { 672, 20 }, { 685, 81 } };
		int result = minRefuelStops(target, startFuel, stations);
		System.out.println(result);
	}

	public static int minRefuelStops(int target, int startFuel, int[][] stations) {

		int length = stations.length;
		if (length == 0) {
			if (startFuel >= target)
				return 0;
			else
				return -1;
		}
		int index = 0; // 表示加油站
		boolean reach = true; // 是否到达
		List<Integer> fuelList = new ArrayList<>(); // 存放油
		while (index < length) { // 加油站
			int dis = index == 0 ? stations[index][0] : (stations[index][0] - stations[index - 1][0]);
			// 不够到达加油站 需要加油
			Collections.sort(fuelList);
			while (startFuel < dis) {
				if (fuelList.size() == 0) {// 不可能到达
					reach = false;
					break;
				}
				startFuel = startFuel + fuelList.get(fuelList.size() - 1); // 加上最多的油
				fuelList.remove(fuelList.size() - 1); // 加完后删除
			}
			if (!reach) // 如果到达不了就返回
				break;

			// 顺利达到加油站 把油储存
			fuelList.add(stations[index][1]);
			// 距离改变
			target = target - dis;
			// 油量改变
			startFuel = startFuel - dis;
			// 加油站数量改变
			index++;

			if (target <= 0) // 到达了
				break;

		}

		if (!reach) { // 到达不了
			return -1;

		} else {
			if (target <= 0 || target <= startFuel) // 已经到达或者油量够了 直接返回
				return length - fuelList.size();
			// 还没到达 需要加油 加到满足
			while (target > startFuel) {
				if (fuelList.size() == 0) { // 没有油可以加
					reach = false;
					break;
				}
				// 从最大量开始加
				Collections.sort(fuelList);
				startFuel = startFuel + fuelList.get(fuelList.size() - 1);
				fuelList.remove(fuelList.size() - 1);
			}
			if (!reach)
				return -1;
			else
				return length - fuelList.size();
		}
	}

	/**
	 * 官网解法
	 * 
	 * @param target
	 * @param startFuel
	 * @param stations
	 * @return
	 */
	public int minRefuelStops1(int target, int startFuel, int[][] stations) {
		int N = stations.length;
		long[] dp = new long[N + 1];
		dp[0] = startFuel;
		for (int i = 0; i < N; ++i)
			for (int t = i; t >= 0; --t)
				if (dp[t] >= stations[i][0])
					dp[t + 1] = Math.max(dp[t + 1], dp[t] + (long) stations[i][1]);

		for (int i = 0; i <= N; ++i)
			if (dp[i] >= target)
				return i;
		return -1;
	}

	/**
	 * 官网解法
	 * 
	 * @param target
	 * @param tank
	 * @param stations
	 * @return
	 */
	public int minRefuelStops2(int target, int tank, int[][] stations) {
		// pq is a maxheap of gas station capacities
		PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder()); // 优先队列 实现了排序
		int ans = 0, prev = 0;
		for (int[] station : stations) {
			int location = station[0];
			int capacity = station[1];
			tank -= location - prev;
			while (!pq.isEmpty() && tank < 0) { // must refuel in past
				tank += pq.poll();
				ans++;
			}

			if (tank < 0)
				return -1;
			pq.offer(capacity);
			prev = location;
		}

		// Repeat body for station = (target, inf)
		{
			tank -= target - prev;
			while (!pq.isEmpty() && tank < 0) {
				tank += pq.poll();
				ans++;
			}
			if (tank < 0)
				return -1;
		}

		return ans;
	}
}
