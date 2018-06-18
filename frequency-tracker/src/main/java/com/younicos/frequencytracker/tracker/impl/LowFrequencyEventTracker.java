package com.younicos.frequencytracker.tracker.impl;

import com.younicos.frequencytracker.event.EventType;
import com.younicos.frequencytracker.event.FrequencyUpdateEvent;
import com.younicos.frequencytracker.tracker.EventStateTracker;
import org.springframework.stereotype.Component;

@Component
public class LowFrequencyEventTracker extends EventStateTracker {

    private static final int STOP_THRESHOLD = 3000;
    private static final int START_THRESHOLD = 4200;
    private static final double FREQUENCY_THRESHOLD = 49.95;

    public LowFrequencyEventTracker(){
        super(EventType.LOW_FREQUENCY_EVENT, START_THRESHOLD, STOP_THRESHOLD);
    }

    @Override
    protected boolean isEventStateCondition(FrequencyUpdateEvent event) {
        return  event.getFrequency() != null && event.getFrequency().getFrequencyReading().getFrequency() < FREQUENCY_THRESHOLD;
    }

}
