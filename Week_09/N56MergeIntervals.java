package cn.lihua.week09;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 56. 合并区间
 *
 * 给出一个区间的集合，请合并所有重叠的区间。

 示例 1:

 输入: [[1,3],[2,6],[8,10],[15,18]]
 输出: [[1,6],[8,10],[15,18]]
 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 示例 2:

 输入: [[1,4],[4,5]]
 输出: [[1,5]]
 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。

 */
public class N56MergeIntervals {

    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        int[][] res = new int[intervals.length][2];
        int idx = -1; // res的指针
        for (int i = 0; i < intervals.length; i++) {
            // idx为-1,说明要放第0个元素进去
            // idx第二维值<i第一维的值,说明2个是断开的区间,那么idx要存下一个元素了
            if (idx == -1 || res[idx][1] < intervals[i][0]) {
                res[++idx] = intervals[i];
            } else {
                // 是连续区间,那么第二维就取一个大的值
                res[idx][1] = Math.max(res[idx][1], intervals[i][1]);
            }
        }
        return Arrays.copyOf(res, idx + 1); // idx是下标,长度要+1
    }
    
    @Test
    public void test() {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        intervals = merge(intervals);
        for (int i = 0; i < intervals.length; i++) {
            System.out.println(Arrays.toString(intervals[i]));
        }
    }   
    
    @Test
    public void test2() {
        int[][] intervals = {{2, 3}, {2, 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2}, {4, 6}};
        intervals = merge(intervals);
        for (int i = 0; i < intervals.length; i++) {
            System.out.println(Arrays.toString(intervals[i]));
        }
    }
}
