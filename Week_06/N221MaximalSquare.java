package cn.lihua.week06;

import org.junit.Test;

/**
 * 221. 最大正方形
 *
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

 示例:

 输入:

 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0

 输出: 4
 */
public class N221MaximalSquare {

    /**
     * 动态规划
     * min(上, 左, 左上) + 1
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length < 1) return 0;

        int height = matrix.length;
        int width = matrix[0].length;
        int maxSide = 0;    // 最长边
        int[][] dp = new int[height + 1][width + 1]; // 增加第0行和第0列为0

        // 在matrix范围内循环
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (matrix[row][col] == '1') {
                    // 左,上,左上 的最小边长 + 1  -- min(,)只能有2个参数,故要2次
                    dp[row + 1][col + 1] = Math.min(Math.min(dp[row + 1][col], dp[row][col + 1]),
                            dp[row][col]) + 1;
                    maxSide = Math.max(maxSide, dp[row + 1][col + 1]);
                }
                // == '0' 时不计算,则dp[row][col]==0 初始化时预设了
            }
        }
        return maxSide * maxSide;
    }

    @Test
    public void test() {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(maximalSquare(matrix));
    }

}
