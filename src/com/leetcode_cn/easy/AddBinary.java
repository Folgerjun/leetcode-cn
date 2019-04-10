package com.leetcode_cn.easy;

/************二进制求和**********/
/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * 
 * 输入为非空字符串且只包含数字 1 和 0。
 * 
 * 示例 1:
 * 
 * 输入: a = "11", b = "1" 输出: "100"
 * 
 * 示例 2:
 * 
 * 输入: a = "1010", b = "1011" 输出: "10101"
 * 
 * @author ffj
 *
 */
public class AddBinary {

	public static void main(String[] args) {
		String result = new AddBinary().addBinary("11", "1");
		System.out.println(result);
	}

	public String addBinary(String a, String b) {

		StringBuilder sba = new StringBuilder(a);
		StringBuilder sbb = new StringBuilder(b);
		sba.reverse();
		sbb.reverse();
		String aa = sba.toString(), bb = sbb.toString();

		return helper(aa, bb);
	}

	/**
	 * 遍历 分类 是否有进位
	 * 
	 * @param aa
	 * @param bb
	 * @return
	 */
	private String helper(String aa, String bb) {
		int maxL = Math.max(aa.length(), bb.length());
		int minL = Math.min(aa.length(), bb.length());
		StringBuilder sb = new StringBuilder();
		int index = 0; // 下标
		boolean flag = false; // 是否进位
		while (true) {
			if (index < minL) {
				if (flag) {
					// 有进位
					if (aa.charAt(index) == '1' && bb.charAt(index) == '1') // 都是 1
						sb.append("1");
					else if (aa.charAt(index) == '1' || bb.charAt(index) == '1') // 有一个 1
						sb.append("0");
					else {// 都是 0
						sb.append("1");
						flag = false;
					}
				} else {
					// 无进位
					if (aa.charAt(index) == '1' && bb.charAt(index) == '1') { // 都是 1
						sb.append("0");
						flag = true;
					} else if (aa.charAt(index) == '1' || bb.charAt(index) == '1') // 有一个 1
						sb.append("1");
					else // 都是 0
						sb.append("0");
				}
			} else {
				if (index == maxL) {// 最后一位
					if (flag)
						sb.append("1");
					break;
				} else if (index >= aa.length()) {
					// bb 较长
					if (flag) { // 有进位
						if (bb.charAt(index) == '1') {
							sb.append("0");
						} else {
							sb.append("1");
							flag = false;
						}
					} else {
						sb.append(bb.charAt(index));
					}
				} else if (index >= bb.length()) {
					// aa 较长
					if (flag) { // 有进位
						if (aa.charAt(index) == '1') {
							sb.append("0");
						} else {
							sb.append("1");
							flag = false;
						}
					} else {
						sb.append(aa.charAt(index));
					}
				}

			}
			index++;
		}
		return sb.reverse().toString();
	}

}
