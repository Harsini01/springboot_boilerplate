package com.sample.springboot_boilerplate.entity;


//import java.util.Date;
//import java.sql.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "employee"
)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "org_id", nullable = false, length = 64)
    private String orgid;

    @Column(name = "emp_mail")
    private String emp_mail;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "ph_no")
    private String phno;

    @Column(name = "designation")
    private String designation;

    @Column(name = "addr")
    private String addr;

    @Column(name = "salary")
    private Integer salary;
    
    @Column(name = "m_mail")
    private String man_mail;

    @Column(name = "dob")
    private String dob;

    @Column(name = "doj")
    private String doj;
}

