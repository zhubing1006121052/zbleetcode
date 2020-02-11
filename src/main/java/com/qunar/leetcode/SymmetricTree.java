package com.qunar.leetcode;

import com.qunar.common.TreeNode;

public class SymmetricTree {



    public static boolean isSymmetric(TreeNode root) {
        if(root == null){
            return false;
        }
        //空树也是对称的？
        //比如有上一个节点  他的左边节点是root 他的右边节点也是root
        return digui(root.left,root.right);
    }

    /**
     *          1
     *        / \
     *       2   2
     *     / \ / \
     *   3  4 4  3
     *  从根节点开始，根节点判断左右节点(l1,r1)是否相等
     *  然后再判断l1的左节点和r1的有节点,l1的右节点和r1的左节点是否都等
     *  以此类推递归下去(直到出现不等或者出现空)，
     *  就是判断左右两边相等节点的左右节点和右左节点是否都等，相等的话继续往下递归
     * @param left
     * @param right
     * @return
     */
    public static boolean digui(TreeNode left,TreeNode right){

        //左右都是空  ->相等
        if( left == null && null == right){
            return true;
        }
        //有一边为空   不等
        if(left == null || right == null){
            return false;
        }
        //到这肯定是不为空的
        if(left.val == right.val){
            return digui(left.left,right.right) && digui(left.right,right.left);
        }
        return false;
    }
}
