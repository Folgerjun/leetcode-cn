package com.leetcode_cn.hard;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*********************合并K个排序链表**************/
/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 
 * 示例:
 * 
 * 输入: [
 * 
 * 1->4->5,
 * 
 * 1->3->4,
 * 
 * 2->6
 * 
 * ]
 * 
 * 输出: 1->1->2->3->4->4->5->6
 * 
 * @author ffj
 *
 */
public class MergeKSortedLists {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode mergeKLists(ListNode[] lists) {

		ListNode list = null;
		// 两两合并
		for (int i = 0; i < lists.length; i++) {
			list = helper(list, lists[i]);
		}
		return list;

	}

	/**
	 * 合并两个链表
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	private ListNode helper(ListNode list1, ListNode list2) {

		if (list1 == null)
			return list2;
		if (list2 == null)
			return list1;
		if (list1.val < list2.val) {
			list1.next = helper(list1.next, list2);
			return list1;
		} else {
			list2.next = helper(list1, list2.next);
			return list2;
		}

	}

	/**
	 * 讨论中方法 队列排序
	 * 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists1(List<ListNode> lists) {
		if (lists == null || lists.size() == 0)
			return null;
		// 排序 头结点大小升序
		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				if (o1.val < o2.val)
					return -1;
				else if (o1.val == o2.val)
					return 0;
				else
					return 1;
			}
		});

		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;
		// 循环依次加入队列中
		for (ListNode node : lists)
			if (node != null)
				queue.add(node);

		while (!queue.isEmpty()) {
			// 将最小的值取出
			tail.next = queue.poll();
			tail = tail.next;
			// 再将剩余链表放入队列中
			if (tail.next != null)
				queue.add(tail.next);
		}
		return dummy.next;
	}

}
