package com.leetcode_cn.easy;

/***********翻转二叉树********/
/**
 * 翻转一棵二叉树。
 * 
 * 示例：
 * 
 * 输入：
 * 
 * 4
 * 
 * / \
 * 
 * 2 7
 * 
 * / \ / \
 * 
 * 1 3 6 9
 * 
 * 输出：
 * 
 * 4
 * 
 * / \
 * 
 * 7 2
 * 
 * / \ / \
 * 
 * 9 6 3 1
 * 
 * 备注: 这个问题是受到 Max Howell 的 原问题 启发的 ：
 * 
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 * 
 * @author ffj
 *
 */
public class InvertBinaryTree {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public TreeNode invertTree(TreeNode root) {

		// 翻转二叉树
		helper(root);
		return root;

	}

	/**
	 * 实现该节点下的左右节点交换
	 * 
	 * @param node
	 */
	private void helper(TreeNode node) {
		if (node == null)
			return;
		// 交换节点
		TreeNode temp = node.left;
		node.left = node.right;
		node.right = temp;
		// 递归遍历所有节点
		helper(node.left);
		helper(node.right);
	}

}
