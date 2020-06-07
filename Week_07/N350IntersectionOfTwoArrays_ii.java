package cn.lihua.week07;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 350. 两个数组的交集 II
 *
 * 给定两个数组，编写一个函数来计算它们的交集。

 示例 1:

 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 输出: [2,2]
 示例 2:

 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 输出: [4,9]
 说明：

 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 我们可以不考虑输出结果的顺序。
 进阶:

 如果给定的数组已经排好序呢？你将如何优化你的算法？
 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？

 */
public class N350IntersectionOfTwoArrays_ii {

    /**
     * 效率不高:
     * 执行用时 :5 ms, 在所有 Java 提交中击败了35.73%的用户
     * 内存消耗 :39.7 MB, 在所有 Java 提交中击败了5.13%的用户
     */
    public int[] intersect1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return new int[0];
        // 让nums1中的长度> nums2,若不满足,则交换
        if (nums1.length < nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int len1 = nums1.length;
        int len2 = nums2.length;
        List<Integer> list = new LinkedList<>();
        int k = 0;
        for (int i = 0; i < len2; i++) {
            for (int j = k; j < len1; j++) {
                if (nums2[i] == nums1[j]) {
                    list.add(nums2[i]);
                    k = j + 1;  // 这个地方要从j后面开始
                    break;
                }
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }


    /**
     * 双指针
     * 但结果先用List存着,在放到数组中
     * 效率不高:
     * 执行用时 :4 ms, 在所有 Java 提交中击败了60.42%的用户
     * 内存消耗 :39.9 MB, 在所有 Java 提交中击败了5.13%的用户
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return new int[0];

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> list = new LinkedList<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 双指针 : 效率高
     * 但结果直接借用数组1
     * 执行用时 :2 ms, 在所有 Java 提交中击败了99.81%的用户
     * 内存消耗 :39.9 MB, 在所有 Java 提交中击败了5.13%的用户
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return new int[0];

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int k = 0;
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                nums1[k++] = nums1[i++];
                j++;
            }
        }
        return Arrays.copyOfRange(nums1, 0, k); // 不包含k
    }

    @Test
    public void test() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(intersect(nums1, nums2)));
    }

    @Test
    public void test2() {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        System.out.println(Arrays.toString(intersect(nums1, nums2)));
    }

    @Test
    public void test3() {
        int[] nums1 = {4, 9, 4, 6};
        int[] nums2 = {9, 4, 9, 8, 9, 4, 5, 4, 4};
        System.out.println(Arrays.toString(intersect(nums1, nums2)));
    }

    @Test
    public void test4() {
        int[] nums1 = {1, 2};
        int[] nums2 = {1, 1};
        System.out.println(Arrays.toString(intersect(nums1, nums2)));
    }

}
