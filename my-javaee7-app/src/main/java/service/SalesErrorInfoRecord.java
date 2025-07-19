package service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
public class SalesErrorInfoRecord {

    /**
     * 受付ID
     */
    private BigInteger registerId;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthDate;

    private String jobId;

}
