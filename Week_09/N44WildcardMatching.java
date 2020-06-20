package cn.lihua.week09;

import org.junit.Test;

/**
 * 44. 通配符匹配
 *
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

 '?' 可以匹配任何单个字符。
 '*' 可以匹配任意字符串（包括空字符串）。
 两个字符串完全匹配才算匹配成功。

 说明:
 s 可能为空，且只包含从 a-z 的小写字母。
 p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。

 示例 1:
 输入:
 s = "aa"
 p = "a"
 输出: false
 解释: "a" 无法匹配 "aa" 整个字符串。

 示例 2:
 输入:
 s = "aa"
 p = "*"
 输出: true
 解释: '*' 可以匹配任意字符串。

 示例 3:
 输入:
 s = "cb"
 p = "?a"
 输出: false
 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。

 示例 4:
 输入:
 s = "adceb"
 p = "*a*b"
 输出: true
 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".

 示例 5:
 输入:
 s = "acdcb"
 p = "a*c?b"
 输出: false
 */
public class N44WildcardMatching {

    /**
     * 双指针贪心算法
     * s和p匹配过程中可能会遇到的情况：
     * 1.如果i和j标记的字符正好相等或者j字符是'?'匹配成功，则"移除"i和j元素，即自增i、j。
     * 2.否则如果j字符是'*'依然可以匹配成功，则用istart和jstart分别标记i元素和j元素之后自增j。
     * 3.再否则如果istart>-1说明之前匹配过'*'，因为'*'可以匹配多个字符，
     *      所以这里要再次利用这个最近匹配过的'*'匹配更多的字符，移动i标记istart的下一个字符，
     *      再让istart重新标记i元素同时移动j标记jstart的下一个字符。
     * 上述三种情况都不满足，则匹配失败，返回false。
     * 最后当s中的字符都判断完毕，则认为s为空，此时需要p为空或者p中只剩下星号的时候，才能成功匹配。
     */
    public boolean isMatch(String s, String p) {
        if (p == null || p.length() == 0) return s == null || s.length() == 0;
        int i = 0, j = 0, iStar = -1, jStar = -1, sLen = s.length(), pLen = p.length();
        while (i < sLen) { // 遍历s字符串
            if (j < pLen && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < pLen && p.charAt(j) == '*') {
                iStar = i; // 标识 s中*能匹配到的位置
                jStar = j++; // 标识最近 * 出现的位置
            } else if (iStar > -1) { // 前2中都匹配不上,则要把当前s中的字符匹配*
                i = ++iStar; // iStar和指针i都要后移一个
                j = jStar + 1; // j不动,始终在*之后的位置上等待匹配
            } else {
                return false;   // 以上三种都不满足,则为假
            }
        }
        // s中的字符都判断完毕，则认为s为空，此时需要p为空或者p中只剩下星号的时候，才能成功匹配。
        // 若s中已完毕,而p未完毕,且不是*,那么说明s中一直匹配不上p中的字符,导致s匹配* 到结束.
        // 如果p中剩余的都是*，则可以移除剩余的*
        while (j < pLen && p.charAt(j) == '*') j++;
        return j == pLen;   // 如果p还没结束,说明还剩余了字符没有匹配上
    }

    @Test
    public void test() {
        String s = "acdcbbcb";
        String p = "a**c*?b**";
        System.out.println(isMatch(s, p));
    }

    @Test
    public void test2() {
        String s = "adceb";
        String p = "*a*b";
        System.out.println(isMatch(s, p)); //true
    }
}
