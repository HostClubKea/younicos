package com.younicos.frequencytracker.consumer;


import com.younicos.frequencytracker.event.EventUpdate;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Collector  implements ApplicationListener<EventUpdate> {

    private static List<EventUpdate> updates = new ArrayList<>();

    @Override
    public void onApplicationEvent(EventUpdate eventUpdate) {
        updates.add(eventUpdate);
    }

    public static List<EventUpdate> getUpdates() {
        return updates;
    }
}
