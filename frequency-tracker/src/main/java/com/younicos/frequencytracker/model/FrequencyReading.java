package com.younicos.frequencytracker.model;

public class FrequencyReading {

    private long timestamp;
    private double frequency;

    public FrequencyReading() {
    }

    public FrequencyReading( double frequency, long timestamp) {
        this.timestamp = timestamp;
        this.frequency = frequency;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }
}
