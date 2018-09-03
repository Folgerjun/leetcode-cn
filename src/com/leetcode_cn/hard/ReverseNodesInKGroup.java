package com.leetcode_cn.hard;

import java.util.Stack;

/************************k个一组翻转链表****************/
/**
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
 * 
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 * 
 * 示例 :
 * 
 * 给定这个链表：1->2->3->4->5
 * 
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * 
 * 说明 :
 * 
 * 你的算法只能使用常数的额外空间。 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 
 * @author ffj
 *
 */
public class ReverseNodesInKGroup {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	/**
	 * 创建新链表 挨个赋值
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		if (k == 1 || head == null)
			return head;
		ListNode node = new ListNode(0);
		ListNode res = node;
		Stack<Integer> stack = new Stack<>();

		while (true) {
			while (true) { // 一次压入一批
				stack.push(head.val);
				head = head.next;
				if (stack.size() == k || head == null)
					break;
			}
			if (head == null && stack.size() != k) { // 最后不满足条件的值不改变顺序
				for (int i = 0; i < stack.size(); i++) {
					node.next = new ListNode(stack.get(i));
					node = node.next;
				}
				break;
			} else {
				while (!stack.isEmpty()) { // 挨个取出
					node.next = new ListNode(stack.pop());
					node = node.next;
				}
				if (head == null) // 表示刚好是k的整数倍
					break;
			}
		}
		return res.next;

	}

	/**
	 * 讨论中解法
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroup1(ListNode head, int k) {
		ListNode curr = head;
		int count = 0;
		while (curr != null && count != k) { // find the k+1 node
			curr = curr.next;
			count++;
		}
		if (count == k) { // if k+1 node is found
			curr = reverseKGroup1(curr, k); // reverse list with k+1 node as head
			// head - head-pointer to direct part,
			// curr - head-pointer to reversed part;
			while (count-- > 0) { // reverse current k-group:
				ListNode tmp = head.next; // tmp - next head in direct part
				head.next = curr; // preappending "direct" head to the reversed list
				curr = head; // move head of reversed part to a new node
				head = tmp; // move "direct" head to the next node in direct part
			}
			head = curr;
		}
		return head;
	}

}
