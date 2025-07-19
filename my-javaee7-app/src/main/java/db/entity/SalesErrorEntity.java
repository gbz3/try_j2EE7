package db.entity;


import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEES")
@NamedQueries({
        @NamedQuery(
                name = "SalesErrorEntity.findAll",
                query = "SELECT e FROM SalesErrorEntity e"
        )
})
@Getter
@ToString
public class SalesErrorEntity {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private Integer employeeId;

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
    private Integer birth;

    @Column(name = "JOB_ID")
    private String jobId;

    @Column(name = "SALARY")
    private Double salary;

}
