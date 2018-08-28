package com.leetcode_cn.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**********************除法求值*******************/
/**
 * 
 * 给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k
 * 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。
 * 
 * 示例 : 给定 a / b = 2.0, b / c = 3.0
 * 
 * 问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 
 * 返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * 
 * 输入为: vector<pair<string, string>> equations, vector<double>& values,
 * vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)， 其中 equations.size() ==
 * values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。
 * 返回vector<double>类型。
 * 
 * 基于上述例子，输入如下：
 * 
 * equations(方程式) = [ ["a", "b"], ["b", "c"] ],
 * 
 * values(方程式结果) = [2.0, 3.0],
 * 
 * queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"]
 * ].
 * 
 * 输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。
 * 
 * @author ffj
 *
 */
public class EvaluateDivision {

	public static void main(String[] args) {
		String[][] equations = { { "a", "b" }, { "b", "c" } };
		double[] values = { 2.0, 3.0 };
		String[][] queries = { { "a", "c" }, { "b", "a" }, { "a", "b" }, { "a", "a" }, { "x", "x" } };
		System.out.println(Arrays.toString(new EvaluateDivision().calcEquation(equations, values, queries)));

	}

	/**
	 * 图像a / b = k作为节点a和b之间的链路，从a到b的权重是k，反向链路是1 / k。查询是在两个节点之间找到路径。
	 * 
	 * @param eq
	 * @param vals
	 * @param q
	 * @return
	 */
	public double[] calcEquation(String[][] eq, double[] vals, String[][] q) {
		Map<String, Map<String, Double>> m = new HashMap<>();
		for (int i = 0; i < vals.length; i++) {
			m.putIfAbsent(eq[i][0], new HashMap<>());
			m.putIfAbsent(eq[i][1], new HashMap<>());
			m.get(eq[i][0]).put(eq[i][1], vals[i]);
			m.get(eq[i][1]).put(eq[i][0], 1 / vals[i]);
		}
		double[] r = new double[q.length];
		for (int i = 0; i < q.length; i++) // 给数组循环赋值
			r[i] = dfs(q[i][0], q[i][1], 1.0, m, new HashSet<>());
		return r;
	}

	double dfs(String s, String t, double r, Map<String, Map<String, Double>> m, Set<String> seen) {
		if (!m.containsKey(s) || !seen.add(s)) // 放入seen集合中 避免重复循环
			return -1;
		if (s.equals(t))
			return r;
		Map<String, Double> next = m.get(s);
		for (String c : next.keySet()) {
			double result = dfs(c, t, r * next.get(c), m, seen);
			if (result != -1)
				return result;
		}
		return -1;
	}

}
