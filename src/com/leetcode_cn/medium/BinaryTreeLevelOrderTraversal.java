package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/********************二叉树的层次遍历************/
/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * 
 * 例如: 给定二叉树: [3,9,20,null,null,15,7],
 * 
 * 3
 * 
 * / \
 * 
 * 9 20
 * 
 * / \
 * 
 * 15 7
 * 
 * 返回其层次遍历结果：
 * 
 * [ [3], [9,20], [15,7] ]
 * 
 * @author ffj
 *
 */
public class BinaryTreeLevelOrderTraversal {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<List<Integer>> levelOrder(TreeNode root) {

		List<List<Integer>> list = new ArrayList<>();
		List<TreeNode> l = new ArrayList<>();
		l.add(root);
		helper(list, l);
		return list;

	}

	/**
	 * 递归 层层遍历
	 * 
	 * @param list
	 * @param treeList
	 */
	private void helper(List<List<Integer>> list, List<TreeNode> treeList) {
		if (treeList.size() == 0)
			return;
		List<Integer> listInt = new ArrayList<>();
		List<TreeNode> treeL = new ArrayList<>();
		// 逐层添值
		for (TreeNode node : treeList) {
			if (node != null) {
				listInt.add(node.val);
				treeL.add(node.left);
				treeL.add(node.right);
			}
		}
		if (listInt.size() > 0)
			list.add(listInt);

		helper(list, treeL);
	}

	/**
	 * 使用队列 queue
	 * 
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrder1(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

		if (root == null)
			return wrapList;

		queue.offer(root);
		while (!queue.isEmpty()) {
			// 有值就塞入集合 同时将其左右子节点添加到队列中
			int levelNum = queue.size();
			List<Integer> subList = new LinkedList<Integer>();
			for (int i = 0; i < levelNum; i++) {
				if (queue.peek().left != null)
					queue.offer(queue.peek().left);
				if (queue.peek().right != null)
					queue.offer(queue.peek().right);
				subList.add(queue.poll().val);
			}
			wrapList.add(subList);
		}
		return wrapList;
	}

}
