package com.leetcode_cn.easy;

/********************二叉树的直径****************/
/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 * 
 * 示例 : 给定二叉树
 * 
 * 1
 * 
 * / \
 * 
 * 2 3
 * 
 * / \
 * 
 * 4 5
 * 
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * 
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 * 
 * @author ffj
 *
 */
public class DiameterOfBinaryTree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	int ans;

	public int diameterOfBinaryTree(TreeNode root) {

		ans = 1;
		helper(root);
		return ans - 1;
	}

	/**
	 * 返回该节点左右较长的那条
	 * 
	 * @param node
	 * @return
	 */
	private int helper(TreeNode node) {
		if (node == null)
			return 0;
		int left = helper(node.left);
		int right = helper(node.right);
		ans = Math.max(ans, left + right + 1);
		return Math.max(left, right) + 1;

	}
}
