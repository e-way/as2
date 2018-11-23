package com.yy.EmployeeManagement.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yy.EmployeeManagement.Domain.Employee;

public class Validator {

	public static boolean isUserRole(String userName, String password) {
		if (userName != null && userName.equals("user") && password.equals("user")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isAdminRole(String userName, String password) {
		if (userName != null && userName.equals("admin") && password.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidEmployee(Employee employee) {
		if (employee == null) {
			return false;
		}
		if (!isValidName(employee.getFirstName()) || !isValidName(employee.getLastName())) {
			return false;
		}
		if (!isValidID(employee.getID())) {
			return false;
		}
		return true;
	}

	private static boolean isValidName(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		return true;
	}

	public static boolean isValidID(String id) {
		if (id == null || id.isEmpty()) {
			return false;
		}

		Pattern pattern = Pattern.compile("^A0\\d{7}");
		Matcher isValidId = pattern.matcher(id);
		if (!isValidId.matches()) {
			return false;
		}
		return true;
	}

}
