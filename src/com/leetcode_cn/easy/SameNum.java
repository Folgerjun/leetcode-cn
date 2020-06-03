package com.leetcode_cn.easy;
/****************数组中重复的数字**********/

/**
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *  
 *
 * 限制：
 *
 * 2 <= n <= 100000
 *
 */
public class SameNum {

    public int findRepeatNumber(int[] nums) {

        int[] temp = new int[nums.length];
        for (int num : nums) {
            if (temp[num] == 1)
                return num;
            temp[num] = 1;
        }
        return -1;
    }

    /**
     * 原地互换
     * @param nums
     * @return
     */
    public int findRepeatNumber1(int[] nums) {
        int temp;
        for(int i = 0; i < nums.length; i++) {
            while (nums[i] != i){ // 保证下标即是值
                if (nums[i] == nums[nums[i]]) { // 找到重复 返回重复值
                    return nums[i];
                }
                temp = nums[i]; // 互换值
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }
}
