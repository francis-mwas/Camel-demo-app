package com.fram.camelDemo.camelmicroserviceDemoa.routes.b;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFilesRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .routeId("Files-Input-Route")
                .log("${body}")
                .to("file:files/output");
    }
}
