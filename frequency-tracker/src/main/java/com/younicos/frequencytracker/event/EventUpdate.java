package com.younicos.frequencytracker.event;

import org.springframework.context.ApplicationEvent;

public class EventUpdate extends ApplicationEvent {

    private final EventType type;
    private final EventState state;

    public EventUpdate(Object source, EventType type, EventState state) {
        super(source);
        this.type = type;
        this.state = state;
    }

    public EventType getType() {
        return type;
    }

    public EventState getState() {
        return state;
    }
}
