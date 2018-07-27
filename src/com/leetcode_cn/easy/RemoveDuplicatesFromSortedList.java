package com.leetcode_cn.easy;

/***************** 删除排序链表中的重复元素 ****************************/
/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 
 * 示例 1:
 * 
 * 输入: 1->1->2 输出: 1->2
 * 
 * 示例 2:
 * 
 * 输入: 1->1->2->3->3 输出: 1->2->3
 * 
 * @author ffj
 *
 */
public class RemoveDuplicatesFromSortedList {
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode p = head;
		ListNode q = head.next;
		while (p != null && q != null) {
			while (q != null && q.val == p.val) { // 值相等一直后移
				q = q.next;
			}
			p.next = q; // 赋值
			p = q; // p后移
			if (q != null) {
				q = q.next; // 后移
			}
		}
		return head;
	}

	/**
	 * 官网解法
	 * 
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates1(ListNode head) {
		ListNode current = head;
		while (current != null && current.next != null) {
			if (current.next.val == current.val) {
				current.next = current.next.next;
			} else {
				current = current.next;
			}
		}
		return head;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

}
