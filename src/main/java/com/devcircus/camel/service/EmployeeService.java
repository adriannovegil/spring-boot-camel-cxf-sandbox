package com.devcircus.camel.service;

import com.devcircus.camel.repository.EmployeeRepository;
import com.jpworks.employee.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.feature.Features;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Features(features = "org.apache.cxf.ext.logging.LoggingFeature")
public class EmployeeService implements EmployeeServicePortType {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeesResponse getEmployeesByName(EmployeeByNameRequest parameters) {
        EmployeesResponse employeesResponse = new EmployeesResponse();
        try {
            employeesResponse.getEmployee().addAll(employeeRepository.getEmployeesByName(parameters.getFirstname(), parameters.getLastname()));
        } catch (Exception e) {
            log.error("Error while setting values for employee object", e);
        }
        return employeesResponse;
    }

    @Override
    public EmployeeResponse getEmployeeById(EmployeeByIdRequest parameters) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        try {
            employeeResponse.setEmployee(employeeRepository.getEmployeeById(parameters.getId()));
        } catch (Exception e) {
            log.error("Error while setting values for employee object", e);
        }
        return employeeResponse;
    }
}
