package com.konoplyanikovd.interview.actual;

import java.util.HashMap;
import java.util.Map;

public class LRUMap<K, V> {
    private final Map<K, V> cacheMap = new HashMap<>();


    private int cacheSize;

    private int nodeCount;


    private Node<K, V> header;

    private Node<K, V> tailer;

    public LRUMap(int cacheSize) {
        this.cacheSize = cacheSize;
        header = new Node<>();
        header.next = null;

        tailer = new Node<>();
        tailer.tail = null;

        header.tail = tailer;

        tailer.next = header;


    }

    public void put(K key, V value) {
        cacheMap.put(key, value);

        addNode(key, value);
    }

    public V get(K key){

        Node<K, V> node = getNode(key);

        moveToHead(node) ;

        return cacheMap.get(key);
    }

    private void moveToHead(Node<K,V> node){

        if (node.tail == null){
            node.next.tail = null ;
            tailer = node.next ;
            nodeCount -- ;
        }

        if (node.next == null){
            return ;
        }

        if (node.tail != null && node.next != null){
            node.tail.next = node.next ;
            node.next.tail = node.tail;
            nodeCount -- ;
        }

        node = new Node<>(node.getKey(),node.getValue()) ;
        addHead(node) ;

    }

    private Node<K,V> getNode(K key){
        Node<K,V> node = tailer ;
        while (node != null){

            if (node.getKey().equals(key)){
                return node ;
            }

            node = node.next ;
        }

        return null ;
    }


    private void addNode(K key, V value) {

        Node<K, V> node = new Node<>(key, value);

        if (cacheSize == nodeCount) {
            delTail();
        }

        addHead(node);

    }


    private void addHead(Node<K, V> node) {

        header.next = node;
        node.tail = header;
        header = node;
        nodeCount++;

        if (nodeCount == 2) {
            tailer.next.next.tail = null;
            tailer = tailer.next.next;
        }

    }

    private void delTail() {
        cacheMap.remove(tailer.getKey());

        tailer.next.tail = null;
        tailer = tailer.next;

        nodeCount--;

    }

    private class Node<K, V> {
        private K key;
        private V value;
        Node<K, V> tail;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder() ;
        Node<K,V> node = tailer ;
        while (node != null){
            sb.append(node.getKey()).append(":")
                    .append(node.getValue())
                    .append("-->") ;

            node = node.next ;
        }


        return sb.toString();
    }
}
