package com.yy.EmployeeManagement.Model;

import java.util.ArrayList;
import java.util.List;

import com.yy.EmployeeManagement.Domain.Employee;
import com.yy.EmployeeManagement.Service.EmployeeService;

public class EmployeesLoader {
	private static List<Employee> employees = new ArrayList<Employee>();
	static {
		var service = new EmployeeService();
		setEmployees(service.ListEmployees());
	}

	public static List<Employee> getEmployees() {
		return employees;
	}

	public static void setEmployees(List<Employee> employees) {
		EmployeesLoader.employees = employees;
	}
}
