package com.younicos.frequencytracker;

import com.younicos.frequencytracker.event.EventState;
import com.younicos.frequencytracker.event.EventType;
import com.younicos.frequencytracker.event.EventUpdate;
import com.younicos.frequencytracker.event.FrequencyUpdateEvent;
import com.younicos.frequencytracker.model.Frequency;
import com.younicos.frequencytracker.model.FrequencyReading;
import com.younicos.frequencytracker.tracker.EventTracker;
import com.younicos.frequencytracker.tracker.impl.LowFrequencyEventTracker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FrequencyTrackerApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void lowFrequencyEventFireAfter4200ms(){
		LowFrequencyEventTracker tracker = new LowFrequencyEventTracker();

		assert tracker.getEvent().equals(EventType.LOW_FREQUENCY_EVENT);
		assert tracker.getState().equals(EventState.STOP);

		FrequencyUpdateEvent event = new FrequencyUpdateEvent(this,
				new Frequency(new FrequencyReading(49.94, 0), true));

		tracker.trackEvent(event);
		assert tracker.getState().equals(EventState.STOP);

		event = new FrequencyUpdateEvent(this,
				new Frequency(new FrequencyReading(49.94, 4100), true));
		tracker.trackEvent(event);
		assert tracker.getState().equals(EventState.STOP);

		event = new FrequencyUpdateEvent(this,
				new Frequency(new FrequencyReading(49.94, 4200), true));
		tracker.trackEvent(event);
		assert tracker.getState().equals(EventState.START);
	}

	@Test
	public void lowFrequencyEventStopsAfter3000ms(){
		LowFrequencyEventTracker tracker = new LowFrequencyEventTracker();

		assert tracker.getEvent().equals(EventType.LOW_FREQUENCY_EVENT);
		assert tracker.getState().equals(EventState.STOP);

		FrequencyUpdateEvent event = new FrequencyUpdateEvent(this,
				new Frequency(new FrequencyReading(49.94, 0), true));

		tracker.trackEvent(event);
		assert tracker.getState().equals(EventState.STOP);

		event = new FrequencyUpdateEvent(this,
				new Frequency(new FrequencyReading(49.94, 4200), true));
		tracker.trackEvent(event);
		assert tracker.getState().equals(EventState.START);

		event = new FrequencyUpdateEvent(this,
				new Frequency(new FrequencyReading(50.00, 4300), true));
		tracker.trackEvent(event);
		assert tracker.getState().equals(EventState.START);

		event = new FrequencyUpdateEvent(this,
				new Frequency(new FrequencyReading(50.00, 7300), true));
		tracker.trackEvent(event);
		assert tracker.getState().equals(EventState.STOP);
	}

	@Test
	public void lowFrequencyEventPeriodicalLowFrequencyPreventsStopState(){
		LowFrequencyEventTracker tracker = new LowFrequencyEventTracker();

		assert tracker.getEvent().equals(EventType.LOW_FREQUENCY_EVENT);
		assert tracker.getState().equals(EventState.STOP);

		FrequencyUpdateEvent event = new FrequencyUpdateEvent(this,
				new Frequency(new FrequencyReading(49.94, 0), true));

		tracker.trackEvent(event);
		assert tracker.getState().equals(EventState.STOP);

		event = new FrequencyUpdateEvent(this,
				new Frequency(new FrequencyReading(49.94, 4200), true));
		tracker.trackEvent(event);
		assert tracker.getState().equals(EventState.START);

		event = new FrequencyUpdateEvent(this,
				new Frequency(new FrequencyReading(50.00, 4300), true));
		tracker.trackEvent(event);
		assert tracker.getState().equals(EventState.START);


		//Low frequency comes after 2900 ms, should prevent next update from changing start->stop state
		event = new FrequencyUpdateEvent(this,
				new Frequency(new FrequencyReading(49.94, 7200), true));
		tracker.trackEvent(event);
		assert tracker.getState().equals(EventState.START);

		event = new FrequencyUpdateEvent(this,
				new Frequency(new FrequencyReading(50.00, 7300), true));
		tracker.trackEvent(event);
		assert tracker.getState().equals(EventState.START);
	}



}
