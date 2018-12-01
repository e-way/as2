//package com.yy.EmployeeManagement.Model;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//
//import com.yy.EmployeeManagement.Domain.Employee;
//import com.yy.EmployeeManagement.Factory.DaoFactory;
//
//@ManagedBean(name="employeeManager")
//@ViewScoped
//public class EmployeeBean implements Serializable {
//
//	private static final long serialVersionUID = -3863451804397180202L;
//	private List<Employee> employees;
//
//	public EmployeeBean() {
//
//	}
//
//	public List<Employee> getEmployees() {
//		if (employees == null) {
//			EmployeeDAO employeeDAO = DaoFactory.getDaoFactory().getEmployeeDao();
//			employees = employeeDAO.getAllEmployees();
//		}
//		return employees;
//	}
//
//	public void setEmployees(List<Employee> employees) {
//		this.employees = employees;
//	}
//
//}
