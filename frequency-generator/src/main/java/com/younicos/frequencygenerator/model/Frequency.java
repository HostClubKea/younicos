package com.younicos.frequencygenerator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Frequency {

    private final FrequencyReading frequencyReading;
    private final boolean isValid;

    public Frequency(long timestamp, double frequency, boolean isValid) {
        this.frequencyReading = new FrequencyReading(timestamp, frequency);
        this.isValid = isValid;
    }

    public FrequencyReading getFrequencyReading() {
        return frequencyReading;
    }

    @JsonProperty("isValid")
    public boolean isValid() {
        return isValid;
    }
}
