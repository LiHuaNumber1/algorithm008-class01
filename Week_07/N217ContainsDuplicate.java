package cn.lihua.week07;

import org.junit.Test;

import java.util.*;

/**
 * 217. 存在重复元素
 *
 * 给定一个整数数组，判断是否存在重复元素。

 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。

  

 示例 1:

 输入: [1,2,3,1]
 输出: true
 示例 2:

 输入: [1,2,3,4]
 输出: false
 示例 3:

 输入: [1,1,1,3,3,4,3,2,4,2]
 输出: true
 */
public class N217ContainsDuplicate {

    /**
     * 效率不高:
     * 执行用时 :11 ms, 在所有 Java 提交中击败了21.31%的用户
     * 内存消耗 :46.1 MB, 在所有 Java 提交中击败了6.98%的用户
     */
    public boolean containsDuplicate1(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return true;    // 出现第2次了
            } else {
                map.put(nums[i], 1);
            }
        }
        return false;
    }

    /**
     * 效率不高: 但Set比Map效率要高一点
     * 执行用时 :9 ms, 在所有 Java 提交中击败了55.24%的用户
     * 内存消耗 :46.1 MB, 在所有 Java 提交中击败了6.98%的用户
     */
    public boolean containsDuplicate2(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }

    /**
     * 效率比map和set高
     * 执行用时 :4 ms, 在所有 Java 提交中击败了98.84%的用户
     * 内存消耗 :43.7 MB, 在所有 Java 提交中击败了32.56%的用户
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 1};
        System.out.println(containsDuplicate(nums));
        int[] nums1 = {1, 2, 3, 4};
        System.out.println(containsDuplicate(nums1));
        int[] nums2 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        System.out.println(containsDuplicate(nums2));
    }
}
