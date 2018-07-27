package com.leetcode_cn.medium;

/************* 比较版本号 ***************************/
/**
 * 比较两个版本号 version1 和 version2。 如果 version1 > version2 返回 1， 如果 version1 <
 * version2 返回 -1， 除此之外返回 0。
 * 
 * 你可以假设版本字符串非空，并且只包含数字和 . 字符。
 * 
 * . 字符不代表小数点，而是用于分隔数字序列。
 * 
 * 例如，2.5 不是“两个半”，也不是“差一半到三”，而是第二版中的第五个小版本。
 * 
 * 示例 1:
 * 
 * 输入: version1 = "0.1", version2 = "1.1" 输出: -1
 * 
 * 示例 2:
 * 
 * 输入: version1 = "1.0.1", version2 = "1" 输出: 1
 * 
 * 示例 3:
 * 
 * 输入: version1 = "7.5.2.4", version2 = "7.5.3" 输出: -1
 * 
 * @author ffj
 *
 */
public class CompareVersionNumbers {

	public static void main(String[] args) {
		String version1 = "7.5.2.4";
		String version2 = "7.5.3";
		int result = compareVersion(version1, version2);
		System.out.println(result);

	}

	public static int compareVersion(String version1, String version2) {

		String[] version1Arr = version1.split("\\.");
		String[] version2Arr = version2.split("\\.");
		for (int i = 0; i < version1Arr.length; i++) { // 重组数组 为了准确取值进行比较 （如：1.000.1 这种转换为1.0.1）
			version1Arr[i] = String.valueOf(Integer.parseInt(version1Arr[i]));
		}
		for (int i = 0; i < version2Arr.length; i++) {
			version2Arr[i] = String.valueOf(Integer.parseInt(version2Arr[i]));
		}
		int length1 = version1Arr.length;
		int length2 = version2Arr.length;
		boolean version1Longer = length1 > length2;
		int length = version1Longer ? length2 : length1;
		int value = 0;
		boolean flag = false;
		for (int i = 0; i < length; i++) {
			int version1Int = Integer.parseInt(version1Arr[i]);
			int version2Int = Integer.parseInt(version2Arr[i]);
			if (version1Int > version2Int) {
				value = 1;
				flag = true;
				break;
			} else if (version1Int < version2Int) {
				value = -1;
				flag = true;
				break;
			}
		}
		if (!flag) {

			if (version1Longer) {
				// version1 长
				for (int i = length2; i < length1; i++) {
					if (!"0".equals(version1Arr[i])) {
						value = 1;
						break;
					}
				}
			} else if (length1 == length2) {
				// 长度一样
				value = 0;
			} else {
				// version2 长
				for (int i = length1; i < length2; i++) {
					if (!"0".equals(version2Arr[i])) {
						value = -1;
						break;
					}
				}
			}
		}
		return value;
	}

}
