package com.leetcode_cn.medium;

import java.util.ArrayList;
import java.util.List;

/***************实现 Trie (前缀树)************/
/**
 * 
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * 
 * 示例:
 * 
 * Trie trie = new Trie();
 * 
 * trie.insert("apple");
 * 
 * trie.search("apple"); // 返回 true
 * 
 * trie.search("app"); // 返回 false
 * 
 * trie.startsWith("app"); // 返回 true
 * 
 * trie.insert("app");
 * 
 * trie.search("app"); // 返回 true
 * 
 * 说明:
 * 
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 
 * 保证所有输入均为非空字符串。
 * 
 * @author ffj
 *
 */
public class ImplementTriePrefixTree {

	class TrieNode {

		// R links to node children
		private TrieNode[] links;

		private final int R = 26;

		private boolean isEnd;

		public TrieNode() {
			links = new TrieNode[R];
		}

		public boolean containsKey(char ch) {
			return links[ch - 'a'] != null;
		}

		public TrieNode get(char ch) {
			return links[ch - 'a'];
		}

		public void put(char ch, TrieNode node) {
			links[ch - 'a'] = node;
		}

		public void setEnd() {
			isEnd = true;
		}

		public boolean isEnd() {
			return isEnd;
		}
	}

	class Trie1 {
		private TrieNode root;

		public Trie1() {
			root = new TrieNode();
		}

		// Inserts a word into the trie.
		public void insert(String word) {
			TrieNode node = root;
			for (int i = 0; i < word.length(); i++) {
				char currentChar = word.charAt(i);
				if (!node.containsKey(currentChar)) {
					node.put(currentChar, new TrieNode());
				}
				node = node.get(currentChar);
			}
			node.setEnd();
		}

		// search a prefix or whole key in trie and
		// returns the node where search ends
		private TrieNode searchPrefix(String word) {
			TrieNode node = root;
			for (int i = 0; i < word.length(); i++) {
				char curLetter = word.charAt(i);
				if (node.containsKey(curLetter)) {
					node = node.get(curLetter);
				} else {
					return null;
				}
			}
			return node;
		}

		// Returns if the word is in the trie.
		public boolean search(String word) {
			TrieNode node = searchPrefix(word);
			return node != null && node.isEnd();
		}
	}

	class Trie {

		List<String> list;

		/** Initialize your data structure here. */
		public Trie() {
			list = new ArrayList<>();
		}

		/** Inserts a word into the trie. */
		public void insert(String word) {
			list.add(word);
		}

		/** Returns if the word is in the trie. */
		public boolean search(String word) {
			return list.contains(word);
		}

		/**
		 * Returns if there is any word in the trie that starts with the given prefix.
		 */
		public boolean startsWith(String prefix) {
			for (String str : list) {
				if (str.startsWith(prefix))
					return true;
			}
			return false;
		}
	}

}
