package com.leetcode_cn.medium;

/**********************删除链表的倒数第N个节点***********************/
/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 
 * 示例：
 * 
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5. 说明：
 * 
 * 给定的 n 保证是有效的。
 * 
 * 进阶：
 * 
 * 你能尝试使用一趟扫描实现吗？
 * 
 * @author ffj
 *
 */
public class RemoveNthNodeFromEndOfList {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	/**
	 * 两次循环
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {

		ListNode temp = new ListNode(0);
		temp.next = head;
		ListNode first = head;
		int len = 0;
		while (first != null) { // 计算长度
			len++;
			first = first.next;
		}
		len -= n;
		first = temp;
		while (len > 0) { // 遍历找到移除节点前节点
			len--;
			first = first.next;
		}
		first.next = first.next.next; // 移除节点
		return temp.next;

	}

	/**
	 * 一次循环
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd1(ListNode head, int n) {
		ListNode temp = new ListNode(0);
		temp.next = head;
		ListNode first = temp, second = temp;
		for (int i = 1; i < n + 1; i++) {
			first = first.next;
		}
		while (first != null) { // 当first节点为null时 second节点刚好在移除节点前一节点
			first = first.next;
			second = second.next;
		}
		second.next = second.next.next;
		return temp.next;

	}
}
