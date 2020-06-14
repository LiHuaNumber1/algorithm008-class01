package cn.lihua.week08;

import org.junit.Test;

import java.util.Arrays;

/**
 * 初级排序2: 插入排序
 */
public class InsertSort {
    /**
     * 插入排序
     */
    public int[] insertSort1(int[] nums) {
        if (nums == null) return null;
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] <= current) {
                    nums[j + 1] = current;
                    break;
                } else {
                    nums[j + 1] = nums[j]; // 前面的数后移
                    if (j == 0) nums[j] = current;
                }
            }
        }
        return nums;
    }

    /**
     * 插入排序
     */
    public int[] insertSort(int[] nums) {
        if (nums == null) return null;
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            int preIdx = i - 1; // 当前待插入值,前面的元素
            // 寻找插入点的前一个位置
            while (preIdx >= 0 && nums[preIdx] > current) {
                nums[preIdx + 1] = nums[preIdx];
                preIdx--;
            }
            // 若preIdx == -1,说明该插入在0的位置
            nums[preIdx+1] = current;
        }
        return nums;
    }

    @Test
    public void test() {
        int[] nums = {2, 4, 6, 1, 3, 7, 9, 5};
        System.out.println(Arrays.toString(insertSort(nums)));
    }
}
