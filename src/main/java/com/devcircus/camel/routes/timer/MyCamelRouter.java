package com.devcircus.camel.routes.timer;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyCamelRouter extends RouteBuilder {

    // we can use spring dependency injection
    @Autowired
    MyBean myBean;

    @Override
    public void configure() throws Exception {
        // start from a timer
        from("timer:hello?period={{myPeriod}}")
            .routeId("hello")
            // and call the bean
            .bean(myBean, "saySomething")
            // and print it to system out via stream component
            .to("stream:out");
    }
}
