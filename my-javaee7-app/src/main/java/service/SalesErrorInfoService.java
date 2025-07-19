package service;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;

@ApplicationScoped
public class SalesErrorInfoService {

    public SalesErrorInfoGetResponse getSalesErrorInfo(SalesErrorInfoGetRequest req) {
        return SalesErrorInfoGetResponse.builder()
                .resultCode(0)
                .records(Arrays.asList(
                        SalesErrorInfoRecord.builder().registerId(1L).build(),
                        SalesErrorInfoRecord.builder().registerId(2L).build(),
                        SalesErrorInfoRecord.builder().registerId(3L).build()
                ))
                .build();
    }

}
