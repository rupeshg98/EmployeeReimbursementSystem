package com.revature.repository;

import java.util.ArrayList;

import com.revature.model.EmployeeInfo;
import com.revature.model.User;

public interface Dao {

	User getUser(String username);

	ArrayList<EmployeeInfo> getAllEmployees();
	
}
