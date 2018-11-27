package com.leetcode_cn.easy;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/****************** 二叉树的层次遍历 II *******************/
/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 
 * 例如： 给定二叉树 [3,9,20,null,null,15,7],
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
 * 返回其自底向上的层次遍历为：
 * 
 * [ [15,7], [9,20], [3] ]
 * 
 * @author ffj
 *
 */
public class BinaryTreeLevelOrderTraversalII {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {

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
			// 指定下标 每次都塞到第一个
			wrapList.add(0, subList);
		}
		return wrapList;

	}
}
