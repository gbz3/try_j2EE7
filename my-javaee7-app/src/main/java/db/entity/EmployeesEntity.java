package db.entity;

import db.converter.LocalDateConverter;
import db.converter.LocalDateIntConverter;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEES")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @Convert(converter = LocalDateConverter.class)
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

    public static EmployeesEntity buildFromRs(@NotNull ResultSet rs) throws SQLException {
        return builder()
                .employeeId(rs.getBigDecimal("EMPLOYEE_ID"))
                .firstName(rs.getString("FIRST_NAME"))
                .lastName(rs.getString("LAST_NAME"))
                .email(rs.getString("EMAIL"))
                .phone(rs.getString("PHONE_NUMBER"))
                .hireDate(rs.getDate("HIRE_DATE").toLocalDate())
                .birth(LocalDateIntConverter.convertToLocalDate(rs.getInt("BIRTH")))
                .jobId(rs.getString("JOB_ID"))
                .salary(rs.getDouble("SALARY"))
                .managerId(rs.getInt("MANAGER_ID"))
                .build();
    }

}
