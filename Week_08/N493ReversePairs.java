package cn.lihua.week08;

import org.junit.Test;

import java.util.Arrays;

/**
 * 493. 翻转对
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。

 你需要返回给定数组中的重要翻转对的数量。

 示例 1:

 输入: [1,3,2,3,1]
 输出: 2
 示例 2:

 输入: [2,4,3,5,1]
 输出: 3
 注意:

 给定数组的长度不会超过50000。
 输入数组中的所有数字都在32位整数的表示范围内。
 */
public class N493ReversePairs {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        // terminator
        if (left >= right) return 0;
        // int mid = left + (right - left) / 2;
        int mid = (left + right) >> 1;
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        int[] cache = new int[right - left + 1]; //用于归并的临时数组
        int i = left, j = mid + 1, k = 0, tmp = left; // tmp用于指向翻转对的位置
        while (j <= right) {
            // 每次固定j.
            // 第2次循环开始为什么tmp不重置为0?
            // 因为左右都是升序数组,第1次循环中tmp已经是临界值,当j增大,tmp应该更大才能匹配
            while (tmp <= mid && nums[tmp] <= 2 * (long)nums[j]) tmp++; // tmp指向了下一个翻转对的位置
            count += mid - tmp + 1;   // 由于2个数组都是排序的,左边第tmp-mid 相对于当前第j个元素都是翻转对

            // 没有逆序对时,就将nums[i]的值copy到cache中
            while (i <= mid && nums[i] < nums[j]) cache[k++] = nums[i++];
            // 上一句出循环,有2种情况:1-碰到逆序对,填入下一个nums[j]; 2-左边用完,将剩下右边的放入cache
            cache[k++] = nums[j++];
        }
        // 如果左边没有用完,说明还有更大的数留着了--没用完的都是大的数
        while (i <= mid) cache[k++] = nums[i++];
        System.arraycopy(cache, 0, nums, left, right - left + 1);
        return count;
    }

    @Test
    public void test() {
        int[] nums = {1, 3, 2, 3, 1};
        int[] arr = {2, 4, 3, 5, 1};
        int cnt = reversePairs(nums);
        int cnt2 = reversePairs(arr);
        System.out.println(cnt);
        System.out.println(Arrays.toString(nums));
        System.out.println(cnt2);
        System.out.println(Arrays.toString(arr));
    }
}
