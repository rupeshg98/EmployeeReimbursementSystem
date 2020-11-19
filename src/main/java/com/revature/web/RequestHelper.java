package com.revature.web;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.revature.model.EmployeeInfo;
import com.revature.model.Reimbursements;
import com.revature.service.Service;

public class RequestHelper {
	Service service = new Service();

	public Object processPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// How do we decide what to return?

		final String URL = request.getRequestURI();

		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String key = params.nextElement();
			String value = request.getParameter(key);
			System.out.println("Parameters Received: " + key + ", value: " + value);
		}

		System.out.println("URL received: " + URL);
		String buttonName = request.getParameter("buttonname");
		System.out.println("buttonName: " + buttonName);
		HttpSession mysession = request.getSession();
		String username = (String) mysession.getAttribute("username");
		System.out.println("RequestHelper: Requests Received from " + username);

		if (buttonName.equals("viewMyInfo")) {
			return viewMyInfo(username);
		} else if (buttonName.equals("updateInfo")) {
			return updateInfo(username, request);
		} else if (buttonName.equals("viewMyPendingReimbursements")) {
			return viewMyPendingReimbursements(username, request);
		} else if (buttonName.equals("viewMyApprovedReimbursements")) {
			return viewMyApprovedReimbursements(username, request);
		} else if (buttonName.equals("Submit")) {
			response.sendRedirect("/EmployeeReinbursementSystem/views/employee.html");
			return newRequest(username, request);
		} else if (buttonName.equals("Submit2")) {
			response.sendRedirect("/EmployeeReinbursementSystem/views/manager.html");
			return newRequest(username, request);
		} else if (buttonName.equals("viewAllEmployees")) {
			return viewAllEmployees(username, request);
		} else if (buttonName.equals("viewMyEmployeesPendingRequests")) {
			return viewMyEmployeesPendingRequests(username, request);
		} else if (buttonName.equals("viewAllResolvedRequests")) {
			return viewAllResolvedRequests(username, request);
		} else if (buttonName.equals("viewRequestsFromMyEmployee")) {
			return viewRequestsFromMyEmployee(username, request);
		} else if (buttonName.equals("ApproveorDenyRequests")) {
			return ApproveorDenyRequests(username, request);
		} else if (buttonName.equals("getReceiptForEmployee")) {
			return getReceipt(username, request);
		} else if (buttonName.equals("getReceiptForManager")) {
			String employeeUserName = request.getParameter("employeeusername");
			return getReceipt(employeeUserName, request);
		} else {
			return "No such endpoint or resource";
		}
	}

	private String viewMyInfo(String username) {
		System.out.println("RequestHelper username: " + username);
		ArrayList<EmployeeInfo> employeeInfoList = service.viewMyInfo(username);
		String returnValue = "<table id='MyInfoTable'>" + "<tr>" + "<th>First Name</th>" + "<th>Last Name</th>"
				+ "<th>Role</th>" + "<th>Manager First Name</th>" + "<th>Manager Last Name</th>" + "</tr>";
		for (int i = 0; i < employeeInfoList.size(); i++) {
			EmployeeInfo employeeInfo = employeeInfoList.get(i);
			returnValue = returnValue + "<tr>" + "<td>" + employeeInfo.getFirstname() + "</td>" + "<td>"
					+ employeeInfo.getLastname() + "</td>" + "<td>" + employeeInfo.getRole() + "</td>" + "<td>"
					+ employeeInfo.getManagerFirstName() + "</td>" + "<td>" + employeeInfo.getManagerLastName()
					+ "</td>" + "</tr>";
		}
		returnValue = returnValue + "</table>";
		System.out.println("returnValue: " + returnValue);
		return returnValue;
	}

	private String updateInfo(String username, HttpServletRequest request) {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String returnValue = "";
		try {
			service.updateInfo(username, firstname, lastname);
			returnValue = "<table><tr>Successfully updated Info. FirstName: " + firstname + ", LastName: " + lastname
					+ "</tr></table>";
		} catch (Exception e) {
			returnValue = "<table><tr>Exception Occurred: " + e.getMessage() + "</tr></table>";

		}
		return returnValue;

	}

	private String viewMyPendingReimbursements(String username, HttpServletRequest request) {
		String returnValue = "";
		try {
			ArrayList<Reimbursements> list = service.viewMyReiumbersements(username, true);
			returnValue = "<table><td>";
			returnValue = returnValue + "<table id='MyPendingReimbTable'>" + "<tr>" + "<th>Title</th>" + "<th>Description</th>"
					+ "<th>Amount</th>" + "<th></th>" + "</tr>";
			for (int i = 0; i < list.size(); i++) {
				Reimbursements reimb = list.get(i);
				returnValue = returnValue + "<tr>" + "<td>" + reimb.getTitle() + "</td>" + "<td>"
						+ reimb.getDescription() + "</td>" + "<td>" + reimb.getAmount() + "</td>" + "<td>"
						+ "<button id=\"receiptBtn\" name=\"buttonname\" value=\"getReceipt\"\r\n"
						+ "			onclick=\"getReceipt('getReceiptForEmployee','" + username + "','" + reimb.getTitle()
						+ "'); \">" + "Show Receipt</button>" + "</td>"
						 + "</tr>";
			}
			returnValue = returnValue + "</table>";
			returnValue = returnValue + "</td>";
			returnValue = returnValue + "<td>";
			returnValue = returnValue + "<canvas id='showImageForEmployee' width=\"400\" height=\"150\" ></canvas>";
			returnValue = returnValue + "</td>";
			returnValue = returnValue + "</table>";

			System.out.println("returnValue: " + returnValue);
			return returnValue;
		} catch (Exception e) {
			returnValue = "<table><tr>Exception Occurred: " + e.getMessage() + "</tr></table>";

		}
		return returnValue;

	}

	private String viewMyApprovedReimbursements(String username, HttpServletRequest request) {
		String returnValue = "";
		try {
			ArrayList<Reimbursements> list = service.viewMyReiumbersements(username, false);
			returnValue = "<table><td>";
			returnValue = returnValue+ "<table id='MyApprovedReimb'>" + "<tr>" + "<th>Title</th>" + "<th>Description</th>"
					+ "<th>Amount</th>" + "<th>Status</th>" + "<th>Resolved By</th>"  + "<th></th>" + "</tr>";
			for (int i = 0; i < list.size(); i++) {
				Reimbursements reimb = list.get(i);
				returnValue = returnValue + "<tr>" + "<td>" + reimb.getTitle() + "</td>" + "<td>"
						+ reimb.getDescription() + "</td>" + "<td>" + reimb.getAmount() + "</td>" + "<td>"
						+ reimb.getStatus() + "</td>" + "<td>" + reimb.getApprovedby() + "</td>" + "<td>"
						+ "<button id=\"receiptBtn\" name=\"buttonname\" value=\"getReceipt\"\r\n"
						+ "			onclick=\"getReceipt('getReceiptForEmployee','" + username + "','" + reimb.getTitle()
						+ "'); \">" + "Show Receipt</button>" + "</td>"
						 + "</tr>";;
			}
			returnValue = returnValue + "</table>";
			returnValue = returnValue + "</td>";
			returnValue = returnValue + "<td>";
			returnValue = returnValue + "<canvas id='showImageForEmployee' width=\"400\" height=\"150\"></canvas>";
			returnValue = returnValue + "</td>";
			returnValue = returnValue + "</table>";
			System.out.println("returnValue: " + returnValue);
			return returnValue;
		} catch (Exception e) {
			returnValue = "<table><tr>Exception Occurred: " + e.getMessage() + "</tr></table>";

		}
		return returnValue;

	}

	private String newRequest(String username, HttpServletRequest request) {
		String title = request.getParameter("reimbtitle");
		String description = request.getParameter("description");
		float amount = Float.parseFloat(request.getParameter("amount"));
		Part filePart;
		byte[] receiptContent = null;
		try {
			
			filePart = request.getPart("receipt");
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
			System.out.println("Receipt fileName: " + fileName);
			InputStream fileContent = filePart.getInputStream();
			receiptContent = fileContent.readAllBytes();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // Retrieves <input type="file" name="file">

		String returnValue = "";
		try {
			service.insertNewRequest(username, title, description, amount, receiptContent);
			
			
		} catch (Exception e) {
			returnValue = "<table><tr>Exception Occurred: " + e.getMessage() + "</tr></table>";

		}
		return returnValue;

	}

	private String viewAllEmployees(String username, HttpServletRequest request) {
		System.out.println("RequestHelper username: " + username);
		ArrayList<EmployeeInfo> employeeInfoList = service.getAllEmployees();
		String returnValue = "<table id='MyInfoTable'>" + "<tr>" + "<th>First Name</th>" + "<th>Last Name</th>"
				+ "<th>Role</th>" + "<th>Manager First Name</th>" + "<th>Manager Last Name</th>" + "</tr>";
		for (int i = 0; i < employeeInfoList.size(); i++) {
			EmployeeInfo employeeInfo = employeeInfoList.get(i);
			returnValue = returnValue + "<tr>" + "<td>" + employeeInfo.getFirstname() + "</td>" + "<td>"
					+ employeeInfo.getLastname() + "</td>" + "<td>" + employeeInfo.getRole() + "</td>" + "<td>"
					+ employeeInfo.getManagerFirstName() + "</td>" + "<td>" + employeeInfo.getManagerLastName()
					+ "</td>" + "</tr>";
		}
		returnValue = returnValue + "</table>";
		System.out.println("returnValue: " + returnValue);
		return returnValue;

	}

	private String viewMyEmployeesPendingRequests(String username, HttpServletRequest request) {
		String returnValue = "";
		try {
			ArrayList<Reimbursements> list = service.viewMyPendingEmployeesReiumbersements(username);
			returnValue = "<table><td>";
			returnValue = returnValue + "<table id='MyPendingReimb'>" + "<tr>" + "<th>Username</th>" + "<th>First Name</th>"
					+ "<th>Last Name</th>" + "<th>Request Title</th>" + "<th>Description</th>" + "<th>Amount</th>"+ "<th></th>"
					+ "</tr>";
			for (int i = 0; i < list.size(); i++) {
				Reimbursements reimb = list.get(i);
				returnValue = returnValue + "<tr>" + "<td>" + reimb.getUsername() + "</td>" + "<td>"
						+ reimb.getFirstName() + "</td>" + "<td>" + reimb.getLastName() + "</td>" + "<td>"
						+ reimb.getTitle() + "</td>" + "<td>" + reimb.getDescription() + "</td>" + "<td>"
						+ reimb.getAmount() + "</td>" + "<td>"
								+ "<button id=\"receiptBtn\" name=\"buttonname\" value=\"getReceipt\"\r\n"
								+ "			onclick=\"getReceipt('getReceiptForManager','" + reimb.getUsername() + "','" + reimb.getTitle()
								+ "'); \">" + "Show Receipt</button>" + "</td>" +"</tr>";
			}
			returnValue = returnValue + "</table>";
			returnValue = returnValue + "</td>";
			returnValue = returnValue + "<td>";
			returnValue = returnValue + "<canvas id='showImage' width=\"400\" height=\"150\"></canvas>";
			returnValue = returnValue + "</td>";
			returnValue = returnValue + "</table>";
			System.out.println("returnValue: " + returnValue);
			return returnValue;
		} catch (Exception e) {
			returnValue = "<table><tr>Exception Occurred: " + e.getMessage() + "</tr></table>";

		}
		return returnValue;

	}

	private String viewAllResolvedRequests(String username, HttpServletRequest request) {
		String returnValue = "";
		try {
			
			ArrayList<Reimbursements> list = service.viewAllResolvedRequests();
			returnValue = "<table><td>";
			returnValue = returnValue + "<table id='MyPendingReimb'>" + "<tr>" + "<th>Username</th>" + "<th>First Name</th>"
					+ "<th>Last Name</th>" + "<th>Request Title</th>" + "<th>Description</th>" + "<th>Amount</th>"
					+ "<th>Status</th>" + "<th>Resolved By FirstName</th>" + "<th>Resolved By LastName</th>" +  "<th></th>"+"</tr>";
			for (int i = 0; i < list.size(); i++) {
				Reimbursements reimb = list.get(i);
				returnValue = returnValue + "<tr>" + "<td>" + reimb.getUsername() + "</td>" + "<td>"
						+ reimb.getFirstName() + "</td>" + "<td>" + reimb.getLastName() + "</td>" + "<td>"
						+ reimb.getTitle() + "</td>" + "<td>" + reimb.getDescription() + "</td>" + "<td>"
						+ reimb.getAmount() + "</td>" + "<td>" + reimb.getStatus() + "</td>" + "<td>"
						+ reimb.getMgrFirstName() + "</td>" + "<td>" + reimb.getMgrLastName() + "</td>" + "<td>"
						+ "<button id=\"receiptBtn\" name=\"buttonname\" value=\"getReceipt\"\r\n"
						+ "			onclick=\"getReceipt('getReceiptForManager','" + reimb.getUsername() + "','" + reimb.getTitle()
						+ "'); \">" + "Show Receipt</button>" + "</td>"+ "</tr>";
				
			}
			returnValue = returnValue + "</table>";
			returnValue = returnValue + "</td>";
			returnValue = returnValue + "<td>";
			returnValue = returnValue + "<canvas id='showImage' width=\"400\" height=\"150\"></canvas>";
			returnValue = returnValue + "</td>";
			returnValue = returnValue + "</table>";
			System.out.println("returnValue: " + returnValue);
			return returnValue;
		} catch (Exception e) {
			returnValue = "<table><tr>Exception Occurred: " + e.getMessage() + "</tr></table>";

		}
		return returnValue;

	}

	private String viewRequestsFromMyEmployee(String username, HttpServletRequest request) {
		String returnValue = "";
		try {
			String employee_username = request.getParameter("employee_username");
			ArrayList<Reimbursements> list = service.viewRequestsFromMyEmployee(username, employee_username);
			returnValue = "<table><td>";
			returnValue = returnValue + "<table id='MyPendingReimb'>" + "<tr>" + "<th>Username</th>" + "<th>First Name</th>"
					+ "<th>Last Name</th>" + "<th>Request Title</th>" + "<th>Description</th>" + "<th>Amount</th>"
					+ "<th>Status</th>"+ "<th></th>"+ "</tr>";
			for (int i = 0; i < list.size(); i++) {
				Reimbursements reimb = list.get(i);
				returnValue = returnValue + "<tr>" + "<td>" + reimb.getUsername() + "</td>" + "<td>"
						+ reimb.getFirstName() + "</td>" + "<td>" + reimb.getLastName() + "</td>" + "<td>"
						+ reimb.getTitle() + "</td>" + "<td>" + reimb.getDescription() + "</td>" + "<td>"
						+ reimb.getAmount() + "</td>" + "<td>" + reimb.getStatus() + "</td>" + "<td>"
						+ "<button id=\"receiptBtn\" name=\"buttonname\" value=\"getReceipt\"\r\n"
						+ "			onclick=\"getReceipt('getReceiptForManager','" + reimb.getUsername() + "','" + reimb.getTitle()
						+ "'); \">" + "Show Receipt</button>" + "</td>"+ "</tr>";
			}
			returnValue = returnValue + "</table>";
			returnValue = returnValue + "</td>";
			returnValue = returnValue + "<td>";
			returnValue = returnValue + "<canvas id='showImage' width=\"400\" height=\"150\"></canvas>";
			returnValue = returnValue + "</td>";
			returnValue = returnValue + "</table>";
			System.out.println("returnValue: " + returnValue);
			return returnValue;
		} catch (Exception e) {
			returnValue = "<table><tr>Exception Occurred: " + e.getMessage() + "</tr></table>";

		}
		return returnValue;

	}

	private String ApproveorDenyRequests(String username, HttpServletRequest request) {
		String returnValue = "";
		try {
			String employee_username = request.getParameter("username");
			String title = request.getParameter("title");
			String approve_deny = request.getParameter("approve_deny");
			service.ApproveorDenyRequests(username, employee_username, title, approve_deny);

		} catch (Exception e) {
			returnValue = "<table><tr>Exception Occurred: " + e.getMessage() + "</tr></table>";

		}
		return returnValue;

	}

	private byte[] getReceipt(String username, HttpServletRequest request) {
		
		String title = request.getParameter("title");
		
		byte[] returnValue = null;
		try {
			returnValue = service.getReceipt(username, title);
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = null;
		}

		System.out.println("getReceipt returning: " + returnValue);
		return returnValue;
	}
}