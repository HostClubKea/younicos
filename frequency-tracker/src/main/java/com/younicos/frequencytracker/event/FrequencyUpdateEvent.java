package com.younicos.frequencytracker.event;

import com.younicos.frequencytracker.model.Frequency;
import org.springframework.context.ApplicationEvent;

import java.util.Date;

public class FrequencyUpdateEvent extends ApplicationEvent {

    private Frequency frequency;
    private long eventTimestamp;

    public FrequencyUpdateEvent(Object source, Frequency frequency) {
        super(source);
        this.frequency = frequency;
        this.eventTimestamp = frequency != null? frequency.getFrequencyReading().getTimestamp() : (new Date()).getTime();
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public long getEventTimestamp() {
        return eventTimestamp;
    }
}
