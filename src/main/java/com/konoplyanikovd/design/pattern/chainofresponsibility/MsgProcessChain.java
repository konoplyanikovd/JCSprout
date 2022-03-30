package com.konoplyanikovd.design.pattern.chainofresponsibility;

import java.util.ArrayList;
import java.util.List;


public class MsgProcessChain {

    private List<Process> chains = new ArrayList<>() ;

    public MsgProcessChain addChain(Process process){
        chains.add(process) ;
        return this ;
    }

    public void process(String msg){
        for (Process chain : chains) {
            chain.doProcess(msg);
        }
    }
}
