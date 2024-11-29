package com.sample.springboot_boilerplate.controller;

import com.sample.springboot_boilerplate.dto.OrganizationDTO;
import com.sample.springboot_boilerplate.dto.ProductDTO;
import com.sample.springboot_boilerplate.dto.EmployeeDTO;
import com.sample.springboot_boilerplate.dto.EmpDTO;
import com.sample.springboot_boilerplate.exception.ResourceNotFoundException;
import com.sample.springboot_boilerplate.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/org")
@CrossOrigin(origins = "http://localhost:5173")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrganizationDTO>> getAllOrganizations() {
        List<OrganizationDTO> organizations = organizationService.getAllOrganizations();
        return ResponseEntity.ok(organizations); // 200 OK
    }

    @GetMapping("/{id}/product/list")
    public ResponseEntity<List<ProductDTO>> getProductList(@PathVariable("id") Integer id) {
        List<ProductDTO> products = organizationService.getProductList(id);
        return ResponseEntity.ok(products); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeDEtails(@PathVariable("id") Integer id) {
        EmployeeDTO details = organizationService.getEmployeeDetails(id);
        return ResponseEntity.ok(details); // 200 OK
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOrganizationById(@PathVariable Integer id) {
        try {
            OrganizationDTO organization = organizationService.getOrganizationById(id);
            return ResponseEntity.ok(organization); // 200 OK
        } catch (ResourceNotFoundException ex) {
            // Return 404 Not Found with error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Organization not found with ID: " + id);
        } catch (Exception e) {
            // Return 500 Internal Server Error for any unexpected issues
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error occurred");
        }
    }

    @GetMapping("/{man_mail}/employee/list")
    public ResponseEntity<?> getEmployeeByMail(@PathVariable String man_mail)
    {
        try{
            List<EmpDTO> employee=organizationService.getEmployeeByMail(man_mail);
            return ResponseEntity.ok(employee);
        }
        catch(ResourceNotFoundException ex){
             // Return 404 Not Found with error message
             return ResponseEntity.status(HttpStatus.NOT_FOUND)
             .body("manager not found with ID: " + man_mail);
        } 
    //     catch (Exception e) {
    //  // Return 500 Internal Server Error for any unexpected issues
    //  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //          .body("Internal server error occurred");
    //     }
    }
}
