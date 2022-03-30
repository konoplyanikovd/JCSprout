package com.konoplyanikovd.algorithm;

import java.util.Stack;

public class TwoStackQueue<T> {

    private Stack<T> input = new Stack() ;

    private Stack<T> out = new Stack() ;

    public void appendTail(T t){
        input.push(t) ;
    }

    public T deleteHead(){

        if (out.isEmpty()){
            while (!input.isEmpty()){
                out.push(input.pop()) ;
            }
        }

        return out.pop() ;
    }


    public int getSize(){
        return input.size() + out.size() ;
    }

}
