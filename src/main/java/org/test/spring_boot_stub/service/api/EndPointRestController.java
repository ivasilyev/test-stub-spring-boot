package org.test.spring_boot_stub.service.api;

import io.micrometer.core.instrument.Counter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.test.spring_boot_stub.constants.Constants;
import org.test.spring_boot_stub.dto.DelayDto;
import org.test.spring_boot_stub.utils.Utils;

import java.lang.invoke.MethodHandles;


@RestController
public class EndPointRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public final static String ENDPOINT_NAME = "endpoint";

    private static final String BASE_URL = "/" + ENDPOINT_NAME;

    private static final String API_URL = BASE_URL + "/api";

    private final String RUID = Utils.getUuid();

    private final int DEFAULT_DELAY_MS = 500;

    private int delayMs = DEFAULT_DELAY_MS;

    private Counter opsProcessed;

    @Operation(summary = "Get delay in milliseconds")
    @GetMapping(
        value = API_URL + "/get-delay"
    )
    public DelayDto getDelay() {
        DelayDto delayDto = new DelayDto();
        delayDto.setDelayMs(this.delayMs);
        return delayDto;
    }

    @Operation(summary = "Set delay in milliseconds")
    @PostMapping(
        value = API_URL + "/set-delay"
    )
    public DelayDto setDelay(@RequestBody DelayDto delayDto) {
        LOGGER.info(
                "Delay setting request received from '{}' to '{}' at {} for endpoint '{}'",
                Utils.getUuid(),
                this.RUID,
                Constants.DATE_FORMAT.format(Utils.getDate()),
                ENDPOINT_NAME
        );
        this.delayMs = delayDto.getDelayMs();
        return delayDto;
    }

    @Operation(summary = "Reset delay to default")
    @PostMapping(
        value = API_URL + "/reset-delay"
    )
    public DelayDto resetDelay() {

        LOGGER.info(
                "Delay resetting request received from '{}' to '{}' at {} for endpoint '{}'",
                Utils.getUuid(),
                this.RUID,
                Constants.DATE_FORMAT.format(Utils.getDate()),
                ENDPOINT_NAME
        );

        this.delayMs = this.DEFAULT_DELAY_MS;

        return this.getDelay();
    }

    @Operation(summary = "Get basic response")
    @GetMapping(
            value = BASE_URL,
            produces = "application/json"
    )
    public String basicResponse() {
        LOGGER.info(
                "Request received from '{}' to '{}' at {} for endpoint '{}'",
                Utils.getUuid(),
                this.RUID,
                Constants.DATE_FORMAT.format(Utils.getDate()),
                ENDPOINT_NAME
        );
        Utils.pause(this.delayMs, this.RUID);
        opsProcessed.increment();
        return "{\"success\":\"true\"}";
    }

}
