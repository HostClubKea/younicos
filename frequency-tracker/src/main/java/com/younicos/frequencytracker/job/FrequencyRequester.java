package com.younicos.frequencytracker.job;


import com.younicos.frequencytracker.event.FrequencyUpdateEvent;
import com.younicos.frequencytracker.model.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FrequencyRequester {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Value("${frequencySource.url}")
    private String frequencySourceUrl;

    @Scheduled(fixedRate=100)
    public void readFrequencies(){
        RestTemplate restTemplate = new RestTemplate();
        try {
            Frequency frequency = restTemplate.getForObject(frequencySourceUrl, Frequency.class);
            publishFrequencyUpdateEvent(frequency);
        } catch (Exception ex){
            publishFrequencyUpdateEvent(null);
        }

    }

    private void publishFrequencyUpdateEvent(Frequency frequency){
        FrequencyUpdateEvent frequencyUpdateEvent = new FrequencyUpdateEvent(this, frequency);
        applicationEventPublisher.publishEvent(frequencyUpdateEvent);
    }

}
