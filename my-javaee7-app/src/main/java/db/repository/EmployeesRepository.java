package db.repository;

import db.entity.EmployeesEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.SalesErrorInfoGetRequest;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequestScoped
public class EmployeesRepository {

    private static final Logger log = LogManager.getLogger();

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;

    private static final String SELECT_FROM = "SELECT"
            + " EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, BIRTH, JOB_ID, SALARY"
            + " FROM EMPLOYEES";

    private static final String SQL_findByFruits = SELECT_FROM
            + " WHERE JOB_ID IN (?, ?, ?, ?, ?)";

    public List<EmployeesEntity> findBy(SalesErrorInfoGetRequest request) throws SQLException {
        // EntityManager 管理下にあるためクローズはしない
        Connection conn = em.unwrap(Connection.class);
        if (conn == null) {
            log.error("Connection obtained from database failed.");
            throw new IllegalStateException("Connection obtained from database failed.");
        }

        AtomicInteger paramIndex = new AtomicInteger(1);
        try (PreparedStatement stmt = conn.prepareStatement(SQL_findByFruits)) {
            fillFruits(s -> stmt.setString(paramIndex.getAndIncrement(), s), request);

            try (ResultSet rs = stmt.executeQuery()) {
                List<EmployeesEntity> result =  new ArrayList<>();
                while (rs.next()) {
                    result.add(EmployeesEntity.buildFromRs(rs));
                }
                return result;
            }
        }
    }

    private void fillFruits(ConsumerWithSQLException<String> filler, SalesErrorInfoGetRequest request) throws SQLException {
        List<String> fruits = request.getFruits();
        String lastElement = fruits.isEmpty() ? "0" :  fruits.get(fruits.size() - 1);
        List<String> params = new ArrayList<>(fruits);
        while (params.size() < 5) {
            params.add(lastElement);
        }

        for (String param : params) {
            filler.accept(param);
        }
    }

    /**
     * 検査例外 (Exception) をスローできる Consumer のためのカスタム関数型インターフェース。
     */
    @FunctionalInterface
    private interface ConsumerWithSQLException<T> {
        void accept(T t) throws SQLException; // Exception をスローできる
    }

}
