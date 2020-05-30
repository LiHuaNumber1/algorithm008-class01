package cn.lihua.week06;

import org.junit.Test;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。

 示例 1:

 输入: coins = [1, 2, 5], amount = 11
 输出: 3
 解释: 11 = 5 + 5 + 1


 示例 2:

 输入: coins = [2], amount = 3
 输出: -1
  

 说明:
 你可以认为每种硬币的数量是无限的。

 */
public class N322CoinChange {
    /**
     * 方法一、搜索回溯 [超出时间限制]
     * @param coins 所有的面额的硬币
     * @param amount 总金额
     * @return
     */
    public int coinChange1(int[] coins, int amount) {
        return coinChange1(0, coins, amount);
    }

    private int coinChange1(int idxCoin, int[] coins, int amount) {
        if (amount == 0) return 0;

        // 硬币索引要在下标范围内
        if (idxCoin < coins.length && amount >0) {
            int maxVal = amount / coins[idxCoin];   // 当前面额的硬币的最大个数
            int minCost = Integer.MAX_VALUE;
            // 遍历当前面额的硬币个数
            for (int x = 0; x <= maxVal; x++) {
                // 当前面额的数量的金额 没 超过总金额
                if (coins[idxCoin] * x <= amount) {
                    // 余下的金额,要在后面的面额中凑个数
                    int res = coinChange1(idxCoin + 1, coins, amount - coins[idxCoin] * x);
                    if (res != -1) {    // 凑起来了
                        minCost = Math.min(minCost, x + res);   // 硬币数相加
                    }
                }
            }
            return (minCost == Integer.MAX_VALUE) ? -1 : minCost;
        }
        return -1;
    }

    /**
     * 贪心 + dp 剪枝
     * 此题精妙之处在于:降序排列 + 剪枝
     */
    int answer = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        Arrays.sort(coins); // 升序
        int n = coins.length;
        // 降序排列
        for (int i = 0; i < n / 2; i++) {
            int temp = coins[i];
            coins[i] = coins[n - 1 - i];
            coins[n - 1 - i] = temp;
        }
        coinChange(coins, amount, 0, 0);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private void coinChange(int[] coins, int amount, int c_index, int count) {
        if (amount == 0) {
            answer = Math.min(answer, count);
            return;
        }

        if (c_index == coins.length) return;    // 硬币索引超出了

        // k + count < answer 这个剪枝为什么?
        // 因为币值从高到底排列,而k是金额不超情况下的数量,若 此时数量就已经超过answer了,
        // 那么(k-1)个当前币值 + 低于该币值的硬币数量就更加超过answer了
        for (int k = amount / coins[c_index] ; k >=0 && k + count < answer; k--) {
            coinChange(coins, amount - k * coins[c_index], c_index + 1, count + k);
        }
    }

    @Test
    public void test1() {
        int[] cs = {1, 2, 5};
        // 降序排列
//        Arrays.sort(cs);
//        int n = cs.length;
//        int[] coins = new int[n];
//        for (int i = 0; i < n; i++) {
//            coins[i] = cs[n - 1 - i];
//        }
//        System.out.println(Arrays.toString(coins));

        System.out.println(coinChange(cs, 11));
    }

    @Test
    public void test2() {
        int[] coins = {2};
        // 降序排列
//        int n = coins.length;
//        for (int i = 0; i < n / 2; i++) {
//            int temp = coins[i];
//            coins[i] = coins[n - 1 - i];
//            coins[n - 1 - i] = temp;
//        }
//        System.out.println(Arrays.toString(coins));

        System.out.println(coinChange(coins, 3));
    }
}
