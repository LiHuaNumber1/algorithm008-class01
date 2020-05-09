package cn.lihua.week03;

import org.junit.Test;

/**
 * 70. 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

 注意：给定 n 是一个正整数。

 示例 1：

 输入： 2
 输出： 2
 解释： 有两种方法可以爬到楼顶。
 1.  1 阶 + 1 阶
 2.  2 阶
 示例 2：

 输入： 3
 输出： 3
 解释： 有三种方法可以爬到楼顶。
 1.  1 阶 + 1 阶 + 1 阶
 2.  1 阶 + 2 阶
 3.  2 阶 + 1 阶


 */
public class N70ClimbStairs2 {

    /*
     * 记忆化递归
     * 把每一步的结果存在数组中
     */
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        return climb_Stairs(0, n, memo);
    }

    private int climb_Stairs(int i, int n, int[] memo) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {  // 如果第i个存储过,则返回存储的值
            return memo[i];
        }
        // 当前数=爬1阶+爬2阶
        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }

    /*
     * 暴力法
     * climb_Stairs(i,n)
     * i表示当前阶数,n表示目标阶数
     * +1,+2 是爬1阶和爬2阶的情形
     * climb_Stairs(i, n) = climb_Stairs(i+1, n) + climb_Stairs(i+2, n)
     */
    public int climbStairs2(int n) {
         return climb_Stairs2(0, n);
    }

    private int climb_Stairs2(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_Stairs2(i + 1, n) + climb_Stairs2(i + 2, n);
    }


    /*
    分析: 方法一
    第1级: 1 种
    第2级: 2 种
    第3级: 只能从第1级 或 第2级 台阶走过来 f(1) + f(2) = 3
    第4级: 只能从第2级 或 第3级 台阶走过来 f(2) + f(3)
    第n级: 只能从第n-2级 或 第n-1级 台阶走过来 f(n-2) + f(n-1)
     */
    public int climbStairs1(int n) {
        int f1 = 1;
        int f2 = 2;
        int f3 = 3;
        if (n <= 2) {
            return n;
        }
        for (int i = 3; i <= n; i++) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }

    @Test
    public void test() {
        int n = 1;
        System.out.println(climbStairs(n));

        n = 2;
        System.out.println(climbStairs(n));

        n = 3;
        System.out.println(climbStairs(n));

        n = 4;
        System.out.println(climbStairs(n));

        n = 5;
        System.out.println(climbStairs(n));
    }
}
