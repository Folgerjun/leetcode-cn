package com.leetcode_cn.easy;

import java.util.ArrayList;
import java.util.List;

/***************二叉树的最大深度**********/
/**
 * 给定一个二叉树，找出其最大深度。
 * 
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 示例：
 * 
 * 给定二叉树 [3,9,20,null,null,15,7]，
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
 * 返回它的最大深度 3 。
 * 
 * @author ffj
 *
 */
public class MaximumDepthOfBinaryTree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	/**
	 * 层次遍历
	 * 
	 * @param root
	 * @return
	 */
	public int maxDepth(TreeNode root) {

		if (root == null)
			return 0;
		List<TreeNode> nodes = new ArrayList<>();
		nodes.add(root);
		int num = 0;
		List<TreeNode> nodesTemp; // 临时存放节点
		while (!nodes.isEmpty()) {
			num++;
			nodesTemp = new ArrayList<>();
			for (TreeNode node : nodes) {
				if (node.left != null)
					nodesTemp.add(node.left);
				if (node.right != null)
					nodesTemp.add(node.right);
			}
			nodes.clear(); // 先清除再复制值
			nodes.addAll(nodesTemp);
		}
		return num;
	}

	/**
	 * DFS 比较大小
	 * 
	 * @param root
	 * @return
	 */
	public int maxDepth1(TreeNode root) {
		if (root == null)
			return 0;
		else {
			int leftLen = maxDepth1(root.left);
			int rightLen = maxDepth1(root.right);
			return Math.max(leftLen, rightLen) + 1;
		}
	}

}
