package org.test.spring_boot_stub.utils;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

import org.test.spring_boot_stub.constants.Constants;


public class Utils {
    private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());

    public static Date getDate() { return new Date(); }

    public static String getUuid() { return UUID.randomUUID().toString(); }

    public static void pause(int delayMs, String rUid) {
        Date requestReceiveDate = getDate();

        LOGGER.fine(String.format(
            "Request received from '%s' at %s",
            rUid,
            Constants.DATE_FORMAT.format(requestReceiveDate)
        ));

        long timeDelta = delayMs - requestReceiveDate.getTime() + getDate().getTime();

        if (timeDelta > 0.) {
            try {
                Thread.sleep(timeDelta);
            } catch (InterruptedException e) {
                LOGGER.info(String.format("Error: %s", e.getMessage()));
            }
        }

        Date delayedResponseDate = getDate();

        LOGGER.info(String.format(
            "Response sent from '%s' at %s",
            rUid,
            Constants.DATE_FORMAT.format(delayedResponseDate)
        ));

        LOGGER.info(String.format(
            "Response time for '%s': %s ms",
            rUid,
            delayedResponseDate.getTime() - requestReceiveDate.getTime()
        ));

    }

    public static boolean isDelayValid(int newValue, int oldValue) {
        return (newValue >= 0 && newValue != oldValue);
    }

}
