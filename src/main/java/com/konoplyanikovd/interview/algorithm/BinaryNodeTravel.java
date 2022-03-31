package com.konoplyanikovd.interview.algorithm;

import java.util.LinkedList;

public class BinaryNodeTravel {

    private Object data ;
    private BinaryNodeTravel left ;
    private BinaryNodeTravel right ;
    public BinaryNodeTravel next;

    public BinaryNodeTravel() {
    }

    public BinaryNodeTravel(Object data, BinaryNodeTravel left, BinaryNodeTravel right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BinaryNodeTravel getLeft() {
        return left;
    }

    public void setLeft(BinaryNodeTravel left) {
        this.left = left;
    }

    public BinaryNodeTravel getRight() {
        return right;
    }

    public void setRight(BinaryNodeTravel right) {
        this.right = right;
    }


    public BinaryNodeTravel createNode(){
        BinaryNodeTravel nodeA = new BinaryNodeTravel("A",null,null) ;
        BinaryNodeTravel nodeB = new BinaryNodeTravel("B",null,null) ;
        BinaryNodeTravel nodeC = new BinaryNodeTravel("C",null,null) ;
        BinaryNodeTravel nodeD = new BinaryNodeTravel("D",null,null) ;
        BinaryNodeTravel nodeE = new BinaryNodeTravel("E",null,null) ;
        BinaryNodeTravel nodeF = new BinaryNodeTravel("F",null,null) ;

        nodeA.setLeft(nodeB);
        nodeB.setLeft(nodeD);
        nodeA.setRight(nodeC);
        nodeC.setLeft(nodeE);
        nodeC.setRight(nodeF);

        return nodeA ;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public BinaryNodeTravel levelIterator(BinaryNodeTravel node){
        LinkedList<BinaryNodeTravel> queue = new LinkedList<>() ;


        BinaryNodeTravel pre = null;

        queue.offer(node) ;
        BinaryNodeTravel current ;
        while (!queue.isEmpty()){
            current = queue.poll();

            if (pre == null){
                pre = current ;
            }else {
                pre.next = current ;
                pre = current;
            }

            if (current.getLeft() != null){
                queue.offer(current.getLeft()) ;
            }
            if (current.getRight() != null){
                queue.offer(current.getRight()) ;
            }
        }

        return node ;
    }
}
