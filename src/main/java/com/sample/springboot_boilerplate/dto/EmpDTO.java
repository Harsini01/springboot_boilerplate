package com.sample.springboot_boilerplate.dto;

//import java.util.Date;
//import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpDTO {
    private Integer id;
    private String emp_mail;
    private String fname;
    private String lname;
}
