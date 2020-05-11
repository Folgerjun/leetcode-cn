package com.leetcode_cn.easy;
/********上升下降字符串******/


/**
 * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
 *
 * 1.从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
 * 2.从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
 * 3.重复步骤 2 ，直到你没法从 s 中选择字符。
 * 4.从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
 * 5.从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
 * 6.重复步骤 5 ，直到你没法从 s 中选择字符。
 *
 * 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
 * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
 *
 * 请你返回将 s 中字符重新排序后的 结果字符串 。
 *
 *
 * 示例 1：
 *
 * 输入：s = "aaaabbbbcccc"
 * 输出："abccbaabccba"
 * 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
 * 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
 * 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
 * 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
 * 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
 *
 * 示例 2：
 *
 * 输入：s = "rat"
 * 输出："art"
 * 解释：单词 "rat" 在上述算法重排序以后变成 "art"
 * 示例 3：
 *
 * 输入：s = "leetcode"
 * 输出："cdelotee"
 * 示例 4：
 *
 * 输入：s = "ggggggg"
 * 输出："ggggggg"
 * 示例 5：
 *
 * 输入：s = "spo"
 * 输出："ops"
 *  
 * 提示：
 *
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 *
 */
public class increasingDecreasingString {

    public static void main(String[] args) {
        System.out.println(sortString("aaaabbbbcccc"));
    }

    public static String sortString(String s) {
        if (s == null || s == "") return "";
        int[] letterNums = new int[26];
        char[] letters = s.toCharArray();
        StringBuilder result = new StringBuilder();
        // 遍历统计相同字符个数
        for (char letter : letters) {
            letterNums[letter - 'a']++;
        }
        while (hasNum(letterNums)) {
            // 先从小到大
            for (int i = 0; i < letterNums.length; i++) {
                if (letterNums[i] > 0) {
                    result.append((char)(i + 'a'));
                    letterNums[i]--;
                }
            }
            // 再从大到小
            for (int i = letterNums.length - 1; i >= 0; i--) {
                if (letterNums[i] > 0) {
                    result.append((char)(i + 'a'));
                    letterNums[i]--;
                }
            }
        }
        return result.toString();
    }

    /**
     * 判断是否还有剩余的字符未被处理
     * @param letterNums
     * @return
     */
    private static boolean hasNum(int[] letterNums) {
        for (int letterNum : letterNums) {
            if (letterNum > 0)
                return true;
        }
        return false;
    }
}
