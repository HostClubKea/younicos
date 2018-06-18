package com.younicos.frequencygenerator.job;

import com.younicos.frequencygenerator.generator.CurrentGenerator;
import com.younicos.frequencygenerator.generator.Generator;
import com.younicos.frequencygenerator.model.CurrentFrequency;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FrequencyUpdater {

    @Scheduled(fixedRate=100)
    public void generateFrequency() {

        CurrentFrequency.setFrequency(CurrentGenerator.get().generate());
        //System.out.println(CurrentFrequency.getFrequency());
    }
}
