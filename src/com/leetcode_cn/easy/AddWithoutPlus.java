package com.leetcode_cn.easy;
/***************不用加号的加法************/

/**
 * 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
 *
 * 示例:
 *
 * 输入: a = 1, b = 1
 * 输出: 2
 *  
 *
 * 提示：
 *
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 *
 */
public class AddWithoutPlus {

    public int add(int a, int b) {
        int sum = 0;
        int carry = 0;
        while (b != 0) {
            sum = a ^ b; // 取其不需要进位的部分 | 只有不同才会为1
            carry = (a & b) << 1; // 取其需要进位的部分 | 只有都为1才会为1 | 左移一位表示进位
            a = sum; // 循环往复 当没有需要进位时说明全部该进位的都进位了
            b = carry;
        }
        return a;
    }
}
