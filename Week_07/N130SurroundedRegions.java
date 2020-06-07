package cn.lihua.week07;

import org.junit.Test;

/**
 * 130. 被围绕的区域
 *
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

 示例:

 X X X X
 X O O X
 X X O X
 X O X X
 运行你的函数后，矩阵变为：

 X X X X
 X X X X
 X X X X
 X O X X
 解释:

 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class N130SurroundedRegions {

    public void solve(char[][] board) {
        if (board == null || board.length < 3 || board[0].length < 3) return;
        int rows = board.length;
        int cols = board[0].length;
        // 第0行和最后一行中的O的连通图
        for (int i = 0; i < cols; i++) {
            dfs(board, 0, i, rows, cols);   // 第0行的O的联通图
            dfs(board, rows - 1, i, rows, cols);   // 最后一行的O的联通图
        }
        // 第0列和最后一列中的O的连通图
        for (int i = 0; i < rows; i++) {
            dfs(board, i, 0, rows, cols);   // 第0行的O的联通图
            dfs(board, i, cols - 1, rows, cols);   // 最后一行的O的联通图
        }
        // 递归结束后,将中间的O改为X, 将边界的#改回O
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    /**
     * dfs: 与四条边联通的O, 变为 #
     */
    private void dfs(char[][] board, int i, int j, int rows, int cols) {
        // 排除边界 和 'X'
        if (i < 0 || j < 0 || i >= rows || j >= cols || board[i][j] == 'X' || board[i][j] == '#') return;

        board[i][j] = '#';
        dfs(board, i - 1, j, rows, cols);   // 上
        dfs(board, i + 1, j, rows, cols);   // 下
        dfs(board, i, j - 1, rows, cols);   // 左
        dfs(board, i, j + 1, rows, cols);   // 右
    }

    @Test
    public void test() {
        char[][] board = {{'X', 'X', 'X', 'X'},
                        {'X', 'O', 'O', 'X'},
                        {'X', 'X', 'O', 'X'},
                        {'X', 'O', 'X', 'X'}};
        solve(board);
        for (char[] chars : board) {
            System.out.println(String.valueOf(chars));
        }
    }
}
