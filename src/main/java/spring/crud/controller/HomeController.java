package spring.crud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.crud.constant.MessageConstant;
import spring.crud.dto.EmployeeRequest;
import spring.crud.dto.EmployeeResponse;
import spring.crud.service.EmployeeService;

@Controller
public class HomeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/")
	public String homePage() {
		return "/views/index";
	}

	@RequestMapping("/signup")
	public String signup(EmployeeRequest employeeRequest) {
		return "/views/signup-form";
	}

	@PostMapping("/saveForm")
	public String saveEmployee(@Valid EmployeeRequest employeeRequest, BindingResult bindingResult, Model model) {
		System.out.println("printing");
		String message = employeeService.saveEmployee(employeeRequest);
		if (message.equalsIgnoreCase(MessageConstant.SUCCESS)) {
			model.addAttribute("success", message);

			return "/views/signup-form";

		} else {

			model.addAttribute("error", message);
			System.out.println("email is already exists");
			return "/views/signup-form";

		}

	}
	
	//@ResponseBody
	@GetMapping("/employee")
	public String fetchEmployee(Model model)
	{
	 List<EmployeeResponse> listEmployeeResponse =	employeeService.employeeFetch();
	 if(!listEmployeeResponse.isEmpty())
	 {
		 model.addAttribute("employeeList", listEmployeeResponse);
		 
		 //return listEmployeeResponse;
		 
		 return "/views/employee-all";
	 }
	 else {
		 
		 model.addAttribute("employeeList", listEmployeeResponse);
		 return "/views/employee-all";
		 //return listEmployeeResponse;
		 
	 }
					
	}
	
	@GetMapping("/edit/{id}")
	public String saveEmployee(@Valid @PathVariable("id") Long id,EmployeeRequest employeeRequest, BindingResult bindingResult, Model model) {
		System.out.println("printing");
		EmployeeResponse employeeResponse = employeeService.editEmployee(id);
		if (employeeResponse!=null){
			model.addAttribute("employeeResponse", employeeResponse);

			return "/views/employee-edit-form";

		} else {

			model.addAttribute("error", "Record not available");
			
			return "/views/employee-edit-form";

		}

	}
	
	@PostMapping(path = {"/update"})
	public String updateEmployee(@Valid EmployeeResponse employeeResponse,  BindingResult bindingResult, Model model) {
		System.out.println("printing");
		String response= employeeService.updateEmployee(employeeResponse);
		if (response.equalsIgnoreCase(MessageConstant.UPDATE)){
			model.addAttribute("employeeResponse", response);

			return "redirect:/employee";
			

		} else {

			model.addAttribute("error", "Record not available");
			
			return "/views/employee-edit-form";

		}

	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@Valid @PathVariable("id") Long id,EmployeeRequest employeeRequest, BindingResult bindingResult, Model model) {
		System.out.println("printing");
		String response = employeeService.deleteEmployee(id);
		if (response.equalsIgnoreCase(MessageConstant.DELETE)){
			model.addAttribute("employeeResponse", response);

			return "redirect:/employee";

		} else {

			model.addAttribute("error", response);
			
			return "redirect:/employee";

		}

	}
	
	@RequestMapping("/emp")
	public String empPage() {
		return "/views/employee-all";
	}


}
