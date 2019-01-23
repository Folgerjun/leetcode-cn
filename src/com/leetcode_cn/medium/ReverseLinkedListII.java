package com.leetcode_cn.medium;

/*************反转链表 II**********/
/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 
 * 说明: 1 ≤ m ≤ n ≤ 链表长度。
 * 
 * 示例:
 * 
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 
 * 输出: 1->4->3->2->5->NULL
 * 
 * @author ffj
 *
 */
public class ReverseLinkedListII {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null)
			return null;
		// 新建一个节点并指向 head
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		// pre 为需要反转的前节点
		for (int i = 0; i < m - 1; i++)
			pre = pre.next;

		// 需要反转的节点 双指针
		ListNode start = pre.next;
		ListNode then = start.next;

		// 反转节点
		for (int i = 0; i < n - m; i++) {
			start.next = then.next;
			then.next = pre.next;
			pre.next = then;
			then = start.next;
		}
		return dummy.next;
	}
}
