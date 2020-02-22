package com.qunar.sort;

import com.qunar.common.ListNode;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 */
public class LinkedListSort {

    public static void main(String[] args) {

        ListNode head = new ListNode(4);
        ListNode a = new ListNode(1);
        head.next = a;
        ListNode b = new ListNode(2);
        a.next = b;
        ListNode c = new ListNode(3);
        b.next = c;
        sortList(head);
        while(head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
    public static ListNode sortList(ListNode head) {
        //快速排序中有一种左右指针法适合链表的排序并且时间复杂度也符合
        if(head == null || head.next == null){
            return head;
        }
        quicksort(head,head,null);
        return head;
    }

    public static void quicksort(ListNode head,ListNode start,ListNode end){

        //第一步：应该是找到中间node的上一个节点
        ListNode midlle = sort(head,start,end);
        if(midlle == null){
            return ;
        }
        if(start != midlle){
            //递归左边
            quicksort(head,start,midlle);
        }
        if(midlle != null && midlle != end && midlle.next != end){
            //递归右边
            quicksort(head,midlle.next,end);
        }
    }
    public static ListNode sort(ListNode head,ListNode start,ListNode end){

        //基数就为第一个节点了
        int key = start.val;
        ListNode pre = start,curr = start.next;
        while(curr != end){

            while(curr.val < key && (pre = pre.next) != curr){
                swap(curr,pre);
            }
            curr = curr.next;
        }
        swap(pre,start);
        return pre;
    }
    public static void swap(ListNode a, ListNode b){
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }
}
