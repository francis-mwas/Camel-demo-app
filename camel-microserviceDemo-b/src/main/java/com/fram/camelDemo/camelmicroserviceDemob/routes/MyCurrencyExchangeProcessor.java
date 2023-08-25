package com.fram.camelDemo.camelmicroserviceDemob.routes;

import com.fram.camelDemo.camelmicroserviceDemob.CurrencyExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


//Process the recieved message from the queue without changing it
@Component
public class MyCurrencyExchangeProcessor {

    Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class);

    public void processMessage(CurrencyExchange currencyExchange){
        logger.info("Doing some processing with currencyExchange.getConversionMultiple() value which is {}", currencyExchange);
    }
}


//Transform the received message from the queue
@Component
 class MyCurrencyExchangeTransformer {

    Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class);

    public CurrencyExchange processMessage(CurrencyExchange currencyExchange){
        currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));

        return  currencyExchange;
    }
}
