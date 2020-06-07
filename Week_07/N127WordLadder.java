package cn.lihua.week07;

import java.util.*;

/**
 * 127. 单词接龙
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

 每次转换只能改变一个字母。
 转换过程中的中间单词必须是字典中的单词。
 说明:

 如果不存在这样的转换序列，返回 0。
 所有单词具有相同的长度。
 所有单词只由小写字母组成。
 字典中不存在重复的单词。
 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 示例 1:

 输入:
 beginWord = "hit",
 endWord = "cog",
 wordList = ["hot","dot","dog","lot","log","cog"]

 输出: 5

 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 返回它的长度 5。
 示例 2:

 输入:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]

 输出: 0

 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
public class N127WordLadder {

    /**
     * 广度优先搜索 BFS 层序遍历
     * 看官方题解
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0; //endWord不在字典中，无法进行转换。

        Map<String, Boolean> visited = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        visited.put(beginWord, true);
        queue.add(beginWord);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            // BFS层序遍历
            for (int i = 0; i < size; i++) {
                String curWord = queue.poll();
                // 遍历字典
                for (String nextWord : wordList) {
                    if (visited.get(nextWord) != null) continue;    // 如果单词已经访问过,就跳过
                    if (!canTransfer(curWord, nextWord)) continue;  // 如果当前和下一个单词不能转换,就跳过
                    if (endWord.equals(nextWord)) return ++level;   // 如果下一个单词就是结尾单词,结束
                    queue.add(nextWord);
                    visited.put(nextWord, true);
                }
            }
        }
        return 0;
    }
    private boolean canTransfer(String curWord, String nextWord) {
        int cnt = 0;
        for (int i = 0; i < curWord.length(); i++) {
            // 查找两个单词间不同的字母的个数
            if (curWord.charAt(i) != nextWord.charAt(i)) {
                cnt++;
                if (cnt >= 2) return false; // 不同字母已经达到2个,说明不能转
            }
        }
        return cnt == 1;    // 有一个字母不同的单词,才可以转换
    }


    /**
     * 双向BFS
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList__) {
        Set<String> wordList = new HashSet<>(wordList__);   // 查找 O(1)
        if (!wordList.contains(endWord)) return 0; //endWord不在字典中，无法进行转换。
        Set<String> beginSet = new HashSet<>(), // 从 beginWord 扩散出来的set
                    endSet = new HashSet<>();   // 从 endWord 扩散出来的set

        int len = 1;
        int strLen = beginWord.length();
        Set<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        // BFS start here
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // 每次扩散时只扩展数量小的set
            if (beginSet.size() > endSet.size()) {
                // 只是交换指针
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<>();
            // 遍历beginSet,对每一个单词变换一个字符
            for (String word : beginSet) {
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char old = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;   // 替换第i个字母
                        String target = String.valueOf(chars);  // 字符数组变为String

                        if (endSet.contains(target)) return len + 1;    // 找到,结束

                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                    }
                    chars[i] = old;
                }
            }
            beginSet = temp;
            len++;
        }
        return 0;
    }
}
