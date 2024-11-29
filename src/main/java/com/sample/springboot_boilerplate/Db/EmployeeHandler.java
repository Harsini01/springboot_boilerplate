package com.sample.springboot_boilerplate.Db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import com.sample.springboot_boilerplate.dto.EmployeeDTO;

import java.util.List;

@Repository
public class EmployeeHandler {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Object[]> getEmployeeDetails(Integer id, String empMail) {
        StringBuilder query = new StringBuilder();
        query.append("select fname,lname,designation from employee where org_id= :id and emp_mail = :empMail;");

        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        nativeQuery.setParameter("id", id);
        nativeQuery.setParameter("empMail", empMail);
        return nativeQuery.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getAllEmployeeFeedback(Integer oid, String manMail) {
        StringBuilder query = new StringBuilder();
        query.append(
                "select meeting_id, emp_mail, man_mail, date_of_meet, mode, man_feedback from feedback where org_id = :oid and man_mail = :manMail");

        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        nativeQuery.setParameter("oid", oid);
        nativeQuery.setParameter("manMail", manMail);
        return nativeQuery.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getAllManagerFeedback(Integer oid, String empMail) {
        StringBuilder query = new StringBuilder();
        query.append(
                "select meeting_id, emp_mail, man_mail, date_of_meet, mode, emp_feedback from feedback where org_id = :oid and emp_mail = :empMail");

        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        nativeQuery.setParameter("oid", oid);
        nativeQuery.setParameter("empMail", empMail);
        return nativeQuery.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getHierarchy(String emp_mail) {
        StringBuilder query = new StringBuilder();
        query.append("select emp_mail,man_mail from hierarchy where emp_mail= :emp_mail");
        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        return nativeQuery.setParameter("emp_mail", emp_mail).getResultList();
    }

}
