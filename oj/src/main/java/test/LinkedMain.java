package test;

import series.LinkedSeries;
import unit.ListNode;

/**
 *  方便测试的入口方法
 * @author zyf
 */
public class LinkedMain {

    /**
     * 初始化样例
     */
    public static ListNode l1 =
            ListNode.builder().value(1).next(ListNode.builder().value(3).next(ListNode.builder().value(4).build()).build()).build();
    public static ListNode l2 =
            ListNode.builder().value(1).next(ListNode.builder().value(5).next(ListNode.builder().value(6).build()).build()).build();


    public static void main(String[] args) {
        System.out.println(LinkedSeries.mergeTwoLists(l1, l2));
    }

}
