package cn.lihua.week07;

import java.util.Arrays;

/**
 * 37. 解数独
 *
 * 编写一个程序，通过已填充的空格来解决数独问题。

 一个数独的解法需遵循如下规则：

 数字 1-9 在每一行只能出现一次。
 数字 1-9 在每一列只能出现一次。
 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 空白格用 '.' 表示。



 一个数独。



 答案被标成红色。

 Note:

 给定的数独序列只包含数字 1-9 和字符 '.' 。
 你可以假设给定的数独只有唯一解。
 给定数独永远是 9x9 形式的。

 */
public class N37SudokuSolver {

    /**
     * 推荐的比较好的写法1
     */
    public void solveSudoku1(char[][] board) {
        // 判断棋盘是否合法
        if (board == null || board.length == 0) return;
        solve(board);
    }
    private boolean solve(char[][] board) {
        // 遍历整个棋盘
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 如果棋盘格子为空位
                if (board[i][j] == '.') {
                    // 尝试填入1~9的数字
                    for (char c = '1'; c <= '9'; c++) {
                        // 若填入,判断棋盘是否合法
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c; // 填入c
                            // 递归调用,如果这个能解决,说明整个棋盘都已经解决了.
                            if (solve(board)) return true;
                            // 否则,恢复,相当于else
                            board[i][j] = '.';
                        }
                    }
                    return false; // 循环结束了,说明不行
                }
            }
        }
        return true;    // 整个棋盘可以到这里,说明找到了
    }
    private boolean isValid(char[][] board, int row, int col, char c) {
        // 只要查当前格子对应的行\列\box 是否已经有该数
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != '.' && board[row][i] == c) return false;
            if (board[i][col] != '.' && board[i][col] == c) return false;
            // 遍历某个box
            if (board[row / 3 * 3 + i / 3][col / 3 * 3 + i % 3] != '.'
                    && board[row / 3 * 3 + i / 3][col / 3 * 3 + i % 3] == c) return false;
        }
        return true;
    }

    /**
     * 推荐较好的写法2
     */
    public void solveSudoku(char[][] board) {
        dfs(board, 0);
    }

    /**
     * @param d level
     */
    private boolean dfs(char[][] board, int d) {
        // terminator
        if (d == 81) return true;

        int i = d / 9, j = d % 9; // 横\纵坐标
        // 如果当前格子有值,直接到下一层
        if (board[i][j] != '.') return dfs(board, d + 1);
        // 否则如果是'.'
        // flag:存储当前格子对应的行\列\box中的1-9的数字,还能否填,若为false,表示已经有了
        boolean[] flag = new boolean[10];
        validate(board, i, j, flag);
        // 遍历1~9的数字
        for (int k = 1; k <= 9; k++) {
            if (flag[k]) {  // 如果数字k,没有在当前格子对应的行\列\box中填入过
                board[i][j] = (char) ('0' + k); // 填入
                if (dfs(board, d + 1)) return true;
            }
        }
        board[i][j] = '.';  // 如果没有解决,恢复'.',以便回溯
        return false;
    }

    private void validate(char[][] board, int i, int j, boolean[] flag) {
        Arrays.fill(flag, true);    // 默认各个数字都能填的
        // 只要查当前格子对应的行\列\box 还能不能放某个数字
        for (int k = 0; k < 9; k++) {
            if (board[i][k] != '.') flag[board[i][k] - '0'] = false;//行
            if (board[k][j] != '.') flag[board[k][j] - '0'] = false;//列
            int r = i / 3 * 3 + k / 3; // 计算box的索引 行
            int c = j / 3 * 3 + k % 3; // 计算box的索引 列
            if (board[r][c] != '.') flag[board[r][c] - '0'] = false;//box
        }
    }
}
