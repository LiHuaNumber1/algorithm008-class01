package cn.lihua.week08;

import org.junit.Test;

import java.util.Arrays;

/**
 * 初级排序1: 选择排序
 * 每次找最小值,然后放到待排序数组的起始位置
 */
public class SelectSort {

    /**
     * 选择排序: 时间复杂度 O(n^2)
     */
    public int[] selectSort(int[] nums) {
        if (nums == null) return null;
        int minIdx;
        for (int i = 0; i < nums.length - 1; i++) {
            minIdx = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIdx]) {
                    minIdx = j;
                }
            }
            // 交换
            int temp = nums[i];
            nums[i] = nums[minIdx];
            nums[minIdx] = temp;
        }
        return nums;
    }

    @Test
    public void test() {
        int[] nums = {2, 4, 6, 1, 3, 7, 9, 5};
        System.out.println(Arrays.toString(selectSort(nums)));
    }
}
