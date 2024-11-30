package com.sample.springboot_boilerplate.service;

import com.sample.springboot_boilerplate.dto.EmployeeDTO;
import com.sample.springboot_boilerplate.dto.HierarchyDTO;
import com.sample.springboot_boilerplate.dto.NotesDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO getEmployeeDetails(Integer id, String empMail);

    List<NotesDTO> getAllEmployeeFeedback(Integer oid, String man_mail);

    List<NotesDTO> getHierarchy2(String emp_mail);

    List<NotesDTO> getAllManagerFeedback(Integer oid, String emp_mail);

    List<HierarchyDTO> getHierarchy(String emp_mail);

    List<NotesDTO> getMeetingFeedback(Integer oid, Integer mid);

    List<NotesDTO> getMeetingNotes(Integer id, String managerEmail,String empEmail);

    public void addNewMeetingRecord(Integer oid, String empMail, String manMail, String meetNote, String author, String date,String mode);

    List<HierarchyDTO> getHierarchyBelow(String man_mail);
}
