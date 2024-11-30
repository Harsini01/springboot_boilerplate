package com.sample.springboot_boilerplate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotesDTO {
   private Integer org_id;
   private String emp_mail;
   private String mgr_mail;
   private String meeting_notes;
   private String author;
   private String date;
   private String mode;
}
