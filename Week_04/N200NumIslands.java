package cn.lihua.week04;

import org.junit.Test;

/**
 * 200. 岛屿数量
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。

 此外，你可以假设该网格的四条边均被水包围。


 示例 1:

 输入:
 11110
 11010
 11000
 00000
 输出: 1

 示例 2:

 输入:
 11000
 11000
 00100
 00011
 输出: 3
 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。

 */
public class N200NumIslands {
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

    @Test
    public void test() {
        char[][] grid = new char[][]{{'1', '1', '1', '1', '0'},
                                     {'1', '1', '0', '1', '0'},
                                     {'1', '1', '0', '0', '0'},
                                     {'0', '0', '0', '0', '0'}};
//        System.out.println(grid.length);    // rows
//        System.out.println(grid[0].length); // cols

        System.out.println(numIslands(grid));   // 岛屿数量
    }

    @Test
    public void test2() {
        char[][] grid = new char[][]{{'1', '1', '0', '0', '0'},
                                     {'1', '1', '0', '0', '0'},
                                     {'0', '0', '1', '0', '0'},
                                     {'0', '0', '0', '1', '1'}};

        System.out.println(numIslands(grid));   // 岛屿数量
    }
}
