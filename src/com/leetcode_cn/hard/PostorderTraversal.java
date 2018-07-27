package com.leetcode_cn.hard;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*********二叉树的后序遍历*********************/
/**
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3] 1 \ 2 / 3
 * 
 * 输出: [3,2,1]
 * 
 * @author ffj
 *
 */
public class PostorderTraversal {

	/**
	 * 迭代
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> postorderTraversal(TreeNode root) {
		LinkedList<Integer> ans = new LinkedList<>();
		Stack<TreeNode> stack = new Stack<>();
		if (root == null)
			return ans;

		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pop();
			ans.addFirst(cur.val);
			if (cur.left != null) {
				stack.push(cur.left);
			}
			if (cur.right != null) {
				stack.push(cur.right);
			}
		}
		return ans;
	}

	/**
	 * 递归
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> postorderTraversal1(TreeNode root) {
		List<Integer> res = new LinkedList<>();
		helper(res, root);
		return res;
	}

	public void helper(List<Integer> list, TreeNode root) {
		if (root == null)
			return;
		helper(list, root.left);
		helper(list, root.right);
		list.add(root.val);
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

}
