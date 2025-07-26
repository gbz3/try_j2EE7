package com.example.service;

import com.example.db.entity.EmployeesEntity;
import com.example.db.repository.EmployeesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class EmployeeService {

    private static final Logger log = LogManager.getLogger();

    @Inject
    private EmployeesRepository employeesRepository;

    public EmployeeServiceResponse getEmployees(EmployeeServiceRequest req) {
        log.info("Start: EmployeeService getEmployees req={}", req);

        try {
            List<EmployeesEntity> entities = employeesRepository.findAll();
            return EmployeeServiceResponse.builder()
                    .resultCode("00")
                    .employees(entities)
                    .build();
        } finally {
            log.info("End: EmployeeService getEmployees");
        }
    }

}
