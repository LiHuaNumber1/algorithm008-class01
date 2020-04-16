package cn.lihua;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * <p>
 * 示例 2:
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 */
public class N66PlusOne {
    public int[] plusOne1(int[] digits) {
        int n = digits.length;
        boolean flag = true;
        for (int i = n - 1; i >= 0; i--) {
            if (flag) {
                if (digits[i] == 9) {
                    digits[i] = 0;
                    flag = true;
                } else {
                    digits[i] += 1;
                    flag = false;
                    break;
                }
            }
        }
        int sum = 0;
        if (flag) {
            int[] d2 = new int[n + 1];
            d2[0] = 1;
            for (int i = 0; i < n; i++) {
                d2[i + 1] = digits[i];
            }
            return d2;
        }
        return digits;
    }

    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                return digits;
            }
        }
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }

    @Test
    public void test() {
        int[] arr = {1, 2, 3};
        System.out.println(Arrays.toString(plusOne(arr)));

        int[] arr1 = {1, 2, 9};
        System.out.println(Arrays.toString(plusOne(arr1)));

        int[] arr2 = {9, 9, 9};
        System.out.println(Arrays.toString(plusOne(arr2)));

        int[] arr3 = {1, 9, 9};
        System.out.println(Arrays.toString(plusOne(arr3)));
    }
}
