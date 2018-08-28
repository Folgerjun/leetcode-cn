package com.leetcode_cn.medium;

/*****************************从前序与中序遍历序列构造二叉树********************/
/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 
 * 注意: 你可以假设树中没有重复的元素。
 * 
 * 例如，给出
 * 
 * 前序遍历 preorder = [3,9,20,15,7] 中序遍历 inorder = [9,3,15,20,7]
 * 
 * 返回如下的二叉树：
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
 * @author ffj
 *
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return helper(0, 0, inorder.length - 1, preorder, inorder);
	}

	/**
	 * 返回根节点
	 * 
	 * @param preStart
	 *            根节点在先序遍历数组中位置
	 * @param inStart
	 *            中序遍历搜索起始位置
	 * @param inEnd
	 *            中序遍历搜索结束位置
	 * @param preorder
	 *            先序遍历数组
	 * @param inorder
	 *            中序遍历数组
	 * @return
	 */
	private TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
		if (preStart > preorder.length - 1 || inStart > inEnd)
			return null;
		TreeNode root = new TreeNode(preorder[preStart]);
		int inIndex = 0;
		for (int i = inStart; i <= inEnd; i++) { // 找到根节点对应的值
			if (inorder[i] == root.val)
				inIndex = i;
		}
		// 递归 划分左右节点
		root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
		root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
		return root;
	}
}
