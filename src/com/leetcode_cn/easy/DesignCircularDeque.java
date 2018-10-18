package com.leetcode_cn.easy;

import java.util.LinkedList;
import java.util.List;

/**********设计循环双端队列***********/
/**
 * 设计实现双端队列。
 * 
 * 你的实现需要支持以下操作：
 * 
 * MyCircularDeque(k)：构造函数,双端队列的大小为k。
 * 
 * insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 * 
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * 
 * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * 
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * 
 * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * 
 * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * 
 * isEmpty()：检查双端队列是否为空。
 * 
 * isFull()：检查双端队列是否满了。
 * 
 * 示例：
 * 
 * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * 
 * circularDeque.insertLast(1); // 返回 true
 * 
 * circularDeque.insertLast(2); // 返回 true
 * 
 * circularDeque.insertFront(3); // 返回 true
 * 
 * circularDeque.insertFront(4); // 已经满了，返回 false
 * 
 * circularDeque.getRear(); // 返回 32
 * 
 * circularDeque.isFull(); // 返回 true
 * 
 * circularDeque.deleteLast(); // 返回 true
 * 
 * circularDeque.insertFront(4); // 返回 true
 * 
 * circularDeque.getFront(); // 返回 4
 * 
 * 
 * 
 * 提示：
 * 
 * 所有值的范围为 [1, 1000]
 * 
 * 操作次数的范围为 [1, 1000]
 * 
 * 请不要使用内置的双端队列库。
 * 
 * @author ffj
 *
 */
public class DesignCircularDeque {

	class MyCircularDeque {

		private List<Integer> list_deque;
		// 设定大小
		private int size;
		// 实际大小
		private int real_size;

		/** Initialize your data structure here. Set the size of the deque to be k. */
		public MyCircularDeque(int k) { // 初始大小
			this.list_deque = new LinkedList<>();
			this.size = k;
		}

		/**
		 * Adds an item at the front of Deque. Return true if the operation is
		 * successful.
		 */
		public boolean insertFront(int value) { // 添加到头部
			if (real_size < size) { // 可以添加
				list_deque.add(0, value);
				real_size++; // 实际大小加一
				return true;
			}
			return false;
		}

		/**
		 * Adds an item at the rear of Deque. Return true if the operation is
		 * successful.
		 */
		public boolean insertLast(int value) { // 添加到尾部
			if (real_size < size) { // 可以添加
				list_deque.add(value);
				real_size++; // 实际大小加一
				return true;
			}
			return false;
		}

		/**
		 * Deletes an item from the front of Deque. Return true if the operation is
		 * successful.
		 */
		public boolean deleteFront() { // 头部删除一个元素
			if (real_size > 0) { // 有元素
				list_deque.remove(0);
				real_size--; // 实际大小减一
				return true;
			}
			return false;
		}

		/**
		 * Deletes an item from the rear of Deque. Return true if the operation is
		 * successful.
		 */
		public boolean deleteLast() { // 尾部删除一个元素
			if (real_size > 0) { // 有元素
				list_deque.remove(--real_size); // 删除并大小减一
				return true;
			}
			return false;
		}

		/** Get the front item from the deque. */
		public int getFront() { // 头部获得一个元素
			if (real_size > 0)
				return list_deque.get(0);
			return -1;
		}

		/** Get the last item from the deque. */
		public int getRear() { // 获得尾部最后一个元素
			if (real_size > 0)
				return list_deque.get(real_size - 1);
			return -1;
		}

		/** Checks whether the circular deque is empty or not. */
		public boolean isEmpty() { // 检查是否为空
			return real_size == 0;
		}

		/** Checks whether the circular deque is full or not. */
		public boolean isFull() { // 检查是否满了
			return real_size == size;
		}
	}

}
