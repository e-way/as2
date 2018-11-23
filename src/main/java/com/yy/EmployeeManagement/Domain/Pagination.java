package com.yy.EmployeeManagement.Domain;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
	private int totalRecords;
	private int recordsPerPage = 5;
	private int totalPages;
	private int currentPageNumber = 1;
	private List<Employee> employeeList = new ArrayList<Employee>();
	
	private String sortItemName;
	private String order;

	public Pagination() {
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
		if (this.totalRecords % this.recordsPerPage == 0) {
			this.totalPages = this.totalRecords / this.recordsPerPage;
		} else {
			this.totalPages = this.totalRecords / this.recordsPerPage + 1;
		}
	}

	public String getSortItemName() {
		return sortItemName;
	}

	public void setSortItemName(String sortItemName) {
		this.sortItemName = sortItemName;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
}
