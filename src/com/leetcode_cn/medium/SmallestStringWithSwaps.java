package com.leetcode_cn.medium;
/****************交换字符串中的元素***********/

import java.util.*;

/**
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 *
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 *
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 *
 *  
 *
 * 示例 1:
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 *
 * 示例 2：
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 *
 * 示例 3：
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s 中只含有小写英文字母
 */
public class SmallestStringWithSwaps {

        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            int len = s.length();

            DSU dsu = new DSU(100000);
            //构造下标集合
            for (List<Integer> list : pairs)
                dsu.union(list.get(0), list.get(1));
            //每个下标集合有1个leader，用leader作为key(唯一)，下标集合List作为value
            HashMap<Integer, List<Integer>> map = new HashMap<>();
            //从小到大遍历，使得List<Integer>中的值是有序的(事后不用再排序了)
            for (int i = 0; i < len; ++i) {
                int key = dsu.find(i);
                map.computeIfAbsent(key, unused -> new ArrayList<>()).add(i);
            }

            StringBuilder res = new StringBuilder(s);
            //遍历所有每个下标集合，进行字符局部排序
            for (List<Integer> idx_list : map.values())
                if (idx_list.size() > 1)
                    sort(res, idx_list);

            return res.toString();
        }

        //根据下标集合进行局部排序
        private void sort(StringBuilder res, List<Integer> idx_list) {
            int len = idx_list.size();
            char[] array = new char[len];
            //根据下标集合构建字符集合
            for (int i = 0; i < len; ++i)
                array[i] = res.charAt(idx_list.get(i));
            //将字符集合排序
            Arrays.sort(array);
            //将字符按序“插入”回StringBuilder
            for (int i = 0; i < len; ++i)
                res.setCharAt(idx_list.get(i), array[i]);
        }

    public static void main(String[] args) {
        System.out.println((int) 'c');
        System.out.println((int) 'a');
        System.out.println((char) ((int) 'c'));

        Integer i = 99;
        System.out.println((char) (int) i);
    }
    }

    class DSU {
        int[] parent;

        public DSU(int len) {
            parent = new int[len];
            for (int i = 0; i < len; ++i)
                parent[i] = i;
        }

        public int find(int x) {
            return parent[x] != x ? parent[x] = find(parent[x]) : x;
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
}
