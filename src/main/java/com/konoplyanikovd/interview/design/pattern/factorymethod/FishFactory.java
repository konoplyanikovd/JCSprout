package com.konoplyanikovd.interview.design.pattern.factorymethod;

public class FishFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Fish() ;
    }
}
