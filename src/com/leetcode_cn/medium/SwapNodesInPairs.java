package com.leetcode_cn.medium;

/*************两两交换链表中的节点************/
/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 
 * 示例:
 * 
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * 
 * 说明:
 * 
 * 你的算法只能使用常数的额外空间。
 * 
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 
 * @author ffj
 *
 */
public class SwapNodesInPairs {

	public static void main(String[] args) {
		ListNode head = new SwapNodesInPairs().new ListNode(1);
		head.next = new SwapNodesInPairs().new ListNode(2);
		head.next.next = new SwapNodesInPairs().new ListNode(3);
		head.next.next.next = new SwapNodesInPairs().new ListNode(4);
		System.out.println(new SwapNodesInPairs().swapPairs1(head));

	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			return "ListNode [val=" + val + ", next=" + next + "]";
		}

	}

	/**
	 * 交换节点
	 * 
	 * @param head
	 * @return
	 */
	public ListNode swapPairs(ListNode head) {

		ListNode list = new ListNode(0);
		list.next = head;
		ListNode root = list; // 指针
		while (root.next != null && root.next.next != null) {
			ListNode first = root.next;
			ListNode second = root.next.next;
			first.next = second.next;
			root.next = second;
			root.next.next = first;
			root = root.next.next;
		}
		return list.next;

	}

	/**
	 * 只交换值
	 * 
	 * @param head
	 * @return
	 */
	public ListNode swapPairs1(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode root = head;
		while (root != null && root.next != null) {
			int temp = root.val;
			root.val = root.next.val;
			root.next.val = temp;
			root = root.next.next;
		}
		return head;
	}

}
