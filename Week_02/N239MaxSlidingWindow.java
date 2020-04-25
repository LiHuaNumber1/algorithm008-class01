package cn.lihua.week02;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 239. 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。

 进阶：
 你能在线性时间复杂度内解决此题吗？

 示例:

 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 输出: [3,3,5,5,6,7]
 解释:

 滑动窗口的位置                最大值
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
  
 提示：
 1 <= nums.length <= 10^5
 -10^4 <= nums[i] <= 10^4
 1 <= k <= nums.length
 */
public class N239MaxSlidingWindow {

    /**
     * 大顶堆
     * PriorityQueue默认小顶堆,容量11.
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {   // 边界,但提示中都是>1
            return new int[0];
        }
        int n = nums.length;
        int[] result = new int[n - k + 1];  // 滑动窗口最大值的个数

        // 大顶堆, 不控制堆的大小,会快一点
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((o1, o2) -> (o2.compareTo(o1)));

        for (int i = 0; i < n; i++) {
            int start = i - k;
            if (start >= 0) {   // 大顶堆里已经满了,左边的应该滑出
                maxPQ.remove(nums[start]);  // 将某个值(左边)删除,内部自动会调整顺序,自顶向下
            }
            maxPQ.offer(nums[i]);           // 添加后边的新值,内部自动回调整顺序,自底向上
            if (maxPQ.size() == k) {    // 小于k时,说明刚刚开始,还没填满
                result[i - k + 1] = maxPQ.peek();
            }
        }
        return result;
    }

    @Test
    public void test1() {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(arr, k)));
    }
}
