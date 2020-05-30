package cn.lihua.week06;

import org.junit.Test;

/**
 * 62. 不同路径
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

 问总共有多少条不同的路径？



 例如，上图是一个7 x 3 的网格。有多少可能的路径？

  

 示例 1:

 输入: m = 3, n = 2
 输出: 3
 解释:
 从左上角开始，总共有 3 条路径可以到达右下角。
 1. 向右 -> 向右 -> 向下
 2. 向右 -> 向下 -> 向右
 3. 向下 -> 向右 -> 向右
 示例 2:

 输入: m = 7, n = 3
 输出: 28
  

 提示：

 1 <= m, n <= 100
 题目数据保证答案小于等于 2 * 10 ^ 9

 */
public class N62UniquePaths {

    /**
     * 动态递推,从底下倒推回第一行
     * a[i][j] = a[i][j+1] + a[i+1][j] //只能向右和向下,右边格子+下边格子
     * @param m 行
     * @param n 列
     * @return
     */
    public int uniquePaths1(int m, int n) {
        int[] a = new int[n];   // 用一维数组,记录当前计算行的值
        for (int i = 0; i < n; i++) {
            a[i] = 1;   // 初始化为1,用于存储最底下的一行的走法
        }

        // 循环的行数:行数应该减少一行
        for (int i = 1; i < m; i++) {
            // 最后一列为1
            for (int j = n - 2; j >= 0; j--) {
                a[j] += a[j + 1];   // 每次等于后面的值+上一行存的值
            }
        }
        return a[0];    // 左上角的值是总数
    }

    /**
     * 无障碍情况下,起点和终点可以互换,从起点走动终点,就是从终点走到起点的方法
     * 也可以理解为,机器人走到当前位置有几条路径
     * a[i][j] = a[i][j+1] + a[i+1][j] //只能向右和向下,右边格子+下边格子
     * 转换为:
     * a[i][j] = a[i][j-1] + a[i-1][j] //只能向左和向上,左边格子+上边格子
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                a[j] += a[j - 1];
            }
        }
        return a[n - 1];
    }

    @Test
    public void test() {
        System.out.println(uniquePaths(3, 7));
    }
}
