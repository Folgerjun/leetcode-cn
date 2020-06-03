package com.leetcode_cn.medium;
/****************水域大小************/

import java.util.*;

/**
 * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
 *
 * 示例：
 *
 * 输入：
 * [
 *   [0,2,1,0],
 *   [0,1,0,1],
 *   [1,1,0,1],
 *   [0,1,0,1]
 * ]
 * 输出： [1,2,4]
 * 提示：
 *
 * 0 < len(land) <= 1000
 * 0 < len(land[i]) <= 1000
 *
 */
public class PondSizes {

    public int[] pondSizes(int[][] land) {

        if (land.length == 0) return new int[]{};
        int rows = land.length;
        int cols = land[0].length;
        if (cols == 0) return new int[]{};
        List<Integer> results = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (land[i][j] == 0) {
                    int index = PondSize(i, j, land);
                    if (index != 0) {
                        results.add(index);
                    }
                }
            }
        }
        int[] resultArr = new int[results.size()];
        for (int i = 0; i < results.size(); i++) {
            resultArr[i] = results.get(i);
        }
        Arrays.sort(resultArr); // 排序
        return resultArr;
    }

    private int PondSize(int i, int j, int[][] land) {
        // 越过边界
        if (i < 0 || j < 0 || i >= land.length || j >= land[0].length) return 0;
        // 不是池塘
        if (land[i][j] != 0) return 0;
        // 设置已经访问
        land[i][j] = -1;
        return 1+
                PondSize(i+1, j, land)+// 下
                PondSize(i-1, j, land)+// 上
                PondSize(i, j+1, land)+// 右
                PondSize(i, j-1, land)+// 左
                PondSize(i-1, j-1, land)+// 左上
                PondSize(i-1, j+1, land)+// 右上
                PondSize(i+1, j-1, land)+// 左下
                PondSize(i+1, j+1, land);// 右下

    }

    public static void main(String[] args) {
        int[][] land = new int[][]{
  {0,2,1,0},
  {0,1,0,1},
  {1,1,0,1},
  {0,1,0,1}
};
       int[] result = new PondSizes().pondSizes(land);
        System.out.println(Arrays.toString(result));
    }
}
