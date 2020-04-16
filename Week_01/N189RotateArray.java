package cn.lihua;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lihua on 2020/3/25.
 * 功能:
 *
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

 示例 1:

 输入: [1,2,3,4,5,6,7] 和 k = 3
 输出: [5,6,7,1,2,3,4]
 解释:
 向右旋转 1 步: [7,1,2,3,4,5,6]
 向右旋转 2 步: [6,7,1,2,3,4,5]
 向右旋转 3 步: [5,6,7,1,2,3,4]
 示例 2:

 输入: [-1,-100,3,99] 和 k = 2
 输出: [3,99,-1,-100]
 解释:
 向右旋转 1 步: [99,-1,-100,3]
 向右旋转 2 步: [3,99,-1,-100]
 说明:

 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 要求使用空间复杂度为 O(1) 的 原地 算法。
 */
public class N189RotateArray {

    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        if (n < 2 || k == 0) {
            return;
        }
        // 如果k 小于 n的一半 ,后k个元素保存起来
        if (k <= n / 2) {
            int t[] = new int[k];
            // 将后 k 个元素保存到另一数组
            for (int i = 0; i < k; i++) {
                t[i] = nums[n - k + i];
            }
            // 将前段的往后移动,倒着移动
            for (int i = n - 1; i - k >= 0; i--) {
                nums[i] = nums[i - k];
            }
            // 将临时数组补到前段
            for (int i = 0; i < k; i++) {
                nums[i] = t[i];
            }
        } else {
            int t[] = new int[n - k];
            // 将前 k 个元素保存到另一数组
            for (int i = 0; i < t.length; i++) {
                t[i] = nums[i];
            }
            // 将后段的往前移动,顺着移动
            for (int i = 0; i < k; i++) {
                nums[i] = nums[i + n - k];
            }
            // 将临时数组补到后段
            for (int i = 0; i < t.length; i++) {
                nums[i + k] = t[i];
            }
        }

    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        int temp = 0;
        while (start < end) {
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 4, 5};
        rotate(nums, 2);
        System.out.println(Arrays.toString(nums));

        int[] nums2 = {1};
        rotate(nums2, 2);
        System.out.println(Arrays.toString(nums2));

        int[] nums3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        rotate(nums3, 2);
        System.out.println(Arrays.toString(nums3));

    }
}
