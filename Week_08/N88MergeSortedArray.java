package cn.lihua.week08;

/**
 * 88. 合并两个有序数组
 *
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。

  

 说明:

 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
  

 示例:

 输入:
 nums1 = [1,2,3,0,0,0], m = 3
 nums2 = [2,5,6],       n = 3

 输出: [1,2,2,3,5,6]

 */
public class N88MergeSortedArray {

    /**
     * 归并排序
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[nums1.length];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            temp[k++] = nums1[i] <= nums2[j] ? nums1[i++] : nums2[j++];
        }
        while (i < m) temp[k++] = nums1[i++];
        while (j < n) temp[k++] = nums2[j++];

        for (int p = 0; p < temp.length; p++) {
            nums1[p] = temp[p];
        }
    }

    /**
     * 从后向前数组遍历
     * 因为 nums1 的空间都集中在后面，所以从后向前处理排序的数据会更好，节省空间，一边遍历一边将值填充进去
     * 设置指针 i 和 j 分别指向 nums1 和 nums2 的有数字尾部，从尾部值开始比较遍历，
     * 同时设置指针 k 指向 nums1 的最末尾，每次遍历比较值大小之后，则进行填充
     * 若 j<0,说明nums2中的数已经都移到nums1中了,结束.
     * 若 i<0 时遍历结束，此时 nums2 中还有数据未拷贝完全，将其直接拷贝到 nums1 的前面，最后得到结果数组
     * 时间复杂度：O(m+n)O(m+n)
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i >= 0 && j >= 0) {
            nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}
