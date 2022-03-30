package com.konoplyanikovd.algorithm;

public class MergeTwoSortedLists {


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0) ;
        ListNode lastNode = head ;

        while (l1 != null  && l2 != null){
            if (l1.currentVal < l2.currentVal){
                lastNode.next = l1 ;
                l1 = l1.next ;
            } else {
                lastNode.next = l2 ;
                l2 = l2.next ;
            }
            lastNode =lastNode.next ;
        }

        if (l1 == null){
            lastNode.next = l2 ;
        }
        if (l2 == null){
            lastNode.next = l1 ;
        }

        return head.next ;
    }


    public static class ListNode {
 
        int currentVal;

        ListNode next;

        ListNode(int val) {
            currentVal = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "currentVal=" + currentVal +
                    ", next=" + next +
                    '}';
        }
    }

}
