package com.revature.model;

public class Reimbursements {
	private String username;
	private String firstName;
	private String lastName;
	private String title;
	private String description;
	private Float amount;
	private String status;
	private String approvedby;
	private String mgrFirstName;
	private String mgrLastName;

	public Reimbursements(String username, String title, String description, Float amount, String status,
			String approvedby) {
		super();
		this.username = username;
		this.title = title;
		this.description = description;
		this.amount = amount;
		this.status = status;
		this.approvedby = approvedby;
	}

	public Reimbursements(String username, String firstName, String lastName, String title, String description,
			Float amount, String status, String approvedby) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.description = description;
		this.amount = amount;
		this.status = status;
		this.approvedby = approvedby;
	}

	public Reimbursements(String username, String firstName, String lastName, String title, String description,
			Float amount, String status, String approvedby, String mgrFirstName, String mgrLastName) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.description = description;
		this.amount = amount;
		this.status = status;
		this.approvedby = approvedby;
		this.mgrFirstName = mgrFirstName;
		this.mgrLastName = mgrLastName;
	}

	public Reimbursements() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApprovedby() {
		return approvedby;
	}

	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMgrFirstName() {
		return mgrFirstName;
	}

	public void setMgrFirstName(String mgrFirstName) {
		this.mgrFirstName = mgrFirstName;
	}

	public String getMgrLastName() {
		return mgrLastName;
	}

	public void setMgrLastName(String mgrLastName) {
		this.mgrLastName = mgrLastName;
	}

	@Override
	public String toString() {
		return "Reimbursements [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", title=" + title + ", description=" + description + ", amount=" + amount + ", status=" + status
				+ ", approvedby=" + approvedby + ", mgrFirstName=" + mgrFirstName + ", mgrLastName=" + mgrLastName
				+ "]";
	}

}
