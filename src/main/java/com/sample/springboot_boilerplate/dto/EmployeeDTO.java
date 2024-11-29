package com.sample.springboot_boilerplate.dto;

//import java.util.Date;
//import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private String orgid;
    private String emp_mail;
    private String fname;
    private String lname;
    private String phno;
    private String designation;
    private String addr;
    private Integer salary;
    private String man_mail;
    private String dob;
    private String doj;
}
