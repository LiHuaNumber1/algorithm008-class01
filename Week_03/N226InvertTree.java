package cn.lihua.week03;

/**
 * 226. 翻转二叉树
 * 翻转一棵二叉树。

 示例：

 输入：

    4
 /   \
 2     7
 / \   / \
 1   3 6   9
 输出：

 4
 /   \
 7     2
 / \   / \
 9   6 3   1
 */
public class N226InvertTree {

    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode invertTree(TreeNode root) {
        // terminator
        if (root == null) {
            return root;
        }
        // drill down
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);

        // restore current status
        root.left = right;
        root.right = left;
        return root;
    }

    public TreeNode invertTree1(TreeNode root) {

        // terminator
        if (root == null || root.left == null && root.right == null) {
            return root;
        }

        // process current logic
        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;

        // drill down
        if (root.left != null) {
            invertTree1(root.left);
        }

        if (root.right != null) {
            invertTree1(root.right);
        }
        // restore current status
        return root;
    }


}
