package com.younicos.frequencytracker;

import com.younicos.frequencytracker.event.EventState;
import com.younicos.frequencytracker.event.EventType;
import com.younicos.frequencytracker.event.EventUpdate;
import com.younicos.frequencytracker.event.FrequencyUpdateEvent;
import com.younicos.frequencytracker.model.Frequency;
import com.younicos.frequencytracker.model.FrequencyReading;
import com.younicos.frequencytracker.tracker.EventTracker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventTrackerTest {

    @Autowired
    EventTracker eventTracker;

    @Test
    public void eventTrackerStartEventWithAlreadyStartedFirstStopCurrentEventAndThenStartAnother(){
        FrequencyUpdateEvent event = new FrequencyUpdateEvent(this,
                new Frequency(new FrequencyReading(49.94, 0), true));

        List<EventUpdate> updates = eventTracker.track(event);
        assert updates.size() == 0;


        //Low frequency event should be started
        event = new FrequencyUpdateEvent(this,
                new Frequency(new FrequencyReading(49.94, 4200), true));
        updates = eventTracker.track(event);
        assert updates.size() == 1;
        assert updates.get(0).getType().equals(EventType.LOW_FREQUENCY_EVENT);
        assert updates.get(0).getState().equals(EventState.START);

        //Then invalid data comes
        event = new FrequencyUpdateEvent(this,
                new Frequency(new FrequencyReading(49.94, 4300), false));
        updates = eventTracker.track(event);
        assert updates.size() == 0;

        //Low frequency event should be stopped and then Frequency data invalid event started
        event = new FrequencyUpdateEvent(this,
                new Frequency(new FrequencyReading(49.94, 6700), false));
        updates = eventTracker.track(event);
        assert updates.size() == 2;
        assert updates.get(0).getType().equals(EventType.LOW_FREQUENCY_EVENT);
        assert updates.get(0).getState().equals(EventState.STOP);
        assert updates.get(1).getType().equals(EventType.FREQUENCY_DATA_INVALID);
        assert updates.get(1).getState().equals(EventState.START);
    }
}
