package db.repository;

import db.entity.SalesErrorEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
public class SalesErrorRepository {

    private static final Logger log = LogManager.getLogger();

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;

    public List<SalesErrorEntity> findBy() {
        //TypedQuery<SalesErrorEntity> query = em.createNamedQuery("SalesErrorEntity.findAll", SalesErrorEntity.class);
        //Query query = em.createNativeQuery("SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, BIRTH, JOB_ID, SALARY FROM EMPLOYEES", SalesErrorEntity.class);
        Query query = em.createNativeQuery("SELECT 123 AS EMPLOYEE_ID FROM EMPLOYEES", SalesErrorEntity.class);
        log.info("query: {}", query);
        
        List<SalesErrorEntity> result = query.getResultList();
        log.info("result: {}", result);

        return result;
    }

}
