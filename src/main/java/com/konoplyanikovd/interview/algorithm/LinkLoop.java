package com.konoplyanikovd.interview.algorithm;

public class LinkLoop {

    public static class Node{
        private Object data ;
        public Node next ;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(Object data) {
            this.data = data ;
        }
    }

    public boolean isLoop(Node node){
        Node slow = node ;
        Node fast = node.next ;

        while (slow.next != null){
            Object dataSlow = slow.data;
            Object dataFast = fast.data;

            if (dataFast == dataSlow){
                return true ;
            }

            if (fast.next == null){
                return false ;
            }
            slow = slow.next ;
            fast = fast.next.next ;

            if (fast == null){
                return false ;
            }
        }
        return false ;
    }
}
