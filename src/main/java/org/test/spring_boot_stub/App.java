package org.test.spring_boot_stub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.invoke.MethodHandles;


@SpringBootApplication
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static SpringApplication springApplication;

    public static void main(String[] args) {

        if (springApplication == null) {
            springApplication = new SpringApplication(App.class);
        }

        springApplication.run(args);
    }
}
