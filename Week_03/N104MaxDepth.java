package cn.lihua.week03;

/**
 * 104. 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。

 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

 说明: 叶子节点是指没有子节点的节点。

 示例：
 给定二叉树 [3,9,20,null,null,15,7]，

 3
 / \
 9  20
 /  \
 15   7
 返回它的最大深度 3 。

 */
public class N104MaxDepth {
    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int maxDepth(TreeNode root) {
        return _max(0, root);
    }

    private int _max(int level, TreeNode root) {
        if (root == null) {
            return level;
        }

        if (root.left == null && root.right == null) {
            return level + 1;
        }

        int left = _max(level + 1, root.left);
        int right = _max(level + 1, root.right);

        return Math.max(left, right);
    }
}
