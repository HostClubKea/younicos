package com.younicos.frequencytracker.tracker.impl;

import com.younicos.frequencytracker.event.EventType;
import com.younicos.frequencytracker.event.FrequencyUpdateEvent;
import com.younicos.frequencytracker.tracker.EventStateTracker;
import org.springframework.stereotype.Component;

@Component
public class FrequencyDataUnavailableEventTracker extends EventStateTracker {

    private static final int STOP_THRESHOLD = 3000;
    private static final int START_THRESHOLD = 3000;

    public FrequencyDataUnavailableEventTracker(){
        super(EventType.FREQUENCY_DATA_UNAVAILABLE, START_THRESHOLD, STOP_THRESHOLD);
    }

    @Override
    protected boolean isEventStateCondition(FrequencyUpdateEvent event) {
        return  event.getFrequency() == null;
    }

}

