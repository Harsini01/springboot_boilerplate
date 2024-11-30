package com.sample.springboot_boilerplate.service.impl;

import com.sample.springboot_boilerplate.util.CryptoUtils;
import com.sample.springboot_boilerplate.Db.EmployeeHandler;
import com.sample.springboot_boilerplate.dto.EmployeeDTO;
import com.sample.springboot_boilerplate.dto.HierarchyDTO;
import com.sample.springboot_boilerplate.dto.NotesDTO;
import com.sample.springboot_boilerplate.service.EmployeeService;


import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeHandler employeeHandler;

    @Override
    public EmployeeDTO getEmployeeDetails(Integer id,String empMail){
        List<Object[]> results = employeeHandler.getEmployeeDetails(id, empMail);
        EmployeeDTO employee = new EmployeeDTO();
        for( Object[] result:results){
            employee.setFname(Objects.toString(result[0]));
            employee.setLname(Objects.toString(result[1]));
            employee.setDesignation(Objects.toString(result[2]));
        }
        return employee;
    }

    public List<NotesDTO> getAllEmployeeFeedback(Integer oid, String man_mail){
   List<Object[]> orgs = employeeHandler.getAllEmployeeFeedback(oid, man_mail);
   List<NotesDTO> notes = new ArrayList<>();


   for(Object[] org:orgs){
       NotesDTO dto = new NotesDTO();
       String decryptNotes = CryptoUtils.decrypt((String) org[4]);
       dto.setOrg_id(Integer.parseInt(Objects.toString(org[1])));
       dto.setEmp_mail((String) org[2]);
       dto.setMgr_mail((String) org[3]);
       dto.setMeeting_notes(decryptNotes);
       dto.setAuthor((String) org[5]);
       dto.setDate((String) org[6]);
       dto.setMode((String) org[7]);
       notes.add(dto);
   }
   return notes;
}

@Override
public List<NotesDTO> getHierarchy2(String emp_mail) {
    List<Object[]> results = employeeHandler.getHierarchy(emp_mail);
    List<NotesDTO> hierarchyList = new ArrayList<>();

    for (Object[] result : results) {
        NotesDTO dto = new NotesDTO();
        dto.setMgr_mail((String) result[0]); // Assuming the query returns manager email in the first column
        hierarchyList.add(dto);
    }

    return hierarchyList;
}

public List<NotesDTO> getAllManagerFeedback(Integer oid, String emp_mail){
   List<Object[]> orgs = employeeHandler.getAllManagerFeedback(oid, emp_mail);
   List<NotesDTO> notes = new ArrayList<>();
for(Object[] org:orgs){
       NotesDTO dto = new NotesDTO();
       String decryptNotes = CryptoUtils.decrypt((String) org[4]);
       dto.setOrg_id(Integer.parseInt(Objects.toString(org[1])));
       dto.setEmp_mail((String) org[2]);
       dto.setMgr_mail((String) org[3]);
       dto.setMeeting_notes(decryptNotes);
       dto.setAuthor((String) org[5]);
       dto.setDate((String) org[6]);
       dto.setMode((String) org[7]);
       notes.add(dto);
   }
   return notes;
}



@Override
    public List<HierarchyDTO> getHierarchy(String emp_mail) {
        List<Object[]> hierarchy = employeeHandler.getHierarchy(emp_mail);
        List<HierarchyDTO> emp = new ArrayList<>();

        for (Object[] org : hierarchy) {
            HierarchyDTO dto = new HierarchyDTO();
            dto.setEmp_mail((String) org[0]);
            dto.setMan_mail((String) org[1]);
            emp.add(dto);
        }

        return emp;
    }

    
@Override
public List<HierarchyDTO> getHierarchyBelow(String man_mail) {
    List<Object[]> hierarchy = employeeHandler.getHierarchyBelow(man_mail);
    List<HierarchyDTO> emp = new ArrayList<>();

    for (Object[] org : hierarchy) {
        HierarchyDTO dto = new HierarchyDTO();
        dto.setEmp_mail((String) org[0]);
        dto.setMan_mail((String) org[1]);
        emp.add(dto);
    }

    return emp;
}

@Override
    public List<NotesDTO> getMeetingFeedback(Integer oid, Integer mid) {
        List<Object[]> feedback = employeeHandler.getMeetingFeedback(oid,mid);
        List<NotesDTO> emp = new ArrayList<>();

        for (Object[] org : feedback) {
            NotesDTO dto = new NotesDTO();
            String decryptNotes = CryptoUtils.decrypt((String) org[4]);
            dto.setOrg_id(Integer.parseInt(Objects.toString(org[1])));
            dto.setEmp_mail((String) org[2]);
            dto.setMgr_mail((String) org[3]);
            dto.setMeeting_notes(decryptNotes);
            dto.setAuthor((String) org[5]);
            dto.setDate((String) org[6]);
            dto.setMode((String) org[7]);
            emp.add(dto);
        }

        return emp;
    }

    @Override
    public List<NotesDTO> getMeetingNotes(Integer id, String managerEmail, String empEmail){
        List<Object[]> org2 = employeeHandler.getMeetingNotes(id,managerEmail,empEmail);
        List<NotesDTO> notes = new ArrayList<>();


        for  (Object[] org : org2) {
            NotesDTO dto = new NotesDTO();
            String decryptNotes = CryptoUtils.decrypt((String) org[4]);
            dto.setOrg_id(Integer.parseInt(Objects.toString(org[0])));
            dto.setEmp_mail((String) org[2]);
            dto.setMgr_mail((String) org[3]);
            dto.setMeeting_notes(decryptNotes);
            dto.setAuthor((String) org[5]);
            dto.setDate((String) org[6]);
            dto.setMode((String) org[7]);
            notes.add(dto);
        }
        return notes;
    }


    @Override
    public void addNewMeetingRecord(Integer oid, String empMail, String manMail, String meetNote, String author,String date ,String mode){
        employeeHandler.addNewMeetingRecord(oid, empMail, manMail, meetNote, author, date,mode);
    }


}
