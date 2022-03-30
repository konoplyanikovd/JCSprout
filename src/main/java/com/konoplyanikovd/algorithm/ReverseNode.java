package com.konoplyanikovd.algorithm;

import java.util.Stack;


public class ReverseNode {


    public void reverseNode1(Node node){

        System.out.println("====翻转之前====");

        Stack<Node> stack = new Stack<>() ;
        while (node != null){

            System.out.print(node.value + "===>");

            stack.push(node) ;
            node = node.next ;
        }

        System.out.println("");

        while (!stack.isEmpty()){
            System.out.print(stack.pop().value + "===>");
        }

    }

    public  void reverseNode(Node head) {
        if (head == null) {
            return ;
        }

        Node node ;

        Node pre = head;
        Node cur = head.next;
        Node next ;
        while(cur != null){
            next = cur.next;

            cur.next = pre;
            pre = cur;

            cur = next;
        }
        head.next = null;
        node = pre;


        while (node != null){
            System.out.println(node.value);
            node = node.next ;
        }

    }


    public void recNode(Node node){

        if (node == null){
            return ;
        }

        if (node.next != null){
            recNode(node.next) ;
        }
        System.out.print(node.value+"===>");
    }


    public static class Node<T>{
        public T value;
        public Node<T> next ;


        public Node(T value, Node<T> next ) {
            this.next = next;
            this.value = value;
        }
    }
}
