package cn.lihua.week03;

/**
 * 98. 验证二叉搜索树
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。

 假设一个二叉搜索树具有如下特征：

 节点的左子树只包含小于当前节点的数。
 节点的右子树只包含大于当前节点的数。
 所有左子树和右子树自身必须也是二叉搜索树。
 示例 1:

 输入:
 2
 / \
 1   3
 输出: true
 示例 2:

 输入:
 5
 / \
 1   4
      / \
     3   6
 输出: false
 解释: 输入为: [5,1,4,null,null,3,6]。
      根节点的值为 5 ，但是其右子节点值为 4 。

 */
public class N98IsValidBST {
    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private long last = Long.MIN_VALUE;
    /*
     * BST 中序遍历是升序的
     */
    public boolean isValidBST(TreeNode root) {
        // terminator
        if (root == null) {
            return true;
        }

        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }

        // 访问根节点
        if (root.val <= last) {
            return false;
        }

        last = root.val;

        // 访问右子树
        return isValidBST(root.right);
    }

    /**
     * 错误的答案
     * 只判断了左节点和右节点与根节点值的对比.
     * 而二叉搜索树是左右子树,不只是节点.
     */
    public boolean isValidBST1(TreeNode root) {
        // terminator
        if (root == null) {
            return true;
        }

        if (root.left != null && root.val <= root.left.val || root.right != null && root.val >= root.right.val) {
            return false;
        }

        // drill down
        return isValidBST(root.left) && isValidBST(root.right);
    }
}
