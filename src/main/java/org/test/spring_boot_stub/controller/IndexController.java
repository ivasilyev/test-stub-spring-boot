package org.test.spring_boot_stub.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.web.servlet.error.ErrorController;

@RestController
@RequestMapping(
    value = "/error*",
    produces = "application/json",
    method = RequestMethod.GET
)
public class IndexController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

    public String getErrorPath() {
        return PATH;
    }
}
