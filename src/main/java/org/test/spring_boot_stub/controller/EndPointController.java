package org.test.spring_boot_stub.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.*;


@RestController
public class EndPointController {
    private static final Logger LOGGER = Logger.getLogger(EndPointController.class.getName());
    private static final SimpleDateFormat DATE_FORMAT =  new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    private final String rUID = UUID.randomUUID().toString();

    // curl -X GET http://localhost:10000/test/endpoint
    @RequestMapping(
        value = "/test/{endpoint}",
        produces = "application/json",
        method = RequestMethod.GET
    )
    public String endPoint(@PathVariable(value = "endpoint") String endpoint) {
        Date requestReceiveDate = new Date();
        LOGGER.fine(String.format(
            "Request received from '%s' at %s",
            this.rUID,
            DATE_FORMAT.format(requestReceiveDate)
        ));
        Date immediateResponseDate = new Date();

        int delta = (int) (long) (immediateResponseDate.getTime() - requestReceiveDate.getTime());
        if (delta < DelayController.endPointDelay) {
            try {
                Thread.sleep(DelayController.endPointDelay - delta);
            } catch (InterruptedException e) {
                LOGGER.info(String.format("Error: %s", e.getMessage()));
            }
        }

        Date delayedResponseDate = new Date();
        LOGGER.info(String.format(
            "Response sent from '%s' at %s",
            this.rUID,
            DATE_FORMAT.format(delayedResponseDate)
        ));
        LOGGER.info(String.format(
            "Response time for '%s': %s ms",
            this.rUID,
            delayedResponseDate.getTime() - requestReceiveDate.getTime()
        ));

        return String.format(
            "{\n" +
            "    \"endPointResponce\": {\n" +
            "        \"uuid\": \"%s\",\n" +
            "        \"requestReceiveDate\": \"%s\",\n" +
            "        \"delayedResponseDate\": \"%s\"\n" +
            "    }\n" +
            "}",
            this.rUID,
            DATE_FORMAT.format(requestReceiveDate),
            DATE_FORMAT.format(delayedResponseDate)
        );
    }
}
