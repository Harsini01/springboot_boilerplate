package com.sample.springboot_boilerplate.service;

import com.sample.springboot_boilerplate.dto.EmployeeDTO;
import com.sample.springboot_boilerplate.dto.HierarchyDTO;
import com.sample.springboot_boilerplate.dto.NotesDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO getEmployeeDetails(Integer id,String empMail);
    List<NotesDTO> getAllEmployeeFeedback(Integer oid,String man_mail);
    List<NotesDTO> getAllManagerFeedback(Integer oid, String emp_mail);
    List<HierarchyDTO> getHierarchy(String emp_mail);

}
