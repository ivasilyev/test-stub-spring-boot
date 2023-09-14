package org.test.spring_boot_stub.dto;

import java.util.logging.Logger;

public class DelayDto {
    private static final Logger LOGGER = Logger.getLogger(DelayDto.class.getName());

    private int delayMs;

    public int getDelayMs() {
        return this.delayMs;
    }

    public void setDelayMs(int newDelay) {
        LOGGER.fine(String.format(
            "Delay changed from %s to %s",
            newDelay,
            this.delayMs
        ));
        this.delayMs = newDelay;
    }

}
