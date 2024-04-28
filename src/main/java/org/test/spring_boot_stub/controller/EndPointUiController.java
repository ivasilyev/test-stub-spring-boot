package org.test.spring_boot_stub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.test.spring_boot_stub.constants.Constants;
import org.test.spring_boot_stub.service.api.EndPointRestController;
import org.test.spring_boot_stub.utils.Utils;

import java.lang.invoke.MethodHandles;


@Controller
public class EndPointUiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String ENDPOINT_NAME = EndPointRestController.ENDPOINT_NAME;

    private static final String BASE_URL = "/" + ENDPOINT_NAME + "/ui";

    private final String RUID = Utils.getUuid();

    @GetMapping(
        value = BASE_URL
    )
    public String endPointUi(Model model) {
        LOGGER.info(
                "UI request received from '{}' to '{}' at {} for endpoint '{}'",
                Utils.getUuid(),
                this.RUID,
                Constants.DATE_FORMAT.format(Utils.getDate()),
                ENDPOINT_NAME
        );

        model.addAttribute("endpoint_name", ENDPOINT_NAME);

        return "ui";
    }
}
