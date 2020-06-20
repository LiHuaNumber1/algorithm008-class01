package cn.lihua.week09;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 771. 宝石与石头
 *
 * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。

 J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。

 示例 1:
 输入: J = "aA", S = "aAAbbbb"
 输出: 3

 示例 2:
 输入: J = "z", S = "ZZ"
 输出: 0

 注意:

 S 和 J 最多含有50个字母。
  J 中的字符不重复。

 */
public class N771JewelsAndStones {

    public int numJewelsInStones1(String J, String S) {
        char[] charsJ = J.toCharArray();
        char[] charsS = S.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c : charsJ) {
            set.add(c);
        }
        int result = 0;
        for (char c : charsS) {
            if (set.contains(c)) result++;
        }
        return result;
    }

    /**
     * 比前面的方法更慢
     */
    public int numJewelsInStones(String J, String S) {
        char[] charsJ = J.toCharArray();
        char[] charsS = S.toCharArray();
        Arrays.sort(charsS);
        Set<Character> set = new HashSet<>();
        for (char c : charsJ) {
            set.add(c);
        }
        int result = 0;
        int len = charsS.length;
        for (int i = 0; i < len; i++) {
            if (set.contains(charsS[i])) {
                result++;
                while (i < len - 1 && charsS[i] == charsS[i + 1]) {
                    result++;
                    i++;
                }
            } else {
                while (i < len - 1 && charsS[i] == charsS[i + 1]) i++;
            }
        }
        return result;
    }

    @Test
    public void test() {
        String J = "aA", S = "aaaAAAAAbbbbAA";
        System.out.println(numJewelsInStones(J, S));
    }
}
