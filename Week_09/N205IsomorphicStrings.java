package cn.lihua.week09;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. 同构字符串
 *
 * 给定两个字符串 s 和 t，判断它们是否是同构的。

 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。

 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。

 示例 1:
 输入: s = "egg", t = "add"
 输出: true

 示例 2:
 输入: s = "foo", t = "bar"
 输出: false

 示例 3:
 输入: s = "paper", t = "title"
 输出: true
 说明:
 你可以假设 s 和 t 具有相同的长度。
 */
public class N205IsomorphicStrings {

    /**
     * 将s和t中的对应字符存为map的key和value,
     * 每次s的字符在key中找,若没找到,说明是新的,
     *      但也要看t中的字符,是否在value中出现过, 都未出现过:存入即可;
     *      排除如: s = "ab", t = "aa"
     * 若找到了,说明已经有,看看value值是否与t的字符一致,如果不一致就不是同构字符串
     * 循环结束还没遇到return false,说明是同构.
     */
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(s.charAt(i))) { // 如果s的字符没出现在key中,说明是新的字符
                if (!map.containsValue(t.charAt(i))) { // 但也要看t中的字符,是否在value中出现过
                    map.put(s.charAt(i), t.charAt(i)); // 将s的字符存在key,将t的字符存在value
                } else {
                    return false; // 如: s = "ab", t = "aa"
                }
            } else if (map.get(s.charAt(i)) != t.charAt(i)) { //如果s的字符出现在key中,则只要比较value与t的字符是否相同
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
//        String s = "egg", t = "add";
//        String s = "foo", t = "bar";
//        String s = "paper", t = "title";
        String s = "ab", t = "aa";
        System.out.println(isIsomorphic(s, t));
    }
}
