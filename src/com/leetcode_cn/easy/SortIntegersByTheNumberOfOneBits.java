package com.leetcode_cn.easy;
/*******************根据数字二进制下 1 的数目排序*************/

import java.util.Arrays;

/**
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 *
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 *
 * 请你返回排序后的数组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [0,1,2,3,4,5,6,7,8]
 * 输出：[0,1,2,4,8,3,5,6,7]
 * 解释：[0] 是唯一一个有 0 个 1 的数。
 * [1,2,4,8] 都有 1 个 1 。
 * [3,5,6] 有 2 个 1 。
 * [7] 有 3 个 1 。
 * 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
 * 示例 2：
 *
 * 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
 * 输出：[1,2,4,8,16,32,64,128,256,512,1024]
 * 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
 * 示例 3：
 *
 * 输入：arr = [10000,10000]
 * 输出：[10000,10000]
 * 示例 4：
 *
 * 输入：arr = [2,3,5,7,11,13,17,19]
 * 输出：[2,3,5,17,7,11,13,19]
 * 示例 5：
 *
 * 输入：arr = [10,100,1000,10000]
 * 输出：[10,100,10000,1000]
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 10^4
 *
 */
public class SortIntegersByTheNumberOfOneBits {

    public int[] sortByBits(int[] arr) {

        // int[] 转 Integer[]
        Integer[] temp = Arrays.stream(arr).boxed().toArray(Integer[] :: new);
        // 排序 ‘1’的个数一样就按照大小 否则按照‘1’的数量
        Arrays.sort(temp, (o1, o2) -> {
            int o1Count = Integer.bitCount(o1);
            int o2Count = Integer.bitCount(o2);
            return o1Count == o2Count ? o1 - o2 : o1Count - o2Count;
        });
        // Integer[] 转 int[]
        return Arrays.stream(temp).mapToInt(Integer::intValue).toArray();
    }

    /**
     * 循环并使用Integer.bitCount计算数字中1的个数，乘以10000000（题目中不会大于10^4）然后加上原数字，
     *
     * 放入数组中，并对数组进行排序，最后 % 10000000获取原来的数字
     *
     * @param arr
     * @return
     */
    public int[] sortByBits1(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.bitCount(arr[i]) * 10000000 + arr[i];
        }
        // 升序排序
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] % 10000000;
        }
        return arr;
    }

    /**
     * 二进制计数法
     * @param n
     * @return
     */
    int count_binary_one(int n)
    {
        int count = 0;
        while(n > 0)
        {
            n &= (n - 1); // 计数完后依次从右往左抹掉一个 '1' 变成 '0'
            ++count;
        }
        return count;
    }

    public static void main(String[] args) {


    }
}
