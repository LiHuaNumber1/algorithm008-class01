package cn.lihua.week08;

import org.junit.Test;

import java.util.Arrays;

/**
 * 338. 比特位计数
 *
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。

 示例 1:

 输入: 2
 输出: [0,1,1]
 示例 2:

 输入: 5
 输出: [0,1,1,2,1,2]
 进阶:

 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 要求算法的空间复杂度为O(n)。
 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。

 */
public class N338CountingBits {

    /**
     * 暴力法:循环,判断每一个数的二进制1的个数
     */
    public int[] countBits1(int num) {
        if (num == 0) return new int[]{0};
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            int cnt = 1;
            int curr = i;
            while ((curr = curr & (curr - 1)) != 0) {
                cnt++;
            }
            result[i] = cnt;
        }
        return result;
    }

    /**
     * 奇数: 一定是前面的偶数+1,最低位多了一个1
     * 偶数: 最低位为0,与右移一位的个数是一致的,右移一位就是除以2
     * 用奇偶性来遍历
     */
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            if ((i & 1) == 1) { // 奇数
                result[i] = result[i - 1] + 1;
            } else {    // 偶数
                result[i] = result[i >> 1];
            }
        }
        return result;
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(countBits(0)));
        System.out.println(Arrays.toString(countBits(2)));
        System.out.println(Arrays.toString(countBits(5)));
        System.out.println(Arrays.toString(countBits(7)));
    }

}
