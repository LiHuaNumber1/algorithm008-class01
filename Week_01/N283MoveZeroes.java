package cn.lihua;

import org.junit.Test;

import java.util.Arrays;

/**
 * 功能: 283 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class N283MoveZeroes {

    public void moveZeroes(int[] nums) {
        boolean flag = false;
        int a = 0;  // 可插入的位
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if(nums[i] != 0) {
                if (flag && i > a) {
                    nums[a] = nums[i];
                    nums[i] = 0;
                    a++;
                }
            } else if (!flag){
                flag = true;
                a = i;
            }
        }
    }

    @Test
    public void test() {
        int[] arr = {2,1};
        moveZeroes(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = {0,0,12,0,3};
        moveZeroes(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {1,0,12};
        moveZeroes(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
