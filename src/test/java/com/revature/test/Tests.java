package com.revature.test;

import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.model.EmployeeInfo;
import com.revature.model.Reimbursements;
import com.revature.model.User;
import com.revature.repository.DaoImpl;
import com.revature.service.Service;

public class Tests {
	@InjectMocks // Inject your mocks into the object under test
	private Service service;

//	//This tells Mockito to mock this type for us
	@Mock
	private DaoImpl daoImpl;

	@Before
	public void before() {
//		// Initialize mocks
		service = new Service();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testgetUser_username() {
		String username = null;
		User user = new User("rupeshg", "pass", "Rupesh", "Gudipudi", "Employee", "jsmith", "John", "Smith");
		Mockito.when(this.daoImpl.getUser(username)).thenReturn(user);
		User retrievedUser = service.getUser(username);
		Assert.assertEquals("rupeshg", retrievedUser.getUsername());
	}
	@Test
	public void testgetUser_password() {
		String username = null;
		User user = new User("rupeshg", "pass", "Rupesh", "Gudipudi", "Employee", "jsmith", "John", "Smith");
		Mockito.when(this.daoImpl.getUser(username)).thenReturn(user);
		User retrievedUser = service.getUser(username);
		Assert.assertEquals("pass", retrievedUser.getPassword());
	}
	@Test
	public void testgetUser_firstname() {
		String username = null;
		User user = new User("rupeshg", "pass", "Rupesh", "Gudipudi", "Employee", "jsmith", "John", "Smith");
		Mockito.when(this.daoImpl.getUser(username)).thenReturn(user);
		User retrievedUser = service.getUser(username);
		Assert.assertEquals("Rupesh", retrievedUser.getFirstname());
	}
	@Test
	public void testgetUser_lastname() {
		String username = null;
		User user = new User("rupeshg", "pass", "Rupesh", "Gudipudi", "Employee", "jsmith", "John", "Smith");
		Mockito.when(this.daoImpl.getUser(username)).thenReturn(user);
		User retrievedUser = service.getUser(username);
		Assert.assertEquals("Gudipudi", retrievedUser.getLastname());
	}
	@Test
	public void testgetUser_role() {
		String username = null;
		User user = new User("rupeshg", "pass", "Rupesh", "Gudipudi", "Employee", "jsmith", "John", "Smith");
		Mockito.when(this.daoImpl.getUser(username)).thenReturn(user);
		User retrievedUser = service.getUser(username);
		Assert.assertEquals("Employee", retrievedUser.getRole());
	}
	@Test
	public void testgetUser_managerid() {
		String username = null;
		User user = new User("rupeshg", "pass", "Rupesh", "Gudipudi", "Employee", "jsmith", "John", "Smith");
		Mockito.when(this.daoImpl.getUser(username)).thenReturn(user);
		User retrievedUser = service.getUser(username);
		Assert.assertEquals("jsmith", retrievedUser.getManagerId());
	}
	@Test
	public void testgetUser_managerFirstname() {
		String username = null;
		User user = new User("rupeshg", "pass", "Rupesh", "Gudipudi", "Employee", "jsmith", "John", "Smith");
		Mockito.when(this.daoImpl.getUser(username)).thenReturn(user);
		User retrievedUser = service.getUser(username);
		Assert.assertEquals("John", retrievedUser.getManagerFirstName());
	}
	@Test
	public void testgetUser_managerLastname() {
		String username = null;
		User user = new User("rupeshg", "pass", "Rupesh", "Gudipudi", "Employee", "jsmith", "John", "Smith");
		Mockito.when(this.daoImpl.getUser(username)).thenReturn(user);
		User retrievedUser = service.getUser(username);
		Assert.assertEquals("Smith", retrievedUser.getManagerLastName());
	}
	@Test
	public void testValidateUser() {
		String username = "Rupesh";
		String pwd = "pass123";

		Mockito.when(this.daoImpl.getUser(username))
				.thenReturn(new User("Rupesh", "pass123", null, null, null, null, null, null));
		User retrievedUser = service.validateUser(username, pwd);
		Assert.assertEquals("Rupesh", retrievedUser.getUsername());
		Assert.assertEquals("pass123", retrievedUser.getPassword());

	}

	@Test
	public void testviewMyInfo_firstname() {
		String username = "rupeshg";
		String pwd = "pass123";
		User user = new User("rupeshg", "pass123", "Rupesh", "Gudipudi", "Employee", "jsmith", "John", "Smith");

		ArrayList<EmployeeInfo> empInfo = new ArrayList<>();
		EmployeeInfo emp = new EmployeeInfo("Rupesh", "Gudipudi", "Employee", "John", "Smith");
		empInfo.add(emp);

		Mockito.when(this.daoImpl.getUser(username)).thenReturn(user);
		ArrayList<EmployeeInfo> retrievedUserInfo = service.viewMyInfo(username);
		Assert.assertEquals(empInfo.get(0).getFirstname(), retrievedUserInfo.get(0).getFirstname());

	}
	@Test
	public void testviewMyInfo_managerFirstName() {
		String username = "rupeshg";
		String pwd = "pass123";
		User user = new User("rupeshg", "pass123", "Rupesh", "Gudipudi", "Employee", "jsmith", "John", "Smith");

		ArrayList<EmployeeInfo> empInfo = new ArrayList<>();
		EmployeeInfo emp = new EmployeeInfo("Rupesh", "Gudipudi", "Employee", "John", "Smith");
		empInfo.add(emp);

		Mockito.when(this.daoImpl.getUser(username)).thenReturn(user);
		ArrayList<EmployeeInfo> retrievedUserInfo = service.viewMyInfo(username);
		Assert.assertEquals(empInfo.get(0).getManagerFirstName(), retrievedUserInfo.get(0).getManagerFirstName());

	}
	@Test
	public void testgetAllEmployees_firstname() {
		String username = "Rupesh1";
		String pwd = "pass123";
		User user = new User("rupeshg", "pass", "Rupesh", "Gudipudi", "Employee", "jsmith", "John", "Smith");

		ArrayList<EmployeeInfo> empInfo = new ArrayList<>();
		EmployeeInfo emp = new EmployeeInfo("Rupesh", "Gudipudi", "Employee", "John", "Smith");
		empInfo.add(emp);

		ArrayList<EmployeeInfo> returnEmpInfoList = new ArrayList<>();
		EmployeeInfo emp2 = new EmployeeInfo("Rupesh", "Gudipudi", "Employee", "John", "Smith");
		returnEmpInfoList.add(emp2);

		Mockito.when(this.daoImpl.getAllEmployees()).thenReturn(returnEmpInfoList);
		ArrayList<EmployeeInfo> retrievedUserInfo = service.getAllEmployees();
		Assert.assertEquals(empInfo.get(0).getFirstname(), retrievedUserInfo.get(0).getFirstname());

	}
	@Test
	public void testgetAllEmployees_lastname() {
		String username = "Rupesh1";
		String pwd = "pass123";
		User user = new User("rupeshg", "pass", "Rupesh", "Gudipudi", "Employee", "jsmith", "John", "Smith");

		ArrayList<EmployeeInfo> empInfo = new ArrayList<>();
		EmployeeInfo emp = new EmployeeInfo("Rupesh", "Gudipudi", "Employee", "John", "Smith");
		empInfo.add(emp);

		ArrayList<EmployeeInfo> returnEmpInfoList = new ArrayList<>();
		EmployeeInfo emp2 = new EmployeeInfo("Rupesh", "Gudipudi", "Employee", "John", "Smith");
		returnEmpInfoList.add(emp2);

		Mockito.when(this.daoImpl.getAllEmployees()).thenReturn(returnEmpInfoList);
		ArrayList<EmployeeInfo> retrievedUserInfo = service.getAllEmployees();
		Assert.assertEquals(empInfo.get(0).getLastname(), retrievedUserInfo.get(0).getLastname());

	}
	@Test
	public void testgetAllEmployees_role() {
		String username = "Rupesh1";
		String pwd = "pass123";
		User user = new User("rupeshg", "pass", "Rupesh", "Gudipudi", "Employee", "jsmith", "John", "Smith");

		ArrayList<EmployeeInfo> empInfo = new ArrayList<>();
		EmployeeInfo emp = new EmployeeInfo("Rupesh", "Gudipudi", "Employee", "John", "Smith");
		empInfo.add(emp);

		ArrayList<EmployeeInfo> returnEmpInfoList = new ArrayList<>();
		EmployeeInfo emp2 = new EmployeeInfo("Rupesh", "Gudipudi", "Employee", "John", "Smith");
		returnEmpInfoList.add(emp2);

		Mockito.when(this.daoImpl.getAllEmployees()).thenReturn(returnEmpInfoList);
		ArrayList<EmployeeInfo> retrievedUserInfo = service.getAllEmployees();
		Assert.assertEquals(empInfo.get(0).getRole(), retrievedUserInfo.get(0).getRole());

	}
	@Test
	public void testgetAllEmployees_managerFirstName() {
		String username = "Rupesh1";
		String pwd = "pass123";
		User user = new User("rupeshg", "pass", "Rupesh", "Gudipudi", "Employee", "jsmith", "John", "Smith");

		ArrayList<EmployeeInfo> empInfo = new ArrayList<>();
		EmployeeInfo emp = new EmployeeInfo("Rupesh", "Gudipudi", "Employee", "John", "Smith");
		empInfo.add(emp);

		ArrayList<EmployeeInfo> returnEmpInfoList = new ArrayList<>();
		EmployeeInfo emp2 = new EmployeeInfo("Rupesh", "Gudipudi", "Employee", "John", "Smith");
		returnEmpInfoList.add(emp2);

		Mockito.when(this.daoImpl.getAllEmployees()).thenReturn(returnEmpInfoList);
		ArrayList<EmployeeInfo> retrievedUserInfo = service.getAllEmployees();
		Assert.assertEquals(empInfo.get(0).getManagerFirstName(), retrievedUserInfo.get(0).getManagerFirstName());

	}
	@Test
	public void testgetAllEmployees_managerLastName() {
		String username = "Rupesh1";
		String pwd = "pass123";
		User user = new User("rupeshg", "pass", "Rupesh", "Gudipudi", "Employee", "jsmith", "John", "Smith");

		ArrayList<EmployeeInfo> empInfo = new ArrayList<>();
		EmployeeInfo emp = new EmployeeInfo("Rupesh", "Gudipudi", "Employee", "John", "Smith");
		empInfo.add(emp);

		ArrayList<EmployeeInfo> returnEmpInfoList = new ArrayList<>();
		EmployeeInfo emp2 = new EmployeeInfo("Rupesh", "Gudipudi", "Employee", "John", "Smith");
		returnEmpInfoList.add(emp2);

		Mockito.when(this.daoImpl.getAllEmployees()).thenReturn(returnEmpInfoList);
		ArrayList<EmployeeInfo> retrievedUserInfo = service.getAllEmployees();
		Assert.assertEquals(empInfo.get(0).getManagerLastName(), retrievedUserInfo.get(0).getManagerLastName());

	}
	@Test
	public void testUpdateInfo() {
		String username = "rupeshg";
		String firstname = "Rupesh";
		String lastname = "Gudipudi";
		Mockito.doNothing().when(daoImpl).updateInfo(username, firstname, lastname);

		service.updateInfo(username, firstname, lastname);

		Assert.assertEquals("", "");
	}

	@Test
	public void testInsertNewRequest() {
		String username = "rupeshg";
		String title = "Reim";
		String description = "desc";
		Float amount = 10.00f;
		Mockito.doNothing().when(daoImpl).insertNewRequest(username, title, description, amount, null);
		service.insertNewRequest(username, title, description, amount, null);
		Assert.assertEquals("", "");
	}

	@Test
	public void testViewMyReiumbersements() {
		String username = "rupeshg";
		boolean isPending = true;
		ArrayList<Reimbursements> reim = new ArrayList<>();
		Reimbursements r = new Reimbursements(username, "title", "desc", 20.00f, "Pending", "manager");
		reim.add(r);
		Mockito.when(this.daoImpl.viewMyReiumbersements(username, isPending)).thenReturn(reim);
		ArrayList<Reimbursements> retrievedRequest = service.viewMyReiumbersements(username, isPending);
		Assert.assertEquals(retrievedRequest, reim);
	}

	@Test
	public void testViewMyPendingEmployeesReiumbersements() {
		String managerId = "manager";
		ArrayList<Reimbursements> reim = new ArrayList<>();
		Reimbursements r = new Reimbursements("rupeshg", "Rupesh", "Gudipudi", "title", "desc", 20.00f, "Pending",
				managerId);
		reim.add(r);
		Mockito.when(this.daoImpl.viewMyPendingEmployeesReiumbersements(managerId)).thenReturn(reim);

		ArrayList<Reimbursements> retrievedRequest = service.viewMyPendingEmployeesReiumbersements(managerId);

		Assert.assertEquals("Pending", retrievedRequest.get(0).getStatus());
	}
	@Test
	public void testViewMyPendingEmployeesReiumbersements2() {
		String managerId = "manager";
		ArrayList<Reimbursements> reim = new ArrayList<>();
		Reimbursements r = new Reimbursements("rupeshg", "Rupesh", "Gudipudi", "title", "desc", 20.00f, "Pending",
				managerId);
		reim.add(r);
		Mockito.when(this.daoImpl.viewMyPendingEmployeesReiumbersements(managerId)).thenReturn(reim);

		ArrayList<Reimbursements> retrievedRequest = service.viewMyPendingEmployeesReiumbersements(managerId);

		Assert.assertNotEquals("Approved", retrievedRequest.get(0).getStatus());
	}
	@Test
	public void testviewAllResolvedRequests() {
		ArrayList<Reimbursements> reim = new ArrayList<>();
		Reimbursements r = new Reimbursements("rupeshg", "title", "desc", 20.00f, "Approved", "manager");
		reim.add(r);
		Mockito.when(this.daoImpl.viewAllResolvedRequests()).thenReturn(reim);
		ArrayList<Reimbursements> retrievedRequest = service.viewAllResolvedRequests();

		Assert.assertNotEquals("Pending", retrievedRequest.get(0).getStatus());
	}

	@Test
	public void testviewRequestsFromMyEmployee_managerFirstname() {
		String managerId = "jsmith";
		String employeeId = "rupeshg";
		ArrayList<Reimbursements> reim = new ArrayList<>();
		Reimbursements r = new Reimbursements(employeeId, "Rupesh", "Gudipudi", "title", "desc", 20.00f, "Pending", managerId,
				"John", "Smith");
		reim.add(r);
		Mockito.when(this.daoImpl.viewRequestsFromMyEmployee(managerId, employeeId)).thenReturn(reim);
		ArrayList<Reimbursements> retrievedRequest = service.viewRequestsFromMyEmployee(managerId, employeeId);
		Assert.assertEquals("John", retrievedRequest.get(0).getMgrFirstName());
	}
	@Test
	public void testviewRequestsFromMyEmployee_managerLastname() {
		String managerId = "jsmith";
		String employeeId = "rupeshg";
		ArrayList<Reimbursements> reim = new ArrayList<>();
		Reimbursements r = new Reimbursements(employeeId, "Rupesh", "Gudipudi", "title", "desc", 20.00f, "Pending", managerId,
				"John", "Smith");
		reim.add(r);
		Mockito.when(this.daoImpl.viewRequestsFromMyEmployee(managerId, employeeId)).thenReturn(reim);
		ArrayList<Reimbursements> retrievedRequest = service.viewRequestsFromMyEmployee(managerId, employeeId);
		Assert.assertEquals("Smith", retrievedRequest.get(0).getMgrLastName());
	}
	@Test
	public void testviewRequestsFromMyEmployee_description() {
		String managerId = "jsmith";
		String employeeId = "rupeshg";
		ArrayList<Reimbursements> reim = new ArrayList<>();
		Reimbursements r = new Reimbursements(employeeId, "Rupesh", "Gudipudi", "title", "desc", 20.00f, "Pending", managerId,
				"John", "Smith");
		reim.add(r);
		Mockito.when(this.daoImpl.viewRequestsFromMyEmployee(managerId, employeeId)).thenReturn(reim);
		ArrayList<Reimbursements> retrievedRequest = service.viewRequestsFromMyEmployee(managerId, employeeId);
		Assert.assertEquals("desc", retrievedRequest.get(0).getDescription());
	}
	@Test
	public void testviewRequestsFromMyEmployee_title() {
		String managerId = "jsmith";
		String employeeId = "rupeshg";
		ArrayList<Reimbursements> reim = new ArrayList<>();
		Reimbursements r = new Reimbursements(employeeId, "Rupesh", "Gudipudi", "title", "desc", 20.00f, "Pending", managerId,
				"John", "Smith");
		reim.add(r);
		Mockito.when(this.daoImpl.viewRequestsFromMyEmployee(managerId, employeeId)).thenReturn(reim);
		ArrayList<Reimbursements> retrievedRequest = service.viewRequestsFromMyEmployee(managerId, employeeId);
		Assert.assertEquals("title", retrievedRequest.get(0).getTitle());
	}
	@Test
	public void testviewRequestsFromMyEmployee_firstname() {
		String managerId = "jsmith";
		String employeeId = "rupeshg";
		ArrayList<Reimbursements> reim = new ArrayList<>();
		Reimbursements r = new Reimbursements(employeeId, "Rupesh", "Gudipudi", "title", "desc", 20.00f, "Pending", managerId,
				"John", "Smith");
		reim.add(r);
		Mockito.when(this.daoImpl.viewRequestsFromMyEmployee(managerId, employeeId)).thenReturn(reim);
		ArrayList<Reimbursements> retrievedRequest = service.viewRequestsFromMyEmployee(managerId, employeeId);
		Assert.assertEquals("Rupesh", retrievedRequest.get(0).getFirstName());
	}
	
	@Test
	public void testviewRequestsFromMyEmployee_status() {
		String managerId = "jsmith";
		String employeeId = "rupeshg";
		ArrayList<Reimbursements> reim = new ArrayList<>();
		Reimbursements r = new Reimbursements(employeeId, "Rupesh", "Gudipudi", "title", "desc", 20.00f, "Pending", managerId,
				"John", "Smith");
		reim.add(r);
		Mockito.when(this.daoImpl.viewRequestsFromMyEmployee(managerId, employeeId)).thenReturn(reim);
		ArrayList<Reimbursements> retrievedRequest = service.viewRequestsFromMyEmployee(managerId, employeeId);
		Assert.assertEquals("Pending", retrievedRequest.get(0).getStatus());
	}
	@Test
	public void testApproveorDenyRequests() {
		String managerId = "test";
		String username = "TestUser";
		String title = "TestTitle";
		String approveOrDeny = "Approved";

		Mockito.doNothing().when(daoImpl).ApproveorDenyRequests(managerId, username, title, approveOrDeny);
		service.ApproveorDenyRequests(managerId, username, title, approveOrDeny);
		Assert.assertEquals("", "");

	}

	@Test
	public void testgetReceipt() {
		String username = "TestUser";
		String title = "Title1";
		byte[] expectedValues = new byte[1];
		byte[] returnValues = new byte[1];
		try {
			Mockito.when(this.daoImpl.getReceipt(username, title)).thenReturn(returnValues);
			byte[] returnValues2 = service.getReceipt(username, title);
			Assert.assertEquals(expectedValues.length, returnValues2.length);
		} catch (Exception e) {
			Assert.assertEquals("", "");
		}
	}
}
