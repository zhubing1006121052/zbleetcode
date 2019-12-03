package com.qunar.leetcode;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写一个程序，找到两个单链表相交的起始节点
 *
 * 1->5->3->6->7->8                              1->5->3
 *                     则两个链表的相交点位              ->6->7->8
 * 2->4->6->7->8                                    2->4
 * 两个链表的相交点位6
 *
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */
public class GetIntersectionNode {


    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode headA = new ListNode(1);
        headA.next = node3;
        headA.next.next = node4;
        ListNode headB = new ListNode(2);
        headB.next = node3;
        headB.next.next = node4;
        System.out.println(getIntersectionNode(headA,headB).val);
        ReentrantLock reentrantLock  = new ReentrantLock ( false );
        reentrantLock.lock();
    }

    /**
     * 两个链表相交：是两个链表在某一处到尾部他们使用的是同一个子链表，
     * 两个链表相交，其实你看下面的链表就知道了，如果第一个链表我们能从5开始，第二个从2开始比较
     * PA:1->5->3->6->7->8
     * PB:   2->4->6->7->8
     * 其实思想就很简单了，怎么能让遍历两个链表，第一个从5开始，第二个从2开始
     * 如果我们把两个链表加起来其长度就一样长了，加起来虽然前面的一些元素错位了，但是链表后面的还是一致的
     * (1->5->3->6->7->8)->2->4->6->7->8
     * (2->4->6->7->8)->1->5->3->6->7->8
     * 这样我们就直接从头遍历到尾就可以了
     * 还有一个问题就是如果当两个链表长度相等(其实可以不用管是否相等，因为两个链表拼接后长度肯定是相等的)，最终肯定会遇到相等的节点那就是末尾节点null
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        /**
         * 注意这里为什么判断的是链表的节点相等，因为是两个链表真的是使用了共同的尾部几个节点，而不是尾部的几个值相等
         */
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public static class ListNode {
        int val;
         ListNode next;
         ListNode(int x) {
             val = x;
             next = null;
         }
    }
}
