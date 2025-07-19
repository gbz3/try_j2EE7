package service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SalesErrorInfoRecord {

    /**
     * 受付ID
     */
    private long registerId;

}
