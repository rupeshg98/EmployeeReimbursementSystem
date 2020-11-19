package com.revature.model;

public class EmployeeInfo {
	private String firstname;
	private String lastname;
	private String role;
	private String managerFirstName;
	private String managerLastName;

	public EmployeeInfo(String firstname, String lastname, String role, String managerFirstName,
			String managerLastName) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.role = role;
		this.managerFirstName = managerFirstName;
		this.managerLastName = managerLastName;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getManagerFirstName() {
		return managerFirstName;
	}

	public void setManagerFirstName(String managerFirstName) {
		this.managerFirstName = managerFirstName;
	}

	public String getManagerLastName() {
		return managerLastName;
	}

	public void setManagerLastName(String managerLastName) {
		this.managerLastName = managerLastName;
	}

	@Override
	public String toString() {
		return "EmployeeInfo [firstname=" + firstname + ", lastname=" + lastname + ", role=" + role
				+ ", managerFirstName=" + managerFirstName + ", managerLastName=" + managerLastName + "]";
	}

}
