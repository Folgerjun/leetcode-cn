package com.leetcode_cn.medium;

/************分隔链表*********/
/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 
 * 你应当保留两个分区中每个节点的初始相对位置。
 * 
 * 示例:
 * 
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 
 * 输出: 1->2->2->4->3->5
 * 
 * @author ffj
 *
 */
public class PartitionList {

	/**
	 * 重新两链表 拼接
	 * 
	 * @param head
	 * @param x
	 * @return
	 */
	public ListNode partition1(ListNode head, int x) {
		ListNode one = new ListNode(0);
		ListNode two = new ListNode(0);
		ListNode first = one, second = two;
		while (head != null) {
			int value = head.val;
			if (value < x) {
				first.next = head;
				first = first.next;
			} else {
				second.next = head;
				second = second.next;
			}
			head = head.next;
		}
		first.next = two.next;
		second.next = null; // 上面节点不是 new 的，引用所以要防止后续节点，若是 new ListNode(value) 则可不用置 null
		return one.next;
	}

	/**
	 * 插入
	 * 
	 * @param head
	 * @param x
	 * @return
	 */
	public ListNode partition(ListNode head, int x) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode p = dummy;
		ListNode tail = dummy;
		while (p != null && p.next != null) {
			if (p.next.val >= x)
				p = p.next;
			else {
				if (p == tail) { // don't forget the edge cases when p==tail
					tail = tail.next;
					p = p.next;
				} else {// 节点交换
					ListNode tmp = p.next;
					p.next = tmp.next;
					tmp.next = tail.next;
					tail.next = tmp;
					tail = tmp; // don't forget to move tail.
				}
			}
		}
		return dummy.next;
	}

}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
