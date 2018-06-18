package com.younicos.frequencytracker.tracker.impl;

import com.younicos.frequencytracker.event.EventType;
import com.younicos.frequencytracker.event.FrequencyUpdateEvent;
import com.younicos.frequencytracker.tracker.EventStateTracker;
import org.springframework.stereotype.Component;

@Component
public class FrequencyDataInvalidEventTracker extends EventStateTracker {

    private static final int STOP_THRESHOLD = 3000;
    private static final int START_THRESHOLD = 2400;

    public FrequencyDataInvalidEventTracker(){
        super(EventType.FREQUENCY_DATA_INVALID, START_THRESHOLD, STOP_THRESHOLD);
    }

    @Override
    protected boolean isEventStateCondition(FrequencyUpdateEvent event) {
        return  event.getFrequency() != null && !event.getFrequency().isValid();
    }

}

