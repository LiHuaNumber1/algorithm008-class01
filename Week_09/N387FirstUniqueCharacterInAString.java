package cn.lihua.week09;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 387. 字符串中的第一个唯一字符
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

 示例：

 s = "leetcode"
 返回 0

 s = "loveleetcode"
 返回 2
  

 提示：你可以假定该字符串只包含小写字母。

 */
public class N387FirstUniqueCharacterInAString {

    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        // 将字母存入map中计数
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 按照字符串的顺序遍历map,第一个1返回
        for (int i = 0; i < n; i++) {
            if (map.get(s.charAt(i)) == 1) return i;
        }
        // 若没找到返回-1
        return -1;
    }


}
