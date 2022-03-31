package com.konoplyanikovd.interview.design.pattern.factorymethod;

public class CatFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }
}
