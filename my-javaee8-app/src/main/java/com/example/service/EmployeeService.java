package com.example.service;

import com.example.db.entity.EmployeesEntity;
import com.example.db.repository.EmployeesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

@RequestScoped
public class EmployeeService {

    private static final Logger log = LogManager.getLogger();

    @Inject
    private EmployeesRepository employeesRepository;

    public EmployeeServiceResponse getEmployees(EmployeeServiceRequest req) {
        log.info("Start: EmployeeService getEmployees req={}", req);

        try {
            List<EmployeesEntity> entities = detectFind(req);
            return EmployeeServiceResponse.builder()
                    .resultCode("00")
                    .employees(entities)
                    .build();
        } finally {
            log.info("End: EmployeeService getEmployees");
        }
    }

    private List<EmployeesEntity> detectFind(EmployeeServiceRequest req) {
        Predicate<EmployeesEntity> filter = e -> true;
        if (req == null) {
            return employeesRepository.findAll(filter);
        }

        Predicate<EmployeesEntity> hasManager = e -> req.getManagerIds().contains(e.getManagerId());
        Predicate<EmployeesEntity> containsName = e -> e.getFirstName().contains(req.getFirstName());
        filter = hasManager.and(containsName);

        if (req.getBirthStart() != null || req.getBirthEnd() != null) {
            LocalDate startNonNull = req.getBirthStart() != null ? req.getBirthStart() : LocalDate.of(1, 1, 1);
            LocalDate endNonNull = req.getBirthEnd() != null ? req.getBirthEnd() : LocalDate.of(9999, 12, 31);
            return employeesRepository.findByBirth(filter, startNonNull, endNonNull);
        }

        return employeesRepository.findAll(filter);
    }

}
