package spring.crud.service;

import java.util.List;

import spring.crud.dto.EmployeeRequest;
import spring.crud.dto.EmployeeResponse;

public interface EmployeeService {
	
 String saveEmployee(EmployeeRequest employeeRequest);
 EmployeeResponse editEmployee(Long id);
 String updateEmployee(EmployeeResponse employeeResponse);
 String deleteEmployee(Long id);
 
List<EmployeeResponse> employeeFetch();

}
