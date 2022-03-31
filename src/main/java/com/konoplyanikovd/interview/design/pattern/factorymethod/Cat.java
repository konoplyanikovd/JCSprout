package com.konoplyanikovd.interview.design.pattern.factorymethod;


public class Cat extends Animal {
    @Override
    protected void desc() {
        System.out.println("Cat name is=" + this.getName());
    }
}
