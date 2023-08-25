package com.fram.camelDemo.camelmicroserviceDemoa.routes.a;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


//@Component
public class MyFirstRouter extends RouteBuilder{
    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;
    @Autowired
    private SimpleLoggingProcessingComponent loggingComponent;
    @Override
    public void configure() throws Exception {
        // listen to a queue / timer
        // transform the message
        // save to a database / log
        //2023-07-29 20:35:15.495  INFO 21816 --- [r://first-timer] first-timer : Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
        from("timer:first-timer")
//                .transform().constant("<My Constant Message")
//                .transform().constant("The Time is "+ LocalDateTime.now())
                //transforming a message
                .bean(getCurrentTimeBean, "getCurrentTime")
                //processing
                .bean(loggingComponent)
                .log("${body}")
                .process(new SimpleLoggingProcessor())
                .to("log:first-timer");
    }
}
//transformation using a bean
@Component
class GetCurrentTimeBean{
    public String getCurrentTime(){
        return "The time is: " + LocalDateTime.now();
    }
}

@Component
class SimpleLoggingProcessingComponent{
    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);

    public void process(String message){
        logger.info("SimpleLoggingProcessingComponent {}", message);
    }
}

class SimpleLoggingProcessor implements Processor{
    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("SimpleLoggingProcessor {}", exchange.getMessage().getBody());
    }
}