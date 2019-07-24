package com.leetcode_cn.easy;

import java.util.ArrayList;
import java.util.List;

/*******************对称二叉树*************/
/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * 
 * 1
 * 
 * / \
 * 
 * 2 2
 * 
 * / \ / \
 * 
 * 3 4 4 3
 * 
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * 
 * 1
 * 
 * / \
 * 
 * 2 2
 * 
 * \ \
 * 
 * 3 3
 * 
 * 说明:
 * 
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 * 
 * @author ffj
 *
 */
public class SymmetricTree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	/**
	 * 递归判断
	 * 
	 * @param root
	 * @return
	 */
	public boolean isSymmetric1(TreeNode root) {
		if (root == null)
			return true;
		return compare(root.left, root.right);
	}

	private boolean compare(TreeNode left, TreeNode right) {

		// 两个都为 null
		if (left == null && right == null)
			return true;
		// 其中有一个为 null
		if (left == null || right == null)
			return false;
		// 两个节点值相同 接着往下对比 | 左节点的左孩子与右节点的右孩子 / 左节点的右孩子与右节点的左孩子对比
		return (left.val == right.val) && compare(left.left, right.right) && compare(left.right, right.left);
	}

	/**
	 * 层次遍历判断
	 * 
	 * @param root
	 * @return
	 */
	public boolean isSymmetric(TreeNode root) {

		if (root == null)
			return true;
		List<TreeNode> nodes = new ArrayList<>();
		List<TreeNode> nodesTem = new ArrayList<>();
		nodes.add(root);
		while (true) {
			if (nodes.size() == 0)
				return true;
			// 挨个拼接 看是否是回文
			String str = "";
			for (TreeNode td : nodes) {
				TreeNode leftNode = td.left;
				TreeNode rightNode = td.right;
				if (leftNode != null) {
					nodesTem.add(leftNode);
					str += leftNode.val + ",";
				} else {
					str += "#,";
				}
				if (rightNode != null) {
					nodesTem.add(rightNode);
					str += rightNode.val + ",";
				} else {
					str += "#,";
				}
			}
			// 判断回文
			if (!check(str)) {
				return false;
			}
			nodes.clear();
			nodes.addAll(nodesTem);
			nodesTem.clear();
		}

	}

	/**
	 * 判断字符串是否回文
	 * 
	 * @param str
	 * @return
	 */
	private boolean check(String str) {
		// 去掉最后一个逗号
		str = str.substring(0, str.length() - 1);
		String[] strs = str.split(",");
		int len = strs.length;
		if (len <= 1)
			return true;
		int head = 0, foot = len - 1;
		for (; head <= foot; head++, foot--) {
			if (!strs[head].equals(strs[foot]))
				return false;
		}
		return true;
	}

}
