package com.konoplyanikovd.interview.actual;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class LRUAbstractMap extends java.util.AbstractMap {

    private final static Logger LOGGER = LoggerFactory.getLogger(LRUAbstractMap.class);

    private ExecutorService checkTimePool ;

    private final static int MAX_SIZE = 1024 ;

    private final static ArrayBlockingQueue<Node> QUEUE = new ArrayBlockingQueue<>(MAX_SIZE) ;


    private final static int DEFAULT_ARRAY_SIZE =1024 ;



    private int arraySize ;


    private Object[] arrays ;



    private volatile boolean flag = true ;



    private final static Long EXPIRE_TIME = 60 * 60 * 1000L ;

    private volatile AtomicInteger size  ;


    public LRUAbstractMap() {


        arraySize = DEFAULT_ARRAY_SIZE;
        arrays = new Object[arraySize] ;

        executeCheckTime();
    }


    private void executeCheckTime() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("check-thread-%d")
                .setDaemon(true)
                .build();
        checkTimePool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1),namedThreadFactory,new ThreadPoolExecutor.AbortPolicy());
        checkTimePool.execute(new CheckTimeThread()) ;

    }

    @Override
    public Set<Entry> entrySet() {
        return super.keySet();
    }

    @Override
    public Object put(Object key, Object value) {
        int hash = hash(key);
        int index = hash % arraySize ;
        Node currentNode = (Node) arrays[index] ;

        if (currentNode == null){
            arrays[index] = new Node(null,null, key, value);

            QUEUE.offer((Node) arrays[index]) ;

            sizeUp();
        }else {
            Node cNode = currentNode ;
            Node nNode = cNode ;

            if (nNode.key == key){
                cNode.val = value ;
            }

            while (nNode.next != null){
                if (nNode.key == key){
                    nNode.val = value ;
                    break ;
                }else {
                    sizeUp();
                    Node node = new Node(nNode,null,key,value) ;

                    QUEUE.offer(currentNode) ;

                    cNode.next = node ;
                }

                nNode = nNode.next ;
            }

        }

        return null ;
    }


    @Override
    public Object get(Object key) {

        int hash = hash(key) ;
        int index = hash % arraySize ;
        Node currentNode = (Node) arrays[index] ;

        if (currentNode == null){
            return null ;
        }
        if (currentNode.next == null){

            currentNode.setUpdateTime(System.currentTimeMillis());

            return currentNode ;

        }

        Node nNode = currentNode ;
        while (nNode.next != null){

            if (nNode.key == key){

                currentNode.setUpdateTime(System.currentTimeMillis());

                return nNode ;
            }

            nNode = nNode.next ;
        }

        return super.get(key);
    }


    @Override
    public Object remove(Object key) {

        int hash = hash(key) ;
        int index = hash % arraySize ;
        Node currentNode = (Node) arrays[index] ;

        if (currentNode == null){
            return null ;
        }

        if (currentNode.key == key){
            sizeDown();
            arrays[index] = null ;

            QUEUE.poll();
            return currentNode ;
        }

        Node nNode = currentNode ;
        while (nNode.next != null){

            if (nNode.key == key){
                sizeDown();
                nNode.pre.next = nNode.next ;
                nNode = null ;

                QUEUE.poll();

                return nNode;
            }

            nNode = nNode.next ;
        }

        return super.remove(key);
    }

    private void sizeUp(){

        flag = true ;

        if (size == null){
            size = new AtomicInteger() ;
        }
        int size = this.size.incrementAndGet();
        if (size >= MAX_SIZE) {
            Node node = QUEUE.poll() ;
            if (node == null){
                throw new RuntimeException("data error") ;
            }

            Object key = node.key ;
            remove(key) ;
            lruCallback() ;
        }

    }

    private void sizeDown(){

        if (QUEUE.size() == 0){
            flag = false ;
        }

        this.size.decrementAndGet() ;
    }

    @Override
    public int size() {
        return size.get() ;
    }

    private class Node{
        private Node next ;
        private Node pre ;
        private Object key ;
        private Object val ;
        private Long updateTime ;

        public Node(Node pre,Node next, Object key, Object val) {
            this.pre = pre ;
            this.next = next;
            this.key = key;
            this.val = val;
            this.updateTime = System.currentTimeMillis() ;
        }

        public void setUpdateTime(Long updateTime) {
            this.updateTime = updateTime;
        }

        public Long getUpdateTime() {
            return updateTime;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }


    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private void lruCallback(){
        LOGGER.debug("lruCallback");
    }


    private class CheckTimeThread implements Runnable{

        @Override
        public void run() {
            while (flag){
                try {
                    Node node = QUEUE.poll();
                    if (node == null){
                        continue ;
                    }
                    Long updateTime = node.getUpdateTime() ;

                    if ((updateTime - System.currentTimeMillis()) >= EXPIRE_TIME){
                        remove(node.key) ;
                    }
                } catch (Exception e) {
                    LOGGER.error("InterruptedException");
                }
            }
        }
    }
}
