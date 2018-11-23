package com.yy.EmployeeManagement.Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.yy.EmployeeManagement.Domain.Employee;
import com.yy.EmployeeManagement.Service.Validator;

@RunWith(Parameterized.class)
public class ValidatorTests {
	private String ID;
	private String firstName;
	private String lastName;
	private Date Dob;
	
	public ValidatorTests(String Id, String firstName, String lastName, Date dob)
	{
		this.ID = Id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.Dob = dob;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
//			{ "B00911199", "firstTestName", "lastTestName", Date.valueOf("2009-03-03") }, 
//			{ "A009111999999", "firstTestName", "lastTestName", Date.valueOf("2009-03-03") },
//			{ "A00911199", "", "lastTestName", Date.valueOf("2009-03-03")},
//			{ "A00911199", "firstName", "", Date.valueOf("2009-03-03") },
//			{ "A00911199", null, "lastTestName", Date.valueOf("2009-03-03") },
//			{ "A00911199", "firstName", null, Date.valueOf("2009-03-03") },
//			{ "A00911199", "firstName", "lastName", null },
			{ "A00911102", "firstName", "lastName", Date.valueOf("2009-03-03") }});
	}

	@Test
	public void testIsValidEmployee() {
		Employee employee = new Employee(ID, firstName, lastName, Dob);
		assertTrue(Validator.isValidEmployee(employee));
	}
}
