package db.repository;

import db.entity.EmployeesEntity;
import org.junit.*;
import service.SalesErrorInfoGetRequest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class SalesErrorRepositoryTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EmployeesRepository employeesRepository;

    @BeforeClass
    public static void setupClass() throws SQLException {
        final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=Oracle";
        try (Connection jdbcConn = DriverManager.getConnection(JDBC_URL, "sa", "")) {
            try (Statement stmt = jdbcConn.createStatement()) {
                //stmt.execute("DROP TABLE EMPLOYEES");
                stmt.execute("CREATE TABLE EMPLOYEES (" +
                        "    employee_id     NUMBER(20)      PRIMARY KEY," +
                        "    first_name      VARCHAR2(20)    NOT NULL," +
                        "    last_name       VARCHAR2(25)    NOT NULL," +
                        "    email           VARCHAR2(100)   UNIQUE," +
                        "    phone_number    VARCHAR2(20)," +
                        "    hire_date       DATE            DEFAULT SYSDATE," +
                        "    birth           NUMBER(7, 0)," +
                        "    job_id          VARCHAR2(10)    NOT NULL," +
                        "    salary          NUMBER(8, 2)    CHECK (salary > 0)," +
                        "    commission_pct  NUMBER(2, 2)," +
                        "    manager_id      NUMBER(6)," +
                        "    department_id   NUMBER(4)" +
                        ")");
                System.out.printf("EMPLOYEES table created.%n");
            }
        }

        emf = Persistence.createEntityManagerFactory("testPU");
    }

    @AfterClass
    public static void tearDownClass() {
        if (emf != null) {
            emf.close();
        }
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();

        employeesRepository = new EmployeesRepository();
        try {
            Field emField = EmployeesRepository.class.getDeclaredField("em");
            emField.setAccessible(true);
            emField.set(employeesRepository, em);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        // Clean up table
        em.getTransaction().begin();
        em.createQuery("DELETE FROM EmployeesEntity").executeUpdate();
        em.persist(
                EmployeesEntity.builder()
                        .employeeId(BigDecimal.ONE)
                        .firstName("太郎")
                        .lastName("山田")
                        .email("test.yamada.taro@example.com")
                        .phone("1234567890")
                        .hireDate(LocalDate.of(2025, 7, 21))
                        .birth(LocalDate.of(1999, 12, 31))
                        .jobId("1")
                        .salary(1000.0)
                        .managerId(1)
                        .build()
        );
        em.getTransaction().commit();
    }

    @After
    public void tearDown() {
        if (em != null) {
            em.close();
            em = null;
        }
    }

    @Test
    public void test() throws SQLException {
        // Given
        SalesErrorInfoGetRequest req = SalesErrorInfoGetRequest.builder()
                .fruits(Collections.emptyList())
                .build();

        // When
        List<EmployeesEntity> actual = employeesRepository.findBy(req);

        // Then
        assertThat(actual).isNotEmpty();
    }

}
