package com.yy.EmployeeManagement.Service;

import java.util.List;

import com.yy.EmployeeManagement.Domain.Employee;
import com.yy.EmployeeManagement.Domain.Pagination;
import com.yy.EmployeeManagement.Factory.DaoFactory;
import com.yy.EmployeeManagement.Model.EmployeeDAO;

public class EmployeeService {
	private final EmployeeDAO employeeDAO = DaoFactory.getDaoFactory().getEmployeeDao();

	public List<Employee> ListEmployees() {
		return employeeDAO.getAllEmployees();
	}

	public void AddEmployee(Employee employee) throws Exception {
		if (Validator.isValidEmployee(employee)) {
			if (employeeDAO.findEmployeeByID(employee.getID()) != null) {
				throw new IdAlreadyExistException("");
			} else {
				employeeDAO.addEmployee(employee);
			}
		} else {
			throw new InvalidEmployeeDataException("");
		}
	}

	public Employee FindEmployee(String employeeId) throws Exception {
		if (!Validator.isValidID(employeeId)) {
			throw new EmployeeServiceException("The employee Id is empty, please check.");
		}
		return employeeDAO.findEmployeeByID(employeeId);
	}

	public void DeleteEmployee(String employeeId) throws Exception {
		if (!Validator.isValidID(employeeId)) {
			throw new EmployeeServiceException("The employee Id is inValid, please check.");
		}
		if (employeeDAO.findEmployeeByID(employeeId) == null) {
			throw new EmployeeServiceException("The Id:"+employeeId+" you try to delete does not exists.");
		}
		employeeDAO.deleteEmployee(employeeId);
	}

	public Pagination paginate(int currentPageNumber, String sortItemName, String order) {
		Pagination page = new Pagination();

		page.setSortItemName(sortItemName);
		page.setOrder(order);
		page.setCurrentPageNumber(currentPageNumber);

		int totalRecords = employeeDAO.getEmployeesTotal();
		page.setTotalRecords(totalRecords);

		int recordsPerPage = page.getRecordsPerPage();
		int startRowNumber = (page.getCurrentPageNumber() - 1) * recordsPerPage;

		List<Employee> employeeList = employeeDAO.getPagedEmployees(startRowNumber, recordsPerPage, sortItemName,
				order);
		page.setEmployeeList(employeeList);

		return page;
	}
}
