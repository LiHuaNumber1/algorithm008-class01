package cn.lihua.week08;

import org.junit.Test;

import java.util.Arrays;

/**
 * 初级排序3: 冒泡排序
 */
public class BubbleSort {

    /**
     * 冒泡排序,每一趟将最大的数放到最后.
     */
    public int[] bubbleSort(int[] nums) {
        if (nums == null) return null;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        return nums;
    }

    @Test
    public void test() {
        int[] nums = {2, 4, 6, 1, 3, 7, 9, 5};
        System.out.println(Arrays.toString(bubbleSort(nums)));
    }
}
