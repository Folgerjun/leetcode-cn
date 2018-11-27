package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*******************二叉树的中序遍历****************/
/**
 * 给定一个二叉树，返回它的中序遍历。
 * 
 * 示例:
 * 
 * 输入: [1,null,2,3]
 * 
 * 1
 * 
 * \
 * 
 * 2
 * 
 * /
 * 
 * 3
 * 
 * 输出: [1,3,2]
 * 
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * 
 * @author ffj
 *
 */
public class BinaryTreeInorderTraversal {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	/**
	 * 递归
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		return helper(list, root);
	}

	private List<Integer> helper(List<Integer> list, TreeNode node) {
		if (node != null) {
			helper(list, node.left);
			list.add(node.val);
			helper(list, node.right);
		}
		return list;
	}

	/**
	 * 栈 stack 来解决
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversal1(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode curr = root;
		while (curr != null || !stack.isEmpty()) {
			while (curr != null) {
				// 将其左节点依次放入 因为先读左节点
				stack.push(curr);
				curr = curr.left;
			}
			// 将栈顶弹出
			curr = stack.pop();
			// 塞值
			res.add(curr.val);
			// 将右节点赋值给它 完美呈现了中序遍历 ： 左 -> 根 -> 右
			curr = curr.right;
		}
		return res;
	}

}
