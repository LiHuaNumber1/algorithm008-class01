package cn.lihua.week07;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 *
 * 给定一个二叉树，返回它的中序 遍历。

 示例:

 输入: [1,null,2,3]
 1
 \
  2
 /
 3

 输出: [1,3,2]
 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class N94BinaryTreeInorderTraversal {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归
     */
    private List<Integer> result = new ArrayList<>();
    public List<Integer> inorderTraversal1(TreeNode root) {

        if (root == null) return result;
        if (root.left != null) {
            inorderTraversal1(root.left);
        }
        result.add(root.val);
        if (root.right != null) {
            inorderTraversal1(root.right);
        }
        return result;
    }

    /**
     * 中序遍历的迭代
     * 思路：每到一个节点 A，因为根的访问在中间，将 A 入栈。
     * 然后遍历左子树，接着访问 A，最后遍历右子树。
     *
     * 在访问完 A 后，A 就可以出栈了。因为 A 和其左子树都已经访问完成。
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        // 为什么用||,因为若root为空了,栈中还应该出栈
        // 栈的非空判断不能用 stack != null
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }
}
