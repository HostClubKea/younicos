package com.younicos.frequencytracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Frequency {

    private FrequencyReading frequencyReading;
    private boolean isValid;

    public Frequency() {
    }

    public Frequency(FrequencyReading frequencyReading, boolean isValid){
        this.frequencyReading = frequencyReading;
        this.isValid = isValid;
    }

    public FrequencyReading getFrequencyReading() {
        return frequencyReading;
    }

    public void setFrequencyReading(FrequencyReading frequencyReading) {
        this.frequencyReading = frequencyReading;
    }

    @JsonProperty("isValid")
    public boolean isValid() {
        return isValid;
    }

    @JsonProperty("isValid")
    public void setValid(boolean valid) {
        isValid = valid;
    }

}
