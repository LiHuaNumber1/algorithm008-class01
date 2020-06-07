package cn.lihua.week07;

import org.junit.Test;

import java.util.*;

/**
 * 51. N皇后
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

 示例:

 输入: 4
 输出: [
 [".Q..",  // 解法 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // 解法 2
 "Q...",
 "...Q",
 ".Q.."]
 ]
 解释: 4 皇后问题存在两个不同的解法。

 */
public class N51SolveNQueens2 {
    private List<List<String>> result;
    /**
     * 回溯算法
     * https://leetcode-cn.com/problems/n-queens/solution/hui-su-suan-fa-xiang-jie-by-labuladong/
     */
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) return null;
        result = new LinkedList<>();
        // '.' 表示空，'Q' 表示皇后，初始化空棋盘。
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, '.'); // 整个一维数组赋值
        }
        backtrack(board, 0);
        return result;
    }

    /**
     * 路径：board中小于row的那些行都已经成功放置了皇后
     * 可选择列表: 第row行的所有列都是放置Q的选择
     * 结束条件: row超过board的最后一行
     */
    private void backtrack(char[][] board, int row) {
        // 触发结束条件
        if (row == board.length) {
            result.add(charToString(board));
            return;
        }

        int cols = board[0].length;
        for (int col = 0; col < cols; col++) {
            // 排除不合法选择. 如果这个列不能摆,continue进入下一列
            if (!isValid(board, row, col)) continue;
            // 做选择
            board[row][col] = 'Q';
            // 进入下一行决策
            backtrack(board, row + 1);
            // 撤销选择
            board[row][col] = '.';
        }
    }
    /* 是否可以在 board[row][col] 放置皇后？ */
    private boolean isValid(char[][] board, int row, int col) {
        int rows = board.length;
        // 在前面的行中,一行一行循环,检查该列是否已经有Q
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < rows; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    /**
     * 将字符数组转为字符串集合List,完成当前的一种解法
     */
    private List<String> charToString(char[][] board) {
        List<String> curr = new LinkedList<>();
        for (char[] chars : board) {
            curr.add(String.valueOf(chars));
        }
        return curr;
    }

    @Test
    public void test() {
        List<List<String>> result = solveNQueens(4);
        for (List<String> list : result) {
            System.out.println(list);
        }
    }

    @Test
    public void test2() {
        List<List<String>> result = new ArrayList<>();
        List<String> currentQueen = new ArrayList<>();
        currentQueen.add("abcd");
        result.add(currentQueen);
        System.out.println(result);

    }
}
