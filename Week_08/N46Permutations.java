package cn.lihua.week08;

import java.util.*;

/**
 * 46. 全排列
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。

 示例:

 输入: [1,2,3]
 输出:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]

 */
public class N46Permutations {

    /**
     * BFS,不会做啊
     */
    public List<List<Integer>> permute1(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        int len = nums.length;
        List<List<Integer>> result = new LinkedList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];
        bfs(nums, len, 0, path, used, result);
        return result;
    }

    private void bfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> result) {
        for (int i = 0; i < len; i++) {
            path.addLast(nums[i]);
            used[i] = true;
            while (!path.isEmpty()) {

            }
        }

    }

    /**
     * DFS
     */
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return new LinkedList<>();

        int len = nums.length;
        List<List<Integer>> result = new LinkedList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];
        dfs(nums, len, 0, path, used, result);  // DFS
        return result;
    }

    private void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> result) {
        // terminator
        if (depth == len) {
            result.add(new ArrayList<>(path));  // 要添加一份path的copy
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            // drill down
            dfs(nums, len, depth + 1, path, used, result);
            // 回溯
            path.removeLast();
            used[i] = false;
        }
    }


}
