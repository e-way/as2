package com.yy.EmployeeManagement.Model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.model.LazyDataModel;

import com.yy.EmployeeManagement.Domain.Employee;

@ManagedBean(name="employeeBean")
@SessionScoped
public class EmployeeManagedBean implements Serializable {

	private static final long serialVersionUID = -8315693426934989256L;
	private LazyDataModel<Employee> employees = null;

	public LazyDataModel<Employee> getAllEmployees() {
		if (employees == null) {
			employees = new EmployeeLazyList();
		}
		return employees;
	}

	public void sort(ActionEvent event) {
		String ComponentId = event.getComponent().getId();

		if ("firstName".equalsIgnoreCase(ComponentId)) {
			Collections.sort(EmployeesLoader.getEmployees(), MusicianComparator.compareByFirstName());
		}
	}

	public static class MusicianComparator {

		public static Comparator<Employee> compareByFirstName() {
			return new Comparator<Employee>() {

				@Override
				public int compare(Employee e1, Employee e2) {

					return e1.getFirstName().compareTo(e2.getFirstName());
				}

			};
		}

	}

}
