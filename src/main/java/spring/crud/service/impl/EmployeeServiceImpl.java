package spring.crud.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.crud.constant.MessageConstant;
import spring.crud.dto.EmployeeRequest;
import spring.crud.dto.EmployeeResponse;
import spring.crud.model.Employee;
import spring.crud.repository.EmployeeRepository;
import spring.crud.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public String saveEmployee(EmployeeRequest employeeRequest) {

		try {

			System.out.println("printing");

			Optional<Employee> emailExist = employeeRepository.findByEmail(employeeRequest.getEmail());
			if (emailExist.isPresent()) {
				return MessageConstant.FAILED;

			} else {

				Employee emp = new Employee();
				emp.setName(employeeRequest.getName());
				emp.setEmail(employeeRequest.getEmail());
				emp.setContactNo(employeeRequest.getContactNo());
				employeeRepository.save(emp);
				return MessageConstant.SUCCESS;

			}

		} catch (Exception exception) {
			return null;

		}

	}

	@Override
	public List<EmployeeResponse> employeeFetch() {

		try {

			List<Employee> listHavingEmployee = employeeRepository.findAll();
			List<EmployeeResponse> listEmployeeResponse = new ArrayList<>();
			
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); 
			
			
	       
			if (!listHavingEmployee.isEmpty()) {
				for (Employee employee : listHavingEmployee) {
					EmployeeResponse employeeResponse = new EmployeeResponse();
					employeeResponse.setId(employee.getId());
					employeeResponse.setName(employee.getName());
					employeeResponse.setEmail(employee.getEmail());
					employeeResponse.setContactNo(employee.getContactNo());
					employeeResponse.setCreatedAt(LocalDateTime.ofInstant(employee.getCreatedAt(), ZoneOffset.UTC));
					employeeResponse.setUpdatedAt(LocalDateTime.ofInstant(employee.getUpdatedAt(), ZoneOffset.UTC));
				        
					listEmployeeResponse.add(employeeResponse);

				}
				return listEmployeeResponse;

			} else {

				return Collections.emptyList();

			}

		} catch (Exception exception) {
			return Collections.emptyList();

		}
	}

	@Override
	public EmployeeResponse editEmployee(Long id) {
		EmployeeResponse employeeResponse=null;
		try {
			
		Optional<Employee> optEmployee =	employeeRepository.findById(id);
		if(optEmployee.isPresent())
		{
			Employee employee =	optEmployee.get();
			employeeResponse = new EmployeeResponse();
			employeeResponse.setId(employee.getId());
			employeeResponse.setName(employee.getName());
			employeeResponse.setEmail(employee.getEmail());
			employeeResponse.setContactNo(employee.getContactNo());
			return employeeResponse;
			
			
		}
		else {
			
			return employeeResponse=null;
			
		}
			
		}
		catch(Exception exception)
		{
			return employeeResponse=null;
			
		}
		
	}

	@Override
	public String updateEmployee(EmployeeResponse employeeResponse) {
		try {
			
							
		Optional<Employee> optEmployee = 	employeeRepository.findById(employeeResponse.getId());
		
		if(optEmployee.isPresent())
		{
			Employee employee = optEmployee.get();
			employee.setName(employeeResponse.getName());
			employee.setEmail(employeeResponse.getEmail());
			employee.setContactNo(employeeResponse.getContactNo());
			
			employeeRepository.save(employee);
			
			return MessageConstant.UPDATE;
			
		}
		else {
			
			return MessageConstant.UPDATE_FAILED;
			
		}
			
			
		}
		catch(Exception exception)
		{
			return MessageConstant.UPDATE_FAILED;
			
		}
		
	}

	@Override
	public String deleteEmployee(Long id) {
		try {
			
			employeeRepository.deleteById(id);
			return MessageConstant.DELETE;
			
		}
		catch(Exception exception)
		{
			return MessageConstant.DELETE_FAILED; 
		}
	}

}
