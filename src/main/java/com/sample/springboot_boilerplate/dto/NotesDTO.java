package com.sample.springboot_boilerplate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotesDTO {
   private Integer meet_id;
   private String emp_mail;
   private String mgr_mail;
   private Date date_of_meet;
   private String mode;
   private String description;
}
