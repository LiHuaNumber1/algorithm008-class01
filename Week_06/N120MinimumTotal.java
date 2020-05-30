package cn.lihua.week06;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。

 例如，给定三角形：
 [
 [2],
 [3,4],
 [6,5,7],
 [4,1,8,3]
 ]
 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。


 说明：
 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class N120MinimumTotal {
    /**
     * 动态规划
     * 第[i,j]的最小值,可以分为 下一行 左边的最小路径  与 右边的最小路径 中取最小值
     * 就成为了分治
     * 此处不在原list上操作,复制了一份给dp
     * 这样最后一行的值不用动了
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        // 复制一份给dp
        List<List<Integer>> dp = new ArrayList<>(new ArrayList<>(triangle));

        // 最后一行的值不用动了,从倒数第二行开始递推
        for (int i = dp.size() - 2; i >= 0 ; i--) {
            // 每行的列要计算
            for (int j = 0; j < dp.get(i).size(); j++) {
                // 当前[i,j] 的下一行的左右值的最小值
                int min = Math.min(dp.get(i + 1).get(j), dp.get(i + 1).get(j + 1));
                // 对第行j列设置值 = 自己 + 下一行的最小值
                dp.get(i).set(j, dp.get(i).get(j) + min);
            }
        }
        // 输出测试用
//        for (List<Integer> list : dp) {
//            System.out.println(list);
//        }
        return dp.get(0).get(0);
    }

    /**
     * 动态规划2
     * 第[i,j]的最小值,可以分为 下一行 左边的最小路径  与 右边的最小路径 中取最小值
     * 就成为了分治
     * 此处 在 原list 上操作
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // 最后一行的值不用动了,从倒数第二行开始递推
        for (int i = triangle.size() - 2; i >= 0; i--) {
            // 每行的列要计算
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // 当前[i,j] 的下一行的左右值的最小值
                int min = Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1));
                // 对第行j列设置值 = 自己 + 下一行的最小值
                triangle.get(i).set(j, triangle.get(i).get(j) + min);
            }
        }
        return triangle.get(0).get(0);
    }

    @Test
    public void test() {
        // 准备数据
        int[][] arr = new int[][]{{2},
                {3, 4},
                {6, 5, 7},
                {4, 1, 8, 3}};
        List<List<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < arr[i].length; j++) {
                row.add(arr[i][j]);
            }
            triangle.add(row);
        }
        for (List<Integer> list : triangle) {
            System.out.println(list);
        }
//        System.out.println(triangle.get(0));    // 下标从0开始

        // 调用
        System.out.println(minimumTotal(triangle));
    }
}
