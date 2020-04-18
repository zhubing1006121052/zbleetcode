package com.qunar.leetcode;

import com.qunar.common.ListNode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 反转一个单链表。
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/submissions/
 */
public class ReverseList {


    public static ListNode aa(ListNode head){

        ListNode pre = null,curr = head;
        while (curr != null){
            ListNode tmpCurr = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmpCurr;
        }
        return pre;
    }

    /**
     * 反转单向链表-就是将链表顺序反过来
     * 1->2->3->4->5->NULL          pre=null,curr=1,nextTempt=2
     * NULL<-1 2->3->4->5->NULL     pre=1,curr=2,nextTempt=3
     * NULL<-1<-2 3->4->5->NULL     pre=null,curr=1,nextTempt=2
     * NULL<-1<-2<-3 4->5->NULL
     * NULL<-1<-2<-3<-4 5->NULL
     * NULL<-1<-2<-3<-4<-5 NULL
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        ListNode pre = null;//当前链表头的上一个节点是没有的
        ListNode curr = head;//当前节点就是头结点
        while(curr != null){
            ListNode nextTempt = curr.getNext();//临时的当前节点
            curr.setNext(pre);//将当前节点的下一个节点指向pre
            pre = curr;//当前节点变成pre
            curr = nextTempt;//临时当前节点变成正式当前节点  ** 特别注意这个，因为链表已经断了
        }
        return pre;
    }

    /**
     * 递归反转链表 1->2->3->4->5->NULL
     * https://blog.csdn.net/w605283073/article/details/86653745
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        // 遍历到链表尾部  其实递归的终点肯定是链表尾部的节点head.next为5
        ListNode newHead = reverseList2(head.next);
        // 反转  找到尾部节点head.next 5后，那么这个节点下一个节点应该是4，所以指向4
        head.next.next = head;
        //head其实是4，所以他的下一个节点此时就为空了
        head.next = null;
        //
        return newHead;
    }
    /**
     * 初始化链表
     * @param size
     * @return
     */
    public ListNode initList(int size){
        ListNode head = null;
        ListNode pre = null;
        for (int i=0;i<size;i++){
            ListNode curr = new ListNode(i + 1,null);
            if(i == 0){
                head = curr;//head也指向内存new ListNode(i + 1,null);这个对象；curr目前也指向它
                pre = curr;//将pre指向new ListNode(i + 1,null);这个对象
                continue;
            }
            pre.next = curr;//上个节点的下一个节点为当前节点
            pre = curr;//当前节点就为上一个节点了
        }
        return head;//最终将链表的头返回
    }

    public void printfList(ListNode head){

        ListNode curr = head;
        while(curr != null){
            System.out.print(curr.num + "->");
            curr = curr.next;
            if(curr == null){
                System.out.print("NULL");
            }
        }
    }

    public static void main(String[] args) {

        ReverseList reverseList = new ReverseList();
        ListNode head = reverseList.initList(5);
        reverseList.printfList(head);
        ListNode listNode = reverseList.reverseList1(head);
        System.out.println();
        reverseList.printfList(listNode);
        System.out.println();

    }

    @AllArgsConstructor
    @Data
    public static class ListNode{
        private Integer num;
        private ListNode next;
    }
}
