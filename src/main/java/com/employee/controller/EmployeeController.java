package com.employee.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.employee.repo.EmployeeRepo;
import com.employee.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRepo EmployeeRepo;

	@PostMapping("/saveEmployee")
	public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee) {
		
			Employee savedEmployee = employeeService.saveEmployee(employee);
			return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
		
	}
	
	

	@GetMapping("/getAll")
	public List<Employee> getAllEmployees() 
	{
		return employeeService.getAllEmployees();
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee != null) {
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	@GetMapping("/tax/{employeeCode}")
    public Map<String, Object> getTaxDeductions(@PathVariable Long employeeCode) {
        Employee employee = EmployeeRepo.findById(employeeCode).orElseThrow(() -> new RuntimeException("Employee not found"));

        double monthlySalary = employee.getSalary();
        LocalDate doj = employee.getDoj();
        LocalDate startOfYear = LocalDate.of(doj.getYear(), 4, 1);
        LocalDate endOfYear = LocalDate.of(doj.getYear() + 1, 3, 31);

        long monthsWorked = ChronoUnit.MONTHS.between( doj, endOfYear) ;
        double yearlySalary = monthlySalary * 12;
        double totalSalary = employeeService.calculateTotalSalary(monthlySalary, monthsWorked);

        double taxAmount = employeeService.calculateTax(totalSalary);
        double cessAmount = employeeService.calculateCess(totalSalary);

        Map<String, Object> response = new HashMap<>();
        response.put("employeeCode", employee.getId());
        response.put("firstName", employee.getFirstName());
        response.put("lastName", employee.getLastName());
        response.put("yearlySalary", yearlySalary);
        response.put("taxAmount", taxAmount);
        response.put("cessAmount", cessAmount);

        return response;
    }
	
	
	

}
