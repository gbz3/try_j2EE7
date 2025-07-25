package service;


import db.entity.EmployeesEntity;
import db.repository.EmployeesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class SalesErrorInfoService {

    private static final Logger log = LogManager.getLogger();

    @Inject
    private EmployeesRepository employeesRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    public SalesErrorInfoGetResponse getSalesErrorInfo(SalesErrorInfoGetRequest req) {
        log.info("SalesErrorInfoService::getSalesErrorInfo req={}", req);

        try {
            List<EmployeesEntity> entities = employeesRepository.findBy(req);
            log.info("SalesErrorInfoService::getSalesErrorInfo entities={}", entities);

            List<SalesErrorInfoRecord> records = entities.stream()
                    .map(e -> SalesErrorInfoRecord.builder()
                            .registerId(e.getEmployeeId().toBigInteger())
                            .firstName(e.getFirstName())
                            .lastName(e.getLastName())
                            .email(e.getEmail())
                            .birthDate(e.getBirth())
                            .jobId(e.getJobId())
                            .managerId(e.getManagerId())
                            .build()
                    )
                    .collect(Collectors.toList());
            log.info("SalesErrorInfoService::getSalesErrorInfo records={}", records);

            return SalesErrorInfoGetResponse.builder()
                    .resultCode(0)
                    .records(records)
                    .build();

        } catch (SQLException e) {
            log.error("SalesErrorInfoService::getSalesErrorInfo req={}", req, e);
            return SalesErrorInfoGetResponse.builder()
                    .resultCode(-1)
                    .records(Collections.emptyList())
                    .build();
        }
    }

}
