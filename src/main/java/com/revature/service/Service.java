package com.revature.service;

import java.util.ArrayList;

import com.revature.model.EmployeeInfo;
import com.revature.model.Reimbursements;
import com.revature.model.User;
import com.revature.repository.DaoImpl;

public class Service {

	private DaoImpl userDaoService;

	public Service() {
		userDaoService = new DaoImpl();
	}

	public User getUser(String username) {
		return userDaoService.getUser(username);
	}

	public User validateUser(String username, String pwd) {
		User user = null;
		try {
			User validateUser = getUser(username);
			if (validateUser != null) {
				if (pwd.equals(validateUser.getPassword())) {
					user = validateUser;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public ArrayList<EmployeeInfo> viewMyInfo(String username) {
		System.out.println("Inside getEmployeeInfo username: " + username);
		User employee = getUser(username);
		ArrayList<EmployeeInfo> returnValues = new ArrayList<EmployeeInfo>();
		if (employee != null) {
			EmployeeInfo employeeInfo = new EmployeeInfo(employee.getFirstname(), employee.getLastname(),
					employee.getRole(), employee.getManagerFirstName(), employee.getManagerLastName());
			returnValues.add(employeeInfo);
		}
		return returnValues;
	}

	public ArrayList<EmployeeInfo> getAllEmployees() {
		ArrayList<EmployeeInfo> returnValues = new ArrayList<EmployeeInfo>();

		ArrayList<EmployeeInfo> values = userDaoService.getAllEmployees();
		if (values != null) {
			returnValues.addAll(values);
		}

		return returnValues;
	}

	public void updateInfo(String username, String firstName, String lastName) {
		userDaoService.updateInfo(username, firstName, lastName);
	}

	public ArrayList<Reimbursements> viewMyReiumbersements(String username, boolean isPending) {
		return userDaoService.viewMyReiumbersements(username, isPending);
	}

	public void insertNewRequest(String username, String title, String description, float amount, byte[] receiptContent) {
		userDaoService.insertNewRequest(username, title, description, amount, receiptContent);
	}

	public ArrayList<Reimbursements> viewMyPendingEmployeesReiumbersements(String managerId) {
		return userDaoService.viewMyPendingEmployeesReiumbersements(managerId);
	}

	public ArrayList<Reimbursements> viewAllResolvedRequests() {
		return userDaoService.viewAllResolvedRequests();
	}

	public ArrayList<Reimbursements> viewRequestsFromMyEmployee(String managerId, String employeeId) {
		return userDaoService.viewRequestsFromMyEmployee(managerId, employeeId);
	}

	public void ApproveorDenyRequests(String managerId, String username, String title, String approveOrDeny) {
		userDaoService.ApproveorDenyRequests(managerId, username, title, approveOrDeny);
	}
	public byte[] getReceipt(String username, String title) throws Exception{
		byte[] image = userDaoService.getReceipt(username, title);
		return image;
	}

	public static void main(String args[]) {
		Service myService = new Service();
		User user = myService.validateUser("rupeshg", "password");
		if (user != null) {
			System.out.println("Valid User");
		} else {
			System.out.println("Invalid User");
		}

	}


}
