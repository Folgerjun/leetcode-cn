package com.leetcode_cn.easy;

/*********************将有序数组转换为二叉搜索树************/
/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 
 * 示例:
 * 
 * 给定有序数组: [-10,-3,0,5,9],
 * 
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * 
 * 0 / \ -3 9 / / -10 5
 * 
 * @author ffj
 *
 */
public class ConvertSortedArrayToBinarySearchTree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	/**
	 * 递归 分成左右两段进行
	 * 
	 * @param nums
	 * @return
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums.length == 0)
			return null;
		return helper(nums, 0, nums.length - 1);
	}

	public TreeNode helper(int[] nums, int low, int high) {
		if (low > high)
			return null;
		int mid = (low + high) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = helper(nums, low, mid - 1);
		root.right = helper(nums, mid + 1, high);
		return root;
	}

}
