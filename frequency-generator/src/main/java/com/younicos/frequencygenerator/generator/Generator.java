package com.younicos.frequencygenerator.generator;

import com.younicos.frequencygenerator.model.Frequency;

public interface Generator {

    Frequency generate();

    String getName();
}
