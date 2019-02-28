package com.leetcode_cn.medium;

/************旋转链表************/
/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 
 * 示例 1:
 * 
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 
 * 输出: 4->5->1->2->3->NULL
 * 
 * 解释:
 * 
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 
 * 示例 2:
 * 
 * 输入: 0->1->2->NULL, k = 4
 * 
 * 输出: 2->0->1->NULL
 * 
 * 解释:
 * 
 * 向右旋转 1 步: 2->0->1->NULL
 * 
 * 向右旋转 2 步: 1->2->0->NULL
 * 
 * 向右旋转 3 步: 0->1->2->NULL
 * 
 * 向右旋转 4 步: 2->0->1->NULL
 * 
 * @author ffj
 *
 */
public class RotateList {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode rotateRight(ListNode head, int k) {

		if (head == null)
			return head;
		ListNode temp = head;
		int length = 1;
		// 计算链表长度
		while (temp.next != null) {
			temp = temp.next;
			length++;
		}
		// 链表后拼接到前头的位数
		int num = k % length;

		if (num == 0)
			return head;

		ListNode tempNode = head;
		while (++num != length)
			tempNode = tempNode.next;
		// 新的 head 节点
		ListNode result = tempNode.next;
		tempNode.next = null;
		// 原先的尾节点指向原先的头结点
		temp.next = head;

		return result;

	}

	/**
	 * 讨论中解法
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode rotateRight1(ListNode head, int n) {
		if (head == null || head.next == null)
			return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode fast = dummy, slow = dummy;

		int i;
		for (i = 0; fast.next != null; i++)// Get the total length
			fast = fast.next;

		for (int j = i - n % i; j > 0; j--) // Get the i-n%i th node
			slow = slow.next;

		fast.next = dummy.next; // Do the rotation
		dummy.next = slow.next;
		slow.next = null;

		return dummy.next;
	}
}
