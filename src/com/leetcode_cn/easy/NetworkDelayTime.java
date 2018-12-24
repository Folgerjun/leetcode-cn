package com.leetcode_cn.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*********网络延迟时间**********/
/**
 * 有 N 个网络节点，标记为 1 到 N。
 * 
 * 给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，其中 u 是源节点，v 是目标节点， w
 * 是一个信号从源节点传递到目标节点的时间。
 * 
 * 现在，我们向当前的节点 K 发送了一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1。
 * 
 * 注意:
 * 
 * N 的范围在 [1, 100] 之间。
 * 
 * K 的范围在 [1, N] 之间。
 * 
 * times 的长度在 [1, 6000] 之间。
 * 
 * 所有的边 times[i] = (u, v, w) 都有 1 <= u, v <= N 且 1 <= w <= 100。
 * 
 * @author ffj
 *
 */
public class NetworkDelayTime {
	Map<Integer, Integer> dist;

	public int networkDelayTime(int[][] times, int N, int K) {
		Map<Integer, List<int[]>> graph = new HashMap<Integer, List<int[]>>();
		for (int[] edge : times) {
			if (!graph.containsKey(edge[0]))
				graph.put(edge[0], new ArrayList<int[]>());
			graph.get(edge[0]).add(new int[] { edge[2], edge[1] });
		}
		for (int node : graph.keySet()) {
			// 排序 按照延迟时间
			Collections.sort(graph.get(node), (a, b) -> a[0] - b[0]);
		}
		dist = new HashMap<Integer, Integer>();
		for (int node = 1; node <= N; ++node)
			dist.put(node, Integer.MAX_VALUE);

		dfs(graph, K, 0);
		int ans = 0;
		for (int cand : dist.values()) {
			if (cand == Integer.MAX_VALUE) // 说明还有点没遍历到
				return -1;
			ans = Math.max(ans, cand); // 取最大值
		}
		return ans;
	}

	/**
	 * 
	 * @param graph
	 * 
	 * 
	 * @param node
	 *            点
	 * @param elapsed
	 *            延时
	 */
	public void dfs(Map<Integer, List<int[]>> graph, int node, int elapsed) {
		if (elapsed >= dist.get(node))
			return;
		dist.put(node, elapsed);
		if (graph.containsKey(node))
			for (int[] info : graph.get(node))
				dfs(graph, info[1], elapsed + info[0]);
	}

}
