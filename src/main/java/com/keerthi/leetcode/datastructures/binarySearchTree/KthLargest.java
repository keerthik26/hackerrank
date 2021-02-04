package com.keerthi.leetcode.datastructures.binarySearchTree;

public class KthLargest {
    int k;
    _TreeNode root;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num : nums) {
            this.root  = insertIntoBSTRecursive(this.root,num);
        }
    }


    public int add(int val) {
        root = insertIntoBSTRecursive(root,val);
        _TreeNode current = root;
        int currentK = k;
        while (current != null) {
            int right = current.right != null ? current.right.count : 0;
            if((right  == currentK - 1) ){
                return current.val;

            }else if((right > (currentK - 1))){
                current = current.right;
            }else {
                current = current.left;
                currentK = currentK - (right + 1);
            }
        }

        return 0;
    }

    private _TreeNode insertIntoBSTRecursive(_TreeNode root, int val) {
        if (root == null) {
            return new _TreeNode(val, 1);
        }
        if(val <= root.val){
            root.left = insertIntoBSTRecursive(root.left, val);
        }else {
            root.right = insertIntoBSTRecursive(root.right, val);
        }
        root.count++;
        return root;
    }

    public static void main(String[] args) {
        KthLargest obj = new KthLargest(3, new int[] {4,2,5,8});
        System.out.println(obj.add(3));
        System.out.println(obj.add(5));
    }
}

class _TreeNode{
    public int val;
    public _TreeNode left;
    public _TreeNode right;
    public int count;
    public _TreeNode() {
    }

    public _TreeNode(int val, int i) {
        this.val = val;
        count = i;
    }

    public _TreeNode(int val, _TreeNode left, _TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}