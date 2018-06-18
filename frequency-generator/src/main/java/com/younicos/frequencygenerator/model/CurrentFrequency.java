package com.younicos.frequencygenerator.model;

public class CurrentFrequency {
    private static volatile Frequency frequency;

    public static Frequency getFrequency() {
        return frequency;
    }

    public static void setFrequency(Frequency frequency) {
        CurrentFrequency.frequency = frequency;
    }
}
