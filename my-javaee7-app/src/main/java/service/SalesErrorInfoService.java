package service;


import db.entity.SalesErrorEntity;
import db.repository.SalesErrorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class SalesErrorInfoService {

    private static final Logger log = LogManager.getLogger();

    @Inject
    private SalesErrorRepository salesErrorRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    public SalesErrorInfoGetResponse getSalesErrorInfo(SalesErrorInfoGetRequest req) {
        log.info("SalesErrorInfoService::getSalesErrorInfo req={}", req);

        try {
            List<SalesErrorEntity> entities = salesErrorRepository.findBy();
            log.info("SalesErrorInfoService::getSalesErrorInfo entities={}", entities);

            List<SalesErrorInfoRecord> records = entities.stream()
                    .map(e -> SalesErrorInfoRecord.builder().registerId(BigInteger.valueOf(e.getEmployeeId())).build())
                    .collect(Collectors.toList());
            log.info("SalesErrorInfoService::getSalesErrorInfo records={}", records);

            return SalesErrorInfoGetResponse.builder()
                    .resultCode(0)
                    .records(records)
                    .build();
        } catch (Exception e) {
            log.error("SalesErrorInfoService::getSalesErrorInfo req={}", req, e);
            throw e;
        }
    }

}
