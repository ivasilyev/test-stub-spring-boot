package org.test.spring_boot_stub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static SpringApplication springApplication;

    public static void main(String[] args) {

        if (springApplication == null) {
            springApplication = new SpringApplication(App.class);
        }

        springApplication.run(args);
    }
}
