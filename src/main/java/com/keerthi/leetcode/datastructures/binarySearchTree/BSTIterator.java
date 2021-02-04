package com.keerthi.leetcode.datastructures.binarySearchTree;

import com.keerthi.leetcode.datastructures.TreeNode;

import java.util.Stack;

public class BSTIterator {

    TreeNode current;
    Stack<TreeNode> stack = new Stack<>();
    TreeNode resultPointer;

    public BSTIterator(TreeNode root) {
        current = root;
        if (current != null) {
            stack.push(current);
        }
    }

    public int next() {
        if (!stack.isEmpty()) {
            while (current.left != null) {
                stack.push(current.left);
                current = current.left;
            }
            resultPointer = stack.pop();
            if (resultPointer.right != null) {
                stack.push(resultPointer.right);
                current = resultPointer.right;
            }
        }
        return resultPointer.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
