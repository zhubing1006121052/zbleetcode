package com.qunar.leetcode;

import com.qunar.common.ListNode;

/**
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，
 * 你将只被给定要求被删除的节点
 *
 * 示例 1:
 *
 * 输入: head = [4,5,1,9], node = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2:
 *
 * 输入: head = [4,5,1,9], node = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *  
 *
 * 说明:
 *
 * 链表至少包含两个节点。
 * 链表中所有节点的值都是唯一的。
 * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
 * 不要从你的函数中返回任何结果。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DeleteNode {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        node.setNext(listNode2);
        ListNode listNode3 = new ListNode(3);
        listNode2.setNext(listNode3);
        ListNode listNode4 = new ListNode(4);
        listNode3.setNext(listNode4);
        ListNode listNode5 = new ListNode(5);
        listNode4.setNext(listNode5);
        listNode5.setNext(null);
        deleteNode(node);
        deleteNode(listNode3);
    }

    /**
     * 正确删除方法是这个，要删除一个链表节点，这个节点就是node,node就是链表中的一个节点
     * 1->2->3->4->5->null
     * 其实要删除一个节点，一般人想法就是当前把节点从链表中删除，指向它的指针指向下一个，他不指向任何节点
     * 这块考虑的是把下一个节点删除！！！
     * 然后吧下一个节点的值赋值给当前节点，然后当前节点的下一个节点设置成当前节点的下下一个节点
     * 可以多做一次，就是把下一个节点的next指针置为null 方便gc回收
     * @param node
     */
    public static void deleteNode(ListNode node) {

        ListNode next = node.getNext();
        node.setVal(node.getNext().getVal());
        node.setNext(node.getNext().getNext());
        next.setNext(null);//中间节点删除了   置为空更方便gc回收
    }

    public static void deleteNode(ListNode node,int target){

        ListNode pre = null;
        ListNode curr = node;
        while(curr.getVal() != target){
            if(curr.getNext() == null){
                return;
            }
            pre = curr;
            curr = curr.getNext();
        }
        if(pre == null){
            pre = node;
            //此处写法有问题   这块方法里这个node是指针的复制   改变不了原始的指针位置，所以头结点还是原来的
            node = node.getNext();
            pre.setNext(null);
        }else{
            pre.setNext(curr.getNext());
            curr.setNext(null);
        }
    }
}
