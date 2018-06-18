package com.younicos.frequencytracker.tracker;

import com.younicos.frequencytracker.event.EventState;
import com.younicos.frequencytracker.event.EventType;
import com.younicos.frequencytracker.event.EventUpdate;
import com.younicos.frequencytracker.event.FrequencyUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventTracker{

    @Autowired
    private List<EventStateTracker> eventStateTrackers;

    private EventType startedEvent = null;

    public List<EventUpdate> track(FrequencyUpdateEvent updateEvent) {

        List<EventUpdate> updates = new ArrayList<>();

        for (EventStateTracker tracker: eventStateTrackers) {
            EventState state = tracker.trackEvent(updateEvent);

            if(EventState.STOP.equals(state) && tracker.getEvent().equals(startedEvent)){
                //stop event
                startedEvent = null;
                updates.add(new EventUpdate(this, tracker.getEvent(), EventState.STOP));
            }

            if(EventState.START.equals(state) && !tracker.getEvent().equals(startedEvent)){
                if(startedEvent!= null){
                    updates.add(new EventUpdate(this, startedEvent, EventState.STOP));

                    //TODO: not elegant solution, have to force stop event in eventtracker otherwise it could rise START egain
                    eventStateTrackers.stream()
                            .filter(etr->etr.getEvent().equals(startedEvent))
                            .findFirst().orElse(null)
                            .forceStop();

                }

                startedEvent = tracker.getEvent();
                updates.add(new EventUpdate(this, tracker.getEvent(), EventState.START));
            }
        }

        return updates;

    }
}
