package cn.lihua.week06;

import org.junit.Test;

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

 示例 1:

 输入: [1,2,3,1]
 输出: 4
 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
      偷窃到的最高金额 = 1 + 3 = 4 。
 示例 2:

 输入: [2,7,9,3,1]
 输出: 12
 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class N198HouseRobber {
    /**
     * a[i]: 从 0 到 i 能偷到的最大值.最后变成 求a[n-1] ,即 从 0 到 n-1 个房子,能偷到的最大值
     * a[i][0,1]: 0 - 第i个房子不偷, 1 - 第一个房子偷 的最大值
     * 1. 第i个房子不偷, 则i-1 可以偷,也可以不偷,取第i-1个房子偷与不偷的最大值
     *      a[i][0] = Max( a[i-1][0], a[i-1][1] )
     * 2. 第i个房子偷, 则 i-1 就不能偷了(a[i-1][0]), 然后要加上当前的金额nums[i]
     *      a[i][1] = a[i-1][0] + nums[i]
     * 最后在n-1房子的偷与不偷中找最大值  Max(a[n-1][0], a[n-1][1] )
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0; // 边界

        int n = nums.length;
        int[][] a = new int[n][2];  // 第二维 用于存放 偷与不偷的最优解,下标0表示不偷,下标1表示偷

        a[0][0] = 0;        // 第0个房子不偷,金额为0
        a[0][1] = nums[0];  // 第0个房子偷,金额为数组的第0个元素值 nums[0]

        for (int i = 1; i < n; i++) {
            a[i][0] = Math.max(a[i - 1][0], a[i - 1][1]);   // 第i个房子不偷
            a[i][1] = a[i - 1][0] + nums[i];                // 第i个房子偷
        }

        return Math.max(a[n - 1][0], a[n - 1][1]); // 在最后一个房子 偷 与 不偷 中找最大值
    }

    @Test
    public void test1() {
        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println(rob(nums));  // 4
    }

    @Test
    public void test2() {
        int[] nums = new int[]{2, 7, 9, 3, 1};
        System.out.println(rob(nums));  // 12
    }
}
