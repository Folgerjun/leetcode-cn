package com.leetcode_cn.easy;

/*********************相同的树***************/
/**
 * 
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * 
 * 示例 1:
 * 
 * 输入:
 * 
 * -1-- 1
 * 
 * / \ / \
 * 
 * 2 3 2 3
 * 
 * [1,2,3], [1,2,3]
 * 
 * 输出: true
 * 
 * 示例 2:
 * 
 * 输入:
 * 
 * 1 1
 * 
 * / \
 * 
 * 2 2
 * 
 * [1,2], [1,null,2]
 * 
 * 输出: false
 * 
 * 示例 3:
 * 
 * 输入:
 * 
 * -1-- 1
 * 
 * / \ / \
 * 
 * 2 1 1 2
 * 
 * [1,2,1], [1,1,2]
 * 
 * 输出: false
 * 
 * @author ffj
 *
 */
public class SameTree {

	public static void main(String[] args) {

	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	/**
	 * DFS
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {

		if (p == null && q == null)
			return true;

		if ((p == null && q != null) || (p != null && q == null) || p.val != q.val)
			return false;
		boolean value1, value2;
		value1 = isSameTree(p.left, q.left);
		value2 = isSameTree(p.right, q.right);
		return value1 && value2;

	}

}
