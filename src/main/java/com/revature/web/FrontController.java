package com.revature.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeReinbursementSystem/FrontController")
@MultipartConfig
/**
 * Servlet implementation class EmployeeDetails
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RequestHelper requestHelper;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		requestHelper = new RequestHelper();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			response.getWriter().append("Invalid Entry: ").append(request.getContextPath());
		}

		// Delegate to RequestHelper in case of POST requests
		else if (request.getMethod().equals("POST")) {
//			response.getWriter().write(
//					new ObjectMapper().writeValueAsString(requestHelper.processPost(request, response)));
			String buttonName = request.getParameter("buttonname");
			System.out.println ("FrontController: buttonName: " + buttonName);
			if (buttonName.equals("getReceiptForEmployee") || buttonName.equals("getReceiptForManager")) {

				// Copy the contents of the file to the output stream
				byte[] buf = (byte[]) requestHelper.processPost(request, response);
				if (buf != null) {
					OutputStream out = response.getOutputStream();
					int count = 0;

					out.write(buf, 0, buf.length);

					out.close();
				} else {
					response.getWriter().print("No Image");
				}
			} else {
				response.getWriter().print(requestHelper.processPost(request, response));
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
