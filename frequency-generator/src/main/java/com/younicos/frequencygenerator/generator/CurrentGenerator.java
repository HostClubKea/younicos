package com.younicos.frequencygenerator.generator;

public class CurrentGenerator {

    private static volatile Generator generator = new NormalGenerator();

    public static Generator get(){
        return generator;
    };

    public static void set(Generator g){
        generator = g;
    }
}
