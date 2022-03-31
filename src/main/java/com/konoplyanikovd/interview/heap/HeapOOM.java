package com.konoplyanikovd.interview.heap;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(10) ;
        while (true){
            list.add("1") ;
        }
    }
}
