package com.sample.springboot_boilerplate.Db;

import com.sample.springboot_boilerplate.util.CryptoUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

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
                "select id,org_id, emp_mail, man_mail, meeting_notes,authored_by,date_of_meet, mode from feedback where org_id = :oid and man_mail = :manMail and authored_by!= :manMail");

        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        nativeQuery.setParameter("oid", oid);
        nativeQuery.setParameter("manMail", manMail);
        return nativeQuery.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getHierarchy2(String emp_mail)
    {
        StringBuilder query=new StringBuilder();
        query.append("select man_mail from hierarchy where emp_mail=:emp_mail");
        Query nativeQuery=entityManager.createNativeQuery(query.toString());
        nativeQuery.setParameter("empMail",emp_mail);
        return nativeQuery.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getAllManagerFeedback(Integer oid, String empMail) {
        StringBuilder query = new StringBuilder();
        query.append(
                "select id, org_id,emp_mail, man_mail,meeting_notes,authored_by, date_of_meet, mode from feedback where org_id = :oid and emp_mail = :empMail and authored_by!= :empMail");

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

    @SuppressWarnings("unchecked")
    public List<Object[]> getHierarchyBelow(String man_mail) {
        StringBuilder query = new StringBuilder();
        query.append("select emp_mail,man_mail from hierarchy where man_mail= :man_mail union select emp_mail,man_mail from hierarchy where emp_mail= :man_mail ");
        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        return nativeQuery.setParameter("man_mail", man_mail).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getMeetingFeedback(Integer oid, Integer mid) {
        StringBuilder query = new StringBuilder();
        query.append("select * from feedback where id= :mid and org_id = :oid");
        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        nativeQuery.setParameter("oid", oid);
        nativeQuery.setParameter("mid", mid);
        return nativeQuery.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getMeetingNotes(Integer id, String managerEmail, String empEmail) {
        StringBuilder query = new StringBuilder();
        query.append(
                "select meeting_id,org_id,emp_mail,man_mail,meeting_notes,authored_by,date_of_meet,mode from feedback where org_id=:id and manager_email= :managerEmail and emp_email = :empEmail and authored_by = :managerEmail");
        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        nativeQuery.setParameter("id", id);
        nativeQuery.setParameter("empEmail", empEmail);
        nativeQuery.setParameter("managerEmail", managerEmail);
        return nativeQuery.getResultList();
    }

    @Transactional
    public void addNewMeetingRecord(Integer oid, String empMail, String manMail, String meetNote, String author,
            String date, String mode) {
        StringBuilder query = new StringBuilder();
        query.append(
                "insert into feedback(org_id, emp_mail, man_mail, meeting_notes, authored_by, date_of_meet,mode) " +
                        "values (:oid, :empMail, :manMail, :meetNote, :author, :date, :mode)");
        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        String notes = CryptoUtils.encrypt((String) meetNote);
        System.out.println(meetNote);
        nativeQuery.setParameter("oid", oid);
        nativeQuery.setParameter("empMail", empMail);
        nativeQuery.setParameter("manMail", manMail);
        nativeQuery.setParameter("meetNote", notes);
        nativeQuery.setParameter("author", author);
        nativeQuery.setParameter("date", date);
        nativeQuery.setParameter("mode", mode);
        nativeQuery.executeUpdate();
        System.out.println(nativeQuery);
    }

}
