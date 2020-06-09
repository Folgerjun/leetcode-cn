package com.leetcode_cn.easy;
/******************最小绝对差**************/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你个整数数组 arr，其中每个元素都 不相同。
 *
 * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
 *
 *  
 *
 * 示例 1：
 * 输入：arr = [4,2,1,3]
 * 输出：[[1,2],[2,3],[3,4]]
 *
 * 示例 2：
 * 输入：arr = [1,3,6,10,15]
 * 输出：[[1,3]]
 *
 * 示例 3：
 * 输入：arr = [3,8,-10,23,19,-4,-14,27]
 * 输出：[[-14,-10],[19,23],[23,27]]
 *  
 *
 * 提示：
 *
 * 2 <= arr.length <= 10^5
 * -10^6 <= arr[i] <= 10^6
 *
 */
public class MinimumAbsoluteDifference {

    public List<List<Integer>> minimumAbsDifference(int[] arr) {

        List<List<Integer>> list = new ArrayList<>();
        // 升序
        Arrays.sort(arr);
        // 差值
        int dv = Math.abs(arr[0] - arr[1]);
        List<Integer> list1 = new ArrayList<>();
        list1.add(arr[0]);
        list1.add(arr[1]);
        list.add(list1);
        for (int i = 1; i < arr.length - 1; i++) {
            int dvTemp = Math.abs(arr[i] - arr[i+1]);
            if (dvTemp <= dv) {
                List<Integer> list2 = new ArrayList<>();
                list2.add(arr[i]);
                list2.add(arr[i+1]);
                if (dvTemp < dv) {
                    list.clear(); // 清空原先的
                    dv = dvTemp; // 新的差值
                }
                list.add(list2);
            }
        }
        return list;
    }
}
