package cn.lihua.week08;

import org.junit.Test;

import java.util.Arrays;

/**
 * 高级排序1: 快速排序
 */
public class QuickSort {

    public int[] quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    // Java
    public  void quickSort(int[] array, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    int partition(int[] a, int begin, int end) {
        // pivot: 标杆位置，counter: 小于pivot的元素的个数,也是指向第一个大于pivot的下标
        int pivot = end, counter = begin;
        for (int i = begin; i < end; i++) {
            if (a[i] < a[pivot]) {
                if (i != counter) {
                    int temp = a[counter];
                    a[counter] = a[i];
                    a[i] = temp;
                }
                counter++; // 记录小pivot的元素个数
            }
        }
        int temp = a[pivot]; a[pivot] = a[counter]; a[counter] = temp;
        return counter;
    }

    @Test
    public void test() {
        int[] nums = {2, 4, 6, 1, 8, 3, 7, 9, 5};
        System.out.println(Arrays.toString(quickSort(nums)));
    }

}
