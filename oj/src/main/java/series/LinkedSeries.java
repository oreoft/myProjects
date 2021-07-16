package series; /**
 * @author zyf
 * @date 2021-07-16 12:07
 * @describe :
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unit.ListNode;

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





}
