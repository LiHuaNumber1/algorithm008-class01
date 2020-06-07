# 第七周学习笔记

## 字典树 trie
### 字典树数据结构
字典树，即 Trie 树，又称单词查找树或键树，是一种树形结构。典型应用于统计和排序大量的字符串（但不仅限于字符串）所以经常被搜索引擎系统用于文本词频统计。
优点：最大限度的减少无谓的字符串比较，查询效率比hash表高。
### 字典树核心思想
空间换时间。
利用字符串公共前缀，降低查询时间开销，以达到提高效率目的。
### 字典树的基本性质
节点本身不存完整的单词。
从根结点到某一结点，路径上经过的结点连接起来，为该结点对应的字符串。
每个结点的所有子结点路径代表的字符都不相同。

## 双向BFS

基本的双向BFS，就是每次迭代选择状态空间小的进行BFS，直到找到共同的单词，就可以退出。

beginSet 和 endSet是对等的，从前找到后，和从后找到前是一样的，所以每次选择状态空间小的进行扩散。

不用双向BFS时，我采用层序BFS，时间复杂度很高。用了双向BFS后，关键是每次选择小的状态空间，时间复杂度就小了很多。

这里要注意到的一个问题是：因为beginSet增加新的待选后，向endSet中查看是否存在，但是beginSet和endSet又是不断更新的，会不会出现，beginSet新增加的待选出现在之前的endSet中，而没有出现在现在的endSet中呢？根据结果来看是没有的，现在根据自己的理解解释的话：从begin到end的过程最短的路径要分几步，说明是有一个变化的过程，而每次while是将这个过程往前推进一步，如果用双向，因为从begin和end两头推进，如果有最短路径的话，二者会在中间相遇，之间的变化只有一步了，而之前说的问题，相当于从begin推进几步后，再变化一步，到了end之前的某个状态，而这之间跳过了end，肯定是大于一步的，不可实现。

```
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
    ```