package com.leetcode_cn.medium;

/**********两数相加********https://leetcode-cn.com/articles/add-two-numbers/*****/
/**
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 * 
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 
 * 示例：
 * 
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8 原因：342 + 465 = 807
 * 
 * @author ffj
 *
 */
public class AddTwoNumbers {

	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);

		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);
		ListNode result = addTwoNumbers(l1, l2);
		System.out.println(result.val + "->" + result.next.val + "->" + result.next.next.val);
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		ListNode dummyHead = new ListNode(0);
		ListNode p = l1, q = l2, curr = dummyHead;
		int carry = 0;
		while (p != null || q != null) {
			int x = (p != null) ? p.val : 0;
			int y = (q != null) ? q.val : 0;
			int sum = carry + x + y;
			// 取整
			carry = sum / 10;
			// 取余作为该值
			curr.next = new ListNode(sum % 10);
			// 游标后移
			curr = curr.next;
			if (p != null) // p q 还有值就后移
				p = p.next;
			if (q != null)
				q = q.next;
		}
		if (carry > 0) {
			// 进位
			curr.next = new ListNode(carry);
		}
		return dummyHead.next;
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

}
