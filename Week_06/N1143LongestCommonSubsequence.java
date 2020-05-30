package cn.lihua.week06;

import org.junit.Test;

/**
 * 1143. 最长公共子序列
 *
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。

 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。

 若这两个字符串没有公共子序列，则返回 0。
  

 示例 1:

 输入：text1 = "abcde", text2 = "ace"
 输出：3
 解释：最长公共子序列是 "ace"，它的长度为 3。

 示例 2:

 输入：text1 = "abc", text2 = "abc"
 输出：3
 解释：最长公共子序列是 "abc"，它的长度为 3。

 示例 3:

 输入：text1 = "abc", text2 = "def"
 输出：0
 解释：两个字符串没有公共子序列，返回 0。
  

 提示:
 1 <= text1.length <= 1000
 1 <= text2.length <= 1000
 输入的字符串只含有小写英文字符。
 */
public class N1143LongestCommonSubsequence {

    /**
     * 用二维数组,类似路径问题
     * 这里将null值归入第0行和第0列,故数组行列长度都需要+1
     * 循环时从1开始,到长度+1 // 这个容易错
     * 比较时,是字符串中的,下标从0开始,故i-1,j-1,如text1.charAt(j - 1)
     * 返回结果时,注意长度已经是字符串长度+1了,故要dp[rows][cols]
     * 总结:本题的错误主要在下标没弄清楚
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int cols = text1.length();
        int rows = text2.length();
        int[][] dp = new int[rows + 1][cols + 1];   // 行和列都要+1,将null 算进去
        // 由于空串的时候肯定最长公共子串为0，所以直接从dp[1][1]开始遍历
        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < cols + 1; j++) {
                // 若s1[j] == s2[i] 时, 是去掉此字符时的长度+1
                if (text1.charAt(j - 1) == text2.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    //s1[i] != s2[j]则表示至少有一个不在（要么s1[i]不在，要么s2[j]不在，或者都不在）。
                    // 所以当前结果就相当于之前结果的中最大的那一个
                    // 二维数组上面格子 和 左边格子 的最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[rows][cols];
    }

    @Test
    public void test() {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(longestCommonSubsequence(text1, text2));
    }
}
