package com.fram.camelDemo.camelmicroserviceDemob.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


//@Component
public class KafkaReceiverRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("kafka:myKafkaTopic")
                .log("${body}")
                .to("log:received-message-from-kafka");
    }
}
