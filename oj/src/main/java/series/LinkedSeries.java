package series; /**
 * @author zyf
 * @date 2021-07-16 12:07
 * @describe :
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unit.ListNode;

import javax.print.DocFlavor;

/**
 * 链表相关的题目
 * @author oreoft
 */
public class LinkedSeries {
    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * @param l1 入参链表1
     * @param l2 入参链表2
     * @return 新合并以后的链表
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 创建头结点
        ListNode head = new ListNode(-1, null);
        // 初始化头指针
        ListNode p0 = head;
        while (l1 != null && l2 != null) {
            // 如果l1的值比l2更小, 则让l1的这个结点接到新链表上
            if (l1.value <= l2.value) {
                p0.next = l1;
                l1 = l1.next;
            } else {
                p0.next = l2;
                l2 = l2.next;
            }
            // 最后把头指针往下移
            p0 = p0.next;
        }

        // 如果首次进来有一方为null或者循环过后有一方为null了, 则直接把把另外一方接到新链表后面
        // 注意, l1如果为null,不一定能确定l2是不是null, 但是不影响null接入到新链表后面, 因为此时l1和l2都是null, 最后结点的后继就是null
        p0.next = l1 != null ? l1 : l2;

        // 返回一个链表不含头结点
        return head.next;
    }

    /**
     * 递归实现
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * @param l1 入参链表1
     * @param l2 入参链表2
     * @return 新合并以后的链表
     */
    public static ListNode mergeTwoListsRecursion(ListNode l1, ListNode l2) {
        // 这里判空都是递归的结束标志, 如果有一方为null, 则把这一方进行向上抛
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // 这里进入递归调用
        if (l1.value <= l2.value) {
            // 如果l1的结点小于l2的结点, 则保留当前的l1这个结点, 然后去递归计算l1.next的下一个结点
            // 同理, 递归方法二次进来也是一样的思路, 准备把比较小的这个结点向上抛, 然后在递归调用作为这个结点的next
            // 最后直到一方为null了, 然后开始一层一层向上抛;
            l1.next = mergeTwoListsRecursion(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeTwoListsRecursion(l1, l2.next);
            return l2;
        }
    }






}
