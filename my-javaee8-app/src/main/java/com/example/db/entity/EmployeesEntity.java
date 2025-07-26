package com.example.db.entity;

import com.example.db.converter.LocalDateIntConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEES")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesEntity {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private BigDecimal employeeId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phone;

    @Column(name = "HIRE_DATE")
    private LocalDate hireDate;

    @Column(name = "BIRTH")
    @Convert(converter = LocalDateIntConverter.class)
    private LocalDate birth;

    @Column(name = "JOB_ID")
    private String jobId;

    @Column(name = "SALARY")
    private Double salary;

    @Column(name = "MANAGER_ID")
    private Integer managerId;

}
