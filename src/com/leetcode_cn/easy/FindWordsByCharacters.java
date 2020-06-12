package com.leetcode_cn.easy;
/*************拼写单词**************/

import java.util.Arrays;

/**
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 *
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 *
 * 注意：每次拼写（指拼写词汇表中的一个单词）时，chars 中的每个字母都只能用一次。
 *
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：
 * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 * 示例 2：
 *
 * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * 输出：10
 * 解释：
 * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
 *  
 *
 * 提示：
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, chars.length <= 100
 * 所有字符串中都仅包含小写英文字母
 *
 */
public class FindWordsByCharacters {

    public int countCharacters(String[] words, String chars) {

        int[] arr = new int[26]; // 存放对应字母的个数
        int result = 0;
        int index;
        int[] copyArr; // 拷贝数组
        char[] chars1;
        for (char c : chars.toCharArray()) {
            index = (int) c - 'a';
            arr[index] += 1;
        }
        // 遍历判断
        for (String s : words) {
            if (s.length() > chars.length())
                continue;
            chars1 = s.toCharArray();
            copyArr = Arrays.copyOf(arr, arr.length);
            for (int i = 0; i < chars1.length; i++) {
                index = (int) chars1[i] - 'a';
                if (copyArr[index] != 0) {
                    copyArr[index] -= 1;
                } else
                    break;
                if (i == chars1.length - 1) {
                    result = result + s.length();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println((int) 'a');
        System.out.println((int) 'z');
        System.out.println((int) 'b'-'a');
    }
}
