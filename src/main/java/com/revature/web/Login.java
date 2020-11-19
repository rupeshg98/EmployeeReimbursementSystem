package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.User;
import com.revature.service.Service;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {

	Service service = new Service();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		//PrintWriter out = response.getWriter();  
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = service.validateUser(username, password);
		if(user != null){
			//System.out.println("User is valid");
			//response.getWriter().write("User is valid");
			if (user.getRole().equals("employee")){
				HttpSession mySession = request.getSession();
				System.out.println ("Inside Login user: " + username);
				mySession.setAttribute("username", username);
				response.sendRedirect("./views/employee.html");
			} else {
				HttpSession mySession = request.getSession();
				System.out.println ("Inside Login user: " + username);
				mySession.setAttribute("username", username);
				response.sendRedirect("./views/manager.html");
			}
			
		} else {
			//response.getWriter().write("User is NOT valid");
			response.sendRedirect("/EmployeeReinbursementSystem/");
		}
		
	
	}

}
