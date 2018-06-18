package com.younicos.frequencytracker.tracker;

import com.younicos.frequencytracker.event.EventType;
import com.younicos.frequencytracker.event.EventState;
import com.younicos.frequencytracker.event.FrequencyUpdateEvent;
import org.springframework.stereotype.Component;

public abstract class EventStateTracker {

    private EventState state = EventState.STOP;
    private final EventType event;
    private final int startThreshold;
    private final int endThreshold;
    private Long sequenceStartTime;

    public EventStateTracker(EventType event, int startThreshold, int endThreshold){
        this.startThreshold = startThreshold;
        this.endThreshold = endThreshold;
        this.event = event;
    }

    protected abstract boolean isEventStateCondition(FrequencyUpdateEvent event);

    public EventType getEvent(){
        return event;
    }

    public EventState getState() {
        return state;
    }

    public void forceStop() {
        this.sequenceStartTime = null;
        state = EventState.STOP;
    }

    public EventState trackEvent(FrequencyUpdateEvent update){
        if(EventState.START.equals(state)){
            trackEndState(update);
        } else {
            trackStartState(update);
        }
        return state;
    }

    private void trackStartState(FrequencyUpdateEvent update) {
        if(!isEventStateCondition(update))
            sequenceStartTime = null;

        if(isEventStateCondition(update) && sequenceStartTime == null)
            sequenceStartTime = update.getEventTimestamp();

        if(sequenceStartTime != null && update.getEventTimestamp() - sequenceStartTime >= startThreshold) {
            state = EventState.START;
            sequenceStartTime = null;
        }
    }

    private void trackEndState(FrequencyUpdateEvent update) {
        if(isEventStateCondition(update))
            sequenceStartTime = null;

        if(!isEventStateCondition(update) && sequenceStartTime == null)
            sequenceStartTime = update.getEventTimestamp();

        if(sequenceStartTime != null && update.getEventTimestamp() - sequenceStartTime >= endThreshold) {
            state = EventState.STOP;
            sequenceStartTime = null;
        }
    }
}
