package cn.lihua.week08;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 面试题40. 最小的k个数
 *
 * 输入整数数组 arr ，找出其中最小的 k 个数。
 * 例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

 示例 1：

 输入：arr = [3,2,1], k = 2
 输出：[1,2] 或者 [2,1]
 示例 2：

 输入：arr = [0,1,2,1], k = 1
 输出：[0]
  

 限制：
 0 <= k <= arr.length <= 10000
 0 <= arr[i] <= 10000

 */
public class Interview40GetLeastNumbers {
    /**
     * 小顶堆
     * PriorityQueue是heap,默认是小顶堆,容量11
     */
    public int[] getLeastNumbers1(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            heap.add(arr[i]);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap.poll();   // 每次取出最小的
        }
        return result;
    }

    /**
     * 快速排序
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        quickSort(arr, 0, arr.length - 1);
        int[] res = new int[k];
        return Arrays.copyOf(arr, k);
    }

    private void quickSort(int[] arr, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(arr, begin, end);
        quickSort(arr, begin, pivot - 1);
        quickSort(arr, pivot + 1, end);
    }

    private int partition(int[] arr, int begin, int end) {
        int pivot = end, counter = begin;
        for (int i = begin; i < end; i++) {
            if (arr[i] < arr[pivot]) {
                if (i != counter) {
                    int temp = arr[i];
                    arr[i] = arr[counter];
                    arr[counter] = temp;
                }
                counter++;
            }
        }
        int temp = arr[pivot];
        arr[pivot] = arr[counter];
        arr[counter] = temp;
        return counter;
    }

    @Test
    public void test1() {
        int[] arr = {3, 2, 1};
        int k = 2;
        System.out.println(Arrays.toString(getLeastNumbers(arr, k)));

        int[] arr2 = {0, 1, 2, 1};
        k = 1;
        System.out.println(Arrays.toString(getLeastNumbers(arr2, k)));
    }
}
