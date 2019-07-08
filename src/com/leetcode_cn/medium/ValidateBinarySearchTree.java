package com.leetcode_cn.medium;

import java.util.Deque;
import java.util.LinkedList;

/*************验证二叉搜索树*********/
/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 
 * 假设一个二叉搜索树具有如下特征：
 * 
 * 节点的左子树只包含小于当前节点的数。
 * 
 * 节点的右子树只包含大于当前节点的数。
 * 
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 
 * 示例 1:
 * 
 * 输入:
 * 
 * 2
 * 
 * / \
 * 
 * 1 3
 * 
 * 输出: true
 * 
 * 示例 2:
 * 
 * 输入:
 * 
 * 5
 * 
 * / \
 * 
 * 1 4
 * 
 *   / \
 * 
 *   3 6
 * 
 * 输出: false
 * 
 * 解释:
 * 
 * 输入为: [5,1,4,null,null,3,6]。
 * 
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 * 
 * @author ffj
 *
 */
public class ValidateBinarySearchTree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public boolean isValidBST(TreeNode root) {

		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode p = root;
		TreeNode pre = null;
		// 中序遍历
		while (p != null || !stack.isEmpty()) {
			while (p != null) {
				stack.push(p);
				p = p.left;
			}
			p = stack.pop();
			// 如不满足则 false 前一节点与该节点作比较
			if (pre != null && pre.val >= p.val)
				return false;
			pre = p;
			p = p.right;
		}
		return true;
	}

}
