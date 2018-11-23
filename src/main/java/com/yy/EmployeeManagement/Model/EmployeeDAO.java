package com.yy.EmployeeManagement.Model;

import java.util.List;

import com.yy.EmployeeManagement.Domain.Employee;

public interface EmployeeDAO {

	public List<Employee> getAllEmployees();

	public void addEmployee(Employee employee);

	public Employee findEmployeeByID(String id) throws Exception;

	public void deleteEmployee(String id);
	
	public int getEmployeesTotal();
	
	public List<Employee>getPagedEmployees(int startRows, int recordsPerPage);
	
	public List<Employee>getPagedEmployees(int startRows, int recordsPerPage, String sortItemName, String order);
}
