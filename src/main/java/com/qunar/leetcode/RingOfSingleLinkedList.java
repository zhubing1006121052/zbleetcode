package com.qunar.leetcode;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/**
 * 单链表是否存在环
 * a->b->c->d->e(->b指向第二个节点形成环)
 */
public class RingOfSingleLinkedList {


    public static void main(String[] args) {
        RingOfSingleLinkedList.ListNode l1 = new RingOfSingleLinkedList.ListNode(1);
        RingOfSingleLinkedList.ListNode l2 = new RingOfSingleLinkedList.ListNode(2);
        RingOfSingleLinkedList.ListNode l3 = new RingOfSingleLinkedList.ListNode(3);
        RingOfSingleLinkedList.ListNode l4 = new RingOfSingleLinkedList.ListNode(4);
        RingOfSingleLinkedList.ListNode l5 = new RingOfSingleLinkedList.ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
//        l5.next = l3;

        System.err.print(hasRing(l1));
    }

    /**
     * 判断单链表是否存在环
     * 两个指针，一个快指针，一个慢指针，如果存在环那么这两个指针肯定能相遇
     * 快指针：速度是慢指针的2倍，起点也是在慢指针的后两位
     * @param node
     * @return
     */
    public static boolean hasRing(RingOfSingleLinkedList.ListNode node){

        ListNode slow = node;
        if(slow != null && slow.next != null){
            ListNode fast = node.next.next;
            while(slow != null && fast != null){
               if(slow == fast){
                   return true;
               }
               slow = slow.next;
               if(fast.next == null){
                   return false;
               }
               fast = fast.next.next;
           }
        }
        return false;
    }

    @AllArgsConstructor
    @Data
    public static class ListNode implements Comparable<ListNode>{
        private Integer num;
        private ListNode next;

        public ListNode(Integer num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "num=" + num +
                    ", next=" + next +
                    '}';
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ListNode listNode = (ListNode) o;
            return Objects.equals(num, listNode.num) &&
                    Objects.equals(next, listNode.next);
        }

        @Override
        public int hashCode() {

            return Objects.hash(num, next);
        }


        @Override
        public int compareTo(ListNode o) {
            return 0;
        }
    }
}
