package com.leetcode_cn.easy;

/***************旋转字符串**********/
/**
 * 给定两个字符串, A 和 B。
 * 
 * A 的旋转操作就是将 A 最左边的字符移动到最右边。 例如, 若 A = 'abcde'，在移动一次之后结果就是'bcdea'
 * 。如果在若干次旋转操作之后，A 能变成B，那么返回True。
 * 
 * 示例 1:
 * 
 * 输入: A = 'abcde', B = 'cdeab' 输出: true
 * 
 * 示例 2:
 * 
 * 输入: A = 'abcde', B = 'abced' 输出: false
 * 
 * 注意：
 * 
 * A 和 B 长度不超过 100。
 * 
 * @author ffj
 *
 */
public class RotateString {

	public static void main(String[] args) {
		String A = "abcde", B = "abced";
		System.out.println(new RotateString().rotateString(A, B));
	}

	public boolean rotateString(String A, String B) {
		int len = A.length();
		if (len != B.length())
			return false;
		if (A.equals(B))
			return true;
		String copyA = A;
		for (int i = 0; i < len; i++) {
			copyA = copyA.substring(1) + copyA.substring(0, 1);
			if (copyA.equals(B))
				return true;
		}
		return false;
	}

	/**
	 * 妙不可言
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public boolean rotateString1(String A, String B) {
		if (A.length() != B.length())
			return false;
		return (A + A).contains(B);
	}

}
