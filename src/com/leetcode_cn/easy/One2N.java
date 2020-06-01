package com.leetcode_cn.easy;

/*************打印从1到最大的n位数*********/

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import java.util.Arrays;

/**
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *  
 *
 * 说明：
 *
 * 用返回一个整数列表来代替打印
 * n 为正整数
 *
 */
public class One2N {

    public static void main(String[] args) {
//        int[] result = new One2N().printNumbers(3);
//        System.out.println(Arrays.toString(result));

        new One2N().printNumbers1(3);
    }

    public int[] printNumbers(int n) {
        // 长度
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(9);
        }
        int maxNum = Integer.parseInt(sb.toString());
        int[] result = new int[maxNum];
        for (int i = 0; i < maxNum; i++) {
            result[i] = i+1;
        }
        return result;
    }

    public void printNumbers1(int n) {
        StringBuilder str = new StringBuilder();
        // 将str初始化为n个'0'字符组成的字符串
        for (int i = 0; i < n; i++) {
            str.append('0');
        }
        while(!increment(str)){
            // 去掉左侧的0
            int index = 0;
            while (index < str.length() && str.charAt(index) == '0'){
                index++;
            }
            // 打印每一位的值
            System.out.println(str.toString().substring(index));
        }
    }

    /**
     * 挨个累加
     * @param str
     * @return
     */
    public boolean increment(StringBuilder str) {
        boolean isOverflow = false;
        for (int i = str.length() - 1; i >= 0; i--) {
            char s = (char) (str.charAt(i) + 1);
            // 如果s大于'9'则发生进位
            if (s > '9') {
                str.replace(i, i + 1, "0");
                if (i == 0) {
                    isOverflow = true;
                }
            }
            // 没发生进位则跳出for循环
            else {
                str.replace(i, i + 1, String.valueOf(s));
                break;
            }
        }
        return isOverflow;
    }
}
