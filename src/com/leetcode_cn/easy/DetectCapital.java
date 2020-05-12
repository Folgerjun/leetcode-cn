package com.leetcode_cn.easy;
/***********检测大写字母*********/

/**
 * 给定一个单词，你需要判断单词的大写使用是否正确。
 *
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 *
 * 全部字母都是大写，比如"USA"。
 * 单词中所有字母都不是大写，比如"leetcode"。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
 * 否则，我们定义这个单词没有正确使用大写字母。
 *
 * 示例 1:
 *
 * 输入: "USA"
 * 输出: True
 * 示例 2:
 *
 * 输入: "FlaG"
 * 输出: False
 * 注意: 输入是由大写和小写拉丁字母组成的非空单词。
 *
 */
public class DetectCapital {

    public static void main(String[] args) {

        System.out.println((int)'A'); // 65
        System.out.println((int)'Z'); // 90
        System.out.println((int)'a'); // 97
        System.out.println((int)'z'); // 122
    }

    public boolean detectCapitalUse(String word) {

        // 全大写
        if (word.toUpperCase().equals(word))
            return true;
        // 只有首字母大写 | 全小写
        if (word.substring(1).toLowerCase().equals(word.substring(1)))
            return true;
        return false;

    }

    public boolean detectCapitalUse1(String word) {
        int l = word.length();
        // 统计大写字母出现次数
        int count = 0;
        for(int i = 0; i < l; i++) {
            // 是大写字母且数量是否一致
            if(word.charAt(i) < 91  && count++ < i) {
                return false;
            }
        }
        // count
        // 0:全是小写字母 1:只有首字母大写 l:都是大写
        return count <= 1 || count == l;
    }
}
