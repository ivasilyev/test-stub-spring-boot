package org.test.spring_boot_stub.controller;

import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.test.spring_boot_stub.constants.Constants;
import org.test.spring_boot_stub.utils.Utils;


@Controller
public class EndPointUiController {
    private static final Logger LOGGER = Logger.getLogger(EndPointUiController.class.getName());

    private static final String ENDPOINT_NAME = "endpoint";
    private static final String BASE_URL = "/" + ENDPOINT_NAME + "/ui";

    private final String RUID = Utils.getUuid();

    @GetMapping(
        value = BASE_URL
    )
    public String endPointUi(Model model) {
        LOGGER.fine(String.format(
            "UI request received from '%s' to '%s' at %s for endpoint '%s'",
            Utils.getUuid(),
            this.RUID,
            Constants.DATE_FORMAT.format(Utils.getDate()),
            ENDPOINT_NAME
        ));
        model.addAttribute("endpoint_name", ENDPOINT_NAME);
        return "ui";
    }

}
