package com.yy.EmployeeManagement.Model;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.LazyDataModel;

import com.yy.EmployeeManagement.Controller.Constants;
import com.yy.EmployeeManagement.Domain.Employee;
import com.yy.EmployeeManagement.Service.EmployeeService;
import com.yy.EmployeeManagement.Service.EmployeeServiceException;
import com.yy.EmployeeManagement.Service.IdAlreadyExistException;
import com.yy.EmployeeManagement.Service.InvalidEmployeeDataException;

@ManagedBean(name = "employeeBean")
@SessionScoped
public class EmployeeManagedBean implements Serializable {

	private static final long serialVersionUID = -8315693426934989256L;
	private LazyDataModel<Employee> employees = null;

	private EmployeeService service;

	public EmployeeManagedBean() {
		service = new EmployeeService();
	}

	public LazyDataModel<Employee> getAllEmployees() {
		if (employees == null) {
			employees = new EmployeeLazyList();
		}
		return employees;
	}

	public String findEmployeeBy(String value) {
		HttpServletRequest request = null;
		try {
			request = getRequest();

			Employee employee = service.FindEmployee(value);
			if (employee != null) {
				findEmployeeResponse(request, employee.getLastName() + " " + employee.getFirstName(),
						Constants.STATUS_CODE_FIND_SUCCESS, "Success.");
			} else {
				findEmployeeResponse(request, null, Constants.STATUS_CODE_NO_MATCH_FOUND, "No match found");
			}
		} catch (Exception e) {
			findEmployeeResponse(request, null, Constants.STATUS_CODE_NO_MATCH_FOUND, "No match found");
		}

		return "index.xhtml?faces-redirect=true";

	}

	public String addEmployee(String id, String firstName, String lastName, String dob) {
		HttpServletRequest request = null;

		try {
			request = getRequest();

			Date dobDate = (dob == null || "".equals(dob)) ? null : getSqlDate(dob);

			Employee employee = new Employee(id, firstName, lastName, dobDate);
			service.AddEmployee(employee);
			AddEmployeeResponse(request, Constants.STATUS_CODE_ADD_SUCCESS, "Success");

		} catch (IdAlreadyExistException e1) {
			request.getSession().setAttribute("addInfo", e1.getMessage());
			AddEmployeeResponse(request, "502", "ID already exists for another employee.");

		} catch (InvalidEmployeeDataException e2) {
			AddEmployeeResponse(request, Constants.STATUS_CODE_INVALID_EMPLOYEE_DATA,
					"Description: invalid employee data!");
		} catch (Exception e3) {
			AddEmployeeResponse(request, Constants.STATUS_CODE_OTHER_EXCEPTION, e3.toString());
		}

		return "index.xhtml?faces-redirect=true";
	}

	public String deleteEmployee(String id) {
		HttpServletRequest request = null;
		try {
			request = getRequest();

			service.DeleteEmployee(id);
			removeEmployeeResponse(request, Constants.STATUS_CODE_DELETE_SUCCESS, "Deleted Successfully");
		} catch (EmployeeServiceException e1) {
			removeEmployeeResponse(request, Constants.STATUS_CODE_DELETE_UNSUCCESS, e1.getMessage());
		} catch (Exception e2) {
			removeEmployeeResponse(request, Constants.STATUS_CODE_DELETE_UNSUCCESS, "Deleted UnSuccessful");
		}

		return "index.xhtml?faces-redirect=true";
	}

	private HttpServletRequest getRequest() {
		HttpServletRequest request = null;
		var context = FacesContext.getCurrentInstance().getExternalContext();
		Object requestObj = context.getRequest();

		if (requestObj instanceof HttpServletRequest) {
			request = (HttpServletRequest) requestObj;
		}
		return request;
	}

	private void findEmployeeResponse(HttpServletRequest request, String findResult, String code, String description) {
		request.getSession().setAttribute("findName", findResult);
		addInfoToResponse(request, "find", code, description);
	}

	private void AddEmployeeResponse(HttpServletRequest request, String code, String description) {
		addInfoToResponse(request, "add", code, description);
	}

	private void removeEmployeeResponse(HttpServletRequest request, String code, String description) {
		addInfoToResponse(request, "delete", code, description);
	}

	private void addInfoToResponse(HttpServletRequest request, String prefix, String code, String description) {
		request.getSession().setAttribute(prefix + "ResponseCode", code);
		request.getSession().setAttribute(prefix + "ResponseDescription", description);
	}

	private static java.sql.Date getSqlDate(String dateString) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date date = format.parse(dateString);
		return new java.sql.Date(date.getTime());
	}

}
