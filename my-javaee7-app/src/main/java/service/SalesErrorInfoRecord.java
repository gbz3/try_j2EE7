package service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@ToString
public class SalesErrorInfoRecord {

    /**
     * 受付ID
     */
    private BigInteger registerId;

}
