# 第四周学习笔记
这周主要都在复习，将前面的视频全部重新看了一遍，部分习题过遍数。

## 200. 岛屿数量
```
   /**
     * 碰到一个'1',用DFS将上下左右夷为平地
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        int rows = grid.length;     // 行数
        if (rows == 0) return 0;    // 若为0行,直接返回0
        int cols = grid[0].length;  // 列数

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {    // 等于1 才有岛屿要计算
                    DFSMarking(grid, i, j, rows, cols);
                    count++;
                }
            }
        }
        return count;
    }

    private void DFSMarking(char[][] grid, int i, int j, int rows, int cols) {
        // i 或者j越界了,或者格子的值不是'1',都不用再处理了
        if (i < 0 || j < 0 || i >= rows || j >= cols || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = 0; // 把当前 1 的变为0,夷为平地
        DFSMarking(grid, i - 1, j, rows, cols);     // 上
        DFSMarking(grid, i + 1, j, rows, cols);     // 下
        DFSMarking(grid, i, j - 1, rows, cols);     // 左
        DFSMarking(grid, i, j + 1, rows, cols);     // 右
    }
```

## 455. 分发饼干
```
    /**
     *  贪心算法
     *  当前最优,且不能回退
     * @param g 小孩的胃口值
     * @param s 饼干的尺寸
     * @return  满足胃口值的孩子的最大数量
     */
    public int findContentChildren(int[] g, int[] s) {
        int count = 0;

        int gLen = g.length;   // 胃口数
        int sLen = s.length;   // 饼干数

        if (gLen * sLen == 0) return 0;

        Arrays.sort(g);         // 胃口值  排序
        Arrays.sort(s);         // 饼干尺寸 排序

        for (int i = 0, j = 0; i < g.length && j < s.length; ) {
            if (g[i] <= s[j]) {  // 可以满足胃口
                count++;        // 孩子数+1
                i++;            // 胃口值 指针向后
                j++;            // 尺寸值 指针向后
            } else {
                j++;
            }
        }
        return count;
    }
```


