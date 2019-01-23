package com.leetcode_cn.easy;

/*****************反转链表***************/
/**
 * 反转一个单链表。
 * 
 * 示例:
 * 
 * 输入: 1->2->3->4->5->NULL
 * 
 * 输出: 5->4->3->2->1->NULL
 * 
 * 进阶: 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * 
 * @author ffj
 *
 */
public class ReverseLinkedList {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	/**
	 * 递归
	 * 
	 * @param head
	 * @return
	 */
	public ListNode reverseList(ListNode head) {
		ListNode reverseList = null;
		return helper(head, reverseList);
	}

	private ListNode helper(ListNode head, ListNode reverseList) {
		if (head == null) // 反转结束
			return reverseList;
		// 节点指针变换
		ListNode tempNode = head.next;
		head.next = reverseList;
		return helper(tempNode, head);
	}

	/**
	 * 迭代
	 * 
	 * @param head
	 * @return
	 */
	public ListNode reverseList1(ListNode head) {

		ListNode newHead = null;
		while (head != null) { // 遍历
			ListNode next = head.next;
			head.next = newHead;
			newHead = head;
			head = next;
		}
		return newHead;
	}
}
