package cn.lihua.week08;

import org.junit.Test;

/**
 * 231. 2的幂
 *
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

 示例 1:

 输入: 1
 输出: true
 解释: 20 = 1

 示例 2:

 输入: 16
 输出: true
 解释: 24 = 16

 示例 3:

 输入: 218
 输出: false

 */
public class N231PowerOfTwo {

    /**
     * 2的幂次方,指的是只有一个数字位为1.
     * n 不能为0,
     * 判断只有一位是1, x&(x-1)是清除最低位的1,
     * 若清除后,结果是0,说明只有一个1
     */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    @Test
    public void test() {
        System.out.println(isPowerOfTwo(0));
        System.out.println(isPowerOfTwo(1));
        System.out.println(isPowerOfTwo(16));
        System.out.println(isPowerOfTwo(218));
        System.out.println(isPowerOfTwo(-2147483648));
    }

}
