package com.qunar.leetcode;

import com.qunar.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 *
 */
public class KthSmallest {

    public static void main(String[] args) {
        System.out.println("abcdefg".substring(0,2));
        System.out.println("abcdefg".substring(1,2));
        List<String> dest = new ArrayList<>();
        dest.add("432");
        dest.add("675");
        System.out.println(dest.toString());
        List<String> src = new ArrayList<>();
        src.add("111");
        Collections.copy(dest,src);
        System.out.println(dest.toString());

        System.out.println("11".substring(2,2));
    }

    //首先明确概念,什么是二叉搜索树？？？？
    //二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）
    //都不为空的情况下：左子树值 < 根节点值 < 右子树值 , 可以理解为中序遍历出来的是有序的
    //这个题目其实就简单了，首先中序遍历放到list中，返回了list，然后得到下标为k-1的就是
    public int kthSmallest(TreeNode root, int k) {
        LinkedNode preHead = new LinkedNode(-1,null);
        kthSmallest(root,preHead);
        int i = 0;
        while(preHead.next != null){
            preHead = preHead.next;
            if(++i == k) return preHead.val;
        }
        return 0;
    }
    public void kthSmallest(TreeNode root,LinkedNode node){
        //先序递归二叉树   根节点->左子树->右子树
        if(root != null){
            //添加元素到链表中
            LinkedNode curr = node;
            while(curr != null){
                if(curr.next == null || root.val <= curr.next.val){
                    curr.next = new LinkedNode(root.val,curr.next);
                    break;
                }
                curr = curr.next;
            }
            kthSmallest(root.left,node);
            kthSmallest(root.right,node);
        }
    }

    public static class LinkedNode{
        public int val;
        public LinkedNode next;
        public LinkedNode(int val,LinkedNode next){
            this.val = val;
            this.next = next;
        }
    }

}
