package cn.lihua;

import org.junit.Test;

import java.util.Arrays;

/**
 * 26. 删除排序数组中的重复项
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

 */
public class N26RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int flag = 0;  // 比较的标志位
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[flag] != nums[i]) {  // i=1,2,3,4,5,6,7,8,9
                if (i - flag > 1) {   // 如果不是相邻位置
                    nums[flag + 1] = nums[i]; //数据往比较位后的位置移动
                }
                flag += 1;        // 标志位后移 0,1,2,3,4
            }
        }
        // 出循环i=10,flag记录的是有效元素的下标,数组的长度应是 flag+1;
        return flag + 1;
    }

    @Test
    public void test(){
        int[] nums = {1, 2, 3, 4, 5};
//        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 5};
        //int[] nums = {0, 1, 2, 3, 4, 2, 2, 3, 3, 4};
        int len = removeDuplicates(nums);
        System.out.println("数组长度是: "+len);
        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }
        // 其实是做不到真正缩容的,只是题目说:"你不需要考虑数组中超出新长度后面的元素。"
        System.out.println(Arrays.toString(nums));
    }

}
