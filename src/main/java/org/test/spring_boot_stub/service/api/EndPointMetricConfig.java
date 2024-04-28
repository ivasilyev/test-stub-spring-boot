package org.test.spring_boot_stub.service.api;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EndPointMetricConfig {

    private static SimpleMeterRegistry meterRegistry;

    @Bean
    public Counter endPointCounter() {

        if (meterRegistry == null ) {
            meterRegistry = new SimpleMeterRegistry();
        }

        return Counter
                .builder(String.format(
                        "app_processed_ops_total_%s",
                        EndPointRestController.ENDPOINT_NAME
                ))
                .description(String.format(
                        "The total number of processed events for the endpoint %s",
                        EndPointRestController.ENDPOINT_NAME
                ))
                .register(meterRegistry);
    }
}
