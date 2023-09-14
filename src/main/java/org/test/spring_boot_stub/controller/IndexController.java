package org.test.spring_boot_stub.controller;

import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.web.servlet.error.ErrorController;

import org.test.spring_boot_stub.utils.Utils;
import org.test.spring_boot_stub.constants.Constants;

@RestController
public class IndexController implements ErrorController{

    private static final Logger LOGGER = Logger.getLogger(IndexController.class.getName());

    private final String RUID = Utils.getUuid();

    @GetMapping(
        value = "/error*",
        produces = "application/json"
    )
    public String error() {
        LOGGER.fine(String.format(
            "Error request received from '%s' to '%s' at %s",
            Utils.getUuid(),
            this.RUID,
            Constants.DATE_FORMAT.format(Utils.getDate())
        ));
        return "Not Found";
    }

}
