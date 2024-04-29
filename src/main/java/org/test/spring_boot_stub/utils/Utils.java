package org.test.spring_boot_stub.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.test.spring_boot_stub.constants.Constants;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.UUID;


public class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static Date getDate() { return new Date(); }

    public static String getUuid() { return UUID.randomUUID().toString(); }

    public static void pause(int delayMs, String rUid) {

        Date requestReceiveDate = getDate();

        LOGGER.info(
                "Request received from '{}' at {}",
                rUid,
                Constants.DATE_FORMAT.format(requestReceiveDate)
        );

        long timeDelta = delayMs - requestReceiveDate.getTime() + getDate().getTime();

        if (timeDelta > 0.) {

            try {

                Thread.sleep(timeDelta);

            } catch (InterruptedException e) {

                e.printStackTrace();

                LOGGER.debug("Error: '{}'", e.getMessage());
            }
        }

        Date delayedResponseDate = getDate();

        LOGGER.debug(
                "Response sent from '{}'' at {}",
                rUid,
                Constants.DATE_FORMAT.format(delayedResponseDate)
        );

        LOGGER.debug(
                "Response time for '{}': {} ms",
                rUid,
                delayedResponseDate.getTime() - requestReceiveDate.getTime()
        );

    }

    public static boolean isDelayValid(int newValue, int oldValue) {
        return (newValue >= 0 && newValue != oldValue);
    }

}
