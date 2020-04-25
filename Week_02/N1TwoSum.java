package cn.lihua;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 1. 两数之和
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

  

 示例:

 给定 nums = [2, 7, 11, 15], target = 9

 因为 nums[0] + nums[1] = 2 + 7 = 9
 所以返回 [0, 1]

 */
public class N1TwoSum {

    /**
     * 暴力
     */
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * 两遍哈希表
     */
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i); // key为元素值,value为下标
        }
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another) && map.get(another) != i) {// 不是同一个下标
                return new int[]{i, map.get(another)};
            }
        }
        return null;
    }

    /**
     * 一遍哈希表
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another)) {
//                return new int[]{i, map.get(another)};  // [2, 1] 也对
                return new int[]{map.get(another), i};  // [1, 2]
            }
            map.put(nums[i], i); // key为元素值,value为下标
        }
        return null;
    }
    @Test
    public void test() {
//        int[] nums = {2, 7, 11, 15};
//        System.out.println(Arrays.toString(twoSum(nums, 9)));
//        System.out.println(Arrays.toString(twoSum(nums, 22)));
//        System.out.println(Arrays.toString(twoSum(nums, 21)));

        int[] nums2 = {3, 2, 4};
        System.out.println(Arrays.toString(twoSum(nums2, 6)));
    }
}
