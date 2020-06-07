package cn.lihua.week07;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。

  

 示例：
 二叉树：[3,9,20,null,null,15,7],

 3
 / \
 9  20
 /  \
 15   7
 返回其层次遍历结果：

 [
 [3],
 [9,20],
 [15,7]
 ]
 */
public class N102BinaryTreeLevelOrderTraversal {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 层序遍历
     * 注意:与BFS是有区别的.BFS 只是管了遍历的先后次序,但不能记录当前的层次.
     *      而层序遍历,是一层遍历完成,再遍历另一层,可以记录下层数.
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            int n = queue.size();   // 一层的节点数
            List<Integer> level = new ArrayList<>();
            // 一层遍历
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);   // 虽然加入了下一层的节点,但当前层不遍历
                }
                if (node.right != null) {
                    queue.add(node.right);  // 同上
                }
            }
            result.add(level);
        }
        return result;
    }
}
