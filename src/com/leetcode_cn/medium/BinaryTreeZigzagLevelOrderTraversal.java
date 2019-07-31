package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.List;

/***********二叉树的锯齿形层次遍历*********/
/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 
 * 例如：
 * 
 * 给定二叉树 [3,9,20,null,null,15,7],
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
 * 返回锯齿形层次遍历如下：
 * 
 * [ [3], [20,9], [15,7] ]
 * 
 * @author ffj
 *
 */
public class BinaryTreeZigzagLevelOrderTraversal {

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
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		if (root == null)
			return new ArrayList<>();

		List<Integer> nodes = new ArrayList<>();
		List<Integer> nodesTemp;
		List<TreeNode> tns = new ArrayList<>();
		List<TreeNode> tnsTemp;
		List<List<Integer>> nodeList = new ArrayList<>();
		nodes.add(root.val);
		tns.add(root);
		nodeList.add(nodes);
		// 初始从右开始
		boolean left = false;
		while (true) {
			tnsTemp = new ArrayList<>();
			nodesTemp = new ArrayList<>();
			if (!left) {// 从右边开始
				for (int i = tns.size() - 1; i >= 0; i--) {
					TreeNode node = tns.get(i);
					if (node.right != null) {
						tnsTemp.add(node.right);
						nodesTemp.add(node.right.val);
					}
					if (node.left != null) {
						tnsTemp.add(node.left);
						nodesTemp.add(node.left.val);
					}
				}
				left = true;
			} else {
				// 从左边开始
				for (int i = tns.size() - 1; i >= 0; i--) {
					TreeNode node = tns.get(i);
					if (node.left != null) {
						tnsTemp.add(node.left);
						nodesTemp.add(node.left.val);
					}
					if (node.right != null) {
						tnsTemp.add(node.right);
						nodesTemp.add(node.right.val);
					}
				}
				left = false;
			}
			tns = new ArrayList<>();
			nodes = new ArrayList<>();
			tns.addAll(tnsTemp);
			nodes.addAll(nodesTemp);
			if (nodes.size() == 0)
				break;
			nodeList.add(nodes);
		}
		return nodeList;
	}

}
