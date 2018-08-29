package com.leetcode_cn.medium;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/******************合并区间*******************/
/**
 * 
 * 给出一个区间的集合，请合并所有重叠的区间。
 * 
 * 示例 1:
 * 
 * 输入: [[1,3],[2,6],[8,10],[15,18]] 输出: [[1,6],[8,10],[15,18]]
 * 
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 
 * 示例 2:
 * 
 * 输入: [[1,4],[4,5]] 输出: [[1,5]]
 * 
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 
 * @author ffj
 *
 */
public class MergeIntervals {

	public class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	private Map<Interval, List<Interval>> graph;
	private Map<Integer, List<Interval>> nodesInComp;
	private Set<Interval> visited;

	// return whether two intervals overlap (inclusive)
	private boolean overlap(Interval a, Interval b) {
		return a.start <= b.end && b.start <= a.end;
	}

	// build a graph where an undirected edge between intervals u and v exists
	// iff u and v overlap.
	private void buildGraph(List<Interval> intervals) {
		graph = new HashMap<>();
		for (Interval interval : intervals) {
			graph.put(interval, new LinkedList<>());
		}

		for (Interval interval1 : intervals) {
			for (Interval interval2 : intervals) {
				if (overlap(interval1, interval2)) {
					graph.get(interval1).add(interval2);
					graph.get(interval2).add(interval1);
				}
			}
		}
	}

	// merges all of the nodes in this connected component into one interval.
	private Interval mergeNodes(List<Interval> nodes) {
		int minStart = nodes.get(0).start;
		for (Interval node : nodes) {
			minStart = Math.min(minStart, node.start);
		}

		int maxEnd = nodes.get(0).end;
		for (Interval node : nodes) {
			maxEnd = Math.max(maxEnd, node.end);
		}

		return new Interval(minStart, maxEnd);
	}

	// use depth-first search to mark all nodes in the same connected component
	// with the same integer.
	private void markComponentDFS(Interval start, int compNumber) {
		Stack<Interval> stack = new Stack<>();
		stack.add(start);

		while (!stack.isEmpty()) {
			Interval node = stack.pop();
			if (!visited.contains(node)) {
				visited.add(node);

				if (nodesInComp.get(compNumber) == null) {
					nodesInComp.put(compNumber, new LinkedList<>());
				}
				nodesInComp.get(compNumber).add(node);

				for (Interval child : graph.get(node)) {
					stack.add(child);
				}
			}
		}
	}

	// gets the connected components of the interval overlap graph.
	private void buildComponents(List<Interval> intervals) {
		nodesInComp = new HashMap<Integer, List<Interval>>();
		visited = new HashSet<Interval>();
		int compNumber = 0;

		for (Interval interval : intervals) {
			if (!visited.contains(interval)) {
				markComponentDFS(interval, compNumber);
				compNumber++;
			}
		}
	}

	public List<Interval> merge(List<Interval> intervals) {
		buildGraph(intervals);
		buildComponents(intervals);

		// for each component, merge all intervals into one interval.
		List<Interval> merged = new LinkedList<>();
		for (int comp = 0; comp < nodesInComp.size(); comp++) {
			merged.add(mergeNodes(nodesInComp.get(comp)));
		}

		return merged;
	}

	/**
	 * 排序
	 * 
	 * @author ffj
	 *
	 */
	private class IntervalComparator implements Comparator<Interval> {
		@Override
		public int compare(Interval a, Interval b) {
			return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;
		}
	}

	public List<Interval> merge1(List<Interval> intervals) {

		Collections.sort(intervals, new IntervalComparator());

		LinkedList<Interval> merged = new LinkedList<Interval>();
		for (Interval interval : intervals) {
			// if the list of merged intervals is empty or if the current
			// interval does not overlap with the previous, simply append it.
			if (merged.isEmpty() || merged.getLast().end < interval.start) {
				merged.add(interval);
			}
			// otherwise, there is overlap, so we merge the current and previous
			// intervals.
			else {
				merged.getLast().end = Math.max(merged.getLast().end, interval.end);
			}
		}

		return merged;
	}

}
