package com.yy.EmployeeManagement.Factory;
import com.yy.EmployeeManagement.Model.EmployeeDAO;
import com.yy.EmployeeManagement.Model.EmployeeDAOImpl;

public class DaoFactory {

	private DaoFactory() {

	}

	private static DaoFactory daoFactory;

	public static DaoFactory getDaoFactory() {
		if (daoFactory == null) {
			daoFactory = new DaoFactory();
		}
		return daoFactory;
	}

	public EmployeeDAO getEmployeeDao() {
		return new EmployeeDAOImpl();
	}

}
