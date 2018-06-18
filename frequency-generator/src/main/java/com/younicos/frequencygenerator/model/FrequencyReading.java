package com.younicos.frequencygenerator.model;

public class FrequencyReading {

    private final long timestamp;
    private final double frequency;

    public FrequencyReading(long timestamp, double frequency) {
        this.timestamp = timestamp;
        this.frequency = frequency;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getFrequency() {
        return frequency;
    }
}
