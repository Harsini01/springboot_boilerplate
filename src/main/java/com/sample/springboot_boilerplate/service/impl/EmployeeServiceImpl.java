package com.sample.springboot_boilerplate.service.impl;

import com.sample.springboot_boilerplate.Db.EmployeeHandler;
import com.sample.springboot_boilerplate.Db.OrgHandler;
import com.sample.springboot_boilerplate.dto.OrganizationDTO;
import com.sample.springboot_boilerplate.dto.ProductDTO;
import com.sample.springboot_boilerplate.dto.EmployeeDTO;
import com.sample.springboot_boilerplate.dto.HierarchyDTO;
import com.sample.springboot_boilerplate.dto.NotesDTO;
import com.sample.springboot_boilerplate.dto.EmpDTO;
import com.sample.springboot_boilerplate.entity.Employee;
import com.sample.springboot_boilerplate.entity.Organization;
//import com.sample.springboot_boilerplate.entity.Employee;
import com.sample.springboot_boilerplate.exception.ResourceNotFoundException;
import com.sample.springboot_boilerplate.mapper.OrganizationMapper;
//import com.sample.springboot_boilerplate.mapper.EmployeeMapper;
import com.sample.springboot_boilerplate.repository.OrganizationRepository;
import com.sample.springboot_boilerplate.service.EmployeeService;
import com.sample.springboot_boilerplate.service.OrganizationService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

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
       dto.setMeet_id(Integer.parseInt(Objects.toString(org[0])));
       dto.setEmp_mail((String) org[1]);
       dto.setMgr_mail((String) org[2]);
       dto.setDate_of_meet((Date)org[3]);
       dto.setDescription((String) org[4]);
       notes.add(dto);
   }
   return notes;
}
public List<NotesDTO> getAllManagerFeedback(Integer oid, String emp_mail){
   List<Object[]> orgs = employeeHandler.getAllManagerFeedback(oid, emp_mail);
   List<NotesDTO> notes = new ArrayList<>();




   for(Object[] org:orgs){
       NotesDTO dto = new NotesDTO();
       dto.setMeet_id(Integer.parseInt(Objects.toString(org[0])));
       dto.setEmp_mail((String) org[1]);
       dto.setMgr_mail((String) org[2]);
       dto.setDate_of_meet((Date)org[3]);
       dto.setMode((String) org[4]);
       dto.setDescription((String) org[5]);
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


}
