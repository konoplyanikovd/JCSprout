package com.konoplyanikovd.interview.design.pattern.factorymethod;

public abstract class Animal {

    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected abstract void desc() ;
}
