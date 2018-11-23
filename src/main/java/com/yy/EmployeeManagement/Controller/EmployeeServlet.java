package com.yy.EmployeeManagement.Controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yy.EmployeeManagement.Domain.Employee;
import com.yy.EmployeeManagement.Domain.PagedEmployees;
import com.yy.EmployeeManagement.Domain.Pagination;
import com.yy.EmployeeManagement.Service.EmployeeService;
import com.yy.EmployeeManagement.Service.EmployeeServiceException;
import com.yy.EmployeeManagement.Service.IdAlreadyExistException;
import com.yy.EmployeeManagement.Service.InvalidEmployeeDataException;

/**
 * Servlet implementation class EmployeeServlet
 */
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService service;

	public void init() {
		service = new EmployeeService();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter(Constants.METHOD);
		if (method != null) {
			if (method.equals(Constants.PAGINATE)) {
				paginate(request, response);
			}
			if (method.equals(Constants.LOG_OUT)) {
				logout(request, response);
			}
			if (method.equals(Constants.ADD_EMPLOYEE)) {
				try {
					addEmployee(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (method.equals(Constants.REMOVE_EMPLOYEE)) {
				try {
					removeEmployee(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (method.equals(Constants.FIND_EMPLOYEE)) {
				try {
					findEmployeeBy(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void findEmployeeBy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		try {
			Employee employee = service.FindEmployee(id);
			if (employee != null) {
				findEmployeeResponse(request, employee.getLastName() + " " + employee.getFirstName(),
						Constants.STATUS_CODE_FIND_SUCCESS, "Success.");
			} else {
				findEmployeeResponse(request, null, Constants.STATUS_CODE_NO_MATCH_FOUND, "No match found");
			}
		} catch (Exception e) {
			findEmployeeResponse(request, null, Constants.STATUS_CODE_NO_MATCH_FOUND, "No match found");
		}

		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	private void removeEmployee(HttpServletRequest request, HttpServletResponse response)
			throws EmployeeServiceException, Exception {
		String id = request.getParameter("id");

		try {
			service.DeleteEmployee(id);
			removeEmployeeResponse(request, Constants.STATUS_CODE_DELETE_SUCCESS, "Deleted Successfully");
		} catch (EmployeeServiceException e1) {
			removeEmployeeResponse(request, Constants.STATUS_CODE_DELETE_UNSUCCESS, e1.getMessage());
		}
		catch (Exception e2) {
			removeEmployeeResponse(request, Constants.STATUS_CODE_DELETE_UNSUCCESS, "Deleted UnSuccessful");
		}

		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	private void addEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ParseException {
		String id = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String dob = request.getParameter("dob");

		Date dobDate = (dob == null || "".equals(dob)) ? null : getSqlDate(dob);
		try {
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

		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	private static java.sql.Date getSqlDate(String dateString) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date date = format.parse(dateString);
		return new java.sql.Date(date.getTime());
	}

	private void findEmployeeResponse(HttpServletRequest request, String findResult, String code, String description) {
		request.getSession().setAttribute("findName", findResult);
		addInfoToResponse(request, "find", code, description);
	}

	private void removeEmployeeResponse(HttpServletRequest request, String code, String description) {
		addInfoToResponse(request, "delete", code, description);
	}

	private void AddEmployeeResponse(HttpServletRequest request, String code, String description) {
		addInfoToResponse(request, "add", code, description);
	}

	private void addInfoToResponse(HttpServletRequest request, String prefix, String code, String description) {
		request.getSession().setAttribute(prefix + "ResponseCode", code);
		request.getSession().setAttribute(prefix + "ResponseDescription", description);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	private void paginate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sort = request.getParameter(Constants.EASY_UI_SORT);
		String order = request.getParameter(Constants.EASY_UI_ORDER);
		String page = request.getParameter(Constants.EASY_UI_PAGE);

		Pagination pagination = service.paginate(Integer.valueOf(page), sort, order);

		response.getWriter().write(getJsonEmployeeString(pagination));
	}

	private String getJsonEmployeeString(Pagination pagination) throws JsonProcessingException {
		PagedEmployees employees = new PagedEmployees(pagination.getTotalRecords(), pagination.getEmployeeList());

		ObjectMapper mapper = new ObjectMapper();
		String employeeJson = mapper.writeValueAsString(employees);
		return employeeJson;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
