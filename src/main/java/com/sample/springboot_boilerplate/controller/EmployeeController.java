package com.sample.springboot_boilerplate.controller;


import com.sample.springboot_boilerplate.dto.EmployeeDTO;
import com.sample.springboot_boilerplate.dto.NotesDTO;

import com.sample.springboot_boilerplate.dto.HierarchyDTO;
import com.sample.springboot_boilerplate.exception.ResourceNotFoundException;
import com.sample.springboot_boilerplate.service.EmployeeService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/org")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    

    @GetMapping("/{id}/employee/{emp_mail}/details")
    public ResponseEntity<EmployeeDTO> getEmployeeDetails(@PathVariable("id") Integer id, @PathVariable("emp_mail") String empMail) {
        EmployeeDTO employee = employeeService.getEmployeeDetails(id,empMail);
        return ResponseEntity.ok(employee); // 200 OK
    }

    @GetMapping("/{oid}/employee/{manMail}/myFeedback/list")
public ResponseEntity<List<NotesDTO>> getAllEmployeeFeedback(@PathVariable("oid") Integer oid, @PathVariable("manMail") String manMail){
   List<NotesDTO> notes = employeeService.getAllEmployeeFeedback(oid,manMail);
   return ResponseEntity.ok(notes);
}


@GetMapping("/{oid}/manager/{empMail}/myFeedback/list")
public ResponseEntity<List<NotesDTO>> getAllManagerFeedback(@PathVariable("oid") Integer oid, @PathVariable("empMail") String empMail){
   List<NotesDTO> notes = employeeService.getAllManagerFeedback(oid,empMail);
   return ResponseEntity.ok(notes);
}

@GetMapping("/employee/{emp_mail}/hierarchy/list")
    public ResponseEntity<?> getHierarchy(@PathVariable String emp_mail)
    {
        try{
           List<HierarchyDTO> hierarchy=employeeService.getHierarchy(emp_mail);
            return ResponseEntity.ok(hierarchy);
        }
        catch(ResourceNotFoundException ex){
            // Return 404 Not Found with error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("manager not found with mail: " + emp_mail);
       } 
        catch (Exception e) {
        // Return 500 Internal Server Error for any unexpected issues
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Internal server error occurred");
       }
    }

    @GetMapping("/manager/{man_mail}/hierarchy/list")
    public ResponseEntity<?> getHierarchyBelow(@PathVariable String man_mail)
    {
        try{
           List<HierarchyDTO> hierarchy=employeeService.getHierarchyBelow(man_mail);
            return ResponseEntity.ok(hierarchy);
        }
        catch(ResourceNotFoundException ex){
            // Return 404 Not Found with error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("manager not found with mail: " + man_mail);
       } 
        catch (Exception e) {
        // Return 500 Internal Server Error for any unexpected issues
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Internal server error occurred");
       }
    }

    @GetMapping("/{oid}/feedback/{meeting_id}")
    public ResponseEntity<List<NotesDTO>> getMeetingFeedback(@PathVariable("oid") Integer oid, @PathVariable("meeting_id") Integer mid){
   List<NotesDTO> notes = employeeService.getMeetingFeedback(oid,mid);
   return ResponseEntity.ok(notes);
    }

    @GetMapping("/get/{id}/manager/{managerEmail}/employee/{empEmail}/meeting_notes")
    public ResponseEntity<List<NotesDTO>> getMeetingNotes(@PathVariable("id") Integer id, @PathVariable("managerEmail") String managerEmail , @PathVariable("empEmail") String empEmail){
        List<NotesDTO> notes = employeeService.getMeetingNotes(id,managerEmail,empEmail);
        return ResponseEntity.ok(notes);
    }


    @PostMapping("/{org_id}/{empEmail}/enterFeedback")
    public ResponseEntity<?> employeeEnterNotes(@PathVariable("org_id") Integer org_id, @PathVariable("empEmail") String empEmail, @RequestBody NotesDTO feedback){
        employeeService.addNewMeetingRecord(org_id, empEmail, feedback.getMgr_mail(), feedback.getMeeting_notes(),feedback.getAuthor(), feedback.getDate(), feedback.getMode());
        return ResponseEntity.status(200).body("Notes added successfully.");
   

    }
}
