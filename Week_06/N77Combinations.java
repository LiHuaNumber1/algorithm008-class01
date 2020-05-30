package cn.lihua.week06;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

 示例:

 输入: n = 4, k = 2
 输出:
 [
 [2,4],
 [3,4],
 [2,3],
 [1,2],
 [1,3],
 [1,4],
 ]
 */
public class N77Combinations {
    /**
     * 递归,类似左右括号
     * @param n 表示整数范围[1,n]
     * @param k 取k个数的组合
     */
    public List<List<Integer>> combine(int n, int k) {
        if (n * k == 0 || n < k) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new LinkedList<>();

        recur(1, n, k, new LinkedList<>(), result);
        return result;
    }

    private void recur(int first, int n, int k, LinkedList<Integer> sub, List<List<Integer>> result) {
        // terminater
        if (sub.size() == k) {
            result.add(new ArrayList<>(sub));
            return;
        }

        for (int i = first; i <= n; i++) {
            sub.add(i);
            recur(i + 1, n, k, sub, result);
            // 删除一个,为了first能跟所有的都匹配一遍
            sub.removeLast();
        }
    }

    @Test
    public void test() {
        System.out.println(combine(4, 3));
    }

}
