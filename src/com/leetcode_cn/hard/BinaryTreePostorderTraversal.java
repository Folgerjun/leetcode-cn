package com.leetcode_cn.hard;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**************二叉树的后序遍历***************/
/**
 * 给定一个二叉树，返回它的 后序 遍历。
 * 
 * 示例:
 * 
 * 输入: [1,null,2,3]
 * 
 * 1
 * 
 * \
 * 
 * 2
 * 
 * /
 * 
 * 3
 * 
 * 输出: [3,2,1]
 * 
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * 
 * @author ffj
 *
 */
public class BinaryTreePostorderTraversal {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	/**
	 * 根 -> 右 -> 左 存入，遍历完全后从前往后即是 左 -> 右 -> 根
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
			// 在最前面存值
			ans.addFirst(cur.val);
			// 先左结点入栈
			if (cur.left != null) {
				stack.push(cur.left);
			}
			// 再是右结点入栈
			if (cur.right != null) {
				stack.push(cur.right);
			}
		}
		return ans;
	}

}
