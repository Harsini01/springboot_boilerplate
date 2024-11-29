package com.sample.springboot_boilerplate.service.impl;

import com.sample.springboot_boilerplate.Db.OrgHandler;
import com.sample.springboot_boilerplate.dto.OrganizationDTO;
import com.sample.springboot_boilerplate.dto.ProductDTO;
import com.sample.springboot_boilerplate.dto.EmployeeDTO;
import com.sample.springboot_boilerplate.dto.EmpDTO;
import com.sample.springboot_boilerplate.entity.Organization;
//import com.sample.springboot_boilerplate.entity.Employee;
import com.sample.springboot_boilerplate.exception.ResourceNotFoundException;
import com.sample.springboot_boilerplate.mapper.OrganizationMapper;
//import com.sample.springboot_boilerplate.mapper.EmployeeMapper;
import com.sample.springboot_boilerplate.repository.OrganizationRepository;
import com.sample.springboot_boilerplate.service.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    private final OrgHandler orgHandler;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper, OrgHandler orgHandler) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
        this.orgHandler = orgHandler;
    }

    @Override
    public List<OrganizationDTO> getAllOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizations.stream()
                .map(organizationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductList(Integer id) {
        List<Object[]> orgs = orgHandler.getProductList(id);
        List<ProductDTO> products = new ArrayList<>();

        for (Object[] org : orgs) {
            ProductDTO dto = new ProductDTO();
            dto.setId(Integer.parseInt(Objects.toString(org[0])));
            dto.setName((String) org[1]);
            products.add(dto);
        }

        return products;
    }

    @Override
    public EmployeeDTO getEmployeeDetails(Integer id) {
    // Fetch Employee entity from orgHandler (or database)
  //  EmployeeDTO temp=orgHandler.getEmployeeDetails(id);
  //  EmployeeDTO employee = new EmployeeDTO();// orgHandler returns Employee entity
   // employee.setId(id);
    //employee.setOrgid(temp.getOrgid());

    // Convert Employee entity to EmployeeDTO using EmployeeMapper
    return orgHandler.getEmployeeDetails(id);//EmployeeMapper.toDTO(employee);  // Mapping logic to EmployeeDTO
}


    @Override
    public OrganizationDTO getOrganizationById(Integer id) {
        // Throw exception if the organization is not found
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with ID: " + id));

        return organizationMapper.toDTO(organization);
    }

    @Override
    public List<EmpDTO> getEmployeeByMail(String man_mail) {
        List<Object[]> orgs = orgHandler.getEmployeeByMail(man_mail);
        List<EmpDTO> employee = new ArrayList<>();

        for (Object[] org : orgs) {
            EmpDTO dto = new EmpDTO();
            dto.setId(Integer.parseInt(Objects.toString(org[0])));
            dto.setFname((String) org[1]);
            dto.setLname((String) org[2]);
            dto.setEmp_mail((String) org[3]);
            employee.add(dto);
        }

        return employee;
    }
}
