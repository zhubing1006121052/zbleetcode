package com.qunar.leetcode;

import com.qunar.common.ListNode;

public class OddEvenList {

    public static void main(String[] args) {

    }

    public ListNode oddEvenList(ListNode head) {
        //最少应该有3个节点  否则不用转换
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        //解题思路很简单  就是奇数一个链表，偶数一个链表  最后合并起来
        ListNode oushuHead = head.next,oushuCurr = oushuHead,
                curr = head.next.next,jishuCurr = head;
        int i=3;
        while(true){
            if(i % 2 == 0){
                oushuCurr.next = curr;
                oushuCurr = oushuCurr.next;
            }else{
                jishuCurr.next = curr;
                jishuCurr = jishuCurr.next;
            }
            curr = curr.next;
            i++;
            if(curr == null){
                oushuCurr.next = null;
                break;
            }
        }
        jishuCurr.next = oushuHead;
        return head;
    }
}
