package com.konoplyanikovd.design.pattern.factorymethod;

public class Main {
    public static void main(String[] args) {
        AnimalFactory factory = new CatFactory() ;
        Animal animal = factory.createAnimal();
        animal.setName("123132");
        animal.desc();
    }
}
