package org.test.spring_boot_stub.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;


public class DelayDto {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private int delayMs;

    public int getDelayMs() {
        return this.delayMs;
    }

    public void setDelayMs(int newDelay) {

        LOGGER.debug("Delay changed from {} to {}", newDelay, this.delayMs);

        this.delayMs = newDelay;
    }
}
