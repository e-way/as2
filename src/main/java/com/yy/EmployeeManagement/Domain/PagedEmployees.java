package com.yy.EmployeeManagement.Domain;

import java.util.List;
/**
 * 
 * Serialize employees to easy UI.
 *
 */
public class PagedEmployees {
	public int total;
	public List<Employee> rows;
	
	public PagedEmployees(int total, List<Employee> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Employee> getRows() {
		return rows;
	}
	public void setRows(List<Employee> rows) {
		this.rows = rows;
	}

}
