package cn.lihua.week06;

import org.junit.Test;

import java.util.Queue;

/**
 * 53. 最大子序和
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 示例:

 输入: [-2,1,-3,4,-1,2,1,-5,4],
 输出: 6
 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 进阶:

 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class N53MaxSubArray {

    /**
     * 暴力法
     * 先固定一个数,后面的数遍历,得到最大值
     */
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int currSum = nums[i];
            max = Math.max(max, currSum);
            for (int j = i + 1; j < nums.length; j++) {
                currSum += nums[j];
                max = Math.max(max, currSum);
            }
        }
        return max;
    }

    /**
     * DP: 最后一个的最大子串: 或者 自己,或者包含前面的最大子串
     * 1.分治(子问题) max_sum[i] = Max (max_sum[i-1], 0) + nums[i]
     * 2.状态数组定义 dp[i]
     * 3.DP方程: dp[i] = Max(0, dp[i-1]) + nums[i]
     */
    public int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        int max = dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 当前子数组的最大值: 或者不包含前面的最大子数组,或者包含前面的最大子数组
            // 若前面都<0,还不如不选
            // dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            dp[i] = nums[i] + Math.max(0, dp[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 改进:复用数组,因为循环下标从小到大,前面的已经记录最大值
     * DP: 最后一个的最大子串: 或者 自己,或者包含前面的最大子串
     * 1.分治(子问题) max_sum[i] = Max (max_sum[i-1], 0) + nums[i]
     * 2.状态数组定义 dp[i]
     * 3.DP方程: dp[i] = Max(0, dp[i-1]) + nums[i]
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 当前子数组的最大值: 或者不包含前面的最大子数组,或者包含前面的最大子数组
            // 若前面都<0,还不如不选
            // dp[i] = nums[i] + Math.max(0, dp[i - 1]);
            nums[i] = nums[i] + Math.max(0, nums[i - 1]);
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    @Test
    public void test() {
//        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums = {1};
//        int[] nums = {-2, 1};
        System.out.println(maxSubArray(nums));
    }
}
