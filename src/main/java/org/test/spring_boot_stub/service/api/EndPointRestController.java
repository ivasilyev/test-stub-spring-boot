package org.test.spring_boot_stub.service.api;

import java.util.logging.Logger;

import io.micrometer.core.instrument.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.test.spring_boot_stub.constants.Constants;
import org.test.spring_boot_stub.dto.DelayDto;
import org.test.spring_boot_stub.utils.Utils;

@RestController
public class EndPointRestController {
    private static final Logger LOGGER = Logger.getLogger(EndPointRestController.class.getName());

    public final static String ENDPOINT_NAME = "endpoint";
    private static final String BASE_URL = "/" + ENDPOINT_NAME;

    private static final String API_URL = BASE_URL + "/api";

    private final String RUID = Utils.getUuid();

    private final int DEFAULT_DELAY_MS = 500;

    private int delayMs = DEFAULT_DELAY_MS;

    @Autowired
    private Counter opsProcessed;

    /*
    curl \
        --request GET \
        "http://localhost:10000/endpoint/api/get-delay"
    */
    @GetMapping(
        value = API_URL + "/get-delay"
    )
    public DelayDto getDelay() {
        DelayDto delayDto = new DelayDto();
        delayDto.setDelayMs(this.delayMs);
        return delayDto;
    }

    /*
    curl \
        --request POST \
        --data-binary '{"delayMs":555}' \
        --header 'Content-Type: application/json' \
        "http://localhost:10000/endpoint/api/set-delay"
    */
    @PostMapping(
        value = API_URL + "/set-delay"
    )
    public DelayDto setDelay(@RequestBody DelayDto delayDto) {
        LOGGER.fine(String.format(
            "Delay setting request received from '%s' to '%s' at %s for endpoint '%s'",
            Utils.getUuid(),
            this.RUID,
            Constants.DATE_FORMAT.format(Utils.getDate()),
            ENDPOINT_NAME
        ));
        this.delayMs = delayDto.getDelayMs();
        return delayDto;
    }

    /*
    curl \
        --request POST \
        "http://localhost:10000/endpoint/api/reset-delay"
    */
    @PostMapping(
        value = API_URL + "/reset-delay"
    )
    public DelayDto resetDelay() {
        LOGGER.fine(String.format(
            "Delay resetting request received from '%s' to '%s' at %s for endpoint '%s'",
            Utils.getUuid(),
            this.RUID,
            Constants.DATE_FORMAT.format(Utils.getDate()),
            ENDPOINT_NAME
        ));
        this.delayMs = this.DEFAULT_DELAY_MS;
        return this.getDelay();
    }

    /*
    curl \
        --request GET \
        "http://localhost:10000/endpoint"
    */
    @GetMapping(
            value = BASE_URL,
            produces = "application/json"
    )
    public String basicResponse() {
        LOGGER.fine(String.format(
            "Request received from '%s' to '%s' at %s for endpoint '%s'",
            Utils.getUuid(),
            this.RUID,
            Constants.DATE_FORMAT.format(Utils.getDate()),
            ENDPOINT_NAME
        ));
        Utils.pause(this.delayMs, this.RUID);
        opsProcessed.increment();
        return "{\"success\":\"true\"}";
    }

}
