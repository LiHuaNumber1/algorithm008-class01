package cn.lihua.week03;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

 * 示例：
 输入：n = 3
 输出：[
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]

 */
public class N22GenerateParenthesis {

    private List<String> result;

    /*
    * 左括号随时可以加,只要别超标
    * 左括号个数 > 右括号个数,才可以加右括号
    */
    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        _generate(0, 0, n, "");
        return result;
    }

    private void _generate(int left, int right, int n, String s) {
        // terminator
        if (left == n && right == n) {
            result.add(s);
            System.out.println(s);
            return;
        }
        // process current logic
        // drill down
        if (left < n) {
            _generate(left + 1, right, n, s + "(");
        }

        if (right < left) {
            _generate(left, right + 1, n, s + ")");
        }
        // restore current status
    }

    // 过度代码
    public List<String> generateParenthesis1(int n) {
        _generate1(0, 2 * n, "");
        return null;
    }
    private void _generate1(int level, int max, String s) {
        // terminator
        if (level >= max) { // 数量达到就输出
            System.out.println(s);
            return;
        }

        // process current logic : left , right 只有加( 或 ) 的可能
        String s1 = s + "(";
        String s2 = s + ")";

        // drill down
        _generate1(level + 1, max, s1);  // 当前基础上加 左括号
        _generate1(level + 1, max, s2);  // 当前基础上加 右括号

        // restore current status
    }

    @Test
    public void test() {
        List<String> list = generateParenthesis(3);
        System.out.println("=====================");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
