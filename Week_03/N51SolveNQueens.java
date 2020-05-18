package cn.lihua.week03;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
public class N51SolveNQueens {
    List<List<String>> result = new ArrayList<>();
    // 之前的皇后所占的位置(列,撇,捺)
    HashSet cols = new HashSet();
    HashSet pie = new HashSet();
    HashSet na = new HashSet();

    public List<List<String>> solveNQueens(int n) {
        if (n < 1) {
            return new ArrayList<>();
        }
        recursive(n, 0, new ArrayList<>());
        return result;
    }

    private void recursive(int n, int row, List<String> currentQueen) {
        // terminator
        if (row >= n) {
            result.add(new ArrayList<String>(currentQueen)); // 正确
            // result.add(currentQueen); // 为空
            return;
        }
        // process current logic
        // 遍历列(col),传入参数为行(row)
        for (int col = 0; col < n; col++) {
            if (cols.contains(col) || pie.contains(row + col) || na.contains(n + row - col)) {
                continue; // 能被攻击到就跳过本层循环,进入下一列
            }
            cols.add(col);
            pie.add(row + col);
            na.add(n + row - col);
            // col是皇后对应列的位置，生成奇奇怪怪的格式，形如：[".Q..","...Q","Q...","..Q."]
            StringBuilder cur = new StringBuilder("");
            for (int i = 0; i < n; i++) {
                if (i == col) cur.append("Q");
                else cur = cur.append(".");
            }
            currentQueen.add(cur.toString());

            // drill down
            recursive(n, row + 1, currentQueen);

            // restore current status (清除老皇后和其势力痕迹)
            cols.remove(col);
            pie.remove(row + col);
            na.remove(n + row - col);
            currentQueen.remove(currentQueen.size() - 1);
        }
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
