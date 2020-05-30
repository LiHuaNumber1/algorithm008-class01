package cn.lihua.week06;

import org.junit.Test;

/**
 * 63. 不同路径 II
 *
 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？



 网格中的障碍物和空位置分别用 1 和 0 来表示。

 说明：m 和 n 的值均不超过 100。

 示例 1:

 输入:
 [
   [0,0,0],
   [0,1,0],
   [0,0,0]
 ]
 输出: 2
 解释:
 3x3 网格的正中间有一个障碍物。
 从左上角到右下角一共有 2 条不同的路径：
 1. 向右 -> 向右 -> 向下 -> 向下
 2. 向下 -> 向下 -> 向右 -> 向右

 */
public class N62_2UniquePaths {

    /**
     * 此题有错误,将a[0][0] 赋值了2次,第一次空地设为1,第二次以为是障碍物设为0
     * 故 a[0][0] 要单独设置
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles1111(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        // 设置第0列的值,若为空地设为1,否则设为0
        for (int i = 0; i < rows; i++) {
           if (obstacleGrid[i][0] == 0) { // 空地
               obstacleGrid[i][0] = 1;
           } else {
               obstacleGrid[i][0] = 0;
           }
        }

        // 设置第0行的值,若为空地设为1,否则设为0
        for (int j = 0; j < cols; j++) {
            if (obstacleGrid[0][j] == 0) { // 空地
                obstacleGrid[0][j] = 1;
            } else {
                obstacleGrid[0][j] = 0;
            }
        }

        // 计算当前格子值 = 上边格子 + 左边格子
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (obstacleGrid[i][j] == 0) { // 空地
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        // 最后一个格子的值,是最终结果
        return obstacleGrid[rows - 1][cols - 1];
    }

    /**
     * 修正上面方法的错误
     * a[0][0]不能重复计算
     * 在计算第0列和第0行的时候,要看前面有无障碍物,若有,则路径0条
     */
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        // start就是障碍物,就没有路径了
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        obstacleGrid[0][0] = 1; // 第一个单独算

        // 设置第0列的值,若为空地设为1,否则设为0
        for (int i = 1; i < rows; i++) {
            // 当前是空地,并且上边格子走法是1,否则上边格子就是0(说明之前是障碍物)
            obstacleGrid[i][0] = obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1 ? 1 : 0;
        }

        // 设置第0行的值,若为空地设为1,否则设为0
        for (int j = 1; j < cols; j++) {
            obstacleGrid[0][j] = obstacleGrid[0][j] == 0 && obstacleGrid[0][j - 1] == 1 ? 1 : 0;
        }

        // 计算当前格子值 = 上边格子 + 左边格子
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (obstacleGrid[i][j] == 0) { // 空地
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        // 最后一个格子的值,是最终结果
        return obstacleGrid[rows - 1][cols - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // start就是障碍物,就没有路径了
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0][0] == 1) return 0;

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        int[] dp = new int[cols];
        dp[0] = 1;
        for (int r = 0; r < rows; r++) {
            // 循环不能从1开始,因为第0列也是要计算的,出发第0行的第0列
            for (int j = 0; j < cols; j++) {
                if (obstacleGrid[r][j] == 1) {
                    dp[j] = 0;
                } else if (j > 0) { // 排除第0行的第0列
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[cols - 1];
    }

    @Test
    public void test() {
        int[][] grid = new int[][]{{0, 0, 0}, {1, 1, 0}, {0, 0, 0}};
//        int[][] grid = new int[][]{{0}};
        System.out.println(uniquePathsWithObstacles(grid));
    }
}
