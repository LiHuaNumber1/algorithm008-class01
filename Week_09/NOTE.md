# 第9周学习笔记

## 63. 不同路径 II
与不同路径相比，多了障碍物的判断：
```
    /**
     * 修正前面方法的错误
     * a[0][0]不能重复计算
     * 在计算第0列和第0行的时候,要看前面有无障碍物,若有,则路径0条
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
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
```
