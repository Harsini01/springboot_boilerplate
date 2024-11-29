package com.sample.springboot_boilerplate.mapper;


import com.sample.springboot_boilerplate.dto.EmployeeDTO;
import com.sample.springboot_boilerplate.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getOrgid(),
                employee.getEmp_mail(),
                employee.getFname(),
                employee.getLname(),
                employee.getPhno(),
                employee.getDesignation(),
                employee.getAddr(),
                employee.getSalary(),
                employee.getMan_mail(),
                employee.getDob(),
                employee.getDoj()
        );
    }
    
}