package com.devcircus.camel.configuration;

import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devcircus.camel.service.EmployeeService;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint endpoint(EmployeeService employeeEndpoint) {
        EndpointImpl endpoint = new EndpointImpl(bus, employeeEndpoint);
        endpoint.publish("/service/employee");
        return endpoint;
    }
}
