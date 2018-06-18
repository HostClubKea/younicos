package com.younicos.frequencytracker.tracker;

import com.younicos.frequencytracker.event.EventUpdate;
import com.younicos.frequencytracker.event.FrequencyUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FrequencyUpdateListener implements ApplicationListener<FrequencyUpdateEvent> {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private EventTracker eventTracker;

    @Override
    public void onApplicationEvent(FrequencyUpdateEvent updateEvent) {
        List<EventUpdate> updates = eventTracker.track(updateEvent);

        for(EventUpdate update: updates){
            applicationEventPublisher.publishEvent(update);
        }
    }
}
