package com.younicos.frequencygenerator.generator;

import com.younicos.frequencygenerator.model.Frequency;

import java.util.Date;
import java.util.Random;

public class InvalidGenerator implements Generator {

    private static final double MIN_FREQ = 49.95;
    private static final double MAX_FREQ = 50.05;

    private Random r = new Random();

    @Override
    public Frequency generate() {
        return new Frequency(new Date().getTime(), getFrequency(), false);
    }

    @Override
    public String getName() {
        return "Invalid Frequency";
    }

    private double getFrequency(){
        return  MIN_FREQ + r.nextDouble() * (MAX_FREQ - MIN_FREQ);
    }
}
