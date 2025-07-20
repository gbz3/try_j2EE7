package db.repository;

import db.entity.EmployeesEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
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
            + " EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, BIRTH, JOB_ID, SALARY, MANAGER_ID"
            + " FROM EMPLOYEES";

    public List<EmployeesEntity> findBy(@NotNull SalesErrorInfoGetRequest request) throws SQLException {
        // EntityManager 管理下にあるためクローズしない
        Connection conn = em.unwrap(Connection.class);
        if (conn == null) {
            log.error("Connection obtained from database failed.");
            throw new IllegalStateException("Connection obtained from database failed.");
        }

        String sql = SELECT_FROM + " WHERE JOB_ID IN (?, ?, ?, ?, ?)";
        if (request.getItems() != null) {
            sql = SELECT_FROM + " WHERE MANAGER_ID IN (?, ?, ?, ?) AND JOB_ID IN (?, ?, ?, ?, ?)";
//        } else if (request.getStartDate() != null ||  request.getEndDate() != null) {
//
        }

        AtomicInteger paramIndex = new AtomicInteger(1);
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (request.getItems() != null) {
                fillItems(request, aInt -> stmt.setInt(paramIndex.getAndIncrement(), aInt));
            }
            fillFruits(request, s -> stmt.setString(paramIndex.getAndIncrement(), s));

            log.info("Executing SQL: {}", sql);
            log.info("PreparedStatement {}", stmt);

            try (ResultSet rs = stmt.executeQuery()) {
                List<EmployeesEntity> result =  new ArrayList<>();
                while (rs.next()) {
                    result.add(EmployeesEntity.buildFromRs(rs));
                }
                return result;
            }
        }
    }

    private void fillItems(SalesErrorInfoGetRequest req, ConsumerWithSQLException<Integer> filler) throws SQLException {
        List<String> items = req.getItems();
        int lastElement = items.isEmpty() ? -1 :  Integer.parseInt(items.get(items.size() - 1));
        List<Integer> params = new ArrayList<>();
        for (String item : items) {
            params.add(Integer.parseInt(item));
        }
        while (params.size() < 4) {
            params.add(lastElement);
        }

        for (Integer param : params) {
            filler.accept(param);
        }
    }

    private void fillFruits(SalesErrorInfoGetRequest req, ConsumerWithSQLException<String> filler) throws SQLException {
        List<String> fruits = req.getFruits();
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
