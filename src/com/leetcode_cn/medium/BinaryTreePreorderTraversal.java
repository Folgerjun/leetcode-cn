package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/***************二叉树的前序遍历*************/
/**
 * 给定一个二叉树，返回它的 前序 遍历。
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
 * 输出: [1,2,3]
 * 
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * 
 * @author ffj
 *
 */
public class BinaryTreePreorderTraversal {

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
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		helper(list, root);
		return list;
	}

	private void helper(List<Integer> list, TreeNode node) {
		if (node != null) {
			list.add(node.val);
			if (node.left != null)
				helper(list, node.left);
			if (node.right != null)
				helper(list, node.right);
		}
	}

	/**
	 * 双向队列
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal1(TreeNode root) {
		LinkedList<TreeNode> stack = new LinkedList<>();
		LinkedList<Integer> output = new LinkedList<>();
		if (root == null) {
			return output;
		}
		stack.add(root);
		while (!stack.isEmpty()) {
			// 弹出队列中最后一个
			TreeNode node = stack.pollLast();
			output.add(node.val);
			if (node.right != null) {
				stack.add(node.right);
			}
			if (node.left != null) {
				// 该节点就是下一个要读的根节点
				stack.add(node.left);
			}
		}
		return output;
	}

}
