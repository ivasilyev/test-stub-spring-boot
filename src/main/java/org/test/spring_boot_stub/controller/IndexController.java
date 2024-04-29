package org.test.spring_boot_stub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.spring_boot_stub.constants.Constants;
import org.test.spring_boot_stub.utils.Utils;

import java.lang.invoke.MethodHandles;


@RestController
public class IndexController implements ErrorController{

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final String RUID = Utils.getUuid();

    @GetMapping(
        value = "/error*",
        produces = "application/json"
    )
    public String error() {
        LOGGER.debug(
                "Error request received from '{}' to '{}' at {}",
                Utils.getUuid(),
                this.RUID,
                Constants.DATE_FORMAT.format(Utils.getDate())
        );
        return "Not Found";
    }

}
