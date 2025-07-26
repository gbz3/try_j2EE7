package com.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class EmployeeService {

    private static final Logger log = LogManager.getLogger();

    public EmployeeServiceResponse getEmployees() {
        log.info("Start: EmployeeService getEmployees");

        try {
            return EmployeeServiceResponse.builder()
                    .resultCode("00")
                    .build();
        } finally {
            log.info("End: EmployeeService getEmployees");
        }
    }

}
