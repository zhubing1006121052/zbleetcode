package com.qunar.leetcode;

import com.qunar.common.ListNode;

/**
 * 合并两个有序的数组
 */
public class MergeTwoList {

    public static void main(String[] args) {

    }


    /**
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {

        //伪头结点 ，他指向的下一个节点就是头结点，头结点可能是l1的,也可能是l2的，因为不知道哪个头元素小
        //第一次思考的说l2插入到l1中是不行的，虽然行，但是你最终找不到头结点是哪个了，l1可能不是了
        ListNode preHead = new ListNode(-1);
        ListNode pre = preHead;
        while(l1 != null && l2 != null){
            //谁小就把他放在pre后面，和归并排序的思想基本一样，归并排序的思想是谁小先把他放在数组中
            if(l1.val < l2.val){
               //第一次进来说明l1小   l1的头结点成为合并后的头结点
                pre.next = l1;
                l1 = l1.next;
            }else{
                //
                pre.next = l2;
                l2 = l2.next;
            }
            //既然已经把小的放进来了，那么pre要指向pre的下一个了，
            // 就像数组0的位置已经放了A，那么就要在下标为1的位置放下一个了
            pre = pre.next;
        }
        //肯定存在有一个链表没有插入完
        pre.next = l1 == null ? l2 : l1;
        return preHead.next;
    }
}
