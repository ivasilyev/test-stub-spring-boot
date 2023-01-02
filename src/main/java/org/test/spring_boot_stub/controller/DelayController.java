package org.test.spring_boot_stub.controller;

import java.util.logging.Logger;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class DelayController {
    private static final Logger LOGGER = Logger.getLogger(EndPointController.class.getName());
    public int delayMs = 500;
    public static int endPointDelay = 500;

    // curl -X GET http://localhost:10000/delay/test/500
    @GetMapping(
        value = "/delay/{service}/{setDelayMs}",
        produces = "application/json"
    )
    public String setDelayMs(
        @PathVariable String service,
        @PathVariable int setDelayMs
    ) {
        switch (service) {
            case "test": {
                endPointDelay = setDelayMs;
                break;
            }
        }
        int delayMs_ = (int) this.delayMs;
        boolean isChanged = (this.delayMs != setDelayMs);
        if (isChanged) this.delayMs = setDelayMs;
        LOGGER.info(String.format("New delay set: %s ms", this.delayMs));

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ObjectNode delays = mapper.createObjectNode();

        delays.put("is changed", isChanged);
        delays.put("old delay", delayMs_);
        delays.put("new delay", this.delayMs);

        root.set("delays", delays);

        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
        } catch (Exception e) {
            return "";
        }
    }
}
