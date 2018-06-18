package com.younicos.frequencygenerator.rest;

import com.younicos.frequencygenerator.model.CurrentFrequency;
import com.younicos.frequencygenerator.model.Frequency;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrequencyController {

    @RequestMapping("/frequency")
    public Frequency frequency() {
        return CurrentFrequency.getFrequency();
    }
}
