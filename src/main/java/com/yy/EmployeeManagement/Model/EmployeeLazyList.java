package com.yy.EmployeeManagement.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import com.yy.EmployeeManagement.Domain.Employee;
import com.yy.EmployeeManagement.Service.EmployeeService;

public class EmployeeLazyList extends LazyDataModel<Employee> {

	private static final long serialVersionUID = -3661901772248850955L;

	private List<Employee> employees;
	private EmployeeService service = new EmployeeService();

	public EmployeeLazyList() {
		this.employees = EmployeesLoader.getEmployees();
	}

	@Override
	public List<Employee> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		if (filters == null || filters.size() == 0) {

			// row count
			int dataSize = employees.size();
			setRowCount(dataSize);
			setPageSize(pageSize);

			// paginate
			if ((first + pageSize) > dataSize) // last page
			{
				return employees.subList(first, dataSize);
			} else if (employees.size() > pageSize) {
				
				List<Employee> sub;
				var currentPageNumber = (dataSize / pageSize) - ((dataSize - first) / pageSize) + 1;//(first / pageSize) + 1;
				if (sortField == null) {
					sub = service.paginate(currentPageNumber, "firstName", ConvertSortOrder(sortOrder)).getEmployeeList();
				}

				else {
					sub = service.paginate(currentPageNumber, sortField, ConvertSortOrder(sortOrder)).getEmployeeList();
				}

				return sub;
			} else {
				return employees;
			}

		} else { // Filtering
			if (filters.containsKey("firstName")) {
				var filteredEmployees = new ArrayList<Employee>();

				employees.stream().filter(m -> m.getFirstName().contains((String) filters.get("firstName")))
						.forEach(m -> filteredEmployees.add(m));

				setPageSize(pageSize);
				return filteredEmployees;
			}

		}

		setPageSize(pageSize);
		return employees;
	}

	@Override
	public List<Employee> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {

		System.out.println("List clalled");
		employees = EmployeesLoader.getEmployees().subList(first, pageSize);

		if (getRowCount() <= 0) {
			setRowCount(EmployeesLoader.getEmployees().size());
		}
		setPageSize(pageSize);
		return employees;
	}

	@Override
	public Object getRowKey(Employee employee) {
		return employee.getID();
	}

	@Override
	public Employee getRowData(String employeeId) {
		for (Employee employee : employees) {
			if (employeeId.equals(employee.getID())) {
				return employee;
			}
		}
		return null;
	}

	private String ConvertSortOrder(SortOrder sortOrder) {
		if (sortOrder.toString().equals("ASCENDING")) {
			return "ASC";
		}
		return "DESC";
	}

}
