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

import org.test.spring_boot_stub.dto.EmulatorDto;
import org.test.spring_boot_stub.utils.Utils;

import java.lang.invoke.MethodHandles;

import static org.test.spring_boot_stub.constants.Constants.DATE_FORMAT;
import static org.test.spring_boot_stub.constants.Constants.MIME_JSON;


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
    public EmulatorDto getDelay() {

        EmulatorDto delayDto = new EmulatorDto();

        delayDto.setDelayMs(this.delayMs);

        return delayDto;
    }


    @Operation(summary = "Set delay in milliseconds")
    @PostMapping(
            value = API_URL + "/set-delay"
    )
    public EmulatorDto setDelay(@RequestBody EmulatorDto emulatorDto) {

        LOGGER.debug(
                "Delay setting request received from '{}' to '{}' at {} for endpoint '{}'",
                Utils.getUuid(),
                this.RUID,
                DATE_FORMAT.format(Utils.getDate()),
                ENDPOINT_NAME
        );

        this.delayMs = emulatorDto.getDelayMs();

        return emulatorDto;
    }


    @Operation(summary = "Reset delay to default")
    @PostMapping(
            value = API_URL + "/reset-delay"
    )
    public EmulatorDto resetDelay() {

        LOGGER.debug(
                "Delay resetting request received from '{}' to '{}' at {} for endpoint '{}'",
                Utils.getUuid(),
                this.RUID,
                DATE_FORMAT.format(Utils.getDate()),
                ENDPOINT_NAME
        );

        this.delayMs = this.DEFAULT_DELAY_MS;

        return this.getDelay();
    }


    @Operation(summary = "Get basic response")
    @GetMapping(
            value = BASE_URL,
            produces = MIME_JSON
    )
    public String basicResponse() {
        LOGGER.debug(
                "Request received from '{}' to '{}' at {} for endpoint '{}'",
                Utils.getUuid(),
                this.RUID,
                DATE_FORMAT.format(Utils.getDate()),
                ENDPOINT_NAME
        );

        Utils.pause(this.delayMs, this.RUID);

        this.opsProcessed.increment();

        return "{\"success\":\"true\"}";
    }
}
