package com.younicos.frequencygenerator.generator;

import com.younicos.frequencygenerator.model.Frequency;

import java.util.Date;
import java.util.Random;

public class EmptyGenerator implements Generator {

    @Override
    public Frequency generate() {
        return null;
    }

    @Override
    public String getName() {
        return "None";
    }
}
