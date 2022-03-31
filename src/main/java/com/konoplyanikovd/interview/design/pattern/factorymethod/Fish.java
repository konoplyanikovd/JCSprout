package com.konoplyanikovd.interview.design.pattern.factorymethod;

public class Fish extends Animal {


    @Override
    protected void desc() {
        System.out.println("fish name is=" + this.getName());
    }
}
