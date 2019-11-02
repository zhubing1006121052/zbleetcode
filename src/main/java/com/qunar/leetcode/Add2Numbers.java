package com.qunar.leetcode;

import java.util.List;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 *  链表一样长
 * l1  2->4->3->null
 * l2  5->6->4->null
 * rt  7->0->8->null
 *
 * 链表不一样长
 * l1  2->4->3->null          2->4->3->null
 * l2  5->6->null             5->6->null
 * rt                         7->0->4->null
 * l1 + l2 = 342 + 65 = 407
 *
 * 就像你在纸上计算两个数字的和那样，我们首先从最低有效位也就是列表 l1 和 l2的表头开始相加。
 * 由于每位数字都应当处于 0…9 的范围内，我们计算两个数字的和时可能会出现 “溢出”。
 * 例如，5 + 7 = 12。在这种情况下，我们会将当前位的数值设置为 2，并将进位 carry = 1带入下一次迭代。
 * 进位 carry必定是 0 或 1，这是因为两个数字相加（考虑到进位）可能出现的最大和为 9 + 9 + 1 = 19。
 *
 * 测试             用例	    说明
 * l1=[0,1]，    l2=[0,1,2]	  当一个列表比另一个列表长时
 * l1=[]，      l2=[0,1]	  当一个列表为空时，即出现空列表
 * l1=[9,9]，  l2=[1]	     求和运算最后可能出现额外的进位，这一点很容易被遗忘

 *https://leetcode-cn.com/problems/add-two-numbers/solution/liang-shu-xiang-jia-by-leetcode/
 */
public class Add2Numbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(9);
//        l2.next.next = new ListNode(4);
        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println(listNode);
    }

    /**
     * l1  2->4->3->null          2->4->3->null
     * l2  5->6->null             5->6->null
     * rt                         7->0->4->null
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode tmp = head;
        while(l1 != null || l2 != null){//按最长的计算，短的链表元素置0处理
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + tmp.val;
            int carry = sum/10;
            int curr = sum%10;
            tmp.val = curr;
            if((l1 == null || l1.next == null) && (l2 == null || l2.next == null) && carry == 0){
//                carry == 0 需要加上  排除新增节点
                tmp.next = null;
                break;
            }
            tmp.next = new ListNode(carry);
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            tmp = tmp.next;
        }
        return head;
    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
     }

}
