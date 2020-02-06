package com.qunar.leetcode;

import com.qunar.common.TreeNode;

public class MaxDepthOfTree {


    public static void main(String[] args) {


    }

    /**
     *二叉树最大深度，一般二叉树遍历都是递归，递归也是算法的考点
     *给定一棵二叉树，获取他左边和右边的深度，左边深度大于右边则返回左边
     * 左边深度=递归左边
     * 右边深度=递归右边
     * 需要找到一个临界点，这个临界点就是叶子节点，可以假设只要一个节点，那么深度就是1
     **/
    public int maxDepth1(TreeNode root) {

        if(root != null){
            int left = maxDepth1(root.left);
            int right = maxDepth1(root.right);
            return left > right ? left + 1 : right + 1;
        }
        return 0;
    }

    public static int maxDepth(TreeNode treeNode){
        if(treeNode == null){
            return 0;
        }
        return maxDepth(treeNode,1);
    }

    public static int maxDepth(TreeNode treeNode,int depth){

        int left = 0;
        int right = 0;
        if(treeNode.getLeft() != null){
            left = maxDepth(treeNode.getLeft(),depth + 1);
        }
        if(treeNode.getRight() != null){
            right =  maxDepth(treeNode.getRight(),depth + 1);
        }
        return left > right ? left > depth ? left : depth : right > depth ? right : depth;
    }
}
