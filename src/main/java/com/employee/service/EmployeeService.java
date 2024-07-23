package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.model.Employee;
import com.employee.repo.EmployeeRepo;

@Service
public class EmployeeService {
	
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).get();
    }
    
    
    
    
    
    
    
    public double calculateTax(double salary) {
        double tax = 0;

        if (salary > 1000000) {
            tax =tax+ (salary - 1000000) * 0.20;
            salary = 1000000;
        }
        if (salary > 500000) {
        	 tax =tax+ (salary-500000) * 0.10;
            salary = 500000;
        }
        if (salary > 250000) {
        	 tax =tax+ (salary-250000) * 0.05;
        }

        return tax;
    }

    public double calculateCess(double salary) {
        if (salary > 2500000) {
            return (salary - 2500000) * 0.02;
        }
        return 0;
    }

    public double calculateTotalSalary(double monthlySalary, long monthsWorked) {
        return monthlySalary * monthsWorked;
    }

    public double calculateLossOfPay(double monthlySalary) {
        return monthlySalary / 30;
    }
    
    
    

}
