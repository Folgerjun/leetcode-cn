package com.leetcode_cn.hard;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*********************原子的数量*******************/
/**
 * 给定一个化学式formula（作为字符串），返回每种原子的数量。
 * 
 * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 * 
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
 * 
 * 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
 * 
 * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 * 
 * 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于
 * 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 * 
 * 示例 1:
 * 
 * 输入: formula = "H2O" 输出: "H2O" 解释: 原子的数量是 {'H': 2, 'O': 1}。
 * 
 * 示例 2:
 * 
 * 输入: formula = "Mg(OH)2" 输出: "H2MgO2" 解释: 原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
 * 
 * 示例 3:
 * 
 * 输入: formula = "K4(ON(SO3)2)2" 输出: "K4N2O14S4" 解释: 原子的数量是 {'K': 4, 'N': 2,
 * 'O': 14, 'S': 4}。 注意:
 * 
 * 所有原子的第一个字母为大写，剩余字母都是小写。 formula的长度在[1, 1000]之间。
 * formula只包含字母、数字和圆括号，并且题目中给定的是合法的化学式。
 * 
 * @author ffj
 *
 */
public class NumberOfAtoms {

	int i;

	public String countOfAtoms(String formula) {
		StringBuilder ans = new StringBuilder();
		i = 0;
		Map<String, Integer> count = parse(formula);
		for (String name : count.keySet()) {
			ans.append(name);
			int multiplicity = count.get(name);
			if (multiplicity > 1)
				ans.append("" + multiplicity);
		}
		return new String(ans);
	}

	/**
	 * 计数 存储map中 递归
	 * 
	 * @param formula
	 * @return
	 */
	public Map<String, Integer> parse(String formula) {
		int N = formula.length();
		Map<String, Integer> count = new TreeMap<String, Integer>();
		while (i < N && formula.charAt(i) != ')') {
			if (formula.charAt(i) == '(') { // 左括号
				i++;
				for (Map.Entry<String, Integer> entry : parse(formula).entrySet()) {
					// map中没有key 默认值为0 累加
					count.put(entry.getKey(), count.getOrDefault(entry.getKey(), 0) + entry.getValue());
				}
			} else {
				int iStart = i++;
				while (i < N && Character.isLowerCase(formula.charAt(i))) // 判断是否是小写字母
					i++;
				String name = formula.substring(iStart, i); // 截取元素
				iStart = i;
				while (i < N && Character.isDigit(formula.charAt(i))) // 是否是数字
					i++;
				int multiplicity = iStart < i ? Integer.parseInt(formula.substring(iStart, i)) : 1;
				count.put(name, count.getOrDefault(name, 0) + multiplicity);
			}
		}
		int iStart = ++i;
		while (i < N && Character.isDigit(formula.charAt(i)))
			i++;
		if (iStart < i) {
			int multiplicity = Integer.parseInt(formula.substring(iStart, i));
			for (String key : count.keySet()) {
				count.put(key, count.get(key) * multiplicity);
			}
		}
		return count;
	}

	/**
	 * stack
	 * 
	 * @param formula
	 * @return
	 */
	public String countOfAtoms1(String formula) {
		int N = formula.length();
		Stack<Map<String, Integer>> stack = new Stack<Map<String, Integer>>();
		stack.push(new TreeMap<String, Integer>());

		for (int i = 0; i < N;) {
			if (formula.charAt(i) == '(') {
				stack.push(new TreeMap<String, Integer>());
				i++;
			} else if (formula.charAt(i) == ')') {
				Map<String, Integer> top = stack.pop();
				int iStart = ++i, multiplicity = 1;
				while (i < N && Character.isDigit(formula.charAt(i)))
					i++;
				if (i > iStart)
					multiplicity = Integer.parseInt(formula.substring(iStart, i));
				for (String c : top.keySet()) {
					int v = top.get(c);
					stack.peek().put(c, stack.peek().getOrDefault(c, 0) + v * multiplicity);
				}
			} else {
				int iStart = i++;
				while (i < N && Character.isLowerCase(formula.charAt(i)))
					i++;
				String name = formula.substring(iStart, i);
				iStart = i;
				while (i < N && Character.isDigit(formula.charAt(i)))
					i++;
				int multiplicity = i > iStart ? Integer.parseInt(formula.substring(iStart, i)) : 1;
				stack.peek().put(name, stack.peek().getOrDefault(name, 0) + multiplicity);
			}
		}

		StringBuilder ans = new StringBuilder();
		for (String name : stack.peek().keySet()) {
			ans.append(name);
			int multiplicity = stack.peek().get(name);
			if (multiplicity > 1)
				ans.append("" + multiplicity);
		}
		return new String(ans);
	}

	/**
	 * 正则匹配
	 * 
	 * @param formula
	 * @return
	 */
	public String countOfAtoms2(String formula) {
		Matcher matcher = Pattern.compile("([A-Z][a-z]*)(\\d*)|(\\()|(\\))(\\d*)").matcher(formula);
		Stack<Map<String, Integer>> stack = new Stack<Map<String, Integer>>();
		stack.push(new TreeMap<String, Integer>());

		while (matcher.find()) {
			String match = matcher.group();
			if (match.equals("(")) {
				stack.push(new TreeMap<String, Integer>());
			} else if (match.startsWith(")")) {
				Map<String, Integer> top = stack.pop();
				int multiplicity = match.length() > 1 ? Integer.parseInt(match.substring(1, match.length())) : 1;
				for (String name : top.keySet()) {
					stack.peek().put(name, stack.peek().getOrDefault(name, 0) + top.get(name) * multiplicity);
				}
			} else {
				int i = 1;
				while (i < match.length() && Character.isLowerCase(match.charAt(i))) {
					i++;
				}
				String name = match.substring(0, i);
				int multiplicity = i < match.length() ? Integer.parseInt(match.substring(i, match.length())) : 1;
				stack.peek().put(name, stack.peek().getOrDefault(name, 0) + multiplicity);
			}
		}

		StringBuilder ans = new StringBuilder();
		for (String name : stack.peek().keySet()) {
			ans.append(name);
			final int count = stack.peek().get(name);
			if (count > 1)
				ans.append(String.valueOf(count));
		}
		return ans.toString();
	}

}
