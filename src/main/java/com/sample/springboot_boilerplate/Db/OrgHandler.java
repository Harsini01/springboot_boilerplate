package com.sample.springboot_boilerplate.Db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import com.sample.springboot_boilerplate.dto.EmployeeDTO;
//import com.sample.springboot_boilerplate.entity.Employee;
import java.util.List;
//import java.sql.Date;
@Repository
public class OrgHandler {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Object[]> listAllOrgs() {
        StringBuilder query = new StringBuilder();
        query.append("select * from organisation ");

        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        return nativeQuery.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getProductList(Integer id) {
        StringBuilder query = new StringBuilder();
        query.append("select id, product_name from t_product where org_id = :id ");

        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        return nativeQuery.setParameter("id", id).getResultList();
    }
        @SuppressWarnings("unchecked")
        public EmployeeDTO getEmployeeDetails(Integer id) {
            // Create the SQL query to fetch employee details by emp_id
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM employee WHERE emp_id = :id");
    
            // Create the native query using EntityManager
            Query nativeQuery = entityManager.createNativeQuery(query.toString());
    
            // Set the parameter for the query
            nativeQuery.setParameter("id", id);
    
            // Execute the query and fetch the result list
            List<Object[]> resultList = nativeQuery.getResultList();
    
            // If the result is not empty, map the result to EmployeeDTO
            if (!resultList.isEmpty()) {
                Object[] result = resultList.get(0);  // Assuming only one row is returned
    
                // Create a new EmployeeDTO instance and populate it
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setId((Integer) result[0]); // Map the 'id' field from the result
                employeeDTO.setOrgid((String) result[1]); // Map 'orgid' (second column)
                employeeDTO.setEmp_mail((String) result[2]); // Map 'emp_mail'
                employeeDTO.setFname((String) result[3]); // Map 'fname'
                employeeDTO.setLname((String) result[4]); // Map 'lname'
                employeeDTO.setPhno((String) result[5]); // Map 'phno'
                employeeDTO.setDesignation((String) result[6]); // Map 'designation'
                employeeDTO.setAddr((String) result[7]); // Map 'addr'
                employeeDTO.setSalary((Integer) result[8]); // Map 'salary'
                employeeDTO.setMan_mail((String) result[9]); // Map 'man_mail'
                employeeDTO.setDob((String) result[10]); // Map 'dob' (date of birth)
                employeeDTO.setDoj((String) result[11]); // Map 'doj' (date of joining)
    
                // Return the EmployeeDTO populated with the employee details
                return employeeDTO;
            }
    
            // Return null if no employee found with the given ID
            return null;
        }

        @SuppressWarnings("unchecked")
    public List<Object[]> getEmployeeByMail(String man_mail){
        StringBuilder query=new StringBuilder();
        query.append("select emp_id,fname,lname,emp_mail from employee where m_mail= :man_mail");
        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        return nativeQuery.setParameter("man_mail", man_mail).getResultList();
    }
    
}

