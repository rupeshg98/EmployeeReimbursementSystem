package com.revature.repository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.EmployeeInfo;
import com.revature.model.Reimbursements;
import com.revature.model.User;
import com.revature.utility.ConnectionFactoryUtil;

public class DaoImpl implements Dao {
	private Connection getConnection() {
		Connection conn = null;
		try {
			conn = ConnectionFactoryUtil.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			conn = null;
		}
		return conn;
	}

	@Override
	public User getUser(String username) {
		PreparedStatement stmt = null;
		final String SQL = "select employee.pwd,employee.first_name, employee.last_name, employee.user_role, "
				+ " employee.managerid, manager.first_name as ManagerFirstName, manager.last_name as ManagerLastName "
				+ " from users employee, users manager " + "where employee.username = ?"
				+ "	and employee.managerid = manager.username";
		Connection conn = getConnection();
		User user = null;
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, username);
				ResultSet results = stmt.executeQuery();
				while (results != null && results.next()) {
					String password = results.getString(1);
					String firstname = results.getString(2);
					String lastname = results.getString(3);
					String role = results.getString(4);
					String managerId = results.getString(5);
					String managerFirstName = results.getString(6);
					String managerLastName = results.getString(7);
					user = new User(username, password, firstname, lastname, role, managerId, managerFirstName,
							managerLastName);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}



	@Override
	public ArrayList<EmployeeInfo> getAllEmployees() {
		PreparedStatement stmt = null;
		final String SQL = "select employee.first_name, employee.last_name, employee.user_role, "
				+ "manager.first_name as ManagerFirstName, manager.last_name as ManagerLastName "
				+ " from users employee, users manager " + " where employee.managerid = manager.username";
		Connection conn = getConnection();
		ArrayList<EmployeeInfo> employeesList = new ArrayList<EmployeeInfo>();
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				ResultSet results = stmt.executeQuery();
				while (results != null && results.next()) {
					String firstname = results.getString(1);
					String lastname = results.getString(2);
					String role = results.getString(3);
					String managerFirstName = results.getString(4);
					String managerLastName = results.getString(5);
					EmployeeInfo employeeInfo = new EmployeeInfo(firstname, lastname, role, managerFirstName,
							managerLastName);
					employeesList.add(employeeInfo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employeesList;
	}

	public void updateInfo(String username, String firstName, String lastName) {
		PreparedStatement stmt = null;
		final String SQL = "update Users set first_name = ?, last_name = ? where username = ?";
		Connection conn = getConnection();
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, firstName);
				stmt.setString(2, lastName);
				stmt.setString(3, username);
				stmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Reimbursements> viewMyReiumbersements(String username, boolean isPending) {
		PreparedStatement stmt = null;
		String SQL = "";
		if (isPending) {
			SQL = "select title, description, amount, status, approvedby "
					+ " from reimbursements where username = ? and status = ?";
		} else {
			SQL = "select title, description, amount, status, approvedby "
					+ " from reimbursements where username = ? and status != ?";
		}
		Connection conn = getConnection();
		ArrayList<Reimbursements> list = new ArrayList<Reimbursements>();
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, username);
				stmt.setString(2, "Pending");
				ResultSet results = stmt.executeQuery();
				while (results != null && results.next()) {
					String title = results.getString(1);
					String description = results.getString(2);
					Float amount = results.getFloat(3);
					String status = results.getString(4);
					String approvedby = results.getString(5);
					Reimbursements reimb = new Reimbursements(username, title, description, amount, status, approvedby);
					list.add(reimb);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void insertNewRequest(String username, String title, String description, float amount,
			byte[] receiptContent) {

		PreparedStatement stmt = null;
		final String SQL = "insert into reimbursements values(?,?,?,?,?,?,?)";
		Connection conn = getConnection();
		try {
			if (conn != null) {
				ByteArrayInputStream input = new ByteArrayInputStream(receiptContent);
				PreparedStatement ps = conn.prepareStatement("INSERT INTO images VALUES (?, ?)");
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, username);
				stmt.setString(2, title);
				stmt.setString(3, description);
				stmt.setFloat(4, amount);
				stmt.setString(5, "Pending");
				stmt.setString(6, "");
				stmt.setBinaryStream(7, input, input.available());
				stmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public ArrayList<Reimbursements> viewMyPendingEmployeesReiumbersements(String managerId) {
		PreparedStatement stmt = null;
		final String SQL = "select u.username,u.first_name, u.last_name,r.title, r.description, r.amount, r.status, r.approvedby "
				+ "from reimbursements r, users u " + "where u.managerid = ? " + "and u.username = r.username "
				+ "and r.status = 'Pending' ";
		Connection conn = getConnection();
		ArrayList<Reimbursements> list = new ArrayList<Reimbursements>();
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, managerId);
				ResultSet results = stmt.executeQuery();
				while (results != null && results.next()) {
					String username = results.getString(1);
					String firstName = results.getString(2);
					String lastName = results.getString(3);
					String title = results.getString(4);
					String description = results.getString(5);
					Float amount = results.getFloat(6);
					String status = results.getString(7);
					String approvedby = results.getString(8);
					Reimbursements reimb = new Reimbursements(username, firstName, lastName, title, description, amount,
							status, approvedby);
					list.add(reimb);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<Reimbursements> viewAllResolvedRequests() {
		PreparedStatement stmt = null;
		final String SQL = "select user_details.username,user_details.first_name, user_details.last_name,"
				+ "	user_details.title, user_details.description, user_details.amount, user_details.status, "
				+ "	user_details.approvedby, mgr_details.first_name as mgr_first_name, mgr_details.last_name as mgr_last_name "
				+ "from " + "("
				+ "	select u.username,u.first_name, u.last_name,r.title, r.description, r.amount, r.status, r.approvedby "
				+ "	from reimbursements r, users u " + "	where u.username = r.username "
				+ "	and r.status != 'Pending' " + ") user_details " + "left join users mgr_details "
				+ "on user_details.approvedby = mgr_details.username";
		Connection conn = getConnection();
		ArrayList<Reimbursements> list = new ArrayList<Reimbursements>();
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				ResultSet results = stmt.executeQuery();
				while (results != null && results.next()) {
					String username = results.getString(1);
					String firstName = results.getString(2);
					String lastName = results.getString(3);
					String title = results.getString(4);
					String description = results.getString(5);
					Float amount = results.getFloat(6);
					String status = results.getString(7);
					String approvedby = results.getString(8);
					String mgrFirstName = results.getString(9);
					String mgrLastName = results.getString(10);
					Reimbursements reimb = new Reimbursements(username, firstName, lastName, title, description, amount,
							status, approvedby, mgrFirstName, mgrLastName);
					list.add(reimb);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<Reimbursements> viewRequestsFromMyEmployee(String managerId, String employeeId) {
		PreparedStatement stmt = null;
		final String SQL = "select u.username,u.first_name, u.last_name,r.title, r.description, r.amount, r.status, r.approvedby "
				+ "from reimbursements r, users u " + "where u.managerid = ? " + "and u.username = ? "
				+ "and u.username = r.username ";
		Connection conn = getConnection();
		ArrayList<Reimbursements> list = new ArrayList<Reimbursements>();
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, managerId);
				stmt.setString(2, employeeId);
				ResultSet results = stmt.executeQuery();
				while (results != null && results.next()) {
					String username = results.getString(1);
					String firstName = results.getString(2);
					String lastName = results.getString(3);
					String title = results.getString(4);
					String description = results.getString(5);
					Float amount = results.getFloat(6);
					String status = results.getString(7);
					String approvedby = results.getString(8);
					Reimbursements reimb = new Reimbursements(username, firstName, lastName, title, description, amount,
							status, approvedby);
					list.add(reimb);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void ApproveorDenyRequests(String managerId, String username, String title, String approveOrDeny) {
		System.out.println("ApproveorDenyRequests : managerId: " + managerId + ", username:" + username + ", title:"
				+ title + ",approveOrDeny:" + approveOrDeny);
		PreparedStatement stmt = null;
		final String SQL = "update reimbursements " + "set status = ?, approvedby = ?  " + "where username = ? "
				+ "and title = ? " + "and status = 'Pending' " + "and username in (" + "	select username "
				+ "	from users " + "	where managerid = ? " + ")";
		Connection conn = getConnection();
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, approveOrDeny);
				stmt.setString(2, managerId);
				stmt.setString(3, username);
				stmt.setString(4, title);
				stmt.setString(5, managerId);

				stmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public byte[] getReceipt(String username, String title) throws Exception {
		System.out.println("DAO getReceipt: usename: " + username + ", title: " + title);
		PreparedStatement stmt = null;
		final String SQL = "select receipt from reimbursements where username = ? and title = ?";
		Connection conn = getConnection();
		byte[] returnImageContent = null;
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, username);
				stmt.setString(2, title);
				ResultSet results = stmt.executeQuery();
				while (results != null && results.next()) {
					InputStream imageContent = results.getBinaryStream(1);
					if (imageContent != null)
						returnImageContent = imageContent.readAllBytes();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnImageContent;
	}
	
	
}
