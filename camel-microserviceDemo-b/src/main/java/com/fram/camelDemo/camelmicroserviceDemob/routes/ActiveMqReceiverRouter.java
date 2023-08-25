package com.fram.camelDemo.camelmicroserviceDemob.routes;


import com.fram.camelDemo.camelmicroserviceDemob.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//@Component
public class ActiveMqReceiverRouter extends RouteBuilder{

     @Autowired
     private MyCurrencyExchangeProcessor myProcessor;
     @Autowired
     private MyCurrencyExchangeTransformer myTransformer;

    @Override
    public void configure() throws Exception {

        //receive it as JSON
        //Map to currency conversion Bean
       /* {
            "id": 1000,
                "from": "USD",
                "to": "INR",
                "conversionMultiple": 70
    }*/

//        unmashall to json
       /* from("activemq:my-activemq-queue")
                .unmarshal()
                .json(JsonLibrary.Jackson, CurrencyExchange.class)
                .bean(myProcessor)
                .bean(myTransformer)
                .to("log:received-message-from-active-mq");*/

//        unmashall to xml
        from("activemq:my-activemq-xml-queue")
                .unmarshal()
                .jacksonXml(CurrencyExchange.class)
                .to("log:received-message-from-active-mq");

    }
}



