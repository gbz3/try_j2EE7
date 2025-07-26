package com.example.db.repository;

import com.example.db.entity.EmployeesEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestScoped
public class EmployeesRepository {

    private static final Logger log = LogManager.getLogger();

    @PersistenceContext(unitName = "myEclipseLinkJTA")
    private EntityManager em;

    private static final String SELECT_FROM = "SELECT"
            + " EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, BIRTH, JOB_ID, SALARY, MANAGER_ID"
            + " FROM EMPLOYEES";

    private Stream<EmployeesEntity> findBy(String sql) {
        return em.createQuery(sql, EmployeesEntity.class).getResultStream();
    }

    public List<EmployeesEntity> findAll() {

        return findBy("SELECT e FROM EmployeesEntity e")
                .collect(Collectors.toList());
    }

}
