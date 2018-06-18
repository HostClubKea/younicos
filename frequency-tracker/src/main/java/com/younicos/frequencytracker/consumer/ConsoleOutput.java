package com.younicos.frequencytracker.consumer;

import com.younicos.frequencytracker.event.EventUpdate;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ConsoleOutput implements ApplicationListener<EventUpdate> {
    @Override
    public void onApplicationEvent(EventUpdate eventUpdate) {
        System.out.println(String.format("%s %s %s", eventUpdate.getType(), eventUpdate.getState(), eventUpdate.getTimestamp()));
    }
}
