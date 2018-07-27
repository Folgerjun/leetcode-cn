package com.leetcode_cn.medium;

/***************************最大二叉树*********************/
/**
 * 
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 * 
 * 二叉树的根是数组中的最大元素。 左子树是通过数组中最大值左边部分构造出的最大二叉树。 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 * 
 * Example 1:
 * 
 * 输入: [3,2,1,6,0,5] 输入: 返回下面这棵树的根节点：
 * 
 * 6 / \ 3 5 \ / 2 0 \ 1 注意:
 * 
 * 给定的数组的大小在 [1, 1000] 之间。
 * 
 * @author ffj
 *
 */
public class MaximumBinaryTree {

	public TreeNode constructMaximumBinaryTree(int[] nums) {
		if (nums.length == 0)
			return null;
		int maxNum = 0, maxIndex = 0;
		for (int i = 0; i < nums.length; i++) { // 循环获取最大的那个值
			if (nums[i] > maxNum) {
				maxNum = nums[i];
				maxIndex = i;
			}
		}
		TreeNode node = new TreeNode(maxNum); // 最大数 节点
		int[] leftNums = new int[maxIndex]; // 分割成左右两部分 分别设置长度
		int[] rightNums = new int[nums.length - maxIndex - 1];
		System.arraycopy(nums, 0, leftNums, 0, maxIndex); // 复制数组
		System.arraycopy(nums, maxIndex + 1, rightNums, 0, nums.length - maxIndex - 1);
		node.left = constructMaximumBinaryTree(leftNums); // 递归
		node.right = constructMaximumBinaryTree(rightNums);
		return node;

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
	 * 官网解法一 递归
	 * 
	 * @param nums
	 * @return
	 */
	public TreeNode constructMaximumBinaryTree1(int[] nums) {
		return construct(nums, 0, nums.length);
	}

	public TreeNode construct(int[] nums, int l, int r) {
		if (l == r)
			return null;
		int max_i = max(nums, l, r);
		TreeNode root = new TreeNode(nums[max_i]);
		root.left = construct(nums, l, max_i);
		root.right = construct(nums, max_i + 1, r);
		return root;
	}

	public int max(int[] nums, int l, int r) { // 获取最大值下标
		int max_i = l;
		for (int i = l; i < r; i++) {
			if (nums[max_i] < nums[i])
				max_i = i;
		}
		return max_i;
	}

}
