package com.fram.camelDemo.camelmicroserviceDemoa.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


//@Component
public class ActiveMqSenderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //timer
       /* from("timer:active-mq-timer?period=10000")
                .transform().constant("Message for Active MQ")
                .log("${body}")
                .to("activemq:my-activemq-queue");*/
        //queue


       /* from("file:files/json")
                .log("${body}")
                .to("activemq:my-activemq-queue");*/


      /*  from("file:files/xml")
                .log("${body}")
                .to("activemq:my-activemq-xml-queue");*/

        //kafka
        from("file:files/json")
                .log("${body}")
                .to("kafka:myKafkaTopic");
    }
}
