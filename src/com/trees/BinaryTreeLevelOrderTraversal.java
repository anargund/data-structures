package com.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode#102 Binary Tree Level Order Traversal
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class BinaryTreeLevelOrderTraversal {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal treeTraversal = new BinaryTreeLevelOrderTraversal();
        System.out.println(treeTraversal.levelOrder(null));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) return levels;
        levelOrderHelper(root, 0, levels);
        return levels;
    }

    private void levelOrderHelper(TreeNode node, int level, List<List<Integer>> levels) {
        if (levels.size() == level) {
            levels.add(new ArrayList<>());
        }

        levels.get(level).add(node.val);
        if (node.left != null) levelOrderHelper(node.left, level + 1, levels);
        if (node.right != null) levelOrderHelper(node.right, level + 1, levels);
    }

}
