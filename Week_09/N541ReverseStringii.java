package cn.lihua.week09;

import org.junit.Test;

/**
 * 541. 反转字符串 II
 *
 * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。

 示例:
 输入: s = "abcdefg", k = 2
 输出: "bacdfeg"

 提示：
 该字符串只包含小写英文字母。
 给定字符串的长度和 k 在 [1, 10000] 范围内。

 */
public class N541ReverseStringii {

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int len = s.length();
        int n = len / (2 * k); // 有几个2k,包含0
        int m = len % (2 * k); // 剩余字符
        for (int i = 1; i <= n; i++) {
            int start = 2 * k * (i - 1);
            reverse(chars, start, start + k - 1);
        }
        if (m > 0) {
            if (m < k) {
                reverse(chars, 2 * k * n, len - 1);
            } else { // 小于 2k 但大于或等于 k
                reverse(chars, 2 * k * n, 2 * k * n + k - 1);
            }
        }
        return new String(chars);
    }
    // 将chars数组的[start,end]反转
    private void reverse(char[] chars, int start, int end) {
        int half = (end - start) / 2;
        for (int i = 0; i <= half; i++) {
            char temp = chars[start + i];
            chars[start + i] = chars[end - i];
            chars[end - i] = temp;
        }
    }

    @Test
    public void test() {
        String s = "ab";
//        String s = "abc";
//        String s = "abcd";
//        String s = "abcdef";
//        String s = "abcdefg";
//        String s = "abcdefgh";
//        String s = "abcdefghij";
        int k = 2;
        System.out.println(reverseStr(s, k));
    }
}
