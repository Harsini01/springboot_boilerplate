package com.sample.springboot_boilerplate.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {
    private Integer id;
    private String empmail;
    private String manmail;
    private String emp_feedback;
    private String man_feedback;
    private Date date;
}
