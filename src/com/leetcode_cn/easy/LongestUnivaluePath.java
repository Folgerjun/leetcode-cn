package com.leetcode_cn.easy;

/*******************最长同值路径***********************/
/**
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 * 
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 * 
 * 示例 1:
 * 
 * 输入:
 * 
 * 5
 * 
 * / \
 * 
 * 4 5
 * 
 * / \ \
 * 
 * 1 1 5
 * 
 * 输出: 2
 * 
 * 示例 2:
 * 
 * 输入:
 * 
 * 1
 * 
 * / \
 * 
 * 4 5
 * 
 * / \ \
 * 
 * 4 4 5
 * 
 * 输出: 2
 * 
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 * 
 * @author ffj
 *
 */
public class LongestUnivaluePath {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	/**
	 * 解法一
	 * 
	 * @param root
	 * @return
	 */
	public int longestUnivaluePath(TreeNode root) {
		if (root == null)
			return 0;
		int value = 0;

		helper(root, root, value);

		return value;
	}

	public int helper(TreeNode node, TreeNode parent, int value) {
		if (node == null)
			return 0;
		int left = helper(node.left, node, value);
		int right = helper(node.right, node, value);
		left = (node.left != null && node.left.val == node.val) ? left + 1 : 0;
		right = (node.right != null && node.right.val == node.val) ? right + 1 : 0;
		value = Math.max(value, left + right);
		return Math.max(left, right); // 返回较大的值再进行计算
	}

	/**
	 * 解法二
	 * 
	 * @param root
	 * @return
	 */
	public int longestUnivaluePath1(TreeNode root) {
		if (root == null)
			return 0;
		int value = Math.max(longestUnivaluePath1(root.left), longestUnivaluePath1(root.right));
		return Math.max(value, helper1(root.left, root.val) + helper1(root.right, root.val));
	}

	public int helper1(TreeNode node, int parent) {
		if (node == null || node.val != parent)
			return 0;

		return Math.max(helper1(node.left, node.val), helper1(node.right, node.val)) + 1;
	}

}
