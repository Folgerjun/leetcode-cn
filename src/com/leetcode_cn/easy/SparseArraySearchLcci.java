package com.leetcode_cn.easy;
/***********稀疏数组搜索********/

/**
 * 稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
 *
 * 示例1:
 *
 *  输入: words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ta"
 *  输出：-1
 *  说明: 不存在返回-1。
 * 示例2:
 *
 *  输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ball"
 *  输出：4
 * 提示:
 *
 * words的长度在[1, 1000000]之间
 *
 */
 public class SparseArraySearchLcci {

    public static void main(String[] args) {
        System.out.println(new SparseArraySearchLcci().findString(new String[]{"at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""}, "ta"));
    }

    /**
     * 二分查找
     * @param words
     * @param s
     * @return
     */
    public int findString(String[] words, String s) {
        int start = 0;
        int end = words.length - 1;

        while(start <= end) {
            int mid = (start + end) / 2;
            int omid = mid;
            while(mid >= 0 && words[mid].length() == 0) { // 向左透过空串
                mid--;
            }
            if (mid < 0) return -1;
            if (words[mid].compareTo(s) < 0) {
                start = omid + 1; // 如果在右边，使用初始mid
            } else if (words[mid].compareTo(s) > 0) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
